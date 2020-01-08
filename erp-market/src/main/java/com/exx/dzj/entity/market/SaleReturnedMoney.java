package com.exx.dzj.entity.market;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @ClassName SaleReturnedMoney
 * @Description:
 * @Author yangyun
 * @Date 2020/1/3 0003 16:17
 * @Version 1.0
 **/
@Data
@Accessors(chain = true)
public class SaleReturnedMoney implements Serializable {

    private static final long serialVersionUID = -8098747087713430092L;

    private String userCode;
    private String realName;
    private String month;
    private String companyName;
    private BigDecimal returnedAmount; // 未回款额

    List<BigDecimal> retrunedAmountList;
}
