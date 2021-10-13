package com.codingfuture.service.impl;

import com.codingfuture.dto.StoreOperationDTO;
import com.codingfuture.mapper.StoreOperationMapper;
import com.codingfuture.service.StoreOperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreOperationServiceImpl implements StoreOperationService {

    @Autowired
    private StoreOperationMapper storeOperationMapper;

    @Override
    public List<StoreOperationDTO> findAllStoreOperation(StoreOperationDTO storeOperationDto) {
        return storeOperationMapper.findAllStoreOperation(storeOperationDto);
    }
}
