package com.point.accept.controller;

import com.point.accept.service.WeedFSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author huixing
 * @description asds
 * @date 2019/10/30
 */
@Controller
public class COntrooler {

    @Autowired
    WeedFSService weedFSService;

    @ResponseBody
    @RequestMapping("/")
    public String a(){
        weedFSService.init();
        return "acasc";
    }
}
