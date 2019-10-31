package com.point.accept.config;

import org.springframework.stereotype.Component;

/**
 * @author huixing
 * @description Netty常量
 * @date 2019/10/30
 */
@Component
public class NettyConst {

    /**
     * 最大线程量
     */
    private static final int MAX_THREADS = 1024;
    /**
     * 数据包最大长度
     */
    private static final int MAX_FRAME_LENGTH = 65535;

    public static int getMaxFrameLength() {
        return MAX_FRAME_LENGTH;
    }

    public static int getMaxThreads() {
        return MAX_THREADS;
    }
}
