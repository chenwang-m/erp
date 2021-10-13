package com.codingfuture.web.controller;

import com.codingfuture.dto.PageDataDTO;
import com.codingfuture.dto.StockDetailDTO;
import com.codingfuture.dto.StockGoodsDTO;
import com.codingfuture.dto.StockStoreDTO;
import com.codingfuture.service.StockService;
import com.codingfuture.util.Result;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 库存Controller
 */
@RestController
@RequestMapping("/v2")
public class StockController {
    @Autowired
    private StockService stockService;

    /**
     * 查询所有商品
     *
     * @return Result
     */
    @GetMapping("/storedetail/goodsList")
    public Result goodsList() {
        return Result.success(new PageDataDTO<StockGoodsDTO>(stockService.findAllGoodsName()));
    }


    /**
     * 查询仓库全部信息
     *
     * @return Result
     */
    @GetMapping("/storedetail/storeList")
    public Result storeList() {
        return Result.success(new PageDataDTO<StockStoreDTO>(stockService.findAllStoreName()));
    }

    /**
     * 显示仓库信息列表
     *
     * @param page
     * @param rows
     * @param storeName
     * @param goodsName
     * @return Result
     */
    @GetMapping("/storedetail")
    public Result storeDetailList(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int rows, @Param("storeName") Integer storeName, @Param("goodsName") Integer goodsName) {
        Page<StockDetailDTO> objectPage = PageHelper.startPage(page, rows);
        stockService.storeDetailList(storeName, goodsName);
        PageDataDTO<StockDetailDTO> pageDataDTO = new PageDataDTO<>(objectPage);
        return Result.success(pageDataDTO);
    }
}
