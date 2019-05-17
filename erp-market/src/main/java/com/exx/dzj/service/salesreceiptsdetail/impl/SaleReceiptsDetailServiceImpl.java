package com.exx.dzj.service.salesreceiptsdetail.impl;

import com.exx.dzj.entity.market.LogisticsInfo;
import com.exx.dzj.entity.market.SaleGoodsSelected;
import com.exx.dzj.entity.market.SaleReceiptsDetails;
import com.exx.dzj.mapper.market.SaleReceiptsDetailsMapper;
import com.exx.dzj.service.salesreceiptsdetail.SaleReceiptsDetailService;
import com.sun.org.apache.bcel.internal.generic.DALOAD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author yangyun
 * @create 2019-01-11-11:40
 */
@Component
public class SaleReceiptsDetailServiceImpl implements SaleReceiptsDetailService {
    @Autowired
    private SaleReceiptsDetailsMapper saleReceiptsDetailsMapper;

    @Override
    public void batchInsertSalesReceiptsDeail(List<SaleReceiptsDetails> saleReceiptsDetails) {
        saleReceiptsDetailsMapper.batchInsertSalesReceiptsDeail(saleReceiptsDetails);
    }

    @Override
    public void batchUpdateSalesReceiptsDeail(List<SaleReceiptsDetails> saleReceiptsDetails) {
        saleReceiptsDetailsMapper.batchUpdateSalesReceiptsDeail(saleReceiptsDetails);
    }

    @Override
    public void batchDeleteSalesReceiptsDeail(List<Integer> srdIds) {
        saleReceiptsDetailsMapper.batchDeleteSalesReceiptsDeail(srdIds);
    }

    @Override
    public List<SaleReceiptsDetails> querySaleReceviptDetailList(String saleCode) {
        return saleReceiptsDetailsMapper.querySaleReceviptDetailList(saleCode);
    }

    @Override
    public void addLogisticsInfo(LogisticsInfo logisticsInfo) {
        saleReceiptsDetailsMapper.addLogisticsInfo(logisticsInfo);
    }

    @Override
    public void updateLogisticsInfo(LogisticsInfo logisticsInfo) {
        saleReceiptsDetailsMapper.updateLogisticsInfo(logisticsInfo);
    }

    @Override
    public List<LogisticsInfo> getLogisticsInfo(String saleCode) {
        return saleReceiptsDetailsMapper.getLogisticsInfo(saleCode);
    }

    @Override
    public List<SaleGoodsSelected> getSaleGoodsSelected(String saleCode) {
        return saleReceiptsDetailsMapper.getSaleGoodsSelected(saleCode);
    }
}
