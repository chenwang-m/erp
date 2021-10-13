package com.codingfuture.service;

import com.codingfuture.dto.ReturnOrdersWithReturnOrderDetailsDTO;

public interface ReturnOrdersService {
    void getReturnOrdersList(String state);

    int add(ReturnOrdersWithReturnOrderDetailsDTO returnOrders);

    int doCheck(Long returnOrdersUuid,String operator);

    int doInStore(Long returnOrderDetailUuid, Long storeuuid,String operator,Long empUuid);
}
