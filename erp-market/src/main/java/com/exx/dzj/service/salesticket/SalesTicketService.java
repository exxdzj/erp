package com.exx.dzj.service.salesticket;

import com.exx.dzj.entity.market.SaleGoodsTop;
import com.exx.dzj.entity.market.SaleInfo;
import com.exx.dzj.entity.market.SaleInfoExample;
import com.exx.dzj.entity.market.SaleReceiptsDetails;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author yangyun
 * @create 2019-01-08-10:54
 * 销售单
 */
public interface SalesTicketService {

    long countByExample(SaleInfoExample example);

    void saveSaleReceiptsDetails(SaleReceiptsDetails saleReceiptsDetails);

    void saveSaleInfo(SaleInfo saleInfo);

    List<SaleInfo> querySalesTicketList(SaleInfo saleInfo);

    SaleInfo querySalesTicketById(Integer id);

    void updateSalesTicketById(SaleInfo saleInfo);

    void deleteSaleinfo(Integer id);

    void logisticsInfoDel (Integer id);

    BigDecimal querySumSalesOnDay();

    BigDecimal querySumSalesOnMonth();

    BigDecimal queryAdditionalSumSalesOnDay();

    BigDecimal queryAdditionalSumSalesOnMonth();

    BigDecimal querySumSalesOnYear();

    List<SaleInfo> querySalesTop();

    List<SaleInfo> salesUncollectedTop();

    List<SaleInfo> querySalesTicketTop ();

    List<SaleInfo> queryCustomerSalesToday (SaleInfo saleInfo);

    List<SaleGoodsTop> querySaleGoodsTop (String type);
}
