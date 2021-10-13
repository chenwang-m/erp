package com.codingfuture.entity;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
public class Supplier implements Serializable {
    private Long uuid;
    @NotEmpty(message = "名称不能为空")
    private String name;
    @NotEmpty(message = "地址不能为空")
    private String address;
    @NotEmpty(message = "联系人不能为空")
    private String contact;
    @NotEmpty(message = "电话号不能为空")
    private String tele;
    @NotEmpty(message = "邮箱不能为空")
    private String email;
}
