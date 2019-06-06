package cn.cloudwalk.shark.eureka.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@EnableHystrix
@SpringBootApplication
public class SharkEurekaClientApplication {
    public static void main(String[] args) {
        SpringApplication.run(SharkEurekaClientApplication.class, args);
    }

}
