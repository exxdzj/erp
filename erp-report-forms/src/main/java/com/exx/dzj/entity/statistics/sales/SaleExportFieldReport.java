package com.exx.dzj.entity.statistics.sales;

import lombok.Data;

import java.io.Serializable;

/**
 * @author yangyun
 * @create 2019-06-17-10:21
 */
@Data
public class SaleExportFieldReport implements Serializable {
    private static final long serialVersionUID = 5069722880461305487L;
    private String key;
    private String name;
}
