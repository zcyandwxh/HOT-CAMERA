package com.point.common.database.accessor;

import com.point.common.database.tools.FieldNameConverter;
import com.point.common.exception.DevelopmentException;
import com.point.common.exception.NotSupportedException;
import com.point.common.util.ReflectionUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.*;

/**
 * DB访问器
 */
public abstract class AbstractDaoSupport extends JdbcDaoSupport {

    /**
     * 构造函数
     *
     * @param dataSource 数据源
     */
    public AbstractDaoSupport(DataSource dataSource) {
        super();
        super.setDataSource(dataSource);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected JdbcTemplate createJdbcTemplate(DataSource dataSource) {
        return new JdbcTemplateWrapper(dataSource);
    }

    /**
     * 查询
     *
     * @param sql          sql文
     * @param requiredType 检索结果类型
     * @param args         参数
     * @param <T>          检索结果类型
     * @return 检索结果List
     */
    protected <T> List<T> query(String sql, Class<T> requiredType, Object... args) {
        RowMapper<T> rowMapper = BeanPropertyRowMapper.newInstance(requiredType);
        return getJdbcTemplate().query(sql, rowMapper, args);
    }

    /**
     * 查询
     *
     * @param sql          sql文
     * @param requiredType 检索结果类型
     * @param args         参数
     * @param <T>          检索结果类型
     * @return 检索结果List
     */
    protected <T> List<T> querySingle(String sql, Class<T> requiredType, Object... args) {
        return getJdbcTemplate().queryForList(sql, requiredType, args);
    }

    /**
     * 查询
     *
     * @param sql          sql文
     * @param requiredType 检索结果类型
     * @param args         参数
     * @param <T>          检索结果类型
     * @return 检索结果
     */
    protected <T> T queryForObject(String sql, Class<T> requiredType, Object... args) {

        try {
            if (ReflectionUtil.isPrimitive(requiredType) || String.class.equals(requiredType) || byte[].class.equals(requiredType)) {
                return getJdbcTemplate().queryForObject(sql, requiredType, args);
            } else {
                RowMapper<T> rowMapper = BeanPropertyRowMapper.newInstance(requiredType);
                return getJdbcTemplate().queryForObject(sql, rowMapper, args);
            }
            // 返回空
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    /**
     * 查询
     *
     * @param sql  sql文
     * @param args 参数
     * @return 检索结果
     */
    protected Map<String, Object> queryForMap(String sql, Object... args) {
        return getJdbcTemplate().queryForMap(sql, args);
    }

    /**
     * 查询
     *
     * @param table     表
     * @param columns   检索对象
     * @param where     检索条件
     * @param classType 检索结果类型
     * @param args      参数
     * @param <T>       检索结果类型
     * @return 检索结果
     */
    protected <T> List<T> query(String table, String[] columns, String where, Class<T> classType, Object... args) {

        String sql = makeSql4Select(table, columns, where);
        return query(sql, classType, args);
    }

    /**
     * 插入
     *
     * @param table        表
     * @param columnValues 键值对
     * @return 插入件数
     */
    protected int insert(String table, Map<String, Object> columnValues) {

        String sql = makeSql4Insert(table, columnValues);
        List<Object> values = new ArrayList<>();
        for (Map.Entry<String, Object> entry : columnValues.entrySet()) {
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
     * 插入
     *
     * @param table         表
     * @param bean          键值对
     * @param nameConverter 字段名转换器
     * @return 插入件数
     */
    protected <T> int insert(String table, T bean, FieldNameConverter nameConverter) {
        Map<String, Object> columnValues = new HashMap<>();
        Field[] fields = bean.getClass().getDeclaredFields();
        try {
            for (Field field : fields) {
                field.setAccessible(true);
                Object value = field.get(bean);
                if (value != null) {
                    if (nameConverter == null) {
                        columnValues.put(field.getName(), field.get(bean));
                    } else {
                        columnValues.put(nameConverter.toDbName(field.getName()), field.get(bean));
                    }
                }
            }
            return insert(table, columnValues);
        } catch (IllegalAccessException e) {
            throw new DevelopmentException(e);
        }
    }

    /**
     * 更新
     *
     * @param table        表
     * @param columnValues 键值对
     * @param where        更新条件
     * @param args         更新参数
     * @return 插入件数
     */
    protected int update(String table, Map<String, Object> columnValues, String where, Object... args) {

        String sql = makeSql4Update(table, columnValues, where);
        List<Object> values = new ArrayList<>();
        for (Map.Entry<String, Object> entry : columnValues.entrySet()) {
            values.add(entry.getValue());
        }
        if (args != null) {
            values.addAll(Arrays.asList(args));
        }
        return getJdbcTemplate().update(sql, values.toArray(new Object[0]));
    }

    /**
     * 替换
     *
     * @param table        表
     * @param columnValues 键值对
     * @return 插入件数
     */
    protected int replace(String table, Map<String, Object> columnValues, Set<String> keyColumns) {
        throw new NotSupportedException("Not Implemented.");
    }

    /**
     * 替换
     *
     * @param table 表
     * @param bean  键值对
     * @return 插入件数
     */
    public <T> int replace(String table, T bean, Set<String> keyColumns, FieldNameConverter nameConverter) {
        Map<String, Object> columnValues = new HashMap<>();
        Field[] fields = bean.getClass().getDeclaredFields();
        try {
            for (Field field : fields) {
                field.setAccessible(true);
                Object value = field.get(bean);
                if (value != null) {
                    if (nameConverter == null) {
                        columnValues.put(field.getName(), field.get(bean));
                    } else {
                        columnValues.put(nameConverter.toDbName(field.getName()), field.get(bean));
                    }
                }
            }
            return replace(table, columnValues, keyColumns);
        } catch (IllegalAccessException e) {
            throw new DevelopmentException(e);
        }
    }

    /**
     * 删除
     *
     * @param table 表
     * @param where 删除条件
     * @param args  更新参数
     * @return 删除件数
     */
    protected int delete(String table, String where, Object... args) {
        String sb = "DELETE FROM ".concat(table).concat(StringUtils.isEmpty(where) ? "" : " WHERE ".concat(where));
        return getJdbcTemplate().update(sb, args);
    }

    /**
     * 执行sql文
     *
     * @param sql  sql文
     * @param args 参数
     */
    protected void execute(String sql, Object... args) {
        getJdbcTemplate().update(sql, args);
    }

    /**
     * 生成查询SQL
     *
     * @param table   表
     * @param columns 查询对象
     * @param where   检索条件
     * @return SQL文
     */
    protected String makeSql4Select(String table, String[] columns, String where) {

        StringBuilder sb = new StringBuilder("SELECT ");
        for (String col : columns) {
            sb.append(col);
            sb.append(",");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append(" FROM ");
        sb.append(table);
        if (where != null && where.length() > 0) {
            sb.append(" WHERE ");
            sb.append(where);
        }
        return sb.toString();
    }

    /**
     * 生成插入SQL文
     *
     * @param table        表
     * @param columnValues 插入对象
     * @return SQL文
     */
    protected String makeSql4Insert(String table, Map<String, Object> columnValues) {

        StringBuilder sb = new StringBuilder("INSERT INTO ");
        sb.append(table);
        sb.append(" ( ");

        int valueCount = 0;
        for (Map.Entry<String, Object> entry : columnValues.entrySet()) {
            sb.append(entry.getKey());
            sb.append(" ,");
            valueCount++;
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append(" ) VALUES ( ");
        for (int i = 0; i < valueCount; i++) {
            sb.append(" ?,");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append(")");

        return sb.toString();
    }

    /**
     * 生成更新SQL文
     *
     * @param table        表
     * @param columnValues 插入对象
     * @param where        更新条件
     * @return SQL文
     */
    protected String makeSql4Update(String table, Map<String, Object> columnValues, String where) {
        StringBuilder sb = new StringBuilder("UPDATE ");
        sb.append(table);
        sb.append(" SET ");

        for (Map.Entry<String, Object> entry : columnValues.entrySet()) {
            sb.append(entry.getKey());
            sb.append(" = ?,");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append(" WHERE ");
        sb.append(where);

        return sb.toString();
    }

    /**
     * 生成执行SQL文
     *
     * @param sql 表
     * @return SQL文
     */
    protected String makeSql4Execute(String sql) {
        return sql;
    }
}
