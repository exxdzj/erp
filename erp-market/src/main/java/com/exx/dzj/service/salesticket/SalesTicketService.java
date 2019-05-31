package com.exx.dzj.service.salesticket;

import com.exx.dzj.entity.market.SaleInfo;
import com.exx.dzj.entity.market.SaleReceiptsDetails;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @author yangyun
 * @create 2019-01-08-10:54
 * 销售单
 */
public interface SalesTicketService {

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
}
