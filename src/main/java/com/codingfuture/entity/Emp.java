package com.codingfuture.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.util.Date;


@Data
public class Emp {
    private Long uuid;
    @NotEmpty(message = "登录名不能为空")
    private String userName;
    private String pwd;
    private String name;
    @NotNull(message = "必须选择性别")
    private Long gender;
    @NotEmpty(message = "邮箱不能为空")
    @Email
    private String email;
    @NotEmpty(message = "联系电话不能为空")
    @Pattern(regexp = "^[1][3,4,5,7,8][0-9]{9}$", message = "手机号码的格式不正确")
    private String tele;
    @NotEmpty(message = "联系地址不能为空")
    private String address;
    @NotNull(message = "生日不能为空")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date birthday;
    @NotNull(message = "部门名称不能为空")
    private Long depUuid;
}
