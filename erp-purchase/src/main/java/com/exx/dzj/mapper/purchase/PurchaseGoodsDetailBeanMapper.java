package com.exx.dzj.mapper.purchase;

import com.exx.dzj.entity.purchase.PurchaseGoodsDetailBean;
import com.exx.dzj.entity.purchase.PurchaseGoodsDetailBeanExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PurchaseGoodsDetailBeanMapper {
    long countByExample(PurchaseGoodsDetailBeanExample example);

    int deleteByExample(PurchaseGoodsDetailBeanExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(PurchaseGoodsDetailBean record);

    int insertSelective(PurchaseGoodsDetailBean record);

    List<PurchaseGoodsDetailBean> selectByExample(PurchaseGoodsDetailBeanExample example);

    PurchaseGoodsDetailBean selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") PurchaseGoodsDetailBean record, @Param("example") PurchaseGoodsDetailBeanExample example);

    int updateByExample(@Param("record") PurchaseGoodsDetailBean record, @Param("example") PurchaseGoodsDetailBeanExample example);

    int updateByPrimaryKeySelective(PurchaseGoodsDetailBean record);

    int updateByPrimaryKey(PurchaseGoodsDetailBean record);
}