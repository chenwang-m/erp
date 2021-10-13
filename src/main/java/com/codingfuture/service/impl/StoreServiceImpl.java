package com.codingfuture.service.impl;

import com.codingfuture.entity.Store;
import com.codingfuture.entity.StoreAlert;
import com.codingfuture.mapper.StoreAlertMapper;
import com.codingfuture.mapper.StoreMapper;
import com.codingfuture.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreServiceImpl implements StoreService {
    @Autowired
    private StoreMapper storeMapper;

    @Autowired
    private StoreAlertMapper storeAlertMapper;

    @Override
    public List<Store> findAllStore(Store store) {

        return storeMapper.findAllStore(store);
    }

    @Override
    public int storeAdd(Store store) {
        if (!isExistName(store.getName())) {
            return -1;
        } else {
            return storeMapper.storeAdd(store);
        }
    }

    @Override
    public boolean isExistName(String name) {
        boolean flag = true;
        if (storeMapper.findStore().contains(name)) {
            flag = false;
        }
        return flag;
    }


    @Override
    public int storeDel(Long id) {

        int storeDel = storeMapper.storeDel(id);
        return storeDel;
    }

    @Override
    public Store storeUpdateById(Long id) {
        return storeMapper.storeUpdateById(id);
    }

    @Override
    public int storeUpdate(Store store) {
        if (!isExistName(store.getName())) {
            return -1;
        } else {
            return storeMapper.storeUpdate(store);
        }

    }

    @Override
    public List<StoreAlert> getStoreAlertList() {
        return storeAlertMapper.selectAll();
    }

    @Override
    public String sendMessage() {
        List<String> goodsNameList = storeAlertMapper.selectGoodsNames();
        if (goodsNameList != null) {
            StringBuilder goodsNamesStr = new StringBuilder("");
            for (String s : goodsNameList) {
                goodsNamesStr.append(s);
                goodsNamesStr.append("、");
            }
            return goodsNamesStr.toString();
        } else {
            return null;
        }
    }


}
