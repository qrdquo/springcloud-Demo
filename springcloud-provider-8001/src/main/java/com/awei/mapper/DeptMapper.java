package com.awei.mapper;


import com.awei.pojo.Dept;

import java.util.List;

public interface DeptMapper {
    public boolean addDept(Dept dept);
    public Dept queryById(Long id);
    public List<Dept> queryAll();
}