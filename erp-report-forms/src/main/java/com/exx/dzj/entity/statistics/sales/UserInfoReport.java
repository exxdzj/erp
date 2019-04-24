package com.exx.dzj.entity.statistics.sales;

import com.exx.dzj.entity.user.UserInfo;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author yangyun
 * @create 2019-04-19-15:57
 */
@Data
public class UserInfoReport extends UserInfo {

    private static final long serialVersionUID = 7789500832886986588L;

    private List<SaleInfoReport> saleInfoList;

    private BigDecimal backAmountTotal;

    private double totalGoodsNum;

    private BigDecimal totalSaleVolume;

    private BigDecimal totalCost;

    private BigDecimal totalGrossMargin;

    private BigDecimal totalGrossRate;
}
