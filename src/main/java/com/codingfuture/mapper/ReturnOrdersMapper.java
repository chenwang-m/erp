package com.codingfuture.mapper;

import com.codingfuture.dto.ReturnOrdersWithReturnOrderDetailsDTO;
import com.codingfuture.dto.ReturnOrdersWithSupplierNameDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ReturnOrdersMapper {

    List<ReturnOrdersWithSupplierNameDTO> selectByState(String state);

    int insert(ReturnOrdersWithReturnOrderDetailsDTO newReturnOrders);

    int updateState(@Param("returnOrdersUuid") Long returnOrdersUuid, @Param("state") String state, @Param("operator") String operator);

}
