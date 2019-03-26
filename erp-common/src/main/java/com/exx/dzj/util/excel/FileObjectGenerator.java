package com.exx.dzj.util.excel;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author yangyun
 * @create 2019-03-19-9:35
 */
public class FileObjectGenerator {

    public static Workbook getWorkbook(String fileName, InputStream is) throws IOException{
        boolean isExcel2003 = true;
        if(fileName.matches("^.+\\.(?i)(xlsx)$")){
            isExcel2003 = false;
        }

        Workbook wb = null;
        if (isExcel2003){
            wb = new HSSFWorkbook(is);
        }else {
            wb = new XSSFWorkbook(is);
        }

        return wb;
    }
}
