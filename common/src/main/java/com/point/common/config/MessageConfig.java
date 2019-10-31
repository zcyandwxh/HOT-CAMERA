package com.point.common.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Locale;

/**
 * 错误信息配置
 */
@Component
public class MessageConfig {

    @Autowired
    private MessageSource messageSource;

    private MessageSourceAccessor accessor;

    @PostConstruct
    private void init() {
        accessor = new MessageSourceAccessor(messageSource, Locale.CHINA);
    }

    public String get(String code) {
        return accessor.getMessage(code);
    }
    public String get(String code, String... params) {
        return accessor.getMessage(code, params);
    }
}
