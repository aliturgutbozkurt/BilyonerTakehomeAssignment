package com.bilyoner.microservices.homework;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class NumberServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(NumberServiceApplication.class, args);
    }
}
