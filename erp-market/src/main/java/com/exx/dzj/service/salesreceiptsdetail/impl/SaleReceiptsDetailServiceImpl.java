package com.exx.dzj.service.salesreceiptsdetail.impl;

import com.exx.dzj.annotation.SaleLog;
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
    @SaleLog(operate="批量写入收款信息", saleCode = "#saleCode")
    public void batchInsertSalesReceiptsDeail(List<SaleReceiptsDetails> saleReceiptsDetails, String saleCode) {
        saleReceiptsDetailsMapper.batchInsertSalesReceiptsDeail(saleReceiptsDetails);
    }

    @Override
    @SaleLog(operate="批量修改收款信息", saleCode = "#saleCode")
    public void batchUpdateSalesReceiptsDeail(List<SaleReceiptsDetails> saleReceiptsDetails, String saleCode) {
        saleReceiptsDetailsMapper.batchUpdateSalesReceiptsDeail(saleReceiptsDetails);
    }

    @Override
    @SaleLog(operate="批量删除收款信息", saleCode = "#saleCode")
    public void batchDeleteSalesReceiptsDeail(List<Integer> srdIds, String saleCode) {
        saleReceiptsDetailsMapper.batchDeleteSalesReceiptsDeail(srdIds);
    }

    @Override
    public List<SaleReceiptsDetails> querySaleReceviptDetailList(String saleCode) {
        return saleReceiptsDetailsMapper.querySaleReceviptDetailList(saleCode);
    }

    @Override
    @SaleLog(operate="保存物流信息", saleCode = "#saleCode")
    public void addLogisticsInfo(LogisticsInfo logisticsInfo, String saleCode) {
        saleReceiptsDetailsMapper.addLogisticsInfo(logisticsInfo);
    }

    @Override
    @SaleLog(operate="修改物流信息", saleCode = "#saleCode")
    public void updateLogisticsInfo(LogisticsInfo logisticsInfo, String saleCode) {
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

    @Override
    public void insertImportReceiptData(SaleReceiptsDetails bean) {
        saleReceiptsDetailsMapper.insertImportReceiptData(bean);
    }
}
