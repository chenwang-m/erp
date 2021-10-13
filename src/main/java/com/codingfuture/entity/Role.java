package com.codingfuture.entity;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Data
public class Role implements Serializable {
    private Long uuid;
    @NotEmpty(message = "角色名称不能为空")
    @Pattern(regexp = "[\u4e00-\u9fa5]+" ,message = "角色名称必须为汉字")
    private String name;
}
