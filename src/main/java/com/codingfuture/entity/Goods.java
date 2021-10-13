package com.codingfuture.entity;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class Goods implements Serializable {
    private Long uuid;
    @NotEmpty(message = "商品名不能为空")
    private String name;
    @NotEmpty(message = "产地不能为空")
    private String origin;
    @NotEmpty(message = "厂家不能为空")
    private String producer;
    @NotEmpty(message = "计量单位不能为空")
    private String unit;
    @NotNull(message = "进货价格不能为空")
    private Double inPrice;
    @NotNull(message = "销售价格不能为空")
    private Double outPrice;
    private Long goodsTypeUuid;
    private String goodsType;  //goodstypename

}
