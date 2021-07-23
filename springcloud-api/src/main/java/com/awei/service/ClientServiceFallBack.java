package com.awei.service;

import com.awei.pojo.Dept;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ClientServiceFallBack implements ClientService {

    public boolean addDept(Dept dept) {
        return false;
    }

    public Dept queryById(Long id) {
        return new Dept().setId(id).setName("id=>"+id+"的用户信息不存在");
    }

    public List<Dept> queryAll() {
        Dept dept = new Dept();
        dept.setName("服务已关闭，请稍后再试");
        List<Dept> list = new ArrayList();
        list.add(dept);
        return list;
    }
}
