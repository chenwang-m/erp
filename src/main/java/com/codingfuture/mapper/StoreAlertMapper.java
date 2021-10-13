package com.codingfuture.mapper;

import com.codingfuture.entity.StoreAlert;

import java.util.List;

public interface StoreAlertMapper {

    List<StoreAlert> selectAll();

    void insert(StoreAlert storeAlert);

    List<String> selectGoodsNames();

}
