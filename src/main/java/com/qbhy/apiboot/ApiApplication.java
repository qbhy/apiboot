package com.qbhy.apiboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.*;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class ApiApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication app = new SpringApplication(ApiApplication.class);

        // 可以操作点什么

        app.run(args);
    }

}