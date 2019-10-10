package com.exx.dzj.util.excel.export.model;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @ClassName NewYearGift2020Model
 * @Description:
 * @Author yangyun
 * @Date 2019/10/10 0010 14:23
 * @Version 1.0
 **/
@Data
@Accessors(chain = true)
public class NewYearGift2020Model extends BaseRowModel {

//    @ExcelProperty(value = {"新春礼品", "新春礼品","商品类别编码"}, index = 0)
//    private String categoryCode;

    @ExcelProperty(value = {"新春礼品", "新春礼品","商品类别"}, index = 0)
    private String categoryName;

//    @ExcelProperty(value = {"新春礼品", "新春礼品","商品组编码"}, index = 2)
//    private String stockGroupCode;

    @ExcelProperty(value = {"新春礼品", "新春礼品","商品组"}, index = 1)
    private String stockGroupName;

    @ExcelProperty(value = {"新春礼品", "新春礼品","商品编码"}, index = 2)
    private String stockCode;

    @ExcelProperty(value = {"新春礼品", "新春礼品","商品"}, index = 3)
    private String stockName;

    @ExcelProperty(value = {"新春礼品", "新春礼品","商品数量"}, index = 4)
    private Double goodsNum;

    @ExcelProperty(value = {"新春礼品", "新春礼品","销售额"}, index = 5)
    private Double volume;

//    @ExcelProperty(value = {"新春礼品", "新春礼品","商品类别"}, index = 8)
//    private String categoryName;
//
//    @ExcelProperty(value = {"新春礼品", "新春礼品","商品类别"}, index = 9)
//    private String categoryName;
//
//    @ExcelProperty(value = {"新春礼品", "新春礼品","商品类别"}, index = 10)
//    private String categoryName;
//
//    @ExcelProperty(value = {"新春礼品", "新春礼品","商品类别"}, index = 11)
//    private String categoryName;
//
//    @ExcelProperty(value = {"新春礼品", "新春礼品","商品类别"}, index = 12)
//    private String categoryName;
//
//    @ExcelProperty(value = {"新春礼品", "新春礼品","商品类别"}, index = 13)
//    private String categoryName;
//
//    @ExcelProperty(value = {"新春礼品", "新春礼品","商品类别"}, index = 14)
//    private String categoryName;
//
//    @ExcelProperty(value = {"新春礼品", "新春礼品","商品类别"}, index = 15)
//    private String categoryName;
//
//    @ExcelProperty(value = {"新春礼品", "新春礼品","商品类别"}, index = 16)
//    private String categoryName;
}
