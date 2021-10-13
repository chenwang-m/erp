package com.codingfuture.dto;

import lombok.Data;

@Data
public class ResetPasswordDTO {
    private String newPwd;
    private String oldPwd;
}
