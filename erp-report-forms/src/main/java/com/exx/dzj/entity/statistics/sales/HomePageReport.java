package com.exx.dzj.entity.statistics.sales;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author yangyun
 * @create 2019-05-27-19:44
 */
@Data
public class HomePageReport implements Serializable {
    private static final long serialVersionUID = -3558105495335793598L;

    private String year;
    private String month;
    private String day;
    private BigDecimal sumReceivableAccoun;
}
