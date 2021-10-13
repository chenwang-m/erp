package com.codingfuture.service.impl;

import com.codingfuture.dto.EmpSessionDTO;
import com.codingfuture.mapper.EmpMapper;
import com.codingfuture.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private EmpMapper empMapper;

    @Override
    public EmpSessionDTO login(String username) {
        return empMapper.selectNameByUsername(username);
    }

}
