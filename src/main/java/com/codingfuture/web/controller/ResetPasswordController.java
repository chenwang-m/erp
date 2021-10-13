package com.codingfuture.web.controller;

import com.codingfuture.dto.EmpDTO;
import com.codingfuture.dto.PageDataDTO;
import com.codingfuture.service.EmpService;
import com.codingfuture.service.ResetPasswordService;
import com.codingfuture.util.Result;
import com.codingfuture.util.ResultMsg;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 重置密码Controller
 */
@RestController
@RequestMapping("/v2")
public class ResetPasswordController {

    @Autowired
    private EmpService empService;

    @Autowired
    private ResetPasswordService resetPasswordService;

    /**
     * 显示员工列表信息
     *
     * @param page
     * @param rows
     * @param empDto
     * @return Result
     */
    @GetMapping("/emp_listByPage/listByPage")
    public Result empList(@RequestParam int page, @RequestParam int rows, EmpDTO empDto) {
        Page<EmpDTO> objectPage = PageHelper.startPage(page, rows);
        empService.findAllEmp(empDto);
        PageDataDTO<EmpDTO> pageDataDTO = new PageDataDTO<>(objectPage);
        return Result.success(pageDataDTO);
    }

    @PostMapping("/emp_updatePwd_reset/update")
    public ResultMsg updatePwd(@Param("id") Long id, @Param("newPwd") String newPwd) {
        if (resetPasswordService.updatePwd(id, newPwd) > 0) {
            return ResultMsg.SUCCESS(200, "重置密码成功");
        } else if (resetPasswordService.updatePwd(id, newPwd) == -1) {
            return ResultMsg.SUCCESS(0, "重置密码不能为空");
        } else {
            return ResultMsg.SUCCESS(0, "重置密码失败");
        }
    }
}
