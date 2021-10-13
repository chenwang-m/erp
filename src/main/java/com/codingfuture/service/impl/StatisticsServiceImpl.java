package com.codingfuture.service.impl;

import com.codingfuture.dto.MonthSalesDTO;
import com.codingfuture.dto.StatisticsDTO;
import com.codingfuture.mapper.OrderDetailMapper;
import com.codingfuture.mapper.StatisticsMapper;
import com.codingfuture.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StatisticsServiceImpl implements StatisticsService {
    @Autowired
    private StatisticsMapper statisticsMapper;
    @Autowired
    private OrderDetailMapper orderDetailMapper;

    @Override
    public List<StatisticsDTO> findStatisticsGoodsType(StatisticsDTO statistics) {

        return statisticsMapper.findStatisticsGoodsType(statistics);
    }

    @Override
    public Map getMonthSales(String year) {
        Double[] salListByMonth = {0D, 0D, 0D, 0D, 0D, 0D, 0D, 0D, 0D, 0D, 0D, 0D};
        List<MonthSalesDTO> monthSalesDTOS = orderDetailMapper.selectSumMoneyByMonth(year);
        for (MonthSalesDTO salesDTO : monthSalesDTOS) {
            int index = Integer.parseInt(salesDTO.getMonth()) - 1;
            salListByMonth[index] = salesDTO.getMoney();
        }
        Map data =new HashMap<String,Object>();
        data.put("total",monthSalesDTOS.size());
        data.put("rows",monthSalesDTOS);
        data.put("money",salListByMonth);






        return data;
    }
}
