package com.codingfuture.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmpRoleMapper {

    void deleteById(Long id);

    void insert(@Param("id") Long id, @Param("roleUuidList") List<Long> roleUuidList);

}
