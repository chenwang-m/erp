package com.codingfuture.dto;

import lombok.Data;

import java.util.List;

@Data
public class MarketOrderDTO {
    // 接收销售订单录入

    private Long customeruuid;
    private String type;
    private Double totalmoney;
    private Long ordersuuid;
    private String creater;
    private Long ender;
    private List<OrderDetailDTO> marketDetails;
}

