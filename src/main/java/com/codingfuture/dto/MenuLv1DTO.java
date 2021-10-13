package com.codingfuture.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class MenuLv1DTO implements Serializable {
    private String name="";
    private String title;
    private String jump = "";
    private Boolean spread = false;
    private String icon;
    private List<MenuLv2DTO> list = new ArrayList<>();

}
