package com.codingfuture.mapper;

import com.codingfuture.entity.GoodsType;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface GoodsTypeMapper {

    List<GoodsType> findAllGoodsType(GoodsType goodsType);

    int goodsTypeAdd(GoodsType goodsType);

    int goodsTypeDel(Long id);

    GoodsType goodsTypeUpdateById(Long id);


    int goodsTypeUpdate(GoodsType goodsType);

    // 重复输入查询
    @Select("SELECT name FROM goodstype")
    List<String> findGoodsType();
}




