package com.exx.dzj.service.purchasegoods.impl;

import com.exx.dzj.entity.purchase.PurchaseGoodsDetailBean;
import com.exx.dzj.mapper.purchase.PurchaseGoodsDetailBeanMapper;
import com.exx.dzj.service.purchasegoods.PurchaseGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author yangyun
 * @create 2019-01-12-16:07
 * 采购单商品
 */
@Component
public class PurchaseGoodsServiceImpl implements PurchaseGoodsService {

    @Autowired
    private PurchaseGoodsDetailBeanMapper purchaseGoodsDetailBeanMapper;

    @Override
    public void batchInsertPurchaseGoods(List<PurchaseGoodsDetailBean> purchaseGoodsDetailBeans) {
        purchaseGoodsDetailBeanMapper.batchInsertPurchaseGoods(purchaseGoodsDetailBeans);
    }

    @Override
    public void batchUpdatePurchaseGoodsDetails(List<PurchaseGoodsDetailBean> purchaseGoodsDetailBeans) {
        purchaseGoodsDetailBeanMapper.batchUpdatePurchaseGoodsDetails(purchaseGoodsDetailBeans);
    }

    @Override
    public void batchDeletePurchaseGoods(List<Integer> goodIds) {
        purchaseGoodsDetailBeanMapper.batchDeletePurchaseGoods(goodIds);
    }

    @Override
    public PurchaseGoodsDetailBean queryGoodsPriceAndNum(String stockCode, String stockAddressCode) {
        return purchaseGoodsDetailBeanMapper.queryGoodsPriceAndNum(stockCode, stockAddressCode);
    }
}
