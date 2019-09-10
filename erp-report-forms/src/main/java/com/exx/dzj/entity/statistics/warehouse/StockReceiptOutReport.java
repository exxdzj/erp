package com.exx.dzj.entity.statistics.warehouse;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @ClassName StockReceiptOutBase
 * @Description:
 * @Author yangyun
 * @Date 2019/9/9 0009 18:05
 * @Version 1.0
 **/
@Data
@Accessors(chain=true)
public class StockReceiptOutReport implements Serializable {
    private static final long serialVersionUID = 4645720383146105740L;

    private String stockCode;
    private String stockName;
    private String stockClass;
    private String stockClassName;
    private String meterUnit;
    private String stockAddress;
    private BigDecimal standardBuyPrice;
    private Double outInventoryNum; //出库数量
    private BigDecimal outCost; // 出库成本
    private Double receiptInventoryNum; // 入库数量
    private BigDecimal receiptCost; // 入库成本
    private Double minInventory; // 结存数量
    private BigDecimal cost; // 结存成本
}
