package com.codingfuture.dto;

import lombok.Data;


@Data
public class ShowNameDTO {
    String rows;

    public ShowNameDTO(String rows) {
        this.rows = rows;
    }
}
