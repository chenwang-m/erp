package com.codingfuture.service.impl;

import com.codingfuture.dto.InventoryDTO;
import com.codingfuture.entity.Inventory;
import com.codingfuture.entity.StoreDetail;
import com.codingfuture.entity.StoreOper;
import com.codingfuture.mapper.InventoryMapper;
import com.codingfuture.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryServiceImpl implements InventoryService {

    @Autowired
    private InventoryMapper inventoryMapper;
    StoreOper storeOper = new StoreOper();
    @Override
    public int addInventory(Inventory inventory) {
        return inventoryMapper.addInventory(inventory);
    }

    @Override
    public List<InventoryDTO> inventoryList(InventoryDTO inventoryDto) {
        return inventoryMapper.inventoryList(inventoryDto);
    }

    @Override
    public List<InventoryDTO> findOneInventory(Long id) {
        return inventoryMapper.findOneInventory(id);
    }

    @Override
    public int updateInventory(Inventory inventory) {
        return inventoryMapper.updateInventory(inventory);
    }

    @Override
    public int deleteInventory(Long id) {
        return inventoryMapper.deleteInventory(id);
    }

    @Override
    public int doInStore(Long storeUuid, Long goodsUuid, Long num, String type,Long empUUid) {
        StoreDetail storeDetail = inventoryMapper.selectByStoreUuidAndGoodsUuid(storeUuid, goodsUuid);
        if (storeDetail == null) {
            storeDetail = new StoreDetail();
            storeDetail.setStoreuuid(storeUuid);
            storeDetail.setGoodsuuid(goodsUuid);
            storeDetail.setUuid(empUUid);
            if (type.equals("盘亏")) {
                num = 0l - num;
            } else {
                num = num;
            }
            storeDetail.setNum(num);
            inventoryMapper.insert(storeDetail);
        } else {
            Long oldNum = storeDetail.getNum();
            Long newNum = 0l;
            if (type.equals("盘亏")) {
                newNum = oldNum - num;
            } else {
                newNum = oldNum + num;
            }
            Long detailUuid = storeDetail.getUuid();
            inventoryMapper.updateNumById(newNum, detailUuid);
        }
        storeOper.setEmpuuid(empUUid);
        storeOper.setStoreuuid(storeUuid);
        storeOper.setGoodsuuid(goodsUuid);
        storeOper.setNum(num);
        if (type.equals("盘亏")) {
            storeOper.setType("2");
        } else {
            storeOper.setType("1");
        }
         return inventoryMapper.insertStoreOper(storeOper);
    }
}
