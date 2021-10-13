package com.codingfuture.mapper;

import com.codingfuture.dto.StoreOperationDTO;

import java.util.List;

public interface StoreOperationMapper {
    //查询所有库存变动记录
    List<StoreOperationDTO> findAllStoreOperation(StoreOperationDTO storeOperationDto);
}
