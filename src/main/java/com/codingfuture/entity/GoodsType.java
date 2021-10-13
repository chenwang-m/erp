package com.codingfuture.entity;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
public class GoodsType implements Serializable {
    private Long uuid;
    @NotEmpty(message = "商品类型名称不能为空")
    private String name;
}
