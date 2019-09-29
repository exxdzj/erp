package com.exx.dzj.service.purchasegoods;

import com.exx.dzj.entity.purchase.PurchaseGoodsDetailBean;

import java.util.List;

/**
 * @author yangyun
 * @create 2019-01-12-16:06
 */
public interface PurchaseGoodsService {

    void batchInsertPurchaseGoods(List<PurchaseGoodsDetailBean> purchaseGoodsDetailBeans);

    void batchUpdatePurchaseGoodsDetails(List<PurchaseGoodsDetailBean> purchaseGoodsDetailBeans);

    void batchDeletePurchaseGoods(List<Integer> goodIds);

    PurchaseGoodsDetailBean queryGoodsPriceAndNum(String stockCode, String stockAddressCode);
}
