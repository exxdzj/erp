package com.exx.dzj.mapper.statistics.sales;

import com.exx.dzj.entity.bean.CustomerQuery;
import com.exx.dzj.entity.bean.DeptInfoQuery;
import com.exx.dzj.entity.bean.StockInfoQuery;
import com.exx.dzj.entity.bean.UserInfoQuery;
import com.exx.dzj.entity.customer.CustomerSupplierBean;
import com.exx.dzj.entity.statistics.sales.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author yangyun
 * @create 2019-04-11-14:18
 */
public interface SaleTicketReportMapper {
    List<StockTypeReport> statisticsSaleByInventory(StockInfoQuery query);

    List<UserInfoReport> statisticsSaleBySalesMan (UserInfoQuery query);

    List<CustomerReport> querySalesTicketByCust(CustomerQuery query);

    List<SaleDeductionReport> querySalesDeductionBySaleman(UserInfoQuery query);

    List<DeptSaleReport> selectionDeptInfo(@Param("parentCode") String parentCode);

    List<DeptSaleReport> queryDeptSaleReport(DeptInfoQuery query);
}
