package com.codingfuture.service;

public interface ResetPasswordService {

    //重置密码
    int updatePwd(Long id, String newPwd);
}
