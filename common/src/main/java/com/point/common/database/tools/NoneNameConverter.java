package com.point.common.database.tools;

import org.springframework.stereotype.Component;

/**
 * DB字段名和Java名的相互转换（无转换）
 */
@Component
public class NoneNameConverter implements FieldNameConverter {

    /**
     * {@inheritDoc}
     */
    @Override
    public String toDbName(String name) {
        return name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toJavaName(String name) {
        return name;
    }
}
