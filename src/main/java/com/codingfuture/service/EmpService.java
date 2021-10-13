package com.codingfuture.service;

import com.codingfuture.dto.EmpDTO;
import com.codingfuture.entity.Emp;

import java.util.List;

public interface EmpService {
    //查询所有员工信息
    List<EmpDTO> findAllEmp(EmpDTO empDto);

    //添加员工信息
    int addEmp(Emp emp);

    //添加时判断登录名是否存在
    boolean isExistName(String userName);

    //回显查询
    List<EmpDTO> findOneEmp(Long id);

    //更新员工信息
    int updateEmp(Emp emp);

    //编辑时判断登录名是否存在
    boolean isExistUserName(String userName,Long uuid);

    //删除员工信息
    int deleteEmp(Long id);


}
