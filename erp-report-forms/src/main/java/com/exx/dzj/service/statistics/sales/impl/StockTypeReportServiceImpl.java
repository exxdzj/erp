package com.exx.dzj.service.statistics.sales.impl;

import com.exx.dzj.bean.SaleDetailReportQuery;
import com.exx.dzj.entity.bean.CustomerQuery;
import com.exx.dzj.entity.bean.DeptInfoQuery;
import com.exx.dzj.entity.bean.StockInfoQuery;
import com.exx.dzj.entity.bean.UserInfoQuery;
import com.exx.dzj.entity.customer.CustomerSupplierBean;
import com.exx.dzj.entity.statistics.sales.*;
import com.exx.dzj.mapper.statistics.sales.SaleTicketReportMapper;
import com.exx.dzj.service.statistics.sales.SaleTicketReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yangyun
 * @create 2019-04-11-14:17
 */
@Service("stockTypeReportService")
public class StockTypeReportServiceImpl implements SaleTicketReportService {

    @Autowired
    private SaleTicketReportMapper stockTypeReportMapper;

    @Override
//    @Cacheable(value = "statisticssalebyinventory", keyGenerator = "myKeyGenerator")
    public List<StockBaseReport> statisticsSaleByInventory(StockInfoQuery query) {
        return stockTypeReportMapper.statisticsSaleByInventory(query);
    }

//    @Cacheable(value = "statisticsSaleBySalesMan", keyGenerator = "myKeyGenerator")
    @Override
    public List<SalesmanBaseReport> statisticsSaleBySalesMan(UserInfoQuery query) {
        return stockTypeReportMapper.statisticsSaleBySalesMan(query);
    }

//    @Cacheable(value = "querysalesticketbycust", keyGenerator = "myKeyGenerator")
    @Override
    public List<CustomerBaseReport> querySalesTicketByCust(CustomerQuery query) {
        return stockTypeReportMapper.querySalesTicketByCust(query);
    }
//    @Cacheable(value = "querysalesdeductionbysaleman", keyGenerator = "myKeyGenerator")
    @Override
    public List<SaleDeductionReport> querySalesDeductionBySaleman(UserInfoQuery query) {
        return stockTypeReportMapper.querySalesDeductionBySaleman(query);
    }

    @Override
    public List<DeptSaleReport> selectionDeptInfo(String parentCode) {
        return stockTypeReportMapper.selectionDeptInfo(parentCode);
    }

    @Override
    public List<DeptSaleReport> queryDeptSaleReport(DeptInfoQuery query) {
        List<DeptSaleReport> deptSaleReports = stockTypeReportMapper.queryDeptSaleReport(query);
        return deptSaleReports;
    }

    @Override
    public List<HomePageReport> queryStasticsSalesForYear() {
        return stockTypeReportMapper.queryStasticsSalesForYear();
    }

    @Override
    public List<HomePageReport> queryStasticsSalesForMonth() {
        return stockTypeReportMapper.queryStasticsSalesForMonth();
    }

    @Override
    public List<SaleDetailBaseReport> querySaleDetailList(SaleDetailReportQuery query) {
        return stockTypeReportMapper.querySaleDetailList(query);
    }
}
