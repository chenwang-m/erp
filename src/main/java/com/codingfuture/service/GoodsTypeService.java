package com.codingfuture.service;

import com.codingfuture.entity.GoodsType;

import java.util.List;

public interface GoodsTypeService {
    List<GoodsType> findAllGoodsType(GoodsType goodsType);

    int goodsTypeAdd(GoodsType goodsType);

    int goodsTypeDel(Long id);

    //添加时判断商品类型名称名是否存在
    boolean isExistName(String name);

    GoodsType goodsTypeUpdateById(Long id);

    int goodsTypeUpdate(GoodsType goodsType);
}

