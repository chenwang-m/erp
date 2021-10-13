package com.codingfuture.dto;

import lombok.Data;

/**
 * 渲染 return_orders模块 双击弹出窗
 * 关联 ReturnOrdersWithSupplierNameDto
 */
@Data
public class ReturnOrderDetailDTO {
    private Long returnOrderDetailUuid;
    private Long goodsuuid;
    private String goodsname;
    private Long num;
    private Double money;
    private Double price;
    private String state;
}
