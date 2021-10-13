package com.codingfuture.service.impl;

import com.codingfuture.dto.GoodsForPurchaseDTO;
import com.codingfuture.entity.Goods;
import com.codingfuture.entity.GoodsType;
import com.codingfuture.mapper.GoodsMapper;
import com.codingfuture.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {
    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public List<Goods> findAllGoods(Goods goods) {

        return goodsMapper.findAllGoods(goods);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int goodsAdd(Goods goods) {
        goodsMapper.goodsAdd(goods);
        if (isExistName(goods.getName())) {
            return 1;
        } else {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return -1;
        }
    }

    @Override
    public boolean isExistName(String name) {
//        if (goodsMapper.findGoods().contains(name)) {
//            flag = false;
//        }
        int count = goodsMapper.countByName(name);

        return count <= 1;
    }


    @Override
    public List<GoodsType> goodsTypeSelectAll() {
        return goodsMapper.goodsTypeSelectAll();
    }

    @Override
    public int goodsDel(Long uuid) {
        int goodsDel = goodsMapper.goodsDel(uuid);
        return goodsDel;
    }

    @Override
    public Goods goodsFindById(Long id) {
        return goodsMapper.goodsFindById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int goodsUpdate(Goods goods) {
        goodsMapper.goodsUpdate(goods);
        if (isExistName(goods.getName())) {
            return 1;
        } else {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return -1;
        }
    }

    @Override
    public List<GoodsForPurchaseDTO> getGoodsListForOrders() {
        return goodsMapper.getGoodsListForOrders();
    }
}
