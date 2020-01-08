package com.exx.dzj.entity.market;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @ClassName CompanyReturnedAmount
 * @Description:
 * @Author yangyun
 * @Date 2020/1/6 0006 17:10
 * @Version 1.0
 **/
@Data
@Accessors(chain = true)
public class CompanyReturnedAmount implements Serializable {

    private String companyCode;
    private String companyName;
    private BigDecimal monthSum;
    private List<BigDecimal> list;

    private List<SaleReturnedMoney> saleManList;
}
