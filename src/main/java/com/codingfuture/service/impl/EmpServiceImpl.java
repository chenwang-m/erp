package com.codingfuture.service.impl;

import com.codingfuture.dto.EmpDTO;
import com.codingfuture.entity.Emp;
import com.codingfuture.mapper.EmpMapper;
import com.codingfuture.service.EmpService;
import com.codingfuture.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;

    @Override
    public List<EmpDTO> findAllEmp(EmpDTO empDto) {
        return empMapper.findAllEmp(empDto);
    }

    @Override
    public int addEmp(Emp emp) {
        if (!isExistName(emp.getUserName())) {
            return -1;
        } else {
            emp.setPwd(MD5Utils.md5Password(emp.getUserName()));
            return empMapper.addEmp(emp);
        }
    }

    @Override
    public boolean isExistName(String userName) {
        boolean flag = true;
        if (empMapper.findEmpName().contains(userName)) {
            flag = false;
        }
        return flag;
    }

    @Override
    public List<EmpDTO> findOneEmp(Long id) {
        return empMapper.findOneEmp(id);
    }

    @Override
    public int updateEmp(Emp emp) {
        if (!isExistUserName(emp.getUserName(),emp.getUuid())) {
            return -1;
        } else {
            return empMapper.updateEmp(emp);
        }
    }

    @Override
    public boolean isExistUserName(String userName,Long id) {
        boolean flag = true;
        if (empMapper.findEmpUserName(id).contains(userName)) {
            flag = false;
        }
        return flag;
    }

    @Override
    public int deleteEmp(Long id) {
        return empMapper.deleteEmp(id);
    }

}
