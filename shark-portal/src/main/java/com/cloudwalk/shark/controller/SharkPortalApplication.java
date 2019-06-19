package com.cloudwalk.shark.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@EnableEurekaServer
@EnableCaching
@ComponentScan(basePackages = {"com.*"})
@ImportResource(value = {"classpath:spring/spring-*.xml"})
public class SharkPortalApplication {
    public static void main(String[] args) {
        SpringApplication.run(SharkPortalApplication.class, args);
    }

}
