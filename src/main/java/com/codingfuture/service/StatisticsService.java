package com.codingfuture.service;

import com.codingfuture.dto.StatisticsDTO;

import java.util.List;
import java.util.Map;

public interface StatisticsService {
    List<StatisticsDTO> findStatisticsGoodsType(StatisticsDTO statistics);


   Map getMonthSales(String year);
}

