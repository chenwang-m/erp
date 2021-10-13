package com.codingfuture.dto;

import com.codingfuture.entity.Role;
import lombok.Data;

import java.io.Serializable;

@Data
public class RoleDTO extends Role implements Serializable {
    private Long id;
    private int page;
    private int rows;
}
