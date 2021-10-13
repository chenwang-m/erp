package com.codingfuture.service.impl;

import com.codingfuture.dto.ReturnOrderDetailDTO;
import com.codingfuture.dto.ReturnOrdersWithReturnOrderDetailsDTO;
import com.codingfuture.entity.ReturnOrderDetail;
import com.codingfuture.entity.StoreDetail;
import com.codingfuture.entity.StoreOper;
import com.codingfuture.mapper.ReturnOrderDetailMapper;
import com.codingfuture.mapper.ReturnOrdersMapper;
import com.codingfuture.mapper.StoreDetailMapper;
import com.codingfuture.mapper.StoreOperMapper;
import com.codingfuture.service.ReturnOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ReturnOrdersServiceImpl implements ReturnOrdersService {

    @Autowired
    private ReturnOrdersMapper returnOrdersMapper;

    @Autowired
    private ReturnOrderDetailMapper returnOrderDetailMapper;

    @Autowired
    private StoreDetailMapper storeDetailMapper;

    @Autowired
    private StoreOperMapper storeOperMapper;


    @Override
    public void getReturnOrdersList(String state) {
        returnOrdersMapper.selectByState(state);
    }

    @Override
    public int add(ReturnOrdersWithReturnOrderDetailsDTO newReturnOrders) {

//        数据处理
        List<ReturnOrderDetailDTO> returnOrderDetails = newReturnOrders.getReturnOrderDetails();

//        获取 orders 的总价
        Double totalMoney = 0D;
        for (ReturnOrderDetailDTO returnOrderDetail : returnOrderDetails) {
            totalMoney += returnOrderDetail.getMoney();
        }
        newReturnOrders.setTotalmoney(totalMoney);

//        向orders表插入一条数据,并获得自增的主键
        int insertReturnOrdersResult = returnOrdersMapper.insert(newReturnOrders);
        Long returnOrdersUuid = newReturnOrders.getReturnOrdersuuid();

        for (ReturnOrderDetailDTO returnOrderDetail : returnOrderDetails) {
            returnOrderDetailMapper.insert(returnOrderDetail, returnOrdersUuid);
        }

        return insertReturnOrdersResult;
    }

    @Override
    public int doCheck(Long returnOrdersUuid, String operator) {
        return returnOrdersMapper.updateState(returnOrdersUuid, "1", operator);
    }

    @Override
    @Transactional
    public int doInStore(Long returnOrderDetailUuid, Long storeuuid, String operator, Long empUuid) {

//        查询 returnOrderDetail表
        ReturnOrderDetail returnOrderDetail = returnOrderDetailMapper.selectById(returnOrderDetailUuid);

        Long goodsuuid = returnOrderDetail.getGoodsuuid();
        Long num = returnOrderDetail.getNum();

        StoreDetail storeDetail = storeDetailMapper.selectByStoreUuidAndGoodsUuid(storeuuid, goodsuuid);

        if (storeDetail != null) {
            Long stockNum = storeDetail.getNum();
            if (stockNum >= num) {
                Long storeDetailUuid = storeDetail.getUuid();
                Long newNum = stockNum - num;
                storeDetailMapper.updateNumById(newNum, storeDetailUuid);

                StoreOper storeOper = new StoreOper();
                storeOper.setEmpuuid(empUuid);
                storeOper.setStoreuuid(storeuuid);
                storeOper.setGoodsuuid(goodsuuid);
                storeOper.setNum(num);
                storeOper.setType("2");
                storeOperMapper.insert(storeOper);
            } else {
                return 0;
            }
        } else {
            return -1;
        }

        returnOrderDetailMapper.updateById(returnOrderDetailUuid, storeuuid, operator);

        Long returnordersuuid = returnOrderDetail.getReturnordersuuid();
        Integer countState0 = returnOrderDetailMapper.countState0(returnordersuuid);

        if (countState0 == 0) {
            returnOrdersMapper.updateState(returnordersuuid, "3", operator);
        }
        return 1;
    }
}
