package com.wkr.mall.front;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
@EnableAsync
@EnableSwagger2
@Configuration
@EnableTransactionManagement
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class) //去掉数据源
@ComponentScan(basePackages = {"com.wkr", "com.wkr.mall.front"})
@MapperScan(basePackages = {"com.wkr.**.dao"})
public class MallFrontApplication {
    private static Logger logger = LogManager.getLogger(MallFrontApplication.class);

    public static void main(String[] args) {
        logger.info("MallFrontApplication start ...");
        long start = System.currentTimeMillis();
        SpringApplication.run(MallFrontApplication.class, args);
        logger.info("MallFrontApplication start success, cost={}ms", System.currentTimeMillis()-start);
    }
}
