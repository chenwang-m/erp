package com.codingfuture.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class OrderDetail implements Serializable {
    private Long uuid;
    private Long goodsuuid;
    private String goodsname;
    private Double price;
    private Long num;
    private Double money;
    private Date endtime;
    private Long ender;
    private Long storeuuid;
    private String state;
    private Long ordersuuid;
}
