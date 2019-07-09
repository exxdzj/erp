package com.exx.dzj.bean;

import lombok.Data;

@Data
//@ToString(callSuper = true)
public class PurchaseReportQuery {

    private String startDate;
    private String endDate;
    private Integer fromSupplierId;
    private Integer toSupplierId;
}
