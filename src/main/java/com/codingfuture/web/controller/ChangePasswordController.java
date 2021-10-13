package com.codingfuture.web.controller;

import com.codingfuture.dto.EmpSessionDTO;
import com.codingfuture.dto.ResetPasswordDTO;
import com.codingfuture.service.ChangePasswordService;
import com.codingfuture.service.LoginService;
import com.codingfuture.util.MD5Utils;
import com.codingfuture.util.ResultMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/v2")
public class ChangePasswordController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private ChangePasswordService changePasswordService;

    @PostMapping("/updatepwd")
    public ResultMsg changePwd(HttpServletRequest request, ResetPasswordDTO resetPasswordDto) {
        HttpSession httpSession = request.getSession();
        EmpSessionDTO user = (EmpSessionDTO) httpSession.getAttribute("user");
        EmpSessionDTO empSession = loginService.login(user.getUsername());
        if (empSession != null) {
            String oldPwd = empSession.getPwd();
            String inputOldPwd = MD5Utils.md5Password(resetPasswordDto.getOldPwd());
            String newPwd = MD5Utils.md5Password(resetPasswordDto.getNewPwd());
            if (inputOldPwd.equals(oldPwd)) {
                if (changePasswordService.changePwd(user.getUsername(), newPwd) > 0) {
                    return ResultMsg.SUCCESS(200,"密码修改成功");
                } else {
                    return ResultMsg.error(0,"密码修改失败");
                }
            } else {
                return ResultMsg.error(0,"密码不正确");
            }
        } else{
            return ResultMsg.error(0,"该用户不存在，请确认用户名！");
        }
    }
}
