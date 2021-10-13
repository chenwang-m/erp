package com.codingfuture.service;

import com.codingfuture.dto.StockDetailDTO;
import com.codingfuture.dto.StockGoodsDTO;
import com.codingfuture.dto.StockStoreDTO;

import java.util.List;

public interface StockService {

    //查询所有商品
    List<StockGoodsDTO> findAllGoodsName();

    //查询所有仓库
    List<StockStoreDTO> findAllStoreName();

    //显示库存信息列表
    List<StockDetailDTO> storeDetailList(Integer storeName, Integer goodsName);
}
