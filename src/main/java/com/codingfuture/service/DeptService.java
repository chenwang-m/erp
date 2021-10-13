package com.codingfuture.service;

import com.codingfuture.entity.Dept;

import java.util.List;

public interface DeptService {
    //查询部门信息
    List<Dept> findAllDept(Dept dept);

    //添加部门信息
    int addDept(Dept dept);

    //判断添加的部门是否存在
    boolean isExistDept(String deptName);

    //查询修改部门信息
    List<Dept> selectOneUpdateDept(Long id);

    //修改部门信息
    int updateDept(Dept dept);

    //删除部门信息
    int deleteDept(Long id);

    //判断修改的部门是否存在
    boolean isExistUpdateDept(String deptName,Long id);

    //要删除的部门是否包含员工
    boolean deleteDeptIsExistEmp(Long id);
}
