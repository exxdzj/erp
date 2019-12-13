package com.exx.dzj.entity.market;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @ClassName CompanyProfit
 * @Description:
 * @Author yangyun
 * @Date 2019/12/12 0012 10:27
 * @Version 1.0
 **/
@Data
@Accessors(chain = true)
public class CompanyProfit implements Serializable {

    private String companyName;
    private String year;
    private BigDecimal total;
    private BigDecimal profit;
}
