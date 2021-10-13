package com.codingfuture.service;

import com.codingfuture.entity.Store;
import com.codingfuture.entity.StoreAlert;

import java.util.List;

public interface StoreService {
    List<Store> findAllStore(Store store);

    int storeAdd(Store store);

    //添加时判断商品类型名称名是否存在
    boolean isExistName(String name);

    int storeDel(Long id);

    Store storeUpdateById(Long id);

    int storeUpdate(Store store);

    List<StoreAlert> getStoreAlertList();

    String sendMessage();
}
