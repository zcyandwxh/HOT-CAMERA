package com.point.common.util;

import com.point.common.exception.DevelopmentException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.SystemUtils;
import sun.net.util.IPAddressUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * 网络相关工具函数
 */
@Slf4j
public abstract class IpUtil {

    /**
     * 判断网络是否正常
     *
     * @param ipAddr IP地址
     * @return 是否正常
     */
    public static int isIpReachable(String ipAddr) {

//        try {
//            InetAddress address = InetAddress.getByName(ipAddr);
//            boolean reachable = address.isReachable(5000);
//            if (reachable) {
//                return 1;
//            }
//            return 2;
//        } catch (IOException e) {
//            return 0;
//        }

        List<String> command = new ArrayList<>();
        command.add("ping");

        if (SystemUtils.IS_OS_WINDOWS) {
            command.add("-n");
        } else if (SystemUtils.IS_OS_UNIX) {
            command.add("-c");
        } else {
            throw new UnsupportedOperationException("Unsupported operating system");
        }
        command.add("1");
        command.add(ipAddr);

        ProcessBuilder processBuilder = new ProcessBuilder(command);
        Process process;
        try {
            process = processBuilder.start();
            BufferedReader standardOutput = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String outputLine;
            while ((outputLine = standardOutput.readLine()) != null) {
                // Picks up Windows and Unix unreachable hosts
                if (outputLine.toLowerCase().contains("ttl")) {
                    return 1;
                }
            }
        } catch (IOException e) {
            return 0;
        }
        return 2;
    }

    /**
     * 获取本机IP地址
     *
     * @return IP地址
     */
    public static String getLocalIp() {

        try {

            Enumeration<NetworkInterface> allNetInterfaces = NetworkInterface.getNetworkInterfaces();
            while (allNetInterfaces.hasMoreElements()) {
                NetworkInterface netInterface = allNetInterfaces.nextElement();
                Enumeration<InetAddress> addresses = netInterface.getInetAddresses();
                while (addresses.hasMoreElements()) {
                    InetAddress ip = addresses.nextElement();
                    if (!ip.isLinkLocalAddress() && !ip.isLoopbackAddress() && ip instanceof Inet4Address) {
                        return ip.getHostAddress();
                    }
                }
            }
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException | SocketException e) {
            try {
                return InetAddress.getLocalHost().getHostName();
            } catch (UnknownHostException e1) {
                throw new DevelopmentException(e1);
            }
        }
    }

    /**
     * 判断是否是IP地址
     *
     * @param ipAddr IP地址
     * @return 是否是IP地址
     */
    public static boolean isIpAddress(String ipAddr) {
        return IPAddressUtil.isIPv4LiteralAddress(ipAddr);
    }

    /**
     * 根据域名获取IP地址
     *
     * @param ipAddr 域名
     * @return IP地址
     */
    public static String getIpByName(String ipAddr) {

        String ip = ipAddr;
        if (!isIpAddress(ipAddr)) {
            try {
                ip = InetAddress.getByName(ipAddr).getHostAddress();
            } catch (UnknownHostException e) {
                throw new DevelopmentException(e);
            }
        }
        return ip;
    }

    /**
     * 根据URL地址获取主机名或IP
     * 格式:htttp://xxxx:9090/
     *
     * @param url 地址
     * @return 主机名或IP
     */
    public static String getHostByUrl(String url) {

        String hostName = url;
        int beginindex = url.indexOf("//");
        int endindex = url.lastIndexOf(":");
        try {
            hostName = url.substring(beginindex + 2, endindex);
        } catch (Throwable e) {
            // 忽略异常
            log.error("URL不合法，返回空字符串.", e);
            hostName = "";
        }
        return hostName;
    }

    /**
     * 将URL中的域名变换成IP
     *
     * @param url URL地址
     * @return 域名到IP变换结果URL
     */
    public static String formatUrlIpByHost(String url) {
        String hostName = IpUtil.getHostByUrl(url);
        String ip;
        try {
            ip = IpUtil.getIpByName(hostName);
        } catch (Exception e) {
            log.error("Web服务器域名解析失败，使用域名访问！", e);
            return url;
        }
        return url.replace(hostName, ip);
    }

    /**
     * 判断是否本地路径
     *
     * @param urlPath 路径
     * @return 是否本地路径
     */
    public static boolean isLocalPath(String urlPath) {
        return !StringUtils.startsWithIgnoreCase(urlPath, "http");
    }
}
