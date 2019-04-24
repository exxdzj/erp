package com.exx.dzj.service.statistics.sales.impl;

import com.exx.dzj.entity.bean.CustomerQuery;
import com.exx.dzj.entity.bean.StockInfoQuery;
import com.exx.dzj.entity.bean.UserInfoQuery;
import com.exx.dzj.entity.customer.CustomerSupplierBean;
import com.exx.dzj.entity.statistics.sales.CustomerReport;
import com.exx.dzj.entity.statistics.sales.StockTypeReport;
import com.exx.dzj.entity.statistics.sales.UserInfoReport;
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
    @Cacheable(value = "statisticssalebyinventory", keyGenerator = "myKeyGenerator")
    public List<StockTypeReport> statisticsSaleByInventory(StockInfoQuery query) {
        return stockTypeReportMapper.statisticsSaleByInventory(query);
    }

    @Cacheable(value = "statisticsSaleBySalesMan", keyGenerator = "myKeyGenerator")
    @Override
    public List<UserInfoReport> statisticsSaleBySalesMan(UserInfoQuery query) {
        return stockTypeReportMapper.statisticsSaleBySalesMan(query);
    }

    @Cacheable(value = "querysalesticketbycust", keyGenerator = "myKeyGenerator")
    @Override
    public List<CustomerReport> querySalesTicketByCust(CustomerQuery query) {
        return stockTypeReportMapper.querySalesTicketByCust(query);
    }
}
