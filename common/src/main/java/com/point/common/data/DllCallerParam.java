package com.point.common.data;

import lombok.Data;

import java.util.List;

/**
 * DLL调用参数
 */
@Data
public class DllCallerParam {

    private String sessionHandle;
    private List<Class> paramTypes;
    private List<Object> paramValues;
    private int resultLength;
    private String returnType;

}
