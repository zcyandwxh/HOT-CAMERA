package com.point.common.cache;

import com.point.common.consts.Constant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * HTTP回调总入口Controller
 */
@RestController
@EnableAutoConfiguration
@Slf4j
@RequestMapping("cache/*")
public class CacheUpdateController {

    /**
     * 缓存管理器
     */
    @Autowired
    private BizCache bizCache;

    /**
     * HTTP回调总入口 Post
     *
     * @param action 动作
     * @param name   缓存名
     * @return HTTP结果
     */
    @RequestMapping(value = "/{action}/{name}", method = RequestMethod.POST)
    public HttpStatus handlePostRequest(@PathVariable String action, @PathVariable String name, HttpServletRequest request) {

        log.debug("Cache {} {} {}", action, name, request.getRemoteAddr());
        if (Constant.CacheAction.CLEAR.equals(action)) {
            bizCache.clearCache(name);
        }
        return HttpStatus.OK;
    }
}
