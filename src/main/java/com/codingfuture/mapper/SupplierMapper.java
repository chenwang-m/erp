package com.codingfuture.mapper;

import com.codingfuture.entity.Supplier;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface SupplierMapper {

    int insert(@Param("supplier") Supplier supplier, @Param("type") String type);

    int countByName(String name);

    //    select supplier表
    List<Supplier> select(@Param("type") String type, @Param("supplier") Supplier supplier);

    Supplier selectById(String id);

    int deleteById(Long id);

    int update(Supplier supplier);
}
