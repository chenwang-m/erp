package com.codingfuture.mapper;

import com.codingfuture.dto.MenuLv1DTO;
import com.codingfuture.dto.RoleMenuLv1DTO;

import java.util.List;

public interface MenuMapper {

    List<MenuLv1DTO> getMenuSelect(Long empUuid);

    List<RoleMenuLv1DTO> selectAllRoleMenu();
}
