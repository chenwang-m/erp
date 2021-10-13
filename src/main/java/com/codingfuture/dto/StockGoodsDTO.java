package com.codingfuture.dto;

import lombok.Data;

@Data
public class StockGoodsDTO {
    private Long uuid;
    private String goodsName;
    private String origin;
    private String producer;
    private String unit;
    private Double inPrice;
    private Double outPrice;
    private Long goodsTypeUuid;
}
