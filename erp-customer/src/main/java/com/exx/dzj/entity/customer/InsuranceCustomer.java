package com.exx.dzj.entity.customer;

import lombok.Data;

import java.io.Serializable;

/**
 * @author yangyun
 * @create 2019-05-28-15:48
 */
@Data
public class InsuranceCustomer implements Serializable {
    private static final long serialVersionUID = -6651224767304994575L;

    private String rankCode;
    private String rankName;
    private Integer count;
    private Integer newCount;
    private String month;
}
