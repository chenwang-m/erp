package com.codingfuture.entity;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class Store implements Serializable {
    private Long uuid;
    @NotEmpty(message = "仓库名不能为空")
    private String name;
    @NotNull(message = "必须输入员工编号")
    private Long empuuid;

}
