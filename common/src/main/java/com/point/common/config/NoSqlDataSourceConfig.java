package com.point.common.config;

import com.point.common.database.datasource.NoSqlDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * NosqlDatasource配置
 */
@Configuration
@PropertySource("classpath:database.properties")
public class NoSqlDataSourceConfig {

    /**
     * DB 连接
     */
    @Value("${spring.datasource.nosql.url}")
    private String dbUrl;

    /**
     * 用户名
     */
    @Value("${spring.datasource.nosql.username}")
    private String username;

    /**
     * 密码
     */
    @Value("${spring.datasource.nosql.password}")
    private String password;

    /**
     * 驱动class名
     */
    @Value("${spring.datasource.nosql.driver-class-name}")
    private String driverClassName;

    /**
     * 创建NosqlDataSource
     *
     * @return NosqlDataSource
     */
    @Bean
    public NoSqlDataSource createNoSqlDataSource() {
        NoSqlDataSource dataSource = new NoSqlDataSource();
        dataSource.setUrl(this.dbUrl);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setDriverClassName(driverClassName);
        dataSource.setConnectionErrorRetryAttempts(-1);
        return dataSource;
    }

}
