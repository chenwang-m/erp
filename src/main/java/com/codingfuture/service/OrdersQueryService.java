package com.codingfuture.service;

import com.codingfuture.dto.MarketOrderDTO;
import com.codingfuture.dto.OrdersWithSupplierNameDTO;

import java.util.List;

public interface OrdersQueryService {

    List<OrdersWithSupplierNameDTO> ordersQuery(String state);

    int add(MarketOrderDTO marketOrders);

    int doOutStore(Long orderDetailUuid, Long storeUuid, String operator);

}
