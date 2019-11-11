package com.exx.dzj.service.salesgoodsdetail;

import com.exx.dzj.entity.market.LogisticsInfo;
import com.exx.dzj.entity.market.SaleGoodsDetailBean;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author yangyun
 * @create 2019-01-08-10:57
 * 销售单关联商品明细
 */
public interface SalesGoodsDetailService {

    void batchInsertSalesGoodsDetail(List<SaleGoodsDetailBean> goodsDetailBeanList, String saleCode);

    void batchUpdateSalesGoodsDetail(List<SaleGoodsDetailBean> goodsDetailBeanList, String saleCode);

    void batchDeleteSalesGoodsDetail(List<Integer> sgbIds, String saleCode);

    SaleGoodsDetailBean querySaleGoodsDetail(SaleGoodsDetailBean bean);

    List<SaleGoodsDetailBean> queryGoodsForStock(String saleCode, List<String> stockCodeList);

    void batchUpdateSalesGoodsSubtractStatus(List<Integer> ids);

    void insertGoodsInfo (SaleGoodsDetailBean bean);
}
