package com.exx.dzj.entity.purchase;

import lombok.Data;

import java.io.Serializable;
@Data
public class PurchaseExportFieldReport implements Serializable {
    private static final long serialVersionUID = -9139903754940625515L;

    private String key;
    private String name;
}
