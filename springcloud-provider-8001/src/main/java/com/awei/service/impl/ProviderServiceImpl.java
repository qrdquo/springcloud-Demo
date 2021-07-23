package com.awei.service.impl;

import com.awei.mapper.DeptMapper;
import com.awei.pojo.Dept;
import com.awei.service.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProviderServiceImpl implements ProviderService {
    @Autowired
    private DeptMapper deptMapper;

    public boolean addDept(Dept dept) {
        return deptMapper.addDept(dept);
    }

    public Dept queryById(Long id) {
        return deptMapper.queryById(id);
    }

    public List<Dept> queryAll() {
        return deptMapper.queryAll();
    }
}
