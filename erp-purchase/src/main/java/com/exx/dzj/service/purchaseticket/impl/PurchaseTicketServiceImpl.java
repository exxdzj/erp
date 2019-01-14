package com.exx.dzj.service.purchaseticket.impl;

import com.exx.dzj.entity.purchase.PurchaseGoodsDetailBean;
import com.exx.dzj.entity.purchase.PurchaseInfo;
import com.exx.dzj.mapper.purchase.PurchaseInfoMapper;
import com.exx.dzj.service.purchaseticket.PurchaseTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
    public void savePurchaseTicket(PurchaseInfo purchaseInfo) {
        purchaseInfoMapper.insertSelective(purchaseInfo);
    }

    @Override
    public List<PurchaseInfo> queryPurchaseTickets(PurchaseInfo purchaseInfo) {
        return purchaseInfoMapper.queryPurchaseTickets(purchaseInfo);
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
}
