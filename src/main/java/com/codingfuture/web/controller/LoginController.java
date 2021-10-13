package com.codingfuture.web.controller;

import com.codingfuture.dto.EmpSessionDTO;
import com.codingfuture.dto.ShowNameDTO;
import com.codingfuture.dto.UserLoginDTO;
import com.codingfuture.service.LoginService;
import com.codingfuture.util.MD5Utils;
import com.codingfuture.util.Result;
import com.codingfuture.util.ResultMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/v2")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public Result login(HttpServletRequest request, UserLoginDTO userLoginDTO) {
        EmpSessionDTO empSession = loginService.login(userLoginDTO.getUsername());
        if (empSession != null) {
            String pwd = empSession.getPwd();
            String pwdInput = MD5Utils.md5Password(userLoginDTO.getPwd());
            if (pwdInput.equals(pwd)) {
                HttpSession httpSession = request.getSession();
                httpSession.setAttribute("user", empSession);
                return Result.success("登录成功！");
            } else {
                return Result.error("密码错误，请确认密码！");
            }
        } else {
            return Result.error("该用户不存在，请确认用户名！");
        }
    }

    @PostMapping("/login_showName")
    public Result showName(HttpServletRequest request) {
        HttpSession httpSession = request.getSession();
        EmpSessionDTO user = (EmpSessionDTO) httpSession.getAttribute("user");
        String name = user.getName();
        ShowNameDTO showNameDTO = new ShowNameDTO(name);
        return Result.success(showNameDTO);
    }

    //退出登录
    @GetMapping("/login_loginOut")
    public ResultMsg loginOut(HttpServletRequest request) {
        HttpSession httpSession = request.getSession();
//        httpSession.setAttribute("user",null);
        httpSession.invalidate();
        return ResultMsg.SUCCESS(200,"退出登录成功");
    }
}