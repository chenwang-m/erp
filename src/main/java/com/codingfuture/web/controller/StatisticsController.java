package com.codingfuture.web.controller;

import com.codingfuture.dto.PageDataDTO;
import com.codingfuture.dto.StatisticsDTO;
import com.codingfuture.mapper.OrderDetailMapper;
import com.codingfuture.service.StatisticsService;
import com.codingfuture.util.Result;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
//@RequestMapping("/v2")

public class StatisticsController {
    @Autowired
    private StatisticsService statisticsService;

    @Autowired
    private OrderDetailMapper orderDetailMapper;

    // 查询商品类型，销售额,饼状图
    @GetMapping("/getEchartsDataBall")
    @ResponseBody
    public Result getGoodsType(int page, int limit, StatisticsDTO statistics) {
        Page<StatisticsDTO> ePage = PageHelper.startPage(page, limit);
        statisticsService.findStatisticsGoodsType(statistics);
        PageDataDTO<StatisticsDTO> goodsTypePageDataDTO = new PageDataDTO<StatisticsDTO>(ePage);
        return Result.success(goodsTypePageDataDTO);
//        return Result.success(result);
    }

    // 查询商品类型,销售额,折线图
    @GetMapping("/getLine")
    @ResponseBody
    public Result getMonthSales(String year) {
//        Page<MonthSalesDTO> ePage = PageHelper.startPage(1, limit);
        Map data = statisticsService.getMonthSales(year);


        return Result.success(data);
    }


}
