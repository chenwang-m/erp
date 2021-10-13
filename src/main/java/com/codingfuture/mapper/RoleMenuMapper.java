package com.codingfuture.mapper;

import java.util.List;

public interface RoleMenuMapper {

    List<String> selectRoleMenuById(Long id);

    List<Long> selectRoleMenuId(Long id);

}
