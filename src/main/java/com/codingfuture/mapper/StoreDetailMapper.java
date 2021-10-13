package com.codingfuture.mapper;

import com.codingfuture.entity.StoreDetail;
import org.apache.ibatis.annotations.Param;

public interface StoreDetailMapper {

    StoreDetail selectByStoreUuidAndGoodsUuid(@Param("StoreUuid") Long StoreUuid, @Param("GoodsUuid") Long GoodsUuid);

    int insert(StoreDetail storeDetail);

    int updateNumById(@Param("num") Long num, @Param("storeDetailUuid") Long storeDetailUuid);
}
