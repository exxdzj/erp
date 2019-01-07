package com.exx.dzj.entity.purchase;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@ToString
public class PurchaseReceiptsDetailsBean implements Serializable {
    private Integer id;

    private String purchaseCode;

    private String collectionAccount;

    private BigDecimal collectedAmount;

    private String paymentMethod;

    private String refNo;

    private Date createTime;

    private String createUser;
}