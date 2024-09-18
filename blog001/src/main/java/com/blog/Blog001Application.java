package com.blog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.scheduling.annotation.EnableScheduling;


@ComponentScans({
        @ComponentScan("service.impl"),
        @ComponentScan("com.blog.utils"),
        @ComponentScan("com.blog.handler"),
        @ComponentScan("com.blog.filter"),
        @ComponentScan("com.blog.config"),
        @ComponentScan("com.blog.controller"),
        @ComponentScan("com.blog.runner"),
        @ComponentScan("com.blog.job")
})
@MapperScan("mapper")
@SpringBootApplication
@EnableScheduling
public class Blog001Application {

    public static void main(String[] args) {
        SpringApplication.run(Blog001Application.class, args);
    }

}
