package com.codingfuture.service.impl;

import com.codingfuture.mapper.EmpMapper;
import com.codingfuture.service.ChangePasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChangePasswordServiceImpl implements ChangePasswordService {

    @Autowired
    private EmpMapper empMapper;

    @Override
    public int changePwd(String username,String newPwd) {
        return empMapper.changePwd(username,newPwd);
    }
}
