package com.exx.dzj.entity.bean;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author yangyun
 * @create 2019-05-08-9:32
 */
@Data
public class DeptInfoQuery implements Serializable {
    private static final long serialVersionUID = 907242443474547976L;

    private String startDate;
    private String endDate;
    private List<String> deptCodeList;
}
