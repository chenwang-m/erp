package com.codingfuture.web.controller;

import com.codingfuture.dto.PageDataDTO;
import com.codingfuture.entity.GoodsType;
import com.codingfuture.service.GoodsTypeService;
import com.codingfuture.util.Result;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/v2")
public class GoodsTypeController {
    @Autowired
    private GoodsTypeService goodsTypeService;

    // 查询所有商品分类
    @GetMapping("/goodstype/listByPage")
    public Result goodsTypeList(int page, int rows, GoodsType goodsType) {
        Page<GoodsType> ePage = PageHelper.startPage(page, rows);
        goodsTypeService.findAllGoodsType(goodsType);
        PageDataDTO<GoodsType> goodsTypePageDataDTO = new PageDataDTO<GoodsType>(ePage);
        return Result.success(goodsTypePageDataDTO);
    }

    // 增加
    @PostMapping("/goodstype/add")
    public Result goodsTypeAdd(@Validated GoodsType goodsType, BindingResult result) {
        if(result.hasErrors()){
            Result error;
            error = Result.error(0,Objects.requireNonNull(result.getFieldError()).getDefaultMessage());
           return error;
        }
        else if(goodsTypeService.goodsTypeAdd(goodsType) == -1){
            return Result.error(0,"输入内容不能与已有内容重复");
        }
        goodsTypeService.goodsTypeAdd(goodsType);
        return Result.success("操作成功");

    }

    // 删除
    @DeleteMapping("/goodstype")
    public Result goodsTypeDel(Long id) {
        System.out.println(id);
        int result = goodsTypeService.goodsTypeDel(id);
        return Result.success();
    }

    //修改之前查询
    @GetMapping("/goodstype/selectone")
    public Result goodsTypeUpdateById(Long id) {
//        Page<GoodsType> ePage = PageHelper.startPage(1, 1);
        GoodsType goodsTypes = goodsTypeService.goodsTypeUpdateById(id);
//        PageData<GoodsType> goodsTypePageData = new PageData<GoodsType>(ePage);

        return Result.success(goodsTypes);
    }
    //修改
    @PostMapping("/goodstype/update")
    public Result goodsTypeUpdate(@Validated GoodsType goodsType, BindingResult result){
        if(result.hasErrors()){
            Result error;
            error = Result.error(0,Objects.requireNonNull(result.getFieldError()).getDefaultMessage());
            return error;
        }
        else if(goodsTypeService.goodsTypeUpdate(goodsType) == -1){
            return Result.error(0,"输入内容不能与已有内容重复");
        }
        goodsTypeService.goodsTypeUpdate(goodsType);
        return Result.success();

    }
}
