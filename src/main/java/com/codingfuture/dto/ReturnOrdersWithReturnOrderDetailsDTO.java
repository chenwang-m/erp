package com.codingfuture.dto;

import lombok.Data;

import java.util.List;

@Data
public class ReturnOrdersWithReturnOrderDetailsDTO {
    private Long returnOrdersuuid;
    private Long supplieruuid;
    private Double totalmoney;
    private String creater;
    private List<ReturnOrderDetailDTO> returnOrderDetails;
}
