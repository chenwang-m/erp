package com.codingfuture.mapper;

import com.codingfuture.dto.InventoryDTO;
import com.codingfuture.entity.Inventory;
import com.codingfuture.entity.StoreDetail;
import com.codingfuture.entity.StoreOper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface InventoryMapper {

    int addInventory(Inventory inventory);

    List<InventoryDTO> inventoryList(InventoryDTO inventoryDto);

    List<InventoryDTO> findOneInventory(Long id);

    int updateInventory(Inventory inventory);

    int deleteInventory(Long id);

    StoreDetail selectByStoreUuidAndGoodsUuid(@Param("storeUuid") Long storeUuid,@Param("goodsUuid") Long goodsUuid);

    int insert(StoreDetail storeDetail);

    int updateNumById(@Param("newNum") Long newNum,@Param("detailUuid") Long detailUuid);

    int insertStoreOper(StoreOper storeOper);
}
