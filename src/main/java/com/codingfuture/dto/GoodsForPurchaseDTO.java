package com.codingfuture.dto;


import lombok.Data;

@Data
public class GoodsForPurchaseDTO {
    private Long goodsuuid;
    private String goodsname;
    private Double inprice;
    private Double outprice;
    private Double price;
}
