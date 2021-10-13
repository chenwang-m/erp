package com.codingfuture.web.controller;

import com.codingfuture.dto.*;
import com.codingfuture.entity.Inventory;
import com.codingfuture.service.InventoryService;
import com.codingfuture.service.StockService;
import com.codingfuture.util.Result;
import com.codingfuture.util.ResultMsg;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/v2")
public class InventoryController {
    @Autowired
    private StockService stockService;

    @Autowired
    private InventoryService inventoryService;

    /**
     * 查询仓库全部信息
     *
     * @return Result
     */
    @GetMapping("/inventory/storeList")
    public Result storeList() {
        return Result.success(new PageDataDTO<StockStoreDTO>(stockService.findAllStoreName()));
    }

    /**
     * 查询所有商品类型
     *
     * @return Result
     */
    @GetMapping("/inventory/goodsList")
    public Result goodsList() {
        return Result.success(new PageDataDTO<StockGoodsDTO>(stockService.findAllGoodsName()));
    }

    /**
     * 盘盈盘亏记录登记
     *
     * @param inventory
     * @return ResultMsg
     */
    @PostMapping("/inventory/add")
    public ResultMsg addInventory(HttpServletRequest request, Inventory inventory) {
        HttpSession httpSession = request.getSession();
        EmpSessionDTO user = (EmpSessionDTO) httpSession.getAttribute("user");
        inventory.setCreater(user.getName());
        inventory.setState("未审核");
        if (inventoryService.addInventory(inventory) > 0) {
            return ResultMsg.SUCCESS(200, "盘盈盘亏记录登记成功");
        }
        return ResultMsg.error(0, "盘盈盘亏记录登记失败");
    }

    /**
     * 盘盈盘亏记录列表
     *
     * @param inventoryDto
     * @return
     */
    @GetMapping("/inventory/listByPage")
    public Result inventoryList(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int rows, InventoryDTO inventoryDto) {
        Page<InventoryDTO> objects = PageHelper.startPage(page, rows);
        inventoryService.inventoryList(inventoryDto);
        PageDataDTO<InventoryDTO> inventoryDtoPageDataDTO = new PageDataDTO<>(objects);
        return Result.success(inventoryDtoPageDataDTO);
    }

    /**
     * 回显盘盈盘亏记录
     *
     * @param id
     * @return
     */
    @GetMapping("/inventory/selectone")
    public Result selectOneInventory(Long id) {
        return Result.success(new PageDataDTO<InventoryDTO>(inventoryService.findOneInventory(id)));
    }

    /**
     * 审核编辑盘盈盘亏
     *
     * @param inventory
     * @return
     */
    @PostMapping("/inventory/update")
    public ResultMsg updateInventory(HttpServletRequest request,Inventory inventory) {
        HttpSession httpSession = request.getSession();
        EmpSessionDTO user = (EmpSessionDTO) httpSession.getAttribute("user");
        inventory.setChecker(user.getName());
        inventory.setState("已审核");
        Long empUUid = user.getEmpUuid();
        Long storeUuid = inventory.getStoreuuid();
        Long goodsUuid = inventory.getGoodsuuid();
        Long num = inventory.getNum();
        String type = inventory.getType();
        if (inventoryService.updateInventory(inventory) > 0 && inventoryService.doInStore(storeUuid, goodsUuid, num,type,empUUid) > 0) {
            return ResultMsg.SUCCESS(200, "审核/编辑成功");
        } else {
            return ResultMsg.SUCCESS(0, "审核/编辑失败");
        }
    }

    /**
     * 删除盘盈盘亏记录
     *
     * @param id
     * @return
     */
    @DeleteMapping("inventory")
    public ResultMsg deleteInventory(Long id) {
        if (inventoryService.deleteInventory(id) > 0) {
            return ResultMsg.SUCCESS(200, "删除成功");
        } else {
            return ResultMsg.SUCCESS(0, "删除失败");
        }
    }
}
