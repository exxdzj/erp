package com.exx.dzj.entity.market;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author
 * @Date 2019/6/4 0004 9:44
 * @Description  销售单
 */
@Data
public class SaleBean implements Serializable {

    private String saleCode;

    private String userCode;

    private String salesmanCode;

    private String custCode;

    private String orgCode;
}
