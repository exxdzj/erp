package com.exx.dzj.common.export;

import com.alibaba.excel.metadata.Font;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.metadata.TableStyle;
import org.apache.poi.ss.usermodel.IndexedColors;

import java.util.Map;

/**
 * @ClassName ExportUtil
 * @Description:
 * @Author yangyun
 * @Date 2019/10/8 0008 9:24
 * @Version 1.0
 **/
public class ExportUtil {

    private static final short FONT_SIZE = 10;

    /**
     * @description 内容单元格样式
     * @author yangyun
     * @date 2019/4/24 0024
     * @param cellsSize
     * @param sheetName
     * @return com.alibaba.excel.metadata.Sheet
     */
    public static Sheet gainSheet (Map<Integer, Integer> cellsSize, String sheetName){
        // 单 sheet excel
        Sheet sheet = new Sheet(1,0);

        // sheet 内容样式
        TableStyle style = new TableStyle();

        // 有一个问题就是设置标题字体大小和样式会导致内容样式失效
        // 内容字体大小
        Font contentFont = new Font();
        contentFont.setFontHeightInPoints(FONT_SIZE);
        contentFont.setBold(true);

        style.setTableContentFont(contentFont);
        style.setTableContentBackGroundColor(IndexedColors.WHITE1);

        // 设置单元格宽度
        sheet.setColumnWidthMap(cellsSize);
        sheet.setSheetName(sheetName);

        return sheet;
    }
}
