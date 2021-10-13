package com.codingfuture.service.impl;

import com.codingfuture.dto.MarketOrderDTO;
import com.codingfuture.dto.OrderDetailDTO;
import com.codingfuture.dto.OrdersWithSupplierNameDTO;
import com.codingfuture.entity.OrderDetail;
import com.codingfuture.entity.StoreAlert;
import com.codingfuture.entity.StoreDetail;
import com.codingfuture.mapper.*;
import com.codingfuture.service.OrdersQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrdersQueryServiceImpl implements OrdersQueryService {
    @Autowired
    private OrdersQueryMapper ordersQueryMapper;
    @Autowired
    private OrdersMapper ordersMapper;
    @Autowired
    private OrderDetailMapper orderDetailMapper;
    @Autowired
    private StoreDetailMapper storeDetailMapper;
    @Autowired
    private StoreAlertMapper storeAlertMapper;

    @Override
    public List<OrdersWithSupplierNameDTO> ordersQuery(String state) {
        return ordersQueryMapper.ordersQuery(state);
    }

    // 新增销售申请
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int add(MarketOrderDTO marketOrders) {
//        数据处理
        List<OrderDetailDTO> orderDetails = marketOrders.getMarketDetails();

        //        获取 orders 的总价
        Double totalMoney = 0D;
        for (OrderDetailDTO orderDetail : orderDetails) {
            totalMoney += orderDetail.getMoney();
        }
        marketOrders.setTotalmoney(totalMoney);
        //        向orders表插入一条数据,并获得自增的主键
        int insertOrders = ordersQueryMapper.add(marketOrders);
        Long ordersUuid = marketOrders.getOrdersuuid();

        for (OrderDetailDTO orderDetail : orderDetails) {
            int insertDetail = orderDetailMapper.insertDetail(orderDetail, ordersUuid);

//            每次执行后对结果进行判断
//            结果小于零时，表示插入失败，抛出异常，回滚事务
            if (insertDetail <= 0) {
                throw new RuntimeException();
            }
        }
        return insertOrders;
    }

    // 订单出库验证
    @Override
    public int doOutStore(Long orderDetailUuid, Long storeUuid, String operator) {
        //        查询order_detail 获取store_detail表需要的数据
        OrderDetail orderDetail = orderDetailMapper.selectByUuid(orderDetailUuid);

        Long goodsuuid = orderDetail.getGoodsuuid();
        String goodsname = orderDetail.getGoodsname();
        Long num = orderDetail.getNum();

//        查询store_detail库存表 在该仓库是否有该商品的数据
        StoreDetail storeDetail = storeDetailMapper.selectByStoreUuidAndGoodsUuid(storeUuid, goodsuuid);
        if (storeDetail != null) {
            Long stockNum = storeDetail.getNum();
            if (stockNum >= num) {
                Long storeDetailUuid = storeDetail.getUuid();
                Long newNum = stockNum - num;
                storeDetailMapper.updateNumById(newNum, storeDetailUuid);
            } else {
                storeAlertMapper.insert(new StoreAlert(goodsname, stockNum, num));
                return 0;
            }
        } else {
            storeAlertMapper.insert(new StoreAlert(goodsname, 0L, num));
            return -1;
        }

//        更新order_detail表
        orderDetailMapper.updateStoreUuid(orderDetailUuid, storeUuid, operator);

//        查询order_detail表中orders_uuid相同的数据中state是0的数据个数
        Long ordersUuid = orderDetail.getOrdersuuid();
        String count = orderDetailMapper.selectByOrdersIdWithState(ordersUuid);
        if ("0".equals(count)) {

            ordersMapper.updateForMarketOut(ordersUuid, operator);
        }
        return 1;

    }

}
