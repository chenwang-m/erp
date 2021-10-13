package com.codingfuture.mapper;

import com.codingfuture.dto.StatisticsDTO;

import java.util.List;

public interface StatisticsMapper {
    // 饼状图
    List<StatisticsDTO> findStatisticsGoodsType(StatisticsDTO statistics);

    // 折线图
//    List<StatisticsDto> getMonthSales(StatisticsDto statistics);
}
