package com.wkr.mall.admin;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 程序主入口
 */
@Slf4j
@EnableAsync //开启异步调用
@EnableSwagger2
@Configuration
@EnableTransactionManagement
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class) //去掉数据源
@ComponentScan(basePackages = {"com.wkr"})
@MapperScan(basePackages = {"com.wkr.**.dao"})
public class MallAdminApplication {

    public static void main(String[] args) {
        log.info("MallAdminApplication start ...");
        long start = System.currentTimeMillis();
        SpringApplication.run(MallAdminApplication.class, args);
        log.info("MallAdminApplication start success, cost={} ms", System.currentTimeMillis()-start);
    }

}
