package com.codingfuture.mapper;

import org.apache.ibatis.annotations.Param;

public interface ResetPasswordMapper {

    int updatePwd(@Param("id") Long id,@Param("newPwd") String newPwd);
}
