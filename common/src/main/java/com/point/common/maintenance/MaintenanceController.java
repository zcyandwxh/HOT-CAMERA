package com.point.common.maintenance;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * HTTP回调总入口Controller
 */
@RestController
@EnableAutoConfiguration
@Slf4j
@RequestMapping("maintenance/*")
public class MaintenanceController {

    @Autowired
    private UpgradeProcess upgradeProcess;

    /**
     * HTTP回调总入口 Post
     *
     * @return HTTP结果
     */
    @RequestMapping(value = "/upgrade", method = RequestMethod.POST)
    public String handlePostRequest(@RequestParam(value = "file") List<MultipartFile> files, HttpServletRequest request) {

        log.debug("backup start.");
        upgradeProcess.backup();
        log.debug("receive start.");
        String newFile = upgradeProcess.receive(files.get(0));
        log.debug("deploy start.");
        upgradeProcess.deploy(newFile);
        log.debug("deploy end.");
        upgradeProcess.restartService();
        return "OK";
    }

}
