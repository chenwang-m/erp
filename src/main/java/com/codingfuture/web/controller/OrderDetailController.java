package com.codingfuture.web.controller;

import com.codingfuture.dto.EmpSessionDTO;
import com.codingfuture.service.OrderDetailService;
import com.codingfuture.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/v2")
public class OrderDetailController {

    @Autowired
    private OrderDetailService orderDetailService;

    @PostMapping("/orderdetail/doInStore")
    public Result doInStore(@RequestParam("id") Long orderDetailUuid, @RequestParam("storeuuid") Long storeUuid, HttpServletRequest request) {

        HttpSession session = request.getSession();
        EmpSessionDTO user = (EmpSessionDTO) session.getAttribute("user");
        String operator = user.getName();
        Long empUuid = user.getEmpUuid();

        int result = orderDetailService.doInStore(orderDetailUuid, storeUuid, operator,empUuid);
        if (result > 0) {
            return Result.success("入库成功！");
        } else {
            return Result.error("入库失败！");
        }
    }
}
