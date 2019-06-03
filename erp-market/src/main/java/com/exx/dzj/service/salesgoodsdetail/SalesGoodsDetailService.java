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

    void batchInsertSalesGoodsDetail(List<SaleGoodsDetailBean> goodsDetailBeanList);

    void batchUpdateSalesGoodsDetail(List<SaleGoodsDetailBean> goodsDetailBeanList);

    void batchDeleteSalesGoodsDetail(List<Integer> sgbIds);

    SaleGoodsDetailBean querySaleGoodsDetail(SaleGoodsDetailBean bean);

    List<SaleGoodsDetailBean> queryGoodsForStock(LogisticsInfo logisticsInfo);
}
