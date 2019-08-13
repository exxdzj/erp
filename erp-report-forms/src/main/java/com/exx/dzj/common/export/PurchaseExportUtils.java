package com.exx.dzj.common.export;

import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.metadata.Table;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.exx.dzj.constant.CommonConstant;
import com.exx.dzj.entity.purchase.PurchaseGoodsDetailBean;
import com.exx.dzj.entity.purchase.PurchaseGoodsListInfo;
import com.exx.dzj.entity.purchase.PurchaseListInfo;
import com.exx.dzj.entity.purchase.PurchaseReceiptListInfo;
import com.exx.dzj.enummodel.PurchaseListFieldEnum;
import com.exx.dzj.util.DateUtil;
import com.exx.dzj.util.excel.export.model.PurchaseListModel;
import com.exx.dzj.util.excel.export.model.SaleListModel;
import lombok.Cleanup;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.util.CollectionUtils;

import javax.servlet.ServletOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @description: 采购单导出
 * @author yangyun
 * @date 2019/8/6 0006
 */
public class PurchaseExportUtils {

    /**
     * @description: 判断导出字段中是否包含商品或者收款信息 -1 都不包含  0 包含商品 1 包含收款 2 都包含
     * @author yangyun
     * @date 2019/8/9 0009
     * @param goodsMap
     * @param receiptMap
     * @param titleNameList
     * @return int
     */
    private static int isHasChildAttribute(Map<String, List<Field>> goodsMap, Map<String, List<Field>> receiptMap, List<PurchaseListFieldEnum> titleNameList){
        List<Field> gField = null;
        List<Field> rField = null;
        int i = -1;
        for (PurchaseListFieldEnum e : titleNameList){
            gField = goodsMap.get(e.getName());
            rField = receiptMap.get(e.getName());

            if (gField != null){
                i =  0;
            }

            if (rField != null){
                i =  1;
            }

            if (gField != null && rField != null){
                i =  2;
            }

        }
        return i;

    }

    public static void exportPurchaseListInfoDetail (ServletOutputStream outputStream, List<PurchaseListInfo> list, List<String> fieldList){
        try {
            @Cleanup XSSFWorkbook workbook = null;
            workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("采购单");

            // 设置表格默认列宽度为15个字节
            sheet.setDefaultColumnWidth(40);

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
            List<Field> purchase = Arrays.asList(PurchaseListInfo.class.getDeclaredFields());
            Map<String, List<Field>> purchaseMap = purchase.stream().collect(Collectors.groupingBy(Field::getName));

            List<Field> goods = Arrays.asList(PurchaseGoodsListInfo.class.getDeclaredFields());
            Map<String, List<Field>> goodsMap = goods.stream().collect(Collectors.groupingBy(Field::getName));

            List<Field> receipt = Arrays.asList(PurchaseReceiptListInfo.class.getDeclaredFields());
            Map<String, List<Field>> receiptMap = receipt.stream().collect(Collectors.groupingBy(Field::getName));

            List<PurchaseGoodsListInfo> goodsList = null;
            List<PurchaseReceiptListInfo> receiptList = null;

            int receiptSize = 0;
            int goodsSize = 0;
            int froNum = 0;
            // 处理查询出的数据
            while (it.hasNext()) {
                index++;
                row = sheet.createRow(index);
                next = it.next();
                goodsList = next.getGoodsList();
                receiptList = next.getReceiptList();

                goodsSize = goodsList.size();
                receiptSize = receiptList.size();

                c = next.getClass();
                int count = 0;


                for (PurchaseListFieldEnum e : titleNameList){
                    List<Field> pField = purchaseMap.get(e.getName());
                    List<Field> gField = goodsMap.get(e.getName());
                    List<Field> rField = receiptMap.get(e.getName());

                    if (pField != null){

                    }

                    //
                    if (gField != null){
                        froNum = goodsSize;
                    }

                    if (rField != null){
                        froNum = receiptSize;
                    }

                    if (gField != null && rField != null){
                        froNum = goodsSize >= receiptSize ? goodsSize : receiptSize;
                    }

                    if (froNum > CommonConstant.DEFAULT_VALUE_ONE){

                    }
                }


                for (Field f : purchase) {
                    fileName = f.getName();
                    purchaseListFieldEnums = collect.get(fileName);
                    if (purchaseListFieldEnums != null) {
                        // 创建单元格
                        cell = row.createCell(count);

                        // get 方法拼接
                        getMethodName = "get" + fileName.substring(0, 1).toUpperCase() + fileName.substring(1);
                        method = c.getMethod(getMethodName, new Class[]{});
                        value = method.invoke(next, new Object[]{});

                        setCellValue(cell, value);
                        count++;
                    }
                }



            }
            workbook.write(outputStream);
        } catch (Exception e){
            e.printStackTrace();
        }

    }

    /**
     * @description: 单元格值判定设置
     * @author yangyun
     * @date 2019/8/7 0007
     * @param cell
     * @param value
     * @return void
     */
    private static void setCellValue (XSSFCell cell, Object value){
        if (value == null){
            cell.setCellValue("");
        } else {
            if (value instanceof String){
                cell.setCellValue(String.valueOf(value));
            } else if (value instanceof Double){
                cell.setCellValue((Double)value);
            } else if (value instanceof Date){
                cell.setCellValue(DateUtil.getDate((Date) value));
            } else if (value instanceof BigDecimal){
                cell.setCellValue(((BigDecimal)value).doubleValue());
            } else if (value instanceof Boolean){

            }
        }
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

    public static void exportPurchaseListInfoDetail2(ServletOutputStream outputStream, List<PurchaseListInfo> list){
        ExcelWriter writer = new ExcelWriter(outputStream, ExcelTypeEnum.XLSX);
        String sheetName = "采购单";
        Sheet sheet = new Sheet(1,0);
        sheet.setSheetName(sheetName);

        Map<Integer, Integer> map = new HashMap<>();
        map.put(0,3000);
        map.put(1,3200);
        map.put(2,3000);
        map.put(3,5000);
        map.put(4,4000);
        map.put(5,3000);
        map.put(6,3000);
        map.put(7,2800);
        map.put(8,3000);
        map.put(9,3000);
        map.put(10,4000);
        map.put(11,4000);
        map.put(12,3000);
        map.put(13,3000);
        map.put(14,2000);
        map.put(15,3000);
        map.put(16,4000);
        map.put(17,4000);
        map.put(18,3000);
        map.put(19,4000);
        map.put(20,3000);
        map.put(21,3200);
        map.put(22,4000);
        map.put(23,4000);
        map.put(24,2800);
        map.put(25,2800);
        map.put(26,3000);
        map.put(27,4000);
        sheet.setColumnWidthMap(map);

        Table title = new Table(1);
        title.setClazz(PurchaseListModel.class);
        writer.write(null, sheet, title);

        List<PurchaseListModel> content = new ArrayList<>();
        PurchaseListModel model = null;

        int receiptSize = 0;
        int goodsSize = 0;
        int froNum = 0;

        PurchaseGoodsListInfo goods = null;
        PurchaseReceiptListInfo receipt = null;


        for (PurchaseListInfo p : list){
            model = new PurchaseListModel();
            setBasicModel(model, p);
            model.setPurchaseSumVolume(p.getPurchaseSumVolume().doubleValue());
            content.add(model);

            List<PurchaseReceiptListInfo> receiptList = p.getReceiptList();
            receiptSize = receiptList.size();

            List<PurchaseGoodsListInfo> goodsList = p.getGoodsList();
            goodsSize = goodsList.size();

            froNum = goodsSize >= receiptSize ? goodsSize : receiptSize;

            if (froNum > CommonConstant.DEFAULT_VALUE_ONE){
                PurchaseListModel model1 = null;

                for (int i = 0; i < froNum; i++){
                    if (goodsSize > i){
                        goods = goodsList.get(i);
                    } else {
                        goods = null;
                    }

                    if (receiptSize > i){
                        receipt = receiptList.get(i);
                    } else {
                        receipt = null;
                    }

                    if (i == 0){
                        setModelValue(model, receipt, goods);
                    } else {
                        model1 = new PurchaseListModel();
                        setBasicModel(model1, p);
                        setModelValue(model1, receipt, goods);
                        content.add(model1);
                    }
                }
            } else {
                if (goodsSize > 0){
                    goods = goodsList.get(0);
                }

                if (receiptSize > 0){
                    receipt = receiptList.get(0);
                }

                setModelValue(model, receipt, goods);
                receipt = null;
                goods = null;
            }
        }

        writer.write(content, sheet);
        writer.finish();
    }

    private static void setBasicModel (PurchaseListModel model, PurchaseListInfo p){
        model.setPurchaseDate(p.getPurchaseDate());
        model.setPurchaseCode(p.getPurchaseCode());
        model.setPaymentStatus(p.getPaymentStatus());
        model.setDeliveryAddress(p.getDeliveryAddress());
        model.setSalesmanName(p.getSalesmanName());
        model.setPurchaseProject(p.getPurchaseProject());
        model.setPurchaseOrderCode(p.getPurchaseOrderCode());
        model.setInvoiceCode(p.getInvoiceCode());
        model.setPurchaseRemark(p.getPurchaseRemark());
        model.setDiscountAmount(p.getDiscountAmount().doubleValue());
        model.setCustPhoneNum(p.getCustPhoneNum());

        double s = 0;
        if (!CollectionUtils.isEmpty(p.getReceiptList())){

            s = p.getReceiptList().stream().map(PurchaseReceiptListInfo::getCollectedAmount).reduce(BigDecimal.ZERO, BigDecimal::add).doubleValue();
        }
        model.setSumCollectedAmount(s);

        model.setFlowStatus(p.getFlowStatus());
        model.setCollectionTerms(p.getCollectionTerms());
        model.setAccountPeriod(p.getAccountPeriod());
        model.setCreateUser(p.getCreateUser());
        model.setCustName(p.getCustName());
        model.setCustPhoneNum(p.getCustPhoneNum());
    }

    private static void setModelValue(PurchaseListModel model, PurchaseReceiptListInfo receipt, PurchaseGoodsListInfo goods){
        if (receipt != null){
            model.setCollectedAmount(receipt.getCollectedAmount().doubleValue());
            model.setCollectionAccount(receipt.getCollectionAccount());
            model.setPaymentMethod(receipt.getPaymentMethod());
        }

        if (goods != null){
            model.setStockCode(goods.getStockCode());
            model.setStockName(goods.getStockName());
            model.setStockAddress(goods.getStockAddress());
            model.setGoodsNum(goods.getGoodsNum());
            model.setRealSellPrice(goods.getRealSellPrice().doubleValue());
            model.setPurchaseVolume(goods.getPurchaseVolume().doubleValue());
            model.setGoodsRemark(goods.getGoodsRemark());
        }

    }

    private static void exprotPruchaseRawInfo (ServletOutputStream outputStream, List<PurchaseListInfo> list){

    }
}
