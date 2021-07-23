package com.awei.config;


import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    @Bean
    @LoadBalanced//RestTemplate赋予  负载均衡的能力
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }


    /**
     * 定义Ribbon负载均衡的bean配置，用于改变Ribbon的默认负载均衡策略
     * @return
     */
   /* @Bean
    public IRule iRule(){
        //创建一个随机的Ribbon的负载均衡策略，默认为轮询策略
        return new RandomRule();
    }*/
}
