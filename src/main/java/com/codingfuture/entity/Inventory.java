package com.codingfuture.entity;

import lombok.Data;

@Data
public class Inventory {
    private Long uuid;
    private Long goodsuuid;
    private Long storeuuid;
    private Long num;
    private String type;
    private String createtime;
    private String checktime;
    private String creater;
    private String checker;
    private String state;
    private String remark;
}
