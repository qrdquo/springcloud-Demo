package com.awei.service;

import com.awei.pojo.Dept;

import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


/**
 * 服务熔断：服务端，某个服务超时或者异常，引起熔断， 保险丝
 *
 * 服务降级：客户端——从整体网站请求考虑，当某个微服务熔断。或者宕机之后。服务将不再调用
 *          此时在客户端，我们可以准备一个FallBackFactory，返回一个默认值(缺省值)
 */


@FeignClient(name = "SPRINGCLOUD-PROVIDER",
            fallback = ClientServiceFallBack.class
            )
public interface ClientService {

    @PostMapping("/dept/add")
    public boolean addDept( @RequestBody  Dept dept);
    @GetMapping("/dept/queryById/{id}")
    public Dept queryById(@PathVariable("id") Long id);
    @GetMapping("/dept/list")
    public List<Dept> queryAll();
}
//