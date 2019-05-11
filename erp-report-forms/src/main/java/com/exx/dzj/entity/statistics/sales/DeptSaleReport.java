package com.exx.dzj.entity.statistics.sales;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yangyun
 * @create 2019-05-07-14:22
 */
@Data
public class DeptSaleReport implements Serializable {
    private static final long serialVersionUID = -9048077480165935425L;

    private Integer id;
    private String deptCode;
    private String deptName;
    private String parentCode;

    private BigDecimal sumSaleVolume; /*销售额, 总金额-优惠金额*/
    private BigDecimal sumSaleCost; /*成本*/
    private BigDecimal sumGrossMargin; /*毛利*/
    private BigDecimal sumCost; /*额外支出费用*/
    private BigDecimal pureProfit; /*纯利润*/
    private BigDecimal commission; /*佣金*/

    private List<DeptSaleReport> childrenList = new ArrayList<>();

}
