package com.codingfuture.mapper;

import com.codingfuture.dto.MarketOrderDTO;
import com.codingfuture.dto.OrdersWithSupplierNameDTO;

import java.util.List;

public interface OrdersQueryMapper {
    List<OrdersWithSupplierNameDTO> ordersQuery(String state);

    int add(MarketOrderDTO marketOrders);

//    int insertDetail(OrderDetailDto orderDetail, Long ordersUuid);
//    int insertDetail(Long ordersUuid);
}
