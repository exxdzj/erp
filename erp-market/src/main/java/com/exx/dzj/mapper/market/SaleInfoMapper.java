package com.exx.dzj.mapper.market;

import com.exx.dzj.entity.market.*;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface SaleInfoMapper {
    long countByExample(SaleInfoExample example);

    int deleteByExample(SaleInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SaleInfo record);

    int insertSelective(SaleInfo record);

    List<SaleInfo> querySalesTicketList(SaleInfo saleInfo);

    SaleInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SaleInfo record, @Param("example") SaleInfoExample example);

    int updateByExample(@Param("record") SaleInfo record, @Param("example") SaleInfoExample example);

    int updateByPrimaryKeySelective(SaleInfo record);

    int updateByPrimaryKey(SaleInfo record);

    void logisticsInfoDel(@Param("id") Integer id);

    BigDecimal querySumSalesOnDay();

    BigDecimal querySumSalesOnMonth();

    BigDecimal queryAdditionalSumSalesOnDay();

    BigDecimal queryAdditionalSumSalesOnMonth();

    BigDecimal querySumSalesOnYear();

    List<SaleInfo> querySalesTop();

    List<SaleInfo> salesUncollectedTop();

    List<SaleInfo> querySalesTicketTop();

    List<SaleInfo> queryCustomerSalesToday (SaleInfo saleInfo);

    List<SaleGoodsTop> querySaleGoodsTop(@Param("type") String type);

    List<SaleInfo> querySubordinateCompanySelect();
}