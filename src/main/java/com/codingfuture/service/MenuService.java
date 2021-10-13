package com.codingfuture.service;

import com.codingfuture.dto.MenuLv1DTO;

import java.util.List;

public interface MenuService {
    List<MenuLv1DTO> getMenuList(Long empUuid) ;
}
