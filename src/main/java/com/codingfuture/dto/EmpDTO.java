package com.codingfuture.dto;

import com.codingfuture.entity.Emp;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class EmpDTO extends Emp {
    private String dep;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date bebirthday;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date afbirthday;
}
