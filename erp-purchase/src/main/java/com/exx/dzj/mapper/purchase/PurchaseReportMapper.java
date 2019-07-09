package com.exx.dzj.mapper.purchase;

import com.exx.dzj.bean.PurchaseReportQuery;
import com.exx.dzj.entity.purchase.PurchaseInfoBaseReport;

import java.util.List;

/**
 * @author yangyun
 * @create 2019-07-06-11:56
 */
public interface PurchaseReportMapper {

    List<PurchaseInfoBaseReport> queryPurchaseFeePayDetail(PurchaseReportQuery query);
}
