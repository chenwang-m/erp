package com.codingfuture.dto;

import com.github.pagehelper.Page;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * 分页查询结果
 */
@Data
@AllArgsConstructor
public class PageDataDTO<E> {
    private List<E> rows;
    private long total;

    public PageDataDTO(Page<E> page) {
        rows = page.getResult();
        total = page.getTotal();
    }

    public PageDataDTO(List<E> rows) {
        this.rows = rows;
    }

}
