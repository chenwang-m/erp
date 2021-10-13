package com.codingfuture.web.controller;

import com.codingfuture.dto.EmpSessionDTO;
import com.codingfuture.dto.PageDataDTO;
import com.codingfuture.dto.ReturnOrderDetailDTO;
import com.codingfuture.dto.ReturnOrdersWithReturnOrderDetailsDTO;
import com.codingfuture.entity.Store;
import com.codingfuture.service.ReturnOrdersService;
import com.codingfuture.service.StoreService;
import com.codingfuture.util.Result;
import com.codingfuture.util.ResultMsg;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/v2")
public class ReturnOrdersController {

    @Autowired
    private StoreService storeService;

    @Autowired
    private ReturnOrdersService returnOrdersService;

    //        获取仓库列表
    @GetMapping("/returnorders/selectStore")
    public Result getStoreSelect() {
        Store nullStore = new Store();
        List<Store> storeList = storeService.findAllStore(nullStore);
        PageDataDTO<Store> pageDataDTO = new PageDataDTO<>(storeList);
        return Result.success(pageDataDTO);
    }

    @PostMapping("/returnorders/add")
    public Result addReturnOrders(@RequestBody ReturnOrdersWithReturnOrderDetailsDTO returnOrders, HttpServletRequest request) {

        for (ReturnOrderDetailDTO returnOrderDetail : returnOrders.getReturnOrderDetails()) {
            if (returnOrderDetail.getNum() <= 0) {
                return Result.error("数量不允许为负值！");
            }
        }

        HttpSession session = request.getSession();
        EmpSessionDTO user = (EmpSessionDTO) session.getAttribute("user");
        String creater = user.getName();
        returnOrders.setCreater(creater);

        int result = returnOrdersService.add(returnOrders);
        if (result > 0) {
            return Result.success();
        } else {
            return Result.error();
        }
    }

    @GetMapping("/returnorders/listByPage")
    public Result getReturnOrdersList(String state, int page, int rows) {
        Page<Object> objects = PageHelper.startPage(page, rows);
        returnOrdersService.getReturnOrdersList(state);
        PageDataDTO<Object> pageDataDTO = new PageDataDTO<>(objects);
        return Result.success(pageDataDTO);
    }

    @GetMapping("/returnorders/salesReturnlistByPage")
    public Result getAllReturnOrders(int page, int rows) {
        Page<Object> objects = PageHelper.startPage(page, rows);
        returnOrdersService.getReturnOrdersList(null);
        PageDataDTO<Object> pageDataDTO = new PageDataDTO<>(objects);
        return Result.success(pageDataDTO);
    }

    @PostMapping("/returnorders/doCheck")
    public Result doCheck(Long id, HttpServletRequest request) {
        HttpSession session = request.getSession();
        EmpSessionDTO user = (EmpSessionDTO) session.getAttribute("user");
        String operator = user.getName();
        int result = returnOrdersService.doCheck(id, operator);
        if (result > 0) {
            return Result.success();
        } else {
            return Result.error();
        }
    }

    @PostMapping("//returnorders/doInStore")
    public ResultMsg doInStore(Long id, Long storeuuid, HttpServletRequest request) {

        HttpSession session = request.getSession();
        EmpSessionDTO user = (EmpSessionDTO) session.getAttribute("user");
        String operator = user.getName();
        Long empUuid = user.getEmpUuid();

        int result = returnOrdersService.doInStore(id, storeuuid, operator, empUuid);
        if (result > 0) {
            return ResultMsg.SUCCESS(200, "出库成功");
        } else if (result == 0) {
            return ResultMsg.error(0, "库存数量不足,出库失败！");
        } else {
            return ResultMsg.error(0, "该仓库不存在此商品,出库失败！");
        }
    }
}
