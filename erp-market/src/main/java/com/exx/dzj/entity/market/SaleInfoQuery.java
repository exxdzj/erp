package com.exx.dzj.entity.market;

import lombok.Data;

/**
 * @author yangyun
 * @create 2019-06-09-17:11
 */
@Data
public class SaleInfoQuery extends SaleInfo {
    private static final long serialVersionUID = -285735256900205781L;

    private String startTime;
    private String endTime;
}
