package com.exx.dzj.entity.customer;

import lombok.Data;

import java.util.List;

/**
 * @Author
 * @Date 2019/8/6 0006 16:36
 * @Description 客户批量修改 model
 */
@Data
public class CustomerBatchBean extends CustomerSupplierBean {

    private List<String> custCodes;

    private String updateItem;

    private Integer region;
}
