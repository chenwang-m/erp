package com.codingfuture.service.impl;

import com.codingfuture.entity.Dept;
import com.codingfuture.mapper.DeptMapper;
import com.codingfuture.service.DeptService;
import com.codingfuture.util.ValidatorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class DeptServiceImpl implements DeptService {
    @Autowired
    private DeptMapper deptMapper;

    @Override
    public List<Dept> findAllDept(Dept dept) {

        return deptMapper.findAllDept(dept);
    }

    @Override
    public int addDept(Dept dept) {
        if (dept.getName().trim().isEmpty() || dept.getTele().trim().isEmpty()) {
            return -1;
        } else if (!ValidatorUtil.checkNameIsChinese(dept.getName())) {
            return -2;
        } else if (!ValidatorUtil.isPhone(dept.getTele())) {
            return -3;
        } else if (!isExistDept(dept.getName())) {
            return -4;
        } else {
            return deptMapper.addDept(dept);
        }
    }

    @Override
    public boolean isExistDept(String deptName) {
        boolean flag = true;
        if (deptMapper.findDeptName().contains(deptName)) {
            flag = false;
        }
        return flag;
    }

    @Override
    public List<Dept> selectOneUpdateDept(Long id) {
        return deptMapper.selectOneUpdateDept(id);
    }

    @Override
    public int updateDept(Dept dept) {
        if (dept.getName().trim().isEmpty() || dept.getTele().trim().isEmpty()) {
            return -1;
        } else if (!ValidatorUtil.checkNameIsChinese(dept.getName())) {
            return -2;
        } else if (!ValidatorUtil.isPhone(dept.getTele())) {
            return -3;
        } else if (!isExistUpdateDept(dept.getName(),dept.getUuid())) {
            return -4;
        } else {
            return deptMapper.updateDept(dept);
        }
    }

    @Override
    public int deleteDept(Long id) {
        if (!deleteDeptIsExistEmp(id)){
            return -1;
        }
        return deptMapper.deleteDept(id);
    }

    @Override
    public boolean isExistUpdateDept(String deptName,Long id) {
        boolean flag = true;
        if (deptMapper.findUpdateDeptName(id).contains(deptName)) {
            flag = false;
        }
        return flag;
    }

    @Override
    public boolean deleteDeptIsExistEmp(Long id) {
        boolean flag = true;
        if(deptMapper.deleteDeptIsExistEmp(id) == 1){
            flag = false;
        }
        return flag;
    }
}
