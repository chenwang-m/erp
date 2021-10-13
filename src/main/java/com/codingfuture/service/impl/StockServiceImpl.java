package com.codingfuture.service.impl;

import com.codingfuture.dto.StockDetailDTO;
import com.codingfuture.dto.StockGoodsDTO;
import com.codingfuture.dto.StockStoreDTO;
import com.codingfuture.mapper.StockMapper;
import com.codingfuture.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockServiceImpl implements StockService {

    @Autowired
    private StockMapper stockMapper;

    @Override
    public List<StockGoodsDTO> findAllGoodsName() {
        return stockMapper.findAllGoodsName();
    }

    @Override
    public List<StockStoreDTO> findAllStoreName() {
        return stockMapper.findAllStockName();
    }

    @Override
    public List<StockDetailDTO> storeDetailList(Integer storeName, Integer goodsName) {
        return stockMapper.storeDetailList(storeName,goodsName);
    }
}
