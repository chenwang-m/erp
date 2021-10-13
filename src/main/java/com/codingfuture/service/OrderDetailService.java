package com.codingfuture.service;

public interface OrderDetailService {

    /**
     * 更新order_detail表的 store_uuid字段
     *
     * @param orderDetailUuid order_detail表的 uuid
     * @param storeUuid       store表的 uuid
     */
    int doInStore(Long orderDetailUuid, Long storeUuid, String operator,Long empUuid);
}
