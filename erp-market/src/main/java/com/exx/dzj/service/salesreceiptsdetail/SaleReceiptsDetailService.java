package com.exx.dzj.service.salesreceiptsdetail;

import com.exx.dzj.entity.market.LogisticsInfo;
import com.exx.dzj.entity.market.SaleGoodsSelected;
import com.exx.dzj.entity.market.SaleReceiptsDetails;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author yangyun
 * @create 2019-01-11-11:39
 * 销售单关联收款单明细
 */
public interface SaleReceiptsDetailService {

    /**
     * @description 批量添加
     * @author yangyun
     * @date 2019/1/11 0011
     * @param saleReceiptsDetails
     * @return void
     */
    void batchInsertSalesReceiptsDeail(List<SaleReceiptsDetails> saleReceiptsDetails, String saleCode);

    void batchUpdateSalesReceiptsDeail(List<SaleReceiptsDetails> saleReceiptsDetails, String saleCode);

    void batchDeleteSalesReceiptsDeail(List<Integer> srdIds, String saleCode);

    List<SaleReceiptsDetails> querySaleReceviptDetailList(String saleCode);

    void addLogisticsInfo (LogisticsInfo logisticsInfo, String saleCode);

    void updateLogisticsInfo(LogisticsInfo logisticsInfo, String saleCode);

    List<LogisticsInfo> getLogisticsInfo (String saleCode);

    List<SaleGoodsSelected> getSaleGoodsSelected (String saleCode);

    void insertImportReceiptData(SaleReceiptsDetails bean);
}
