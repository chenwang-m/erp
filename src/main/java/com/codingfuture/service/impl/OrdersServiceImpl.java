package com.codingfuture.service.impl;

import com.codingfuture.dto.OrderDetailDTO;
import com.codingfuture.dto.OrderWithOrderDetailsDTO;
import com.codingfuture.dto.OrdersWithSupplierNameDTO;
import com.codingfuture.mapper.OrderDetailMapper;
import com.codingfuture.mapper.OrdersMapper;
import com.codingfuture.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    private OrdersMapper ordersMapper;

    @Autowired
    private OrderDetailMapper orderDetailMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int add(OrderWithOrderDetailsDTO newOrders) {

//        数据处理
        List<OrderDetailDTO> orderDetails = newOrders.getOrderDetails();

//        获取 orders 的总价
        Double totalMoney = 0D;
        for (OrderDetailDTO orderDetail : orderDetails) {
            totalMoney += orderDetail.getMoney();
        }
        newOrders.setTotalmoney(totalMoney);

//        向orders表插入一条数据,并获得自增的主键
        int insertOrders = ordersMapper.insert(newOrders);
        Long ordersUuid = newOrders.getOrdersuuid();

//        向orderDetail表插入多条关联数据
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

    @Override
    public List<OrdersWithSupplierNameDTO> getOrdersList(String type, String state) {
        return ordersMapper.selectByTypeAndState(type, state);
    }

    @Override
    public int doCheck(Long ordersUuid, String state, String operator) {
        return ordersMapper.updateState(ordersUuid, state, operator);
    }

    @Override
    public int doStart(Long ordersUuid, String state, String operator) {
        return ordersMapper.updateState(ordersUuid, state, operator);
    }
}
