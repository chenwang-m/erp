package com.codingfuture.service;

import com.codingfuture.dto.OrderWithOrderDetailsDTO;
import com.codingfuture.dto.OrdersWithSupplierNameDTO;

import java.util.List;

public interface OrdersService {

    //    添加 orders 和 关联的 order_detail 数据
    int add(OrderWithOrderDetailsDTO purchaseOrder);

    List<OrdersWithSupplierNameDTO> getOrdersList(String type, String state);

    int doCheck(Long ordersUuid, String state, String operator);

    int doStart(Long ordersUuid, String state, String operator);
}
