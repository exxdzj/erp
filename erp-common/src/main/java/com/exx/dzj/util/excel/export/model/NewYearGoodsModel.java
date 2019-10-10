package com.exx.dzj.util.excel.export.model;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @ClassName NewYearGoodsModel
 * @Description:
 * @Author yangyun
 * @Date 2019/10/10 0010 14:40
 * @Version 1.0
 **/
@Data
@Accessors(chain = true)
public class NewYearGoodsModel extends BaseRowModel {

    @ExcelProperty(value = {"商品排名", "商品排名","商品编码"}, index = 0)
    private String stockCode;

    @ExcelProperty(value = {"商品排名", "商品排名","商品名称"}, index = 1)
    private String stockName;

    @ExcelProperty(value = {"商品排名", "商品排名","数量"}, index = 2)
    private Double goodsNum;
}
