package com.codingfuture.web.controller;

import com.codingfuture.dto.*;
import com.codingfuture.entity.Supplier;
import com.codingfuture.service.GoodsService;
import com.codingfuture.service.OrdersService;
import com.codingfuture.service.SupplierService;
import com.codingfuture.util.Result;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/v2")
public class OrdersController {

    @Autowired
    private SupplierService supplierService;

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private OrdersService ordersService;


    //    渲染供应商下来列表
    @GetMapping("/orders/showsupplier")
    public Result getSupplierList(String type) {
        Supplier nullSupplier = new Supplier();
        List<Supplier> supplierList = supplierService.getList(type, nullSupplier);
        PageDataDTO<Supplier> supplierPageDataDTO = new PageDataDTO<>(supplierList);
        return Result.success(supplierPageDataDTO);
    }

    //    渲染商品名称下拉列表
    @GetMapping("/orders/listByPage1")
    public Result getGoodsList() {
        List<GoodsForPurchaseDTO> goodsListForOrders = goodsService.getGoodsListForOrders();
        PageDataDTO<GoodsForPurchaseDTO> pageDataDTO = new PageDataDTO<>(goodsListForOrders);
        return Result.success(pageDataDTO);
    }


    //    新增采购申请
    @PostMapping("/orders/add")
    public Result add(@RequestBody OrderWithOrderDetailsDTO newOrders, HttpServletRequest request) {

        for (OrderDetailDTO orderDetail : newOrders.getOrderDetails()) {
            if (orderDetail.getNum() <= 0) {
                return Result.error("数量不允许为负数！");
            }
        }

//        获取登录用户名称
        HttpSession session = request.getSession();
        EmpSessionDTO user = (EmpSessionDTO) session.getAttribute("user");
        String creater = user.getName();

        newOrders.setType("1");
        newOrders.setCreater(creater);
        int result = ordersService.add(newOrders);
        if (result > 0) {
            return Result.success("添加成功！");
        } else {
            return Result.error("添加失败！");
        }
    }

    //    采购订单查询
    @GetMapping("/orders/listByPage")
    public Result getOrdersList(String state, int page, int rows) {
        Page<OrdersWithSupplierNameDTO> objectPage = PageHelper.startPage(page, rows);
        ordersService.getOrdersList("1", state);
        PageDataDTO<OrdersWithSupplierNameDTO> pageDataDTO = new PageDataDTO<>(objectPage);
        return Result.success(pageDataDTO);
    }


    //    采购订单审核
    @PostMapping("/orders/doCheck")
    public Result doCheck(Long id, HttpServletRequest request) {

        HttpSession session = request.getSession();
        EmpSessionDTO user = (EmpSessionDTO) session.getAttribute("user");
        String operator = user.getName();

        int result = ordersService.doCheck(id, "1", operator);
        if (result > 0) {
            return Result.success("审核成功！");
        } else {
            return Result.error("审核失败！");
        }
    }

    //    采购订单确认
    @PostMapping("/orders/doStart")
    public Result doStart(Long id, HttpServletRequest request) {

        HttpSession session = request.getSession();
        EmpSessionDTO user = (EmpSessionDTO) session.getAttribute("user");
        String operator = user.getName();

        int result = ordersService.doStart(id, "2", operator);
        if (result > 0) {
            return Result.success("确认成功！");
        } else {
            return Result.error("确认失败！");
        }
    }
}
