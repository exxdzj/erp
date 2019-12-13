package com.exx.dzj.service.salesgoodsdetail.impl;

import com.exx.dzj.annotation.SaleLog;
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
    @SaleLog(operate = "批量保存销售单商品数据", saleCode = "#saleCode")
    public void batchInsertSalesGoodsDetail(List<SaleGoodsDetailBean> goodsDetailBeanList, String saleCode) {
        saleGoodsDetailBeanMapper.batchInsertSalesGoodsDetail(goodsDetailBeanList);
    }

    @Override
    @SaleLog(operate = "批量修改销售单商品数据", saleCode = "#saleCode")
    public void batchUpdateSalesGoodsDetail(List<SaleGoodsDetailBean> goodsDetailBeanList, String saleCode) {
        saleGoodsDetailBeanMapper.batchUpdateSalesGoodsDetail(goodsDetailBeanList);
    }

    @Override
    @SaleLog(operate = "批量删除销售单商品数据", saleCode = "#saleCode")
    public void batchDeleteSalesGoodsDetail(List<Integer> sgbIds, String saleCode) {
        saleGoodsDetailBeanMapper.batchDeleteSalesGoodsDetail(sgbIds);
    }

    @Override
    public SaleGoodsDetailBean querySaleGoodsDetail(SaleGoodsDetailBean bean) {
        return saleGoodsDetailBeanMapper.querySaleGoodsDetail(bean);
    }

    @Override
    public List<SaleGoodsDetailBean> queryGoodsForStock(String saleCode, List<String> stockCodeList) {
        return saleGoodsDetailBeanMapper.queryGoodsForStock(saleCode, stockCodeList);
//        return null;
    }

    @Override
    public void batchUpdateSalesGoodsSubtractStatus(List<Integer> ids) {
        saleGoodsDetailBeanMapper.batchUpdateSalesGoodsSubtractStatus(ids);
    }

    @Override
    @SaleLog(operate = "保存销售单商品数据")
    public void insertGoodsInfo(SaleGoodsDetailBean bean) {
        saleGoodsDetailBeanMapper.insertGoodsInfo(bean);
    }


}
