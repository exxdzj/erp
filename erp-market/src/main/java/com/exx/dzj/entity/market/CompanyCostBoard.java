package com.exx.dzj.entity.market;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @ClassName CompanyCostBoard
 * @Description:
 * @Author yangyun
 * @Date 2020/1/7 0007 10:24
 * @Version 1.0
 **/
@Data
@Accessors(chain = true)
public class CompanyCostBoard implements Serializable {
    private static final long serialVersionUID = -3832566326670059125L;

    private String stockCode;
    private String stockName;
    private String companyName;
    private BigDecimal cost;

    private List<CompanyCostBoard> childList;
}
