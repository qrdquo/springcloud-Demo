package com.awei.controller;

import com.awei.pojo.Dept;
import com.awei.service.ProviderService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//提供RestFull服务
@RestController
@RequestMapping("/dept")
public class ProviderController {
    @Autowired
    private ProviderService deptService;

    @PostMapping("/add")
    public boolean addDept(@RequestBody  Dept dept){
        return deptService.addDept(dept);
    }


    @HystrixCommand(fallbackMethod="error",
            //ignoreExceptions = Exception.class,这个是忽略异常触发的熔断
            commandProperties ={@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "3500")})
    @GetMapping("/queryById/{id}")
    public Dept queryById(@PathVariable("id") Long id){

        Dept dept = deptService.queryById(id);
        if(dept==null){
            throw new RuntimeException("id不存在，出现异常");
        }
        return dept;
    }

    @GetMapping("/list")
    public List<Dept> queryAll(){
        return deptService.queryAll();
    }

    public Dept error(@PathVariable("id") Long id){
        Dept dept = new Dept();
        dept.setName("亲:用户不存在哦");
        //调用远程服务失败，该如何处理？这些处理逻辑就可以写在该方法中
        return dept;
    }

}
