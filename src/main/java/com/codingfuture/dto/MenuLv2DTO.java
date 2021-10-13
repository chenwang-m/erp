package com.codingfuture.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class MenuLv2DTO implements Serializable {
    private String name;
    private String title;
    private String jump = "";
    private Boolean spread = false;
}
