package com.point.common.database.accessor.impl;

import com.point.common.database.accessor.AbstractDaoSupport;
import com.point.common.database.datasource.CommonDataSource;
import org.springframework.stereotype.Component;

/**
 * Sqlserver JDBC Template
 */
@Component
public class Sqlserver extends AbstractDaoSupport {

    /**
     * 构造函数
     *
     * @param dataSource 数据源
     */
    public Sqlserver(CommonDataSource dataSource) {
        super(dataSource);
    }

}
