package com.exx.dzj.entity.market;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@ToString
@EqualsAndHashCode(of = "id")
public class SaleReceiptsDetails implements Serializable {
    private Integer id;

    private String saleCode;

    private String collectionAccount;

    private BigDecimal collectedAmount;

    private String paymentMethod;

    private String refNo;

    private Date createTime;

    private String createUser;
}