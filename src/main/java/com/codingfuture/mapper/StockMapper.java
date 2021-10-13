package com.codingfuture.mapper;

import com.codingfuture.dto.StockDetailDTO;
import com.codingfuture.dto.StockGoodsDTO;
import com.codingfuture.dto.StockStoreDTO;

import java.util.List;

public interface StockMapper {

    List<StockGoodsDTO> findAllGoodsName();

    List<StockStoreDTO> findAllStockName();

    List<StockDetailDTO> storeDetailList(Integer storeName, Integer goodsName);
}
