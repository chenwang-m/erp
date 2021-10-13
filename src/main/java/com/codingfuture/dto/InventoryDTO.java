package com.codingfuture.dto;

import com.codingfuture.entity.Inventory;
import lombok.Data;

@Data
public class InventoryDTO extends Inventory {
    private String goodsName;
    private String storeName;
}
