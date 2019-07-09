package com.exx.dzj.service.report;

import com.exx.dzj.bean.PurchaseReportQuery;
import com.exx.dzj.entity.purchase.PurchaseInfoBaseReport;

import java.util.List;

/**
 * @author yangyun
 * @create 2019-07-06-16:17
 */
public interface PurchaseReportService {

    List<PurchaseInfoBaseReport> queryPurchaseFeePayDetail(PurchaseReportQuery query);
}
