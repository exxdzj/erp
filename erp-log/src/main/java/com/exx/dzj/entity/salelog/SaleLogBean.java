package com.exx.dzj.entity.salelog;

import lombok.Data;

import java.util.Date;

/**
 * 销售日志实体类
 */
@Data
public class SaleLogBean {
    private Integer id;

    private String operate;

    private String logContent;

    private String params;

    private String saleCode;

    private Date createTime;

    private String createUser;

    private String userName;

    private String method;
}