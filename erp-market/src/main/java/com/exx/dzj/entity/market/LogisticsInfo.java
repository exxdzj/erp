package com.exx.dzj.entity.market;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author yangyun
 * @create 2019-05-11-10:25
 */
@Data
public class LogisticsInfo implements Serializable {
    private static final long serialVersionUID = 117924928803317540L;

    private Integer id;
    private String saleCode;
    private String stockCode;
    private String stockName;
    private String logisticsKind;
    private String logisticsCompanyCode;
    private String logisticsCompamyName;
    private String freihtCode;
    private String deliverGoodsTime;
    private String selectorPhoneNum;
    private String remark;
    private Date createTime;
    private Date lastUpdateTime;
    private BigDecimal expressFee;
    private String chargeMethod;
    private String createUser;
    private String stockAddressCode;
    private String goodsIds;
}
