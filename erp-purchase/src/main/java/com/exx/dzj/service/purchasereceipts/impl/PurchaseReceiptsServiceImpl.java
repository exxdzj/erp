package com.exx.dzj.service.purchasereceipts.impl;

import com.exx.dzj.entity.purchase.PurchaseReceiptsDetailsBean;
import com.exx.dzj.mapper.purchase.PurchaseReceiptsDetailsBeanMapper;
import com.exx.dzj.service.purchasereceipts.PurchaseReceiptsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author yangyun
 * @create 2019-01-12-16:07
 * 采购单收款
 */
@Component
public class PurchaseReceiptsServiceImpl implements PurchaseReceiptsService {

    @Autowired
    private PurchaseReceiptsDetailsBeanMapper purchaseReceiptsDetailsBeanMapper;

    @Override
    public void batchInsertPurchaseReceipts(List<PurchaseReceiptsDetailsBean> purchaseReceiptsDetailsBeans) {
        purchaseReceiptsDetailsBeanMapper.batchInsertPurchaseReceipts(purchaseReceiptsDetailsBeans);
    }

    @Override
    public void batchUpdatePurchaseReceiptsDetails(List<PurchaseReceiptsDetailsBean> purchaseReceiptsDetailsBeans) {
        purchaseReceiptsDetailsBeanMapper.batchUpdatePurchaseReceiptsDetails(purchaseReceiptsDetailsBeans);
    }

    @Override
    public void batchDeletePurchaseReceipts(List<Integer> receiptIds) {
        purchaseReceiptsDetailsBeanMapper.batchDeletePurchaseReceipts(receiptIds);
    }
}
