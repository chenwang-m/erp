package com.codingfuture.service;

import com.codingfuture.dto.InventoryDTO;
import com.codingfuture.entity.Inventory;

import java.util.List;

public interface InventoryService {

    //盘盈盘亏记录进行登记
    int addInventory(Inventory inventory);

    //盘盈盘亏记录列表
    List<InventoryDTO> inventoryList(InventoryDTO inventoryDto);

    //编辑盘盈盘亏
    List<InventoryDTO> findOneInventory(Long id);

    //更新盘盈盘亏记录
    int updateInventory(Inventory inventory);

    //删除盘盈盘亏记录
    int deleteInventory(Long id);

    //增加或减少库存
    int doInStore(Long storeUuid,Long goodsUuid,Long num,String type,Long empUUid);
}
