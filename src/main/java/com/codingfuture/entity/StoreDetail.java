package com.codingfuture.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class StoreDetail implements Serializable {
    private Long uuid;
    private Long storeuuid;
    private Long goodsuuid;
    private Long num;
}
