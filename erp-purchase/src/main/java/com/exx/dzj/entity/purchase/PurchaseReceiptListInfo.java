package com.exx.dzj.entity.purchase;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @ClassName PurchaseReceiptListInfo
 * @Description:
 * @Author yangyun
 * @Date 2019/8/8 0008 9:24
 * @Version 1.0
 **/
@Data
public class PurchaseReceiptListInfo implements Serializable {
    private static final long serialVersionUID = -4431995497426387848L;

    private BigDecimal collectedAmount; // 付款金额
    private String collectionAccount; // 付款账户
    private String paymentMethod; // 付款方式
}
