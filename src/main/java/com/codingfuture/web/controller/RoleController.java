package com.codingfuture.web.controller;

import com.codingfuture.dto.PageDataDTO;
import com.codingfuture.dto.RoleDTO;
import com.codingfuture.dto.RoleMenuLv1DTO;
import com.codingfuture.entity.Role;
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

@RestController
@RequestMapping("/v2")
public class RoleController {

    @Autowired
    private RoleService roleService;

    /**
     * 显示角色一览
     *
     * @param roleDto
     * @return
     */
    @GetMapping("/role/listByPage")
    public Result roleList(RoleDTO roleDto) {
        Page<Object> objects = PageHelper.startPage(roleDto.getPage(), roleDto.getRows());
        roleService.roleList(roleDto);
        PageDataDTO<Object> objectPageDataDTO = new PageDataDTO<>(objects);
        return Result.success(objectPageDataDTO);
    }

    /**
     * 添加角色名称
     *
     * @param role
     * @param result
     * @return
     */
    @PostMapping("/role/add")
    public ResultMsg addRole(@Validated Role role, BindingResult result) {
        if (result.hasErrors()) {
            ResultMsg error;
            error = ResultMsg.error(0, Objects.requireNonNull(result.getFieldError()).getDefaultMessage());
            return error;
        } else if (roleService.addRole(role) == -1) {
            return ResultMsg.error(0, "添加角色与已有角色重复");
        } else {
            roleService.addRole(role);
            return ResultMsg.SUCCESS(200, "角色添加成功");
        }
    }

    /**
     * 回显角色名称
     *
     * @param roleDto
     * @return
     */
    @GetMapping("/role/selectone")
    public Result selectOneRole(RoleDTO roleDto) {
        return Result.success(new PageDataDTO<RoleDTO>(roleService.roleList(roleDto)));
    }

    /**
     * 更新角色名称
     *
     * @param roleDto
     * @param result
     * @return
     */
    @PostMapping("/role/update")
    public ResultMsg updateRole(@Validated RoleDTO roleDto, BindingResult result) {
        if (result.hasErrors()) {
            ResultMsg error;
            error = ResultMsg.error(0, Objects.requireNonNull(result.getFieldError()).getDefaultMessage());
            return error;
        } else if (roleService.updateRole(roleDto) == -1) {
            return ResultMsg.error(0, "添加角色与已有角色重复");
        } else {
            roleService.updateRole(roleDto);
            return ResultMsg.SUCCESS(200, "角色修改成功");
        }
    }

    /**
     * 删除角色
     *
     * @param id
     * @return
     */
    @DeleteMapping("/role")
    public ResultMsg deleteRole(Long id) {
        if (roleService.deleteRole(id) > 0) {
            return ResultMsg.SUCCESS(200, "删除成功");
        } else {
            return ResultMsg.SUCCESS(0, "删除失败");

        }
    }

    @RequestMapping("/role_list")
    public Result RoleList(int page, int rows) {
        Page<Role> objectPage = PageHelper.startPage(page, rows);
        roleService.getAllRole();
        PageDataDTO<Role> pageDataDTO = new PageDataDTO<>(objectPage);
        return Result.success(pageDataDTO);
    }

    @PostMapping("/role_readRoleMenus")
    public Result readRoleMenus(Long id) {
        List<RoleMenuLv1DTO> roleMenu = roleService.getCheckedMenus(id);
        PageDataDTO<RoleMenuLv1DTO> pageDataDTO = new PageDataDTO<>(roleMenu);
        return Result.success(pageDataDTO);
    }

    @PostMapping("/role_updateRoleMenus")
    public Result updateRoleMenus(Long id, String checkedStr) {
        int result = roleService.updateRoleMenus(id, checkedStr);
        if (result > 0) {
            return Result.success();
        } else if (result == 0) {
            return Result.success("至少选择一项!");
        } else {
            return Result.error();
        }
    }
}
