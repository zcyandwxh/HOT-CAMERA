package com.point.webservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by huixing on 2019/11/1.
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
//@ComponentScan(basePackages = {"com.point.webservice","com.point.accept","com.point.db" ,"com.point.common"})
@ComponentScan(basePackages = {"com.point.webservice", "com.point.db"})
@MapperScan({"com.point.db.dao", "com.point.db.dao.ext"})
public class WebServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebServiceApplication.class, args);
    }

}
