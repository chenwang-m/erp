package com.codingfuture.dto;

import com.codingfuture.entity.ReturnOrderDetail;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * 渲染 return_orders模块 数据
 * 对应 returnOrders/listByPage 接口
 */
@Data
public class ReturnOrdersWithSupplierNameDTO {
    private Long uuid;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createtime;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date checktime;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date starttime;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date endtime;
    private String type;
    private String creatername;
    private String checkername;
    private String startername;
    private String endername;
    private String suppliername;
    private Double totalmoney;
    private String state;
    private List<ReturnOrderDetail> returnOrderDetailList;
}
