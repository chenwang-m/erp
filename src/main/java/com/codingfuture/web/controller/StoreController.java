package com.codingfuture.web.controller;

import com.alibaba.fastjson.JSON;
import com.codingfuture.dto.PageDataDTO;
import com.codingfuture.entity.GoodsType;
import com.codingfuture.entity.Store;
import com.codingfuture.entity.StoreAlert;
import com.codingfuture.service.StoreService;
import com.codingfuture.util.Message;
import com.codingfuture.util.Result;
import com.codingfuture.util.ResultMsg;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/v2")
public class StoreController {

    @Autowired
    private StoreService storeService;

    // 查询所有商品分类
    @GetMapping("/store/listByPage")
    public Result storeList(int page, int rows, Store store) {
        Page<GoodsType> ePage = PageHelper.startPage(page, rows);
        storeService.findAllStore(store);
        PageDataDTO<GoodsType> goodsTypePageDataDTO = new PageDataDTO<>(ePage);
        return Result.success(goodsTypePageDataDTO);
    }

    // 增加
    @PostMapping("/store/add")
    public ResultMsg storeAdd(@Validated Store store, BindingResult result) {
        if (result.hasErrors()) {
            ResultMsg error;
            error = ResultMsg.error(0, Objects.requireNonNull(result.getFieldError()).getDefaultMessage());
            return error;
        } else if (storeService.storeAdd(store) == -1) {
            return ResultMsg.error(0, "输入内容不能与已有内容重复");
        }
        storeService.storeAdd(store);
        return ResultMsg.SUCCESS(200, "操作成功");

    }

    // 删除
    @DeleteMapping("/store")
    public ResultMsg storeDel(Long id) {
        int result = storeService.storeDel(id);
        return ResultMsg.SUCCESS(200, "操作成功");
    }

    //编辑之前查询
    @GetMapping("/store/selectone")
    public Result storeUpdateById(Long id) {
        Store store = storeService.storeUpdateById(id);
        return Result.success(store);

    }

    //编辑
    @PostMapping("/store/update")
    public ResultMsg storeUpdate(@Validated Store store, BindingResult result) {
        if (result.hasErrors()) {
            ResultMsg error;
            error = ResultMsg.error(0, Objects.requireNonNull(result.getFieldError()).getDefaultMessage());
            return error;
        } else if (storeService.storeUpdate(store) == -1) {
            return ResultMsg.error(0, "输入内容不能与已有内容重复");
        }
        storeService.storeUpdate(store);
        return ResultMsg.SUCCESS(200, "操作成功");
    }

    //    短信预警显示列表
    @GetMapping("/storealert")
    public Result alert(int page, int rows) {
        Page<StoreAlert> objectPage = PageHelper.startPage(page, rows);
        storeService.getStoreAlertList();
        PageDataDTO<StoreAlert> pageDataDTO = new PageDataDTO<>(objectPage);
        return Result.success(pageDataDTO);
    }

    //发送短信
    @GetMapping("/storealert/send")
    public Result sendMessage() {
        String messageText = storeService.sendMessage();
        System.err.println(messageText);
        Map<String, String> map = new HashMap<>(16);
        map.put("data", messageText);
        Message.viewMessage("18341232510", JSON.toJSONString(map));
        return Result.success("短信已发送，请注意查收！");
    }
}
