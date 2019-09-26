package com.exx.dzj.entity.bean;

import com.exx.dzj.entity.stock.StockInfo;
import lombok.Data;
import lombok.ToString;

import java.util.List;


/**
 * @author yangyun
 * @create 2019-04-10-16:05
 */
@Data
@ToString(callSuper = true)
public class StockInfoQuery extends StockInfo {
    private static final long serialVersionUID = -7357152807420596329L;

    private String startDate;
    private String endDate;
    private List<Integer> businessType; // 业务类型 1 销货 2 退货
    private Integer gross; // 是否计算毛利 1 是 0否
    private String fromStockName; // 商品名称
    private String toStockName; // 商品名称
    private String fromId;
    private String toId;
}
