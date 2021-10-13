package com.codingfuture.mapper;

import com.codingfuture.dto.MonthSalesDTO;
import com.codingfuture.dto.OrderDetailDTO;
import com.codingfuture.entity.OrderDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderDetailMapper {

    /**
     * 向orders_detail表插入一条数据
     */
    int insertDetail(@Param("orderDetail") OrderDetailDTO orderDetail, @Param("ordersuuid") Long ordersuuid);

    int updateStoreUuid(@Param("orderDetailUuid") Long orderDetailUuid, @Param("storeUuid") Long storeUuid, @Param("operator") String operator);

    OrderDetail selectByUuid(@Param("orderDetailUuid") Long orderDetailUuid);

    String selectByOrdersIdWithState(Long ordersUuid);

    List<MonthSalesDTO> selectSumMoneyByMonth(String year);

}
