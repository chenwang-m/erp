package com.codingfuture.service;

import com.codingfuture.dto.EmpSessionDTO;

public interface LoginService {
    EmpSessionDTO login(String username);
}
