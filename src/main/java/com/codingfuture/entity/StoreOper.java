package com.codingfuture.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class StoreOper implements Serializable {
    private Long uuid;
    private Long empuuid;
    private Date opertime;
    private Long storeuuid;
    private Long goodsuuid;
    private Long num;
    private String type;
}
