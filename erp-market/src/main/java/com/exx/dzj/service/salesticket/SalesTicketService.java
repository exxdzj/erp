package com.exx.dzj.service.salesticket;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.exx.dzj.entity.market.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @author yangyun
 * @create 2019-01-08-10:54
 * 销售单
 */
public interface SalesTicketService extends IService<SaleInfo> {

    long countByExample(SaleInfoExample example);

    void saveSaleReceiptsDetails(SaleReceiptsDetails saleReceiptsDetails);

    void saveSaleInfo(SaleInfo saleInfo);

    List<SaleInfo> querySalesTicketList(SaleInfoQuery query);

    List<SaleInfo> getSalesTicketList(SaleInfoQuery query, QueryWrapper queryWrapper);

    SaleInfo querySalesTicketById(Integer id);

    SaleInfo querySalesTicketForAndroid(Integer id);

    List<SaleInfo> printSalesTicket(List<String> saleCodes);

    void updateSalesTicketById(SaleInfo saleInfo);

    void deleteSaleinfo(Integer id);

    void upateSaleTicketStatus(String saleCode);

    void logisticsInfoDel (Integer id);

    List<SaleInfo> querySumSalesOnDay();

    List<SaleInfo> querySumSalesOnMonth();

    List<SaleInfo> queryAdditionalSumSalesOnDay();

    List<SaleInfo> queryAdditionalSumSalesOnMonth();

    List<SaleInfo> querySumSalesOnYear();

    Map<String, Object> queryYearGrowth();

    List<SaleInfo> querySalesTop(String data);

    List<SaleInfo> salesUncollectedTop();

    List<SaleInfo> querySalesTicketTop ();

    List<SaleInfo> queryCustomerSalesToday (SaleInfo saleInfo);

    List<SaleGoodsTop> querySaleGoodsTop (String type);

    List<SaleInfo> querySubordinateCompanySelect();

    int querySaleBySaleCode(String saleCode);

    void updateSalesmanSubordinateCompany(SaleInfo saleInfo);

    List<SaleListInfo> querySalesListForIds(SaleInfoQuery query, QueryWrapper queryWrapper);

    List<SaleListInfo> querySalesListForIds2(SaleInfoQuery query, QueryWrapper queryWrapper);

    List<SaleListInfo> exportSaleList (SaleInfoQuery query, QueryWrapper queryWrapper);

    int updatReceiptStatus (SaleInfo saleInfo);

    void updateReceivableAccoun (String saleCode, BigDecimal receivableAccoun);
    void batchInsertLogistics (List<LogisticsInfo> list);
}
