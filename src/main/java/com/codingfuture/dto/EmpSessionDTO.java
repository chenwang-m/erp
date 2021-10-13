package com.codingfuture.dto;

import lombok.Data;

@Data
public class EmpSessionDTO {
    private Long empUuid;
    private String username;
    private String pwd;
    private String name;
}
