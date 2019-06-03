package com.exx.dzj.service.salesgoodsdetail.impl;

import com.exx.dzj.entity.market.LogisticsInfo;
import com.exx.dzj.entity.market.SaleGoodsDetailBean;
import com.exx.dzj.mapper.market.SaleGoodsDetailBeanMapper;
import com.exx.dzj.service.salesgoodsdetail.SalesGoodsDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author yangyun
 * @create 2019-01-10-16:18
 */
@Component
public class SalesGoodsDetailServiceImpl implements SalesGoodsDetailService {

    @Autowired
    private SaleGoodsDetailBeanMapper saleGoodsDetailBeanMapper;

    @Override
    public void batchInsertSalesGoodsDetail(List<SaleGoodsDetailBean> goodsDetailBeanList) {
        saleGoodsDetailBeanMapper.batchInsertSalesGoodsDetail(goodsDetailBeanList);
    }

    @Override
    public void batchUpdateSalesGoodsDetail(List<SaleGoodsDetailBean> goodsDetailBeanList) {
        saleGoodsDetailBeanMapper.batchUpdateSalesGoodsDetail(goodsDetailBeanList);
    }

    @Override
    public void batchDeleteSalesGoodsDetail(List<Integer> sgbIds) {
        saleGoodsDetailBeanMapper.batchDeleteSalesGoodsDetail(sgbIds);
    }

    @Override
    public SaleGoodsDetailBean querySaleGoodsDetail(SaleGoodsDetailBean bean) {
        return saleGoodsDetailBeanMapper.querySaleGoodsDetail(bean);
    }

    @Override
    public List<SaleGoodsDetailBean> queryGoodsForStock(LogisticsInfo logisticsInfo) {
        return saleGoodsDetailBeanMapper.queryGoodsForStock(logisticsInfo);
//        return null;
    }


}
