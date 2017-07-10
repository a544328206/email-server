package com.basic.rabbitmq.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * 这个是最熟悉不过了，是springboot的启动的类
 * Created by sdc on 2017/7/5.
 */
@SpringBootApplication
@ComponentScan(basePackages = { "com.basic" })
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
