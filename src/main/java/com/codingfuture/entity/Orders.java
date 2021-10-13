package com.codingfuture.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Orders implements Serializable {
    private Long uuid;
    private Date createtime;
    private Date checktime;
    private Date starttime;
    private Date endtime;
    private String type;
    private String creater;
    private String checker;
    private String starter;
    private String ender;
    private Long supplieruuid;
    private Double totalmoney;
    private String state;
    private Long waybillsn;
}
