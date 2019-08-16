package com.exx.dzj.service.statistics.sales;

import com.exx.dzj.bean.SaleDetailReportQuery;
import com.exx.dzj.entity.bean.CustomerQuery;
import com.exx.dzj.entity.bean.DeptInfoQuery;
import com.exx.dzj.entity.bean.StockInfoQuery;
import com.exx.dzj.entity.bean.UserInfoQuery;
import com.exx.dzj.entity.customer.CustomerSupplierBean;
import com.exx.dzj.entity.statistics.sales.*;

import java.util.List;

/**
 * @author yangyun
 * @create 2019-04-11-14:16
 */
public interface SaleTicketReportService {

    public List<StockBaseReport> statisticsSaleByInventory(StockInfoQuery query);

    List<SalesmanBaseReport> statisticsSaleBySalesMan (UserInfoQuery query);

    List<CustomerBaseReport> querySalesTicketByCust (CustomerQuery query);

    List<SaleDeductionReport> querySalesDeductionBySaleman (UserInfoQuery query);
    List<SaleDeductionReport> queryPartiallySalesDeductionBySaleman (UserInfoQuery query);

    List<DeptSaleReport> selectionDeptInfo (String parentCode);

    List<DeptSaleReport> queryDeptSaleReport (DeptInfoQuery query);

    List<HomePageReport> queryStasticsSalesForYear();

    List<HomePageReport> queryStasticsSalesForMonth();

    List<SaleDetailBaseReport> querySaleDetailList(SaleDetailReportQuery query);

    List<VIPCustomerLevelReport> queryVipCustomerlevelList();
    List<VIPCustomerLevelReport> queryVipCustomerlevelList2(VipCustomerQueryCondition query);

    List<VipCustomerCountReport> queryVipCustomerCount();
}
