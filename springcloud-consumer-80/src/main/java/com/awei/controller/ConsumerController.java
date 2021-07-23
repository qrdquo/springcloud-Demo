package com.awei.controller;

import com.awei.pojo.Dept;
import com.awei.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dept")
public class ConsumerController {
    @Autowired
    private ClientService clientService;
    @PostMapping("/add")
    public boolean addDept(@RequestBody Dept dept){
        return clientService.addDept(dept);
    }
    @GetMapping("/queryById/{id}")
    public Dept queryById(@PathVariable("id") Long id){
        return clientService.queryById(id);
    }
    @GetMapping("/list")
    public List<Dept> queryAll(){
        return clientService.queryAll();
    }
}
