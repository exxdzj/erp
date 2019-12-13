package com.exx.dzj.model;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;
import lombok.ToString;

/**
 * @ClassName CustomerCompany
 * @Description:
 * @Author yangyun
 * @Date 2019/12/9 0009 9:17
 * @Version 1.0
 **/
@Data
@ToString
public class CustomerCompany extends BaseRowModel {

    @ExcelProperty(value = "客户编码", index = 0)
    private String custCode;

    @ExcelProperty(value = "客户名称", index = 1)
    private String custName;

    @ExcelProperty(value = "销售员", index = 2)
    private String saleName;

    @ExcelProperty(value = "保险公司", index = 3)
    private String companyName;
}
