package com.codingfuture.mapper;


import com.codingfuture.dto.GoodsForPurchaseDTO;
import com.codingfuture.entity.Goods;
import com.codingfuture.entity.GoodsType;

import java.util.List;

public interface GoodsMapper {

    List<Goods> findAllGoods(Goods goods);

    int goodsAdd(Goods goods);

    List<GoodsType> goodsTypeSelectAll();

    int goodsDel(Long uuid);

    Goods goodsFindById(Long id);

    int goodsUpdate(Goods goods);

    //    查询goods表
    List<GoodsForPurchaseDTO> getGoodsListForOrders();
    // 重复输入查询
//    @Select("SELECT name FROM goods")
//    List<String> findGoods();

    int countByName(String name);
}
