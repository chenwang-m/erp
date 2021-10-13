package com.codingfuture.service;

import com.codingfuture.dto.GoodsForPurchaseDTO;
import com.codingfuture.entity.Goods;
import com.codingfuture.entity.GoodsType;

import java.util.List;


public interface GoodsService {

    List<Goods> findAllGoods(Goods goods);


    int goodsAdd(Goods goods);
    //添加时判断商品类型名称名是否存在
    boolean isExistName(String name);

    List<GoodsType> goodsTypeSelectAll();

    int goodsDel(Long uuid);

    Goods goodsFindById(Long id);

    int goodsUpdate(Goods goods);


    /**
     * @return Goods集合
     */
    List<GoodsForPurchaseDTO> getGoodsListForOrders();
}

