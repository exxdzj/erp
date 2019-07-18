package com.exx.dzj.entity.company;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author
 * @Date 2019/7/17 0017 17:14
 * @Description 保险公司业务
 */
@Data
public class InsuranceCompanyBO implements Serializable {
    private String companyCode;
    private String companyName;
}
