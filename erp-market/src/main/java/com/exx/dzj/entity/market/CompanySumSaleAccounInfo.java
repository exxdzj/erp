package com.exx.dzj.entity.market;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName CompanySumSaleAccounInfo
 * @Description:
 * @Author yangyun
 * @Date 2019/11/13 0013 17:54
 * @Version 1.0
 **/
@Data
@Accessors(chain = true)
public class CompanySumSaleAccounInfo implements Serializable {

    private String subordinateCompanyCode;
    private String subordinateCompanyName;
    private BigDecimal sumThisYearAccoun;
    private BigDecimal sumLastYearAccoun;
    private BigDecimal sumBeforeLastYearAccoun;

    private List<CompanySaleAccounYearOnYearInfo> list = new ArrayList<>();
}
