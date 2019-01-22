package com.exx.dzj.service.salesreceiptsdetail;

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
    void batchInsertSalesReceiptsDeail(List<SaleReceiptsDetails> saleReceiptsDetails);

    void batchUpdateSalesReceiptsDeail(List<SaleReceiptsDetails> saleReceiptsDetails);

    void batchDeleteSalesReceiptsDeail(List<Integer> srdIds);

    List<SaleReceiptsDetails> querySaleReceviptDetailList(String saleCode);
}
