package com.codingfuture.web.controller;

import com.codingfuture.dto.*;
import com.codingfuture.service.EmpService;
import com.codingfuture.service.StockService;
import com.codingfuture.service.StoreOperationService;
import com.codingfuture.util.Result;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 库存变更controller
 */
@RestController
@RequestMapping("/v2")
public class StoreOperationController {
    @Autowired
    private EmpService empService;

    @Autowired
    private StockService stockService;

    @Autowired
    private StoreOperationService storeOperationService;

    /**
     * 返回操作员信息一览
     *
     * @param empDto
     * @return Result
     */
    @GetMapping("/storeoper/emp_list")
    public Result empList(EmpDTO empDto) {
        return Result.success(new PageDataDTO<EmpDTO>(empService.findAllEmp(empDto)));
    }

    /**
     * 查询仓库全部信息
     *
     * @return Result
     */
    @GetMapping("/storeoper/store_list")
    public Result storeList() {
        return Result.success(new PageDataDTO<StockStoreDTO>(stockService.findAllStoreName()));
    }

    /**
     * 查询所有商品类型
     *
     * @return Result
     */
    @GetMapping("/storeoper/goods_list")
    public Result goodsList() {
        return Result.success(new PageDataDTO<StockGoodsDTO>(stockService.findAllGoodsName()));
    }

    /**
     * 库存变动记录查询
     *
     * @param page
     * @param rows
     * @param storeOperationDto
     * @return Result
     */
    @GetMapping("/storeoper")
    public Result storeOperationList(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int rows, StoreOperationDTO storeOperationDto) {
        Page<StoreOperationDTO> objectPage = PageHelper.startPage(page, rows);
        storeOperationService.findAllStoreOperation(storeOperationDto);
        PageDataDTO<StoreOperationDTO> pageDataDTO = new PageDataDTO<>(objectPage);
        return Result.success(pageDataDTO);
    }
}
