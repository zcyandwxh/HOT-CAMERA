package com.point.common.database.accessor;

import com.point.common.database.datasource.NoSqlDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * Nosql数据库访问器
 */
@Component
public class NoSqlAccessor extends AbstractAccessor {

    /**
     * 主键字段
     */
    private Set<String> keyColumns = new HashSet<>();

    /**
     * 构造函数
     *
     * @param dataSource 数据源
     */
    @Autowired
    public NoSqlAccessor(NoSqlDataSource dataSource) {
        super(dataSource);
        keyColumns.add("ID");
    }


    /**
     * 插入
     *
     * @param table         表
     * @param bean          键值对
     * @return 插入件数
     */
    public <T> int insert(String table, T bean) {
        return super.replace(table, bean, keyColumns);
    }

}
