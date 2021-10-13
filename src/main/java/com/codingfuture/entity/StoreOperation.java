package com.codingfuture.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class StoreOperation {
    private Long uuid;
    private Long empuuid;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date opertime;
    private Long storeuuid;
    private Long goodsuuid;
    private Long num;
    private String type;
}
