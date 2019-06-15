package com.exx.dzj.service.salesticket;

import com.exx.dzj.entity.market.*;

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

    List<SaleInfo> querySalesTicketList(SaleInfoQuery query);

    SaleInfo querySalesTicketById(Integer id);

    void updateSalesTicketById(SaleInfo saleInfo);

    void deleteSaleinfo(Integer id);

    void logisticsInfoDel (Integer id);

    List<SaleInfo> querySumSalesOnDay();

    List<SaleInfo> querySumSalesOnMonth();

    List<SaleInfo> queryAdditionalSumSalesOnDay();

    List<SaleInfo> queryAdditionalSumSalesOnMonth();

    List<SaleInfo> querySumSalesOnYear();

    List<SaleInfo> querySalesTop(String data);

    List<SaleInfo> salesUncollectedTop();

    List<SaleInfo> querySalesTicketTop ();

    List<SaleInfo> queryCustomerSalesToday (SaleInfo saleInfo);

    List<SaleGoodsTop> querySaleGoodsTop (String type);

    List<SaleInfo> querySubordinateCompanySelect();

    int querySaleBySaleCode(String saleCode);

    void updateSalesmanSubordinateCompany(SaleInfo saleInfo);

    List<SaleListInfo> querySalesListForIds(SaleInfoQuery query);

    List<SaleListInfo> exportSaleList (SaleInfoQuery query);
}
