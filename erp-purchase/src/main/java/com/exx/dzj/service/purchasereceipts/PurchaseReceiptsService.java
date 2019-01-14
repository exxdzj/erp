package com.exx.dzj.service.purchasereceipts;

import com.exx.dzj.entity.purchase.PurchaseReceiptsDetailsBean;

import java.util.List;

/**
 * @author yangyun
 * @create 2019-01-12-16:06
 */
public interface PurchaseReceiptsService {

    void batchInsertPurchaseReceipts(List<PurchaseReceiptsDetailsBean> purchaseReceiptsDetailsBeans);

    void batchUpdatePurchaseReceiptsDetails(List<PurchaseReceiptsDetailsBean> purchaseReceiptsDetailsBeans);

    void batchDeletePurchaseReceipts(List<Integer> receiptIds);
}
