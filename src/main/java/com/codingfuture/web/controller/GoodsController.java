package com.codingfuture.web.controller;

import com.codingfuture.dto.PageDataDTO;
import com.codingfuture.entity.Goods;
import com.codingfuture.entity.GoodsType;
import com.codingfuture.service.GoodsService;
import com.codingfuture.util.Result;
import com.codingfuture.util.ResultMsg;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/v2")
public class GoodsController {
    @Autowired
    private GoodsService goodsService;

    // 查询所有商品
    @GetMapping("/goods/listByPage")
    public Result goodsList(int page, int rows, Goods goods) {
        Page<Goods> ePage = PageHelper.startPage(page, rows);
        goodsService.findAllGoods(goods);
        PageDataDTO<Goods> goodsPageDataDTO = new PageDataDTO<Goods>(ePage);
        return Result.success(goodsPageDataDTO);
    }

    // 增加之前选择类型
    @GetMapping("/goods/goodstypelist")
    public Result goodsTypeSelectAll() {
        List<GoodsType> goods = goodsService.goodsTypeSelectAll();
        PageDataDTO<GoodsType> goodsTypePageDataDTO = new PageDataDTO<>(goods);
        return Result.success(goodsTypePageDataDTO);
    }

    // 增加

    @PostMapping("/goods/add")
    public ResultMsg goodsAdd(@Validated Goods goods, BindingResult result) {

        if (goods.getInPrice() <= 0 || goods.getOutPrice() <= 0) {
            return ResultMsg.error(0, "价格不能为负数！");
        }


//        if (result.hasErrors()) {
//            ResultMsg error;
//            error = ResultMsg.error(0, Objects.requireNonNull(result.getFieldError()).getDefaultMessage());
//            return error;
//        } else if (goodsService.goodsAdd(goods) == -1) {
//            return ResultMsg.error(0, "输入内容不能与已有内容重复");
//        }
        int i = goodsService.goodsAdd(goods);
        if (i == 1) {
            return ResultMsg.success();
        } else {
            return ResultMsg.error(0, "输入内容不能与已有内容重复");
        }

    }

    // 删除
    @DeleteMapping("/goods")
    public ResultMsg goodsDel(Long id) {
        int result = goodsService.goodsDel(id);
        System.out.println(id);
//        return result>0?Result.success("操作成功！"):Result.error();
        return ResultMsg.success();

    }

    //修改之前查询
    @GetMapping("/goods/selectone")
    public Result goodsFindById(Long id) {
        Goods goods = goodsService.goodsFindById(id);
//       PageData<Goods> goodsPageData = new PageData<>(goods);
//        return Result.success(goodsPageData);
        return Result.success(goods);
    }

    //修改
    @PostMapping("/goods/update")
    public ResultMsg goodsUpdate(@Validated Goods goods, BindingResult result) {

        if (goods.getInPrice() <= 0 || goods.getOutPrice() <= 0) {
            return ResultMsg.error(0, "价格不能为负数！");
        }

        if (result.hasErrors()) {
            ResultMsg error;
            error = ResultMsg.error(0, Objects.requireNonNull(result.getFieldError()).getDefaultMessage());
            return error;
        } else if (goodsService.goodsUpdate(goods) == -1) {
            return ResultMsg.error(0, "输入内容不能与已有内容重复");
        }
        goodsService.goodsUpdate(goods);
        return ResultMsg.success();

    }

}
