package com.codingfuture.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Dept implements Serializable {
    private Long uuid;
    private String name;
    private String tele;
}
