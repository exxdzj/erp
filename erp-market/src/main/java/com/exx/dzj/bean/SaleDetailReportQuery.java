package com.exx.dzj.bean;

import com.exx.dzj.entity.market.SaleInfo;
import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * @author yangyun
 * @create 2019-06-26-11:07
 */
@Data
@ToString(callSuper = true)
public class SaleDetailReportQuery extends SaleInfo {
    private String startDate;
    private String endDate;
    private Integer fromCustId;
    private Integer toCustId;
    private List<String> paymentStatusList;

}
