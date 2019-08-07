package com.exx.dzj.service.report.impl;

import com.exx.dzj.bean.PurchaseReportQuery;
import com.exx.dzj.entity.purchase.PurchaseInfoBaseReport;
import com.exx.dzj.entity.purchase.PurchaseListInfo;
import com.exx.dzj.entity.purchase.PurchaseQuery;
import com.exx.dzj.mapper.purchase.PurchaseReportMapper;
import com.exx.dzj.service.report.PurchaseReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("purchaseReportService")
public class PurchaseReportServiceImpl implements PurchaseReportService {

    @Autowired
    private PurchaseReportMapper purchaseReportMapper;

    @Override
    public List<PurchaseInfoBaseReport> queryPurchaseFeePayDetail(PurchaseReportQuery query) {
        return purchaseReportMapper.queryPurchaseFeePayDetail(query);
    }

    @Override
    public List<PurchaseListInfo> queryPurchaseListInfoDetail(PurchaseQuery query) {
        return purchaseReportMapper.queryPurchaseListInfoDetail(query);
    }
}
