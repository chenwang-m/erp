package com.codingfuture.mapper;

import com.codingfuture.entity.Dept;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface DeptMapper {
    List<Dept> findAllDept(Dept dept);

    int addDept(Dept dept);

    @Select("SELECT name FROM dep")
    List<String> findDeptName();

    List<Dept> selectOneUpdateDept(Long id);

    int updateDept(Dept dept);

    int deleteDept(Long id);

    @Select("SELECT name FROM dep WHERE uuid <> #{id}")
    List<String> findUpdateDeptName(Long id);

    Integer deleteDeptIsExistEmp(Long id);
}
