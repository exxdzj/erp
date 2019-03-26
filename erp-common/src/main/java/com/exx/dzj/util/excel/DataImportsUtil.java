package com.exx.dzj.util.excel;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.IOException;
import java.io.InputStream;

/**
 * @description poi 解析excel 未完成
 * @author yangyun
 * @date 2019/3/19 0019
 * @return
 */
public class DataImportsUtil {

    public static void saleImport(String fileName, InputStream is) throws IOException{
        Workbook wb = FileObjectGenerator.getWorkbook(fileName, is);
        Sheet sheet = wb.getSheetAt(0);
        if (sheet == null){

        }

        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            if (row == null){
                continue;
            }
        }
    }


}
