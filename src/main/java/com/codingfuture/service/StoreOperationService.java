package com.codingfuture.service;

import com.codingfuture.dto.StoreOperationDTO;

import java.util.List;

public interface StoreOperationService {

    //查询所有库存变动记录
    List<StoreOperationDTO> findAllStoreOperation(StoreOperationDTO storeOperationDto);
}
