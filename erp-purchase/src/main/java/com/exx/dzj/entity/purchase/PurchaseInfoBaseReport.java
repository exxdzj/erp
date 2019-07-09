package com.exx.dzj.entity.purchase;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PurchaseInfoBaseReport extends PurchaseBaseReport {

    private static final long serialVersionUID = 1419712810469935891L;
    private String userCode;
    private String realName;
    private BigDecimal purhcaseVolume;
    private BigDecimal discountRate;
    private String stockAddress;
}
