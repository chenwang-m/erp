package com.codingfuture.dto;

import lombok.Data;

/**
 * 渲染 orders模块 双击弹出窗
 * 关联 OrdersWithSupplierNameDto
 */
@Data
public class OrderDetailDTO {
    private Long orderDetailUuid;
    private Long goodsuuid;
    private String goodsname;
    private Long num;
    private Double money;
    private Double price;
    private String state;
}

