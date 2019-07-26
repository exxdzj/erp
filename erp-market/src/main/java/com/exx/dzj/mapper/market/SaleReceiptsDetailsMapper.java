package com.exx.dzj.mapper.market;

import com.exx.dzj.entity.market.LogisticsInfo;
import com.exx.dzj.entity.market.SaleGoodsSelected;
import com.exx.dzj.entity.market.SaleReceiptsDetails;
import com.exx.dzj.entity.market.SaleReceiptsDetailsExample;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

public interface SaleReceiptsDetailsMapper {
    long countByExample(SaleReceiptsDetailsExample example);

    int deleteByExample(SaleReceiptsDetailsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SaleReceiptsDetails record);

    int insertSelective(SaleReceiptsDetails record);

    List<SaleReceiptsDetails> selectByExample(SaleReceiptsDetailsExample example);

    SaleReceiptsDetails selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SaleReceiptsDetails record, @Param("example") SaleReceiptsDetailsExample example);

    int updateByExample(@Param("record") SaleReceiptsDetails record, @Param("example") SaleReceiptsDetailsExample example);

    int updateByPrimaryKeySelective(SaleReceiptsDetails record);

    int updateByPrimaryKey(SaleReceiptsDetails record);

    void batchInsertSalesReceiptsDeail(List<SaleReceiptsDetails> saleReceiptsDetails);

    void batchUpdateSalesReceiptsDeail(List<SaleReceiptsDetails> saleReceiptsDetails);

    void batchDeleteSalesReceiptsDeail(List<Integer> srdIds);

    List<SaleReceiptsDetails> querySaleReceviptDetailList(String saleCode);

    void addLogisticsInfo(LogisticsInfo logisticsInfo);

    void updateLogisticsInfo(LogisticsInfo logisticsInfo);

    List<LogisticsInfo> getLogisticsInfo(@Param("saleCode") String saleCode);

    List<SaleGoodsSelected> getSaleGoodsSelected(@Param("saleCode") String saleCode);

    void insertImportReceiptData(SaleReceiptsDetails bean);
}