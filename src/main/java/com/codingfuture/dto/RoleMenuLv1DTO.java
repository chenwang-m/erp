package com.codingfuture.dto;

import lombok.Data;

import java.util.List;

@Data
public class RoleMenuLv1DTO {
    String title;
    String id;
    List<RoleMenuLv2DTO> children;
}
