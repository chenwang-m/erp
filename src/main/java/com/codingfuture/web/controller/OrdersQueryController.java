package com.codingfuture.web.controller;

import com.codingfuture.dto.*;
import com.codingfuture.entity.Supplier;
import com.codingfuture.service.OrdersQueryService;
import com.codingfuture.service.OrdersService;
import com.codingfuture.service.SupplierService;
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
public class OrdersQueryController {
    @Autowired
    private OrdersQueryService ordersQueryService;
    @Autowired
    private SupplierService supplierService;

    @Autowired
    private OrdersService ordersService;

    // 查询所有销售商品订单，状态查,关联orderdetail表查询
    @GetMapping("market/listByPage")
    public Result ordersQuery(int page, int rows, String state) {
        Page<OrdersWithSupplierNameDTO> orderPage = PageHelper.startPage(page, rows);
        ordersQueryService.ordersQuery(state);
        PageDataDTO<OrdersWithSupplierNameDTO> ordersPageDataDTO = new PageDataDTO<>(orderPage);
        return Result.success(ordersPageDataDTO);
    }

    //    // 出库请求
    @PostMapping("/marketdetail/doOutStore")
    public ResultMsg doOutStore(@RequestParam("id") Long orderDetailUuid,
                                @RequestParam("storeuuid") Long storeUuid,
                                HttpServletRequest request) {
        HttpSession session = request.getSession();
        EmpSessionDTO user = (EmpSessionDTO) session.getAttribute("user");
        String operator = user.getName();

        int result = ordersQueryService.doOutStore(orderDetailUuid, storeUuid, operator);
        if (result > 0) {
            return ResultMsg.SUCCESS(200, "出库成功！");
        } else if (result == 0) {
            return ResultMsg.error(0, "库存数量不足,出库失败！");
        } else {
            return ResultMsg.error(0, "该仓库不存在此商品,出库失败！");
        }
    }

    //    渲染供应商下来列表
    @GetMapping("/market/showsupplier")
    public Result getSupplierList(String type) {
        Supplier customer = new Supplier();
        List<Supplier> supplierList = supplierService.getList(type, customer);
        PageDataDTO<Supplier> supplierPageDataDTO = new PageDataDTO<>(supplierList);
        return Result.success(supplierPageDataDTO);
    }

    // 新增销售订单
    @PostMapping("/market/add")
    public ResultMsg add(@RequestBody MarketOrderDTO marketOrders, HttpServletRequest request) {

        for (OrderDetailDTO marketDetail : marketOrders.getMarketDetails()) {
            if (marketDetail.getNum() <= 0) {
                return ResultMsg.error(0, "数量不能为负值！");
            }
        }

        HttpSession httpSession = request.getSession();
        EmpSessionDTO user = (EmpSessionDTO) httpSession.getAttribute("user");
        String creater = user.getName();
//        Long ender = user.getEmpUuid();
        marketOrders.setType("2");
        marketOrders.setCreater(creater);
//        marketOrders.setEnder(ender);
        int result = ordersQueryService.add(marketOrders);
        if (result > 0) {
            return ResultMsg.SUCCESS(200, "操作成功！");
        } else {
            return ResultMsg.error(0, "添加失败！");
        }
    }


}
