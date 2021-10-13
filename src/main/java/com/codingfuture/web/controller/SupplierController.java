package com.codingfuture.web.controller;

import com.codingfuture.dto.PageDataDTO;
import com.codingfuture.entity.Supplier;
import com.codingfuture.service.SupplierService;
import com.codingfuture.util.Result;
import com.codingfuture.util.ValidatorUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/v2")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    @PostMapping("/supplier/add")
    public Result add(@RequestParam("t.type") String type, @Validated Supplier supplier, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            Result error;
            error = Result.error(0, Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
            return error;
        }
        if (!ValidatorUtil.isTelNumber(supplier.getTele())) {
            return Result.error("无效点电话号码，添加失败！");
        }
        System.err.println(supplier.getAddress());
        if (!ValidatorUtil.isEmailAddress(supplier.getEmail())) {
            return Result.error("无效邮箱地址，添加失败！");
        }

        int result = supplierService.add(supplier, type);
        if (result > 0) {
            return Result.success("添加成功！");
        } else if (result == 0) {
            return Result.error("名称重复，添加失败！");
        } else {
            return Result.error("添加失败！");
        }
    }

    @GetMapping("/supplier/listByPage")
    public Result getList(@RequestParam("t1.type") String type, int page, int rows, Supplier supplier) {
        Page<Supplier> objectPage = PageHelper.startPage(page, rows);
        supplierService.getList(type, supplier);
        PageDataDTO<Supplier> pageDataDTO = new PageDataDTO<>(objectPage);
        return Result.success(pageDataDTO);
    }

    @GetMapping("/supplier/selectone")
    public Result getById(String id) {
        Page<Supplier> objectPage = PageHelper.startPage(1, 1);
        supplierService.getById(id);
        PageDataDTO<Supplier> pageDataDTO = new PageDataDTO<Supplier>(objectPage);
        return Result.success(pageDataDTO);
    }

    @DeleteMapping("/supplier")
    public Result deleteById(Long id) {
        int result = supplierService.deleteById(id);
        if (result > 0) {
            return Result.success("删除成功！");
        } else {
            return Result.error("删除失败！");
        }
    }

    @PostMapping("/supplier/update")
    public Result edit(@Validated Supplier supplier, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            Result error;
            error = Result.error(0, Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
            return error;
        }

        int result = supplierService.edit(supplier);
        if (result > 0) {
            return Result.success("修改成功!");
        } else if (result == 0) {
            return Result.error("名称重复，修改失败！");
        } else {
            return Result.error("修改失败!");
        }
    }
}
