package com.codingfuture.service;

import com.codingfuture.entity.Supplier;

import java.util.List;

public interface SupplierService {
    int add(Supplier supplier, String type);

    /**
     * @param type     ”1“表示供应商,"2"表示客户
     * @param supplier 模糊查询的条件
     * @return Supplier集合
     */
    List<Supplier> getList(String type, Supplier supplier);

    Supplier getById(String id);

    int deleteById(Long id);

    int edit(Supplier supplier);
}
