package com.exx.dzj.entity.customer;

import com.exx.dzj.entity.accountatt.AccountAttributeBean;
import com.exx.dzj.entity.contactway.ContactWayBean;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @Author 天刀
 * @Date 2019/1/5 0005 16:52
 * @Description 客户或供应商详细信息
 */
@Data
@ToString
public class CustomerSupplierInfo extends CustomerSupplierBean implements Serializable {

    private ContactWayBean contactWayBean;

    private AccountAttributeBean accountAttributeBean;
}
