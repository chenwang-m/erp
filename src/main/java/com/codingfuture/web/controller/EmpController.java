package com.codingfuture.web.controller;

import com.codingfuture.dto.EmpDTO;
import com.codingfuture.dto.EmpRoleMenuDTO;
import com.codingfuture.dto.PageDataDTO;
import com.codingfuture.entity.Dept;
import com.codingfuture.entity.Emp;
import com.codingfuture.service.DeptService;
import com.codingfuture.service.EmpRoleService;
import com.codingfuture.service.EmpService;
import com.codingfuture.service.RoleService;
import com.codingfuture.util.Result;
import com.codingfuture.util.ResultMsg;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;


/**
 * 员工controller
 *
 * @author wangchen
 */
@RestController
@RequestMapping("/v2")
public class EmpController {
    @Autowired
    private DeptService deptService;
    @Autowired
    private EmpService empService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private EmpRoleService empRoleService;

    ResultMsg result = null;


    /**
     * 显示部门下拉列表信息
     *
     * @param dept
     * @return
     */
    @GetMapping("/emp/deplist")
    public Result deptList(Dept dept) {
        return Result.success(new PageDataDTO<Dept>(deptService.findAllDept(dept)));
    }

    /**
     * 显示员工列表信息
     *
     * @param page
     * @param rows
     * @param empDto
     * @return Result
     */
    @GetMapping("/emp/listByPage")
    public Result empList(@RequestParam int page, @RequestParam int rows, EmpDTO empDto) {
        Page<EmpDTO> objectPage = PageHelper.startPage(page, rows);
        empService.findAllEmp(empDto);
        PageDataDTO<EmpDTO> pageDataDTO = new PageDataDTO<>(objectPage);
        return Result.success(pageDataDTO);
    }

    /**
     * 添加部门信息
     *
     * @param emp
     * @param result
     * @return ResultMsg
     */
    @PostMapping("/emp/add")
    public ResultMsg addEmp(@Validated Emp emp, BindingResult result) {
        if (result.hasErrors()) {
            ResultMsg error;
            error = ResultMsg.error(0, Objects.requireNonNull(result.getFieldError()).getDefaultMessage());
            return error;
        } else if (empService.addEmp(emp) == -1) {
            return ResultMsg.error(0, "输入内容不能与已有内容重复");
        }
        empService.addEmp(emp);
        return ResultMsg.SUCCESS(200, "操作成功");
    }

    /**
     * 编辑回显员工信息
     *
     * @param id
     * @return Result
     */
    @GetMapping("/emp/selectone")
    public Result selectOneEmp(Long id) {
        return Result.success(new PageDataDTO<EmpDTO>(empService.findOneEmp(id)));
    }

    /**
     * 编辑部门信息
     *
     * @param emp
     * @param result
     * @return ResultMsg
     */
    @PostMapping("/emp/update")
    public ResultMsg updateEmp(@Validated Emp emp, BindingResult result) {
        if (result.hasErrors()) {
            ResultMsg error;
            error = ResultMsg.error(0, Objects.requireNonNull(result.getFieldError()).getDefaultMessage());
            return error;
        } else if (empService.updateEmp(emp) == -1) {
            return ResultMsg.error(0, "登录名不能与已有内容重复");
        } else {
            empService.updateEmp(emp);
            return ResultMsg.SUCCESS(200, "修改成功");
        }
    }

    /**
     * 删除员工信息
     *
     * @param id
     * @return
     */
    @DeleteMapping("/emp")
    public ResultMsg deleteEmp(Long id) {
        if (empService.deleteEmp(id) > 0) {
            result = ResultMsg.SUCCESS(200, "删除成功");
        } else {
            result = ResultMsg.error(200, "删除失败");
        }
        return result;
    }

    @PostMapping("/emp_list")
    public Result getEmpList(int page, int rows) {
        Page<EmpDTO> objectPage = PageHelper.startPage(page, rows);
        EmpDTO nullEmpDTO = new EmpDTO();
        empService.findAllEmp(nullEmpDTO);
        PageDataDTO<EmpDTO> pageDataDTO = new PageDataDTO<>(objectPage);
        return Result.success(pageDataDTO);
    }

    @PostMapping("/emp_readEmpRoles")
    public Result readEmpRoles(Long id) {
        List<EmpRoleMenuDTO> checkedRoleMenu = roleService.getCheckedRoleMenu(id);
        PageDataDTO<EmpRoleMenuDTO> pageDataDTO = new PageDataDTO<>(checkedRoleMenu);
        return Result.success(pageDataDTO);
    }

    @PostMapping("/emp_updateEmpRoles")
    public Result updateEmpRoles(Long id, String checkedStr) {

        empRoleService.update(id, checkedStr);

        return Result.success();
    }


}
