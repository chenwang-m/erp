package com.codingfuture.dto;

import com.codingfuture.entity.StoreOperation;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class StoreOperationDTO extends StoreOperation {
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fopertime;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date topertime;
    private String storeName;
    private String goodsName;
    private String empName;
}
