package com.codingfuture.mapper;

import com.codingfuture.dto.EmpDTO;
import com.codingfuture.dto.EmpSessionDTO;
import com.codingfuture.entity.Emp;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface EmpMapper {
    List<EmpDTO> findAllEmp(EmpDTO empDto);

    int addEmp(Emp emp);

    @Select("SELECT user_name FROM emp")
    List<String> findEmpName();

    List<EmpDTO> findOneEmp(Long id);

    int updateEmp(Emp emp);

    int deleteEmp(Long id);

    List<String> findEmpUserName(Long id);

    EmpSessionDTO selectNameByUsername(String username);

    int changePwd(@Param("username") String username,@Param("newPwd") String newPwd);
}
