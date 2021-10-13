package com.codingfuture.service.impl;

import com.codingfuture.dto.MenuLv1DTO;
import com.codingfuture.mapper.MenuMapper;
import com.codingfuture.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<MenuLv1DTO> getMenuList(Long empUuid) {
        return menuMapper.getMenuSelect(empUuid);
    }
}
