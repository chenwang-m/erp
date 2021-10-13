package com.codingfuture.mapper;

import com.codingfuture.dto.ReturnOrderDetailDTO;
import com.codingfuture.entity.ReturnOrderDetail;
import org.apache.ibatis.annotations.Param;

public interface ReturnOrderDetailMapper {

    Integer countState0(Long returnOrderUuid);

    void insert(@Param("returnOrderDetail") ReturnOrderDetailDTO returnOrderDetail, @Param("returnOrdersUuid") Long returnOrdersUuid);

    ReturnOrderDetail selectById(Long returnOrdersUuid);

    void updateById(@Param("returnOrderDetailUuid") Long returnOrderDetailUuid, @Param("storeuuid") Long storeuuid, @Param("operator") String operator);

}
