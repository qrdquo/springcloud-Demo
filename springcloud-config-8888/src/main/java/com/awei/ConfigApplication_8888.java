package com.awei;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class ConfigApplication_8888 {
    public static void main(String[] args) {
        SpringApplication.run(ConfigApplication_8888.class,args);
    }
}
