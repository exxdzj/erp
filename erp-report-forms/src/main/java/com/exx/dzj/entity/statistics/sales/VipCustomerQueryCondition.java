package com.exx.dzj.entity.statistics.sales;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName VipCustomerQueryCondition
 * @Description:
 * @Author yangyun
 * @Date 2019/8/16 0016 14:54
 * @Version 1.0
 **/
@Data
public class VipCustomerQueryCondition extends VIPCustomerLevelReport {
    private static final long serialVersionUID = 8558935323131667115L;

    private String startDate;
    private String endDate;
    private String userCode;
    private List<String> gradeCodeList;
}
