package com.point.common.data;

import lombok.Data;

import java.util.List;

/**
 * ZMQ调用参数
 */
@Data
public class ZmqServerCallerParam {

    private List<Class> paramTypes;
    private List<Object> paramValues;
    private String returnType;

}
