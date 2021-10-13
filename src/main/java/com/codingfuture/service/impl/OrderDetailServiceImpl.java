package com.codingfuture.service.impl;

import com.codingfuture.entity.OrderDetail;
import com.codingfuture.entity.StoreDetail;
import com.codingfuture.entity.StoreOper;
import com.codingfuture.mapper.OrderDetailMapper;
import com.codingfuture.mapper.OrdersMapper;
import com.codingfuture.mapper.StoreDetailMapper;
import com.codingfuture.mapper.StoreOperMapper;
import com.codingfuture.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {

    @Autowired
    private OrderDetailMapper orderDetailMapper;

    @Autowired
    private StoreDetailMapper storeDetailMapper;

    @Autowired
    private OrdersMapper ordersMapper;

    @Autowired
    private StoreOperMapper storeOperMapper;

    @Override
    @Transactional
    public int doInStore(Long orderDetailUuid, Long storeUuid, String operator,Long empUuid) {

//        查询order_detail表 获取store_detail表需要的数据
        OrderDetail orderDetail = orderDetailMapper.selectByUuid(orderDetailUuid);
        if (orderDetail == null) {
            throw new RuntimeException();
        }
        Long goodsuuid = orderDetail.getGoodsuuid();
        Long num = orderDetail.getNum();

//        查询store_detail表 在该仓库是否有该商品的数据
        StoreDetail storeDetail = storeDetailMapper.selectByStoreUuidAndGoodsUuid(storeUuid, goodsuuid);

        if (storeDetail == null) {
//            为null时 表示没有该数据 需要insert
            storeDetail = new StoreDetail();
            storeDetail.setStoreuuid(storeUuid);
            storeDetail.setGoodsuuid(goodsuuid);
            storeDetail.setNum(num);

            storeDetailMapper.insert(storeDetail);
        } else {
//            不为null 表示已有该条数据 需要update
            Long oldNum = storeDetail.getNum();
            Long newNum = oldNum + num;
            Long detailUuid = storeDetail.getUuid();
            storeDetailMapper.updateNumById(newNum, detailUuid);
        }

//        向store_oper表插入数据
        StoreOper storeOper = new StoreOper();
        storeOper.setUuid(empUuid);
        storeOper.setStoreuuid(storeUuid);
        storeOper.setGoodsuuid(goodsuuid);
        storeOper.setNum(num);
        storeOper.setType("1");
        storeOperMapper.insert(storeOper);

//        更新order_detail表
        int updateResult = orderDetailMapper.updateStoreUuid(orderDetailUuid, storeUuid,operator);
        if (updateResult < 0) {
            throw new RuntimeException();
        }

//        查询order_detail表中orders_uuid相同的数据中state是0的数据个数
        Long ordersUuid = orderDetail.getOrdersuuid();
        String count = orderDetailMapper.selectByOrdersIdWithState(ordersUuid);
        if ("0".equals(count)) {
            ordersMapper.updateState(ordersUuid, "3", operator);
        }
        return 1;
    }
}
