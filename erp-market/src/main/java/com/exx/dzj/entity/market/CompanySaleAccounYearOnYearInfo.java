package com.exx.dzj.entity.market;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @ClassName CompanySaleAccounYearOnYearInfo
 * @Description:
 * @Author yangyun
 * @Date 2019/11/13 0013 15:43
 * @Version 1.0
 **/
@Data
@Accessors(chain = true)
public class CompanySaleAccounYearOnYearInfo implements Serializable {

    private String userCode;
    private String realName;
    private String subordinateCompanyCode;
    private String subordinateCompanyName;
    private BigDecimal thisYearAccoun;
    private BigDecimal lastYearAccoun;
    private BigDecimal beforeLastYearAccoun;
}
