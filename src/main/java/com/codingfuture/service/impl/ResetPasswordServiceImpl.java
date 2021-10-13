package com.codingfuture.service.impl;

import com.codingfuture.mapper.ResetPasswordMapper;
import com.codingfuture.service.ResetPasswordService;
import com.codingfuture.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResetPasswordServiceImpl implements ResetPasswordService {

    @Autowired
    private ResetPasswordMapper resetPasswordMapper;

    @Override
    public int updatePwd(Long id, String newPwd) {
        if (newPwd == null || newPwd.length() <= 0) {
            return -1;
        } else {
            newPwd = MD5Utils.md5Password(newPwd);
            return resetPasswordMapper.updatePwd(id, newPwd);
        }
    }
}
