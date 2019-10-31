package com.point.common.database.accessor.impl;

import com.point.common.database.accessor.AbstractDaoSupport;
import com.point.common.database.datasource.CommonDataSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Mysql JDBC Template
 */
@Component
public class Mysql extends AbstractDaoSupport {

    /**
     * 构造函数
     *
     * @param dataSource 数据源
     */
    public Mysql(CommonDataSource dataSource) {
        super(dataSource);
    }

    /**
     * 插入
     *
     * @param table        表
     * @param columnValues 键值对
     * @return 插入件数
     */
    protected int replace(String table, Map<String, Object> columnValues, Set<String> keyColumns) {

        // 使用 replace 语句
        String sql = makeSql4Replace(table, columnValues, keyColumns);
        List<Object> values = new ArrayList<>();

        for (Map.Entry<String, Object> entry : columnValues.entrySet()) {
            values.add(entry.getValue());
        }

        // value添加两遍
        for (Map.Entry<String, Object> entry : columnValues.entrySet()) {
            // 排除Key字段
            if (keyColumns != null && keyColumns.contains(entry.getKey())) {
                continue;
            }
            values.add(entry.getValue());
        }

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int cnt = getJdbcTemplate().update(
                (conn) -> {
                    PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                    for (int i = 1, size = values.size(); i <= size; i++) {
                        ps.setObject(i, values.get(i - 1));
                    }
                    return ps;
                }, keyHolder);
        if (keyHolder.getKey() == null) {
            return cnt;
        } else {
            return keyHolder.getKey().intValue();
        }
    }

    /**
     * 生成替换SQL文
     *
     * @param table        表
     * @param columnValues 插入对象
     * @param keyColumns   主键字段
     * @return SQL文
     */
    private String makeSql4Replace(String table, Map<String, Object> columnValues, Set<String> keyColumns) {

        String sql = super.makeSql4Insert(table, columnValues);
        StringBuilder sb = new StringBuilder(sql);
        sb.append(" ON DUPLICATE KEY UPDATE ");

        for (Map.Entry<String, Object> entry : columnValues.entrySet()) {
            // 排除Key字段
            if (keyColumns != null && keyColumns.contains(entry.getKey())) {
                continue;
            }
            sb.append(entry.getKey());
            sb.append(" = ?,");
        }
        sb.deleteCharAt(sb.length() - 1);

        // INSERT INTO tablename(field1,field2, field3, ...) VALUES(value1, value2, value3, ...) ON DUPLICATE KEY UPDATE field1=value1,field2=value2, field3=value3, ...;
        return sb.toString();
    }

}
