package com.codingfuture.service.impl;

import com.codingfuture.entity.Supplier;
import com.codingfuture.mapper.SupplierMapper;
import com.codingfuture.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.List;

@Service
public class SupplierServiceImpl implements SupplierService {

    private static final Integer REPEAT = 0;

    @Autowired
    private SupplierMapper supplierMapper;

    @Override
    @Transactional
    public int add(Supplier supplier, String type) {

        int result = supplierMapper.insert(supplier, type);

        if (isRepeat(supplier.getName())) {
            return result;
        } else {
//            手动回滚事务
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return REPEAT;
        }
    }

    @Override
    public List<Supplier> getList(String type, Supplier supplier) {
        return supplierMapper.select(type, supplier);
    }

    @Override
    public Supplier getById(String id) {
        return supplierMapper.selectById(id);
    }

    @Override
    public int deleteById(Long id) {
        return supplierMapper.deleteById(id);
    }

    @Override
    @Transactional
    public int edit(Supplier supplier) {

        int result = supplierMapper.update(supplier);

        if (isRepeat(supplier.getName())) {
            return result;
        } else {
//            手动回滚事务
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return REPEAT;
        }
    }

    /**
     * 校验supplier表中的name字段是否重复
     *
     * @param name
     * @return boolean true->不重复 false->重复
     */
    public Boolean isRepeat(String name) {
        int i = supplierMapper.countByName(name);
        return i <= 1;
    }
}
