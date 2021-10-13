package com.codingfuture.mapper;

import com.codingfuture.dto.EmpRoleMenuDTO;
import com.codingfuture.dto.RoleDTO;
import com.codingfuture.entity.Role;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Set;

public interface RoleMapper {
    List<RoleDTO> roleList(RoleDTO roleDto);

    int addRole(Role role);

    @Select("SELECT name FROM role")
    List<String> findAllRoleName();

    int updateRole(RoleDTO roleDto);

    //@Select("SELECT name FROM role where name<>#{name}")
    List<String> findAllUpdateRoleName(Long id);

    int deleteRole(Long id);

    List<Role> selectAllRole();

    void deleteById(Long id);

    void insert(@Param("roleUuid") Long roleUuid, @Param("menuUuids") Set<String> menuUuids);

    List<EmpRoleMenuDTO> selectAllRoleMenu();
}
