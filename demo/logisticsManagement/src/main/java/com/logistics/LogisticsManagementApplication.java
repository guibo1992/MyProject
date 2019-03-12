package com.logistics;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.filter.DelegatingFilterProxy;

@SpringBootApplication
@ComponentScan(value = "com.logistics.controller,com.logistics.shiro,com.logistics.service.impl")
@MapperScan("com.logistics.dao")
public class LogisticsManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(LogisticsManagementApplication.class, args);
    }
    
}
