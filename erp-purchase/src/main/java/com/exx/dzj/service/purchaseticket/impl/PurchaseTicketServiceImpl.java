package com.exx.dzj.service.purchaseticket.impl;

import com.exx.dzj.entity.purchase.PurchaseHistoryInfo;
import com.exx.dzj.entity.purchase.PurchaseInfo;
import com.exx.dzj.entity.purchase.PurchaseInfoExample;
import com.exx.dzj.entity.purchase.PurchaseQuery;
import com.exx.dzj.mapper.purchase.PurchaseInfoMapper;
import com.exx.dzj.service.purchaseticket.PurchaseTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author yangyun
 * @create 2019-01-12-16:04
 */
@Component
public class PurchaseTicketServiceImpl implements PurchaseTicketService {

    @Autowired
    private PurchaseInfoMapper purchaseInfoMapper;

    @Override
    public long countByExample(PurchaseInfoExample example) {
        return purchaseInfoMapper.countByExample(example);
    }

    @Override
    public void savePurchaseTicket(PurchaseInfo purchaseInfo) {
        purchaseInfoMapper.insertSelective(purchaseInfo);
    }

    @Override
    public List<PurchaseInfo> queryPurchaseTickets(PurchaseQuery query) {
        return purchaseInfoMapper.queryPurchaseTickets(query);
    }

    @Override
    public PurchaseInfo queryPurchaseTicketDetail(Integer id) {
        return purchaseInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public void updatePurchaseTicket(PurchaseInfo purchaseInfo) {
        purchaseInfoMapper.updateByPrimaryKeySelective(purchaseInfo);
    }

    @Override
    public void deletePurchaseTicket(Integer id) {
        purchaseInfoMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<PurchaseHistoryInfo> queryPurchaseHistoryRecord(String stockCode) {
        return purchaseInfoMapper.queryPurchaseHistoryRecord(stockCode);
    }

    @Override
    public BigDecimal sumPurchaseSalesOnYear() {
        return purchaseInfoMapper.sumPurchaseSalesOnYear();
    }
}
