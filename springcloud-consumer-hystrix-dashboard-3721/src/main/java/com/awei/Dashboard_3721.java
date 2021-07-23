package com.awei;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@SpringBootApplication
@EnableHystrixDashboard
public class Dashboard_3721 {
    public static void main(String[] args) {
        SpringApplication.run(Dashboard_3721.class,args);
    }
}
