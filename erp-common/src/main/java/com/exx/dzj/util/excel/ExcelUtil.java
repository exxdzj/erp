package com.exx.dzj.util.excel;


import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.metadata.BaseRowModel;
import com.alibaba.excel.metadata.Sheet;
import com.exx.dzj.constant.CommonConstant;
import com.exx.dzj.excepte.ErpException;
import com.exx.dzj.unique.DefaultIdGenerator;
import com.exx.dzj.unique.DefaultIdGeneratorConfig;
import com.exx.dzj.unique.IdGenerator;
import com.exx.dzj.unique.IdGeneratorConfig;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @description alibaba easyexcel 解析 excel
 * @author yangyun
 * @date 2019/3/19 0019
 * @return
 */
public class ExcelUtil {

    /**
     * @description
     * @author yangyun
     * @date 2019/3/19 0019
     * @param excel 上传excel 文件
     * @param rowModel 对应实体对象
     * @param sheetNo 解析开始行, 有表头的情况从第一行开始
     * @return java.util.List<java.lang.Object>
     */
    public static List<Object> readExcel(MultipartFile excel, BaseRowModel rowModel, int sheetNo) throws IOException {
//        return readExcel(excel, rowModel, sheetNo, CommonConstant.DEFAULT_VALUE_ZERO);
        return readExcel(excel, rowModel, sheetNo, CommonConstant.DEFAULT_VALUE_ONE);
    }

    public static List<Object> readExcel(MultipartFile excel, BaseRowModel rowModel, int sheetNo, int headLineNum) throws IOException {
        ExcelListener excelListener = new ExcelListener();
        ExcelReader reader = getReader(excel, excelListener);
        if (reader == null) {
            return null;
        }
        reader.read(new Sheet(sheetNo, headLineNum, rowModel.getClass()));
        return excelListener.getDatas();
    }

    public static ExcelReader getReader(MultipartFile excel, ExcelListener excelListener) throws IOException{
        String fileName = excel.getOriginalFilename();
        if (fileName == null || !(fileName.toLowerCase().endsWith(".xls") || fileName.toLowerCase().endsWith(".xlsx") || fileName.toLowerCase().endsWith(".xlsm"))){
            throw new ErpException(CommonConstant.FAIL_CODE, "文件格式错误");
        }

        InputStream is = new BufferedInputStream(excel.getInputStream());

        return new ExcelReader(is, null, excelListener, false);
    }

    public static String getCode (){
        IdGeneratorConfig config = new DefaultIdGeneratorConfig();
        IdGenerator idGenerator = new DefaultIdGenerator(config);
        String next = idGenerator.next();
        return next.substring(next.length() - 6, next.length());
    }
}
