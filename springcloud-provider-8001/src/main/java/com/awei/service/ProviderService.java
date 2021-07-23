package com.awei.service;

import com.awei.pojo.Dept;

import java.util.List;

public interface ProviderService {
    public boolean addDept(Dept dept);
    public Dept queryById(Long id);
    public List<Dept> queryAll();
}
