package com.point.common.maintenance;

import com.alibaba.fastjson.JSON;
import com.point.common.exception.UpgradeException;
import com.point.common.util.FileUtil;
import com.point.common.util.ThreadUtil;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Map;

/**
 * 版本升级处理
 */
@Component
public class UpgradeProcess {

    /**
     * 操作系统环境变量
     */
    private Map<String, String> envMap = System.getenv();


    public void backup() {

        String appHome = getAppHome();
        File appHomeDir = new File(appHome);
        File backupDir = getBackupDir();

        String version;
        File versionFile = new File(appHomeDir, "version.dat");
        try {
            version = FileUtils.readFileToString(versionFile);
        } catch (IOException e) {
            throw new UpgradeException("version.dat is not found.");
        }

        File backupVersionZip = new File(backupDir, appHomeDir.getName().concat("_").concat(version).concat(".zip"));
        FileUtil.compressFolder(appHome, backupVersionZip.getAbsolutePath(),
                (dir, name) -> !name.endsWith(".log") && !dir.getName().equals("tmp"));
    }

    public String receive(MultipartFile file) {

        String fileName = file.getOriginalFilename();
        File tmpDir = getTmpDir();
        if (tmpDir.exists()) {
            try {
                FileUtils.cleanDirectory(tmpDir);
            } catch (IOException e) {
                throw new UpgradeException("New App file receive failed.", e);
            }
        } else {
            tmpDir.mkdirs();
        }

        String newFile = new File(tmpDir, fileName).getAbsolutePath();
        try {
            InputStream is = file.getInputStream();
            OutputStream os = new FileOutputStream(newFile);
            IOUtils.copy(is, os);
            IOUtils.closeQuietly(os);
            IOUtils.closeQuietly(is);
        } catch (IOException e) {
            throw new UpgradeException("New App file receive failed.", e);
        }
        return newFile;
    }

    public void deploy(String versionZip) {

        String appHome = getAppHome();
        FileUtil.extractFolder(versionZip, new File(appHome).getParent());
        FileUtil.deleteFile(versionZip);
    }

    public String listBackup() {
        File backupDir = getBackupDir();
        String[] files = backupDir.list();
        return JSON.toJSONString(files);
    }

    public void rollback(String versionZip) {

        String appHome = getAppHome();
        File backupDir = getBackupDir();
        File backupVersionZip = new File(backupDir, versionZip);

        FileUtil.extractFolder(backupVersionZip.getAbsolutePath(), new File(appHome).getParent());
    }

    public void restartService() {

        String javaHome = getJavaHome();
        String java = FileUtil.patchDirPath(javaHome).concat("bin").concat(File.separator).concat("java");
        String jar = getToolsJar();

        String[] script = {java, "-jar", jar, "RestartService", "ViewDepot_".concat(getAppName())};
        try {
            Runtime.getRuntime().exec(script);
        } catch (IOException e) {
            throw new UpgradeException("start service failed.", e);
        }

        ThreadUtil.sleep(2000);

//        System.exit(0);
    }

    private String getAppHome() {

        String appHome = envMap.get("APP_HOME");
        if (appHome == null) {
            throw new UpgradeException("APP_HOME is not set.");
        }
        return appHome;
    }

    private String getAppName() {

        String appName = envMap.get("APP_NAME");
        if (appName == null) {
            throw new UpgradeException("APP_NAME is not set.");
        }
        return appName;
    }

    private String getJavaHome() {

        String javaHome = envMap.get("JAVA_HOME");
        if (javaHome == null) {
            throw new UpgradeException("JAVA_HOME is not set.");
        }
        return javaHome;
    }

    private File getBackupDir() {

        String appHome = getAppHome();
        File backupDir = new File(new File(appHome).getParentFile().getParentFile(), "Backup");
        if (!backupDir.exists()) {
            backupDir.mkdirs();
        }
        return backupDir;
    }

    private File getTmpDir() {

        String appHome = getAppHome();
        return new File(appHome, "tmp");
    }

    private File getCommonDir() {
        String appHome = getAppHome();
        File commonDir = new File(new File(appHome).getParentFile(), "Common");
        if (!commonDir.exists()) {
            throw new UpgradeException("Common folder is not found.");
        }
        return commonDir;
    }

    private String getToolsJar() {
        File commonDir = getCommonDir();
        File toolsDir = new File(commonDir, "tools");
        if (!toolsDir.exists()) {
            throw new UpgradeException("tools folder is not found.");
        }
        File[] files = toolsDir.listFiles();
        if (files == null) {
            throw new UpgradeException("ViewDepotTools jar is not found.");
        }
        for (File file : files) {
            if (file.isFile()) {
                String fileName = file.getName();
                if (fileName.startsWith("ViewDepotTools") && fileName.endsWith(".jar")) {
                    return file.getAbsolutePath();
                }
            }
        }
        throw new UpgradeException("ViewDepotTools jar is not found.");
    }
}
