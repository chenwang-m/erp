package com.codingfuture.service;

import com.codingfuture.dto.EmpRoleMenuDTO;
import com.codingfuture.dto.RoleDTO;
import com.codingfuture.dto.RoleMenuLv1DTO;
import com.codingfuture.entity.Role;

import java.util.List;

public interface RoleService {

    //显示角色列表
    List<RoleDTO> roleList(RoleDTO roleDto);

    //添加角色
    int addRole(Role role);

    //添加时判断数据库中是否含有该角色
    boolean isExistName(String name);

    //修改角色名称
    int updateRole(RoleDTO roleDto);

    //判断修改的角色名称是否存在(除了被修改的本身)
    boolean isExistUpdateName(String name, Long id);

    //删除角色信息
    int deleteRole(Long id);
    public List<Role> getAllRole();

    List<RoleMenuLv1DTO> getCheckedMenus(Long id);

    int updateRoleMenus(Long id, String checkedStr);

    List<EmpRoleMenuDTO> getCheckedRoleMenu(Long id);
}
