package com.exx.dzj.mapper.market;

import com.exx.dzj.entity.market.SaleGoodsDetailBean;
import com.exx.dzj.entity.market.SaleGoodsDetailBeanExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SaleGoodsDetailBeanMapper {
    long countByExample(SaleGoodsDetailBeanExample example);

    int deleteByExample(SaleGoodsDetailBeanExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SaleGoodsDetailBean record);

    int insertSelective(SaleGoodsDetailBean record);

    List<SaleGoodsDetailBean> selectByExample(SaleGoodsDetailBeanExample example);

    SaleGoodsDetailBean selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SaleGoodsDetailBean record, @Param("example") SaleGoodsDetailBeanExample example);

    int updateByExample(@Param("record") SaleGoodsDetailBean record, @Param("example") SaleGoodsDetailBeanExample example);

    int updateByPrimaryKeySelective(SaleGoodsDetailBean record);

    int updateByPrimaryKey(SaleGoodsDetailBean record);

    void batchInsertSalesGoodsDetail(List<SaleGoodsDetailBean> saleGoodsDetailBeansList);

    void batchUpdateSalesGoodsDetail(List<SaleGoodsDetailBean> saleGoodsDetailBeansList);

    void batchDeleteSalesGoodsDetail(List<Integer> sgbIds);
}