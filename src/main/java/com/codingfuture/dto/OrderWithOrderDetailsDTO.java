package com.codingfuture.dto;

import lombok.Data;

import java.util.List;

@Data
public class OrderWithOrderDetailsDTO {
    private Long ordersuuid;
    private String type;
    private String creater;
    private Long supplieruuid;
    private Double totalmoney;
    private List<OrderDetailDTO> orderDetails;
}
