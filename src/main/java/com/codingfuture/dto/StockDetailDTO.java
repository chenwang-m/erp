package com.codingfuture.dto;

import lombok.Data;


@Data
public class StockDetailDTO {
    private Long uuid;
    private String storeName;
    private String goodsName;
    private Long num;
}
