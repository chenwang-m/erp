package com.codingfuture.service.impl;

import com.codingfuture.mapper.EmpRoleMapper;
import com.codingfuture.service.EmpRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmpRoleServiceImpl implements EmpRoleService {

    @Autowired
    private EmpRoleMapper empRoleMapper;

    @Override
    public void update(Long id, String checkedStr) {

        String[] roleUuidStrList = checkedStr.split(",");

        List<Long> roleUuidList = new ArrayList<>();

        for (String roleUuidStr : roleUuidStrList) {
            long roleUuid = Long.parseLong(roleUuidStr);
            roleUuidList.add(roleUuid);
        }

        empRoleMapper.deleteById(id);
        empRoleMapper.insert(id, roleUuidList);
    }
}
