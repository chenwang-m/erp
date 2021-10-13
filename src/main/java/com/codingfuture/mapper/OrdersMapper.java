package com.codingfuture.mapper;

import com.codingfuture.dto.OrderWithOrderDetailsDTO;
import com.codingfuture.dto.OrdersWithSupplierNameDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrdersMapper {

    /**
     * 向orders表插入一条数据
     */
    int insert(OrderWithOrderDetailsDTO ordersData);

    /**
     * 根据stateCode查询orders表所有数据
     */
    List<OrdersWithSupplierNameDTO> selectByTypeAndState(@Param("type") String type, @Param("state") String state);


    /**
     * 根据状态更新stateCode
     */
    int updateState(@Param("ordersUuid") Long ordersUuid, @Param("state") String state, @Param("operator") String operator
    );


    //    销售模块 出库用 update
    int updateForMarketOut(@Param("ordersUuid") Long ordersUuid, @Param("operator") String operator);

}
