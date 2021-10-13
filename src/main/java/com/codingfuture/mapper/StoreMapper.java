package com.codingfuture.mapper;

import com.codingfuture.entity.Store;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface StoreMapper {
    List<Store> findAllStore(Store store);

    int storeAdd(Store store);

    int storeDel(Long id);

    Store storeUpdateById(Long id);

    int storeUpdate(Store store);
    // 重复输入查询
    @Select("SELECT name FROM store")
    List<String> findStore();

}
