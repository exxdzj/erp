package com.exx.dzj.entity.bean;

import com.exx.dzj.entity.customer.CustomerSupplierBean;
import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * @author yangyun
 * @create 2019-04-22-9:25
 */
@Data
@ToString(callSuper = true)
public class CustomerQuery extends CustomerSupplierBean {
    private static final long serialVersionUID = 4624829430466354873L;

    private String startDate;
    private String endDate;
    private List<Integer> businessType;
    private Integer fromCustId;
    private Integer toCustId;
}
