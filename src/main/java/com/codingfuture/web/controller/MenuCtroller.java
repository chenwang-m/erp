package com.codingfuture.web.controller;

import com.codingfuture.dto.EmpSessionDTO;
import com.codingfuture.dto.MenuLv1DTO;
import com.codingfuture.service.MenuService;
import com.codingfuture.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/v2/menu")
public class MenuCtroller {

    @Autowired
    private MenuService menuService;

    @GetMapping("/select")
    public Result getMenuList(HttpServletRequest request) {
        HttpSession session = request.getSession();
        EmpSessionDTO user = (EmpSessionDTO) session.getAttribute("user");
        Long empUuid = user.getEmpUuid();
        System.err.println(empUuid);
        List<MenuLv1DTO> menuList = menuService.getMenuList(empUuid);
        return Result.success(menuList);
    }
}
