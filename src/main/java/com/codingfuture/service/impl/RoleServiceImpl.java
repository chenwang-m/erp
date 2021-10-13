package com.codingfuture.service.impl;

import com.codingfuture.dto.EmpRoleMenuDTO;
import com.codingfuture.dto.RoleDTO;
import com.codingfuture.dto.RoleMenuLv1DTO;
import com.codingfuture.dto.RoleMenuLv2DTO;
import com.codingfuture.entity.Role;
import com.codingfuture.mapper.MenuMapper;
import com.codingfuture.mapper.RoleMapper;
import com.codingfuture.mapper.RoleMenuMapper;
import com.codingfuture.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private RoleMenuMapper roleMenuMapper;

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<RoleDTO> roleList(RoleDTO roleDto) {
        return roleMapper.roleList(roleDto);
    }

    @Override
    public int addRole(Role role) {
        if (isExistName(role.getName())) {
            return -1;
        } else {
            return roleMapper.addRole(role);
        }
    }

    @Override
    public boolean isExistName(String name) {
        boolean flag = false;
        if (roleMapper.findAllRoleName().contains(name)) {
            flag = true;
        }
        return flag;
    }

    @Override
    public int updateRole(RoleDTO roleDto) {
        if (isExistUpdateName(roleDto.getName(),roleDto.getUuid())) {
            return -1;
        } else {
            return roleMapper.updateRole(roleDto);
        }
    }

    @Override
    public boolean isExistUpdateName(String name,Long id) {
        boolean flag = false;
        if (roleMapper.findAllUpdateRoleName(id).contains(name)) {
            flag = true;
        }
        return flag;
    }

    @Override
    public int deleteRole(Long id) {
        return roleMapper.deleteRole(id);
    }


    @Override
    public List<Role> getAllRole() {
        return roleMapper.selectAllRole();
    }

    @Override
    public List<RoleMenuLv1DTO> getCheckedMenus(Long id) {
        List<RoleMenuLv1DTO> roleMenuLv1s = menuMapper.selectAllRoleMenu();
        List<String> checked = roleMenuMapper.selectRoleMenuById(id);

        for (RoleMenuLv1DTO roleMenuLv1 : roleMenuLv1s) {
            List<RoleMenuLv2DTO> roleMenuLv2s = roleMenuLv1.getChildren();
            for (RoleMenuLv2DTO roleMenuLv2 : roleMenuLv2s) {
                for (String s : checked) {
                    if (roleMenuLv2.getId().equals(s)) {
                        roleMenuLv2.setChecked(true);
                        break;
                    }
                }
            }
        }
        return roleMenuLv1s;
    }

    @Override
    @Transactional
    public int updateRoleMenus(Long id, String checkedStr) {
        if ("".equals(checkedStr) || checkedStr == null) {
            return 0;
        }

        String[] strings = checkedStr.split(",");
        Set<String> menuIdSet = new TreeSet<>();
        menuIdSet.add("0");
        for (String string : strings) {
            menuIdSet.add(string.charAt(0) + "00");
            menuIdSet.add(string);
        }
        roleMapper.deleteById(id);
        roleMapper.insert(id, menuIdSet);
        return 1;
    }

    @Override
    public List<EmpRoleMenuDTO> getCheckedRoleMenu(Long id) {
        List<EmpRoleMenuDTO> empRoleMenuChecked = roleMapper.selectAllRoleMenu();
        List<Long> CheckedIdList = roleMenuMapper.selectRoleMenuId(id);
        for (EmpRoleMenuDTO dto : empRoleMenuChecked) {
            for (Long checkId : CheckedIdList) {
                if (checkId.equals(dto.getId())) {
                    dto.setChecked(true);
                    break;
                }
            }
        }
        return empRoleMenuChecked;
    }
}
