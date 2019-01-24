package com.exx.dzj.mapper.purchase;

import com.exx.dzj.entity.purchase.PurchaseReceiptsDetailsBean;
import com.exx.dzj.entity.purchase.PurchaseReceiptsDetailsBeanExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PurchaseReceiptsDetailsBeanMapper {
    long countByExample(PurchaseReceiptsDetailsBeanExample example);

    int deleteByExample(PurchaseReceiptsDetailsBeanExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(PurchaseReceiptsDetailsBean record);

    int insertSelective(PurchaseReceiptsDetailsBean record);

    List<PurchaseReceiptsDetailsBean> selectByExample(PurchaseReceiptsDetailsBeanExample example);

    PurchaseReceiptsDetailsBean selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") PurchaseReceiptsDetailsBean record, @Param("example") PurchaseReceiptsDetailsBeanExample example);

    int updateByExample(@Param("record") PurchaseReceiptsDetailsBean record, @Param("example") PurchaseReceiptsDetailsBeanExample example);

    int updateByPrimaryKeySelective(PurchaseReceiptsDetailsBean record);

    int updateByPrimaryKey(PurchaseReceiptsDetailsBean record);

    void batchInsertPurchaseReceipts(List<PurchaseReceiptsDetailsBean> purchaseReceiptsDetailsBeans);

    void batchUpdatePurchaseReceiptsDetails(List<PurchaseReceiptsDetailsBean> purchaseReceiptsDetailsBeans);

    void batchDeletePurchaseReceipts(List<Integer> receiptIds);

    List<PurchaseReceiptsDetailsBean> queryPurchaseReceviptDetailList(String purchaseCode);
}