package com.exx.dzj.common.export;

import com.exx.dzj.entity.purchase.PurchaseListInfo;
import com.exx.dzj.enummodel.PurchaseListFieldEnum;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.ServletOutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @description: 采购单导出
 * @author yangyun
 * @date 2019/8/6 0006
 */
public class PurchaseExportUtils {

    public static XSSFWorkbook exportPurchaseListInfoDetail (ServletOutputStream outputStream, List<PurchaseListInfo> list, List<String> fieldList){
        XSSFWorkbook workbook = null;
        try {
            workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("采购单");

            // 设置表格默认列宽度为15个字节
            sheet.setDefaultColumnWidth(20);

            // 获取标题字段
            List<PurchaseListFieldEnum> titleNameList = getTitleNameList(fieldList);

            // 标题
            XSSFRow row = sheet.createRow(0);
            XSSFCell titleCell;

            for (int i = 0; i < titleNameList.size(); i++) {
                titleCell = row.createCell(i);
                titleCell.setCellValue(titleNameList.get(i).getName());
            }

            // 具体数据
            Iterator<PurchaseListInfo> it = list.iterator();
            Map<String, List<PurchaseListFieldEnum>> collect = titleNameList.stream().collect(Collectors.groupingBy(PurchaseListFieldEnum::getKey));

            int index = 0;
            Field field = null;
            XSSFCell cell = null;
            String fileName = null;
            String getMethodName = null;
            PurchaseListInfo next = null;
            Class c = null;
            List<PurchaseListFieldEnum> purchaseListFieldEnums = null;
            Method method = null;
            Object value = null;

            // 获取实体类中字段
            Field[] fields = PurchaseListInfo.class.getDeclaredFields();

            // 处理查询出的数据
            while (it.hasNext()) {
                index++;
                row = sheet.createRow(index);
                next = it.next();
                c = next.getClass();
                int count = 0;
                for (Field f : fields) {
                    fileName = f.getName();
                    purchaseListFieldEnums = collect.get(fileName);
                    if (purchaseListFieldEnums != null) {
                        // 创建单元格
                        cell = row.createCell(count);
                        getMethodName = "get" + fileName.substring(0, 1).toUpperCase() + fileName.substring(1);
                        value = method.invoke(next, new Object[]{});
                        cell.setCellValue(value.toString());
                        count++;
                    }
                }
            }
        } catch (Exception e){
            e.printStackTrace();
            return workbook;
        }

        return workbook;
    }

    /**
     * @description: 获取选中字段
     * @author yangyun
     * @date 2019/8/7 0007
     * @param fieldList
     * @return java.util.List<java.lang.String>
     */
    private static List<PurchaseListFieldEnum> getTitleNameList (List<String> fieldList){
        List<PurchaseListFieldEnum> titleEnum = new ArrayList<>();
        fieldList.stream().forEach(
                key -> {
                    Arrays.stream(PurchaseListFieldEnum.values()).forEach(
                            temp -> {
                                if (StringUtils.equals(key, temp.getKey())){
                                    titleEnum.add(temp);
                                }
                            }
                    );
                }
        );

        return titleEnum;
    }
}
