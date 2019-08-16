package com.exx.dzj.entity.statistics.sales;

import lombok.Data;

import java.io.Serializable;

/**
 * @author yangyun
 * @create 2019-08-16-9:14
 */
@Data
public class VipCustomerCountReport implements Serializable {

    private static final long serialVersionUID = -115524230002663425L;

    private String gradeCode;
    private String custGrade;
    private Integer count;
}
