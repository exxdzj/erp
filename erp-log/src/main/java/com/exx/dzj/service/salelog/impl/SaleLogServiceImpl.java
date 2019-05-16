package com.exx.dzj.service.salelog.impl;

import com.exx.dzj.entity.salelog.SaleLogBean;
import com.exx.dzj.mapper.salelog.SaleLogMapper;
import com.exx.dzj.service.salelog.SaleLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author 天刀
 * @Date 2019/5/15 0015 14:48
 * @Description 记录销售单操作日志
 */
@Slf4j
@Component
public class SaleLogServiceImpl implements SaleLogService {

    @Autowired
    private SaleLogMapper saleLogMapper;

    /**
     * @功能: 记录销售单日志信息
     * @param bean
     */
    @Override
    public void saveSaleLog(SaleLogBean bean) {
        try{
            saleLogMapper.insertSelective(bean);
        } catch(Exception ex) {
            log.error("执行方法{}错误信息{}", SaleLogServiceImpl.class.getName()+".saveSaleLog", ex.getMessage());
        }
    }
}
