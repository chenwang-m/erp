package com.codingfuture.service.impl;

import com.codingfuture.entity.GoodsType;
import com.codingfuture.mapper.GoodsTypeMapper;
import com.codingfuture.service.GoodsTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsTypeServiceImpl implements GoodsTypeService {
    @Autowired
    private GoodsTypeMapper goodsTypeMapper;

    @Override
    public List<GoodsType> findAllGoodsType(GoodsType goodsType) {
        return goodsTypeMapper.findAllGoodsType(goodsType);
    }

    @Override
    public int goodsTypeAdd(GoodsType goodsType) {

        if (!isExistName(goodsType.getName())) {
            return -1;
        } else {
            return goodsTypeMapper.goodsTypeAdd(goodsType);
        }
    }
    @Override
    public boolean isExistName(String name) {
        boolean flag = true;
        if (goodsTypeMapper.findGoodsType().contains(name)) {
            flag = false;
        }
        return flag;
    }

    @Override
    public int goodsTypeDel(Long id) {
        int goodsTypeDel = goodsTypeMapper.goodsTypeDel(id);
        return goodsTypeDel;
    }

    @Override
    public GoodsType goodsTypeUpdateById(Long id) {
        return goodsTypeMapper.goodsTypeUpdateById(id);

    }

    @Override
    public int goodsTypeUpdate(GoodsType goodsType) {
        if (!isExistName(goodsType.getName())) {
            return -1;
        } else {
            return goodsTypeMapper.goodsTypeUpdate(goodsType);
        }

    }


}
