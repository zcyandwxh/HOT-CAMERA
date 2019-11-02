package com.point.accept;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableCaching
@EnableScheduling
@ComponentScan(basePackages = {"com.point.accept", "com.point.common"})
@EnableAsync
public class AcceptApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(AcceptApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

    }
}
