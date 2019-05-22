package com.exx.dzj.common.export;

import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.*;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.exx.dzj.constant.CommonConstant;
import com.exx.dzj.entity.bean.CustomerQuery;
import com.exx.dzj.entity.bean.StockInfoQuery;
import com.exx.dzj.entity.bean.UserInfoQuery;
import com.exx.dzj.entity.statistics.sales.*;
import com.exx.dzj.entity.user.UserVo;
import com.exx.dzj.util.DateUtil;
import com.exx.dzj.util.MathUtil;
import com.exx.dzj.util.enums.ExportFileNameEnum;
import com.exx.dzj.util.excel.export.model.CustomerSaleModel;
import com.exx.dzj.util.excel.export.model.InventorySaleModel;
import com.exx.dzj.util.excel.export.model.SaleDeductionModel;
import com.exx.dzj.util.excel.export.model.SalesManSaleModel;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.springframework.util.CollectionUtils;

import javax.servlet.ServletOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

/**
 * @author yangyun
 * @create 2019-04-16-17:21
 */
public class SaleExportUtils {

    private static final short FONT_SIZE = 10;

    /**
     * @description 内容单元格样式
     * @author yangyun
     * @date 2019/4/24 0024
     * @param cellsSize
     * @param sheetName
     * @return com.alibaba.excel.metadata.Sheet
     */
    private static Sheet gainSheet (Map<Integer, Integer> cellsSize, String sheetName){
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

    /**
     * @description 导出, 根据存货
     * @author yangyun
     * @date 2019/4/24 0024
     * @param outputStream
     * @param stockTypeReports
     * @param query
     * @return com.alibaba.excel.ExcelWriter
     */
    public static ExcelWriter inventorySaleExport (ServletOutputStream outputStream, List<StockTypeReport> stockTypeReports, StockInfoQuery query) throws IOException {
        int count = 0;

        String value = ExportFileNameEnum.getExportFileNameEnum(query.getBusinessType()).getValue();


        ExcelWriter writer = new ExcelWriter(outputStream, ExcelTypeEnum.XLSX);

        Map<Integer, Integer> map = new HashMap<>();
        map.put(0,4000);
        map.put(1,4000);
        map.put(2,4000);
        map.put(3,4000);
        map.put(4,4000);
        map.put(5,4000);
        map.put(6,4000);
        map.put(7,2800);
        map.put(8,2000);
        map.put(9,4000);
        map.put(10,2000);
        map.put(11,2800);
        map.put(12,4000);
        map.put(13,4000);

        String sheetName = ExportFileNameEnum.INVENTORY_SALE_NAME.getValue() +" (" + value + ")";
        Sheet sheet = gainSheet(map, sheetName);

        Table title = new Table(1);
        title.setClazz(InventorySaleModel.class);

        writer.write(null, sheet, title);

        List<InventorySaleModel> data1 = new ArrayList<>();
        InventorySaleModel item = new InventorySaleModel();
        item.setStockTypeName("日期: 由" + query.getStartDate());
        item.setVacant("至" + query.getEndDate());
        item.setGrossMargin("打印日期: ");
        item.setGrossRate(DateUtil.getDate(new Date()));
        data1.add(item);
        writer.write(data1, sheet);


        List<InventorySaleModel> dataStock1 = new ArrayList<>();
        for (StockTypeReport stockType : stockTypeReports){
            InventorySaleModel item1 = new InventorySaleModel();

            List<StockInfoReport> stockReportList = stockType.getStockReportList();
            if (CollectionUtils.isEmpty(stockReportList)){
                continue;
            }
            for (StockInfoReport stockinfo : stockReportList){
                List<SaleGoodsReport> saleInfoReports = stockinfo.getSaleInfoReports();

                if (CollectionUtils.isEmpty(saleInfoReports)){
                    continue;
                }

                if (count == CommonConstant.DEFAULT_VALUE_ZERO){
                    item1.setStockTypeName(stockType.getStockTypeName());
                    item1.setVacant("存货编号: " + stockinfo.getStockCode());
                    item1.setCreateTime("存货名称: " + stockinfo.getStockName());
                    item1.setCustName("单位: " + (stockinfo.getMeterUnit() == null ? "" : stockinfo.getMeterUnit()));
                    count ++;
                    dataStock1.add(item1);
                } else {
                    item1 = new InventorySaleModel();
                    item1.setVacant("存货编号: " + stockinfo.getStockCode());
                    item1.setRealName("存货名称: ");
                    item1.setCreateTime(stockinfo.getStockName());
                    item1.setCustName("单位: " + (stockinfo.getMeterUnit() == null ? "" : stockinfo.getMeterUnit()));
                    dataStock1.add(item1);
                }
                for (SaleGoodsReport saleGoods: saleInfoReports){
                    InventorySaleModel itemSaleGoods = new InventorySaleModel();
                    itemSaleGoods.setSaleCode(saleGoods.getSaleCode());
                    itemSaleGoods.setRealName(saleGoods.getRealName());
                    itemSaleGoods.setCreateTime(DateUtil.getDate(saleGoods.getCreateTime()));
                    itemSaleGoods.setStockAddress(saleGoods.getStockAddress());
                    itemSaleGoods.setCustName(saleGoods.getCustName());
                    itemSaleGoods.setGoodsNum(saleGoods.getGoodsNum());
                    itemSaleGoods.setUnitPrice(saleGoods.getUnitPrice());
                    itemSaleGoods.setSalesVolume(saleGoods.getSalesVolume());
                    itemSaleGoods.setStandardBuyPrice(saleGoods.getStandardBuyPrice());
                    itemSaleGoods.setCost(saleGoods.getCost());
                    itemSaleGoods.setGrossMargin(saleGoods.getGrossMargin().toString());
                    itemSaleGoods.setGrossRate(saleGoods.getGrossRate().toString() + "%");
                    dataStock1.add(itemSaleGoods);
                }
                InventorySaleModel subtotal = new InventorySaleModel();
                subtotal.setStockAddress("小计: ");
                subtotal.setGoodsNum(stockinfo.getCountTotal());
                subtotal.setSalesVolume(stockinfo.getSalesTotal());
                subtotal.setCost(stockinfo.getCostTotal());
                subtotal.setGrossMargin(stockinfo.getGrossTotal().toString());
                subtotal.setGrossRate(stockinfo.getGrossRateTotal()+"%");
                dataStock1.add(subtotal);
            }
            count = CommonConstant.DEFAULT_VALUE_ZERO;
            InventorySaleModel summation = new InventorySaleModel();
            summation.setStockAddress("合计: ");
            summation.setGoodsNum(stockType.getCountTotal());
            summation.setSalesVolume(stockType.getSalesTotal());
            summation.setCost(stockType.getCostTotal());
            summation.setGrossMargin(stockType.getGrossTotal().toString());
            summation.setGrossRate(stockType.getGrossRateTotal()+"%");
            dataStock1.add(summation);
        }

        double sum = stockTypeReports.stream().mapToDouble(StockTypeReport::getCountTotal).sum(); // 数量总计
        BigDecimal salesTotal = stockTypeReports.stream().map(StockTypeReport::getSalesTotal).reduce(BigDecimal.ZERO, BigDecimal::add);// 销售额总计
        BigDecimal costTotal = stockTypeReports.stream().map(StockTypeReport::getCostTotal).reduce(BigDecimal.ZERO, BigDecimal::add); // 成本总计
        BigDecimal grossTotal = stockTypeReports.stream().map(StockTypeReport::getGrossTotal).reduce(BigDecimal.ZERO, BigDecimal::add); // 毛利总计
        BigDecimal rateTotal = MathUtil.keepTwoBigdecimal(grossTotal, costTotal, CommonConstant.DEFAULT_VALUE_FOUR);//毛利率总计
        InventorySaleModel summation = new InventorySaleModel();
        summation.setStockAddress("总计: ");
        summation.setGoodsNum(sum);
        summation.setSalesVolume(salesTotal);
        summation.setCost(costTotal);
        summation.setGrossMargin(grossTotal.toString());
        summation.setGrossRate(rateTotal.toString() + "");
        dataStock1.add(summation);

        writer.write(dataStock1, sheet);



        List<InventorySaleModel> dataEnd = new ArrayList<>();
        InventorySaleModel itemEnd = new InventorySaleModel();
        dataEnd.add(itemEnd);
        writer.write(dataEnd, sheet);
        itemEnd.setStockTypeName("公司名称: 正诚文化");
//        itemEnd.setCreateTime("操作员: " + userVo.getRealName());
        writer.write(dataEnd, sheet);

        return writer;
    }

    /**
     * @description 导出, 根据销售员
     * @author yangyun
     * @date 2019/4/24 0024
     * @param outputStream
     * @param mapData
     * @param query
     * @return com.alibaba.excel.ExcelWriter
     */
    public static ExcelWriter salesManSaleExport (ServletOutputStream outputStream, Map<String, Object> mapData, UserInfoQuery query){
        int count = 0;
        String value = ExportFileNameEnum.getExportFileNameEnum(query.getBusinessType()).getValue();

        ExcelWriter writer = new ExcelWriter(outputStream, ExcelTypeEnum.XLSX);

        Map<Integer, Integer> map = new HashMap<>();
        map.put(0,4000);
        map.put(1,4000);
        map.put(2,4000);
        map.put(3,4000);
        map.put(4,4000);
        map.put(5,4000);
        map.put(6,4000);
        map.put(7,2800);
        map.put(8,2000);
        map.put(9,4000);
        map.put(10,2000);
        map.put(11,2800);
        map.put(12,4000);
        map.put(13,4000);

        String sheetName = ExportFileNameEnum.INVENTORY_SALE_NAME.getValue() +" (" + value + ")";
        Sheet sheet = gainSheet(map, sheetName);

        Table title = new Table(1);
        title.setClazz(SalesManSaleModel.class);
        writer.write(null, sheet, title);

        List<SalesManSaleModel> data1 = new ArrayList<>();
        SalesManSaleModel item = new SalesManSaleModel();
        item.setCreateTime("日期: 由" + query.getStartDate());
        item.setSaleCode("至" + query.getEndDate());
        item.setGrossMargin("打印日期: ");
        item.setGrossRate(DateUtil.getDate(new Date()));
        data1.add(item);
        writer.write(data1, sheet);

        List<SalesManSaleModel> content = new ArrayList<>();

        List<UserInfoReport> userInfoReports = (List<UserInfoReport>)mapData.get("userInfoReports");

        for (UserInfoReport ur : userInfoReports){

            List<SaleInfoReport> saleInfoList = ur.getSaleInfoList();
            if (CollectionUtils.isEmpty(saleInfoList)){
                continue;
            }

            SalesManSaleModel saleModel = new SalesManSaleModel();
            saleModel.setCreateTime("销售员编号: ");
            saleModel.setSaleCode(ur.getUserCode());
            saleModel.setCustName("销售员名称: ");
            saleModel.setBackAmount(ur.getSalesmanCode() + ur.getRealName());
            content.add(saleModel);

            for (SaleInfoReport sr : saleInfoList){
                List<SaleGoodsReport> saleGoodsReportList = sr.getSaleGoodsReportList();

                SalesManSaleModel saleModel1 = null;

                for (SaleGoodsReport sgr : saleGoodsReportList) {
                    if (count == CommonConstant.DEFAULT_VALUE_ZERO){
                        saleModel1 = new SalesManSaleModel();
                        saleModel1.setCreateTime(DateUtil.getDate(sr.getCreateTime()));
                        saleModel1.setSaleCode(sr.getSaleCode());
                        saleModel1.setCustName(sr.getCustCode() + sr.getCustName());
                        saleModel1.setBackAmount(sr.getCollectedAmountTotal().toString());
                        saleModel1.setStockName(sgr.getStockName());
                        saleModel1.setGoodsNum(sgr.getGoodsNum().toString());
                        saleModel1.setUnitPrice(sgr.getUnitPrice().toString());
                        saleModel1.setSalesVolume(sgr.getSalesVolume().toString());
                        saleModel1.setCost(sgr.getStandardBuyPrice().toString());
                        saleModel1.setSumCost(sgr.getCost().toString());
                        saleModel1.setGrossMargin(sgr.getGrossMargin().toString());
                        saleModel1.setGrossRate(sgr.getGrossRate().toString());
                        count ++;
                    } else {
                        saleModel1 = new SalesManSaleModel();
                        saleModel1.setStockName(sgr.getStockName());
                        saleModel1.setGoodsNum(sgr.getGoodsNum().toString());
                        saleModel1.setUnitPrice(sgr.getUnitPrice().toString());
                        saleModel1.setSalesVolume(sgr.getSalesVolume().toString());
                        saleModel1.setCost(sgr.getStandardBuyPrice().toString());
                        saleModel1.setSumCost(sgr.getCost().toString());
                        saleModel1.setGrossMargin(sgr.getGrossMargin().toString());
                        saleModel1.setGrossRate(sgr.getGrossRate().toString());
                    }
                    content.add(saleModel1);
                }
                if (saleGoodsReportList.size() > CommonConstant.DEFAULT_VALUE_ONE){
                    saleModel1 = new SalesManSaleModel();
                    saleModel1.setCustName("小计: ");
                    saleModel1.setBackAmount(sr.getCollectedAmountTotal().toString());
                    saleModel1.setGoodsNum(sr.getSumGoodsNum() + "");
                    saleModel1.setSalesVolume(sr.getSumSaleVolume().toString());
                    saleModel1.setSumCost(sr.getSumCost().toString());
                    saleModel1.setGrossMargin(sr.getSumGrossMargin().toString());
                    saleModel1.setGrossRate(sr.getSumGrossRate().toString());
                    content.add(saleModel1);
                }
                count = CommonConstant.DEFAULT_VALUE_ZERO;
            }

            saleModel = new SalesManSaleModel();
            saleModel.setCustName("合计: ");
            saleModel.setBackAmount(ur.getBackAmountTotal().toString());
            saleModel.setGoodsNum(ur.getTotalGoodsNum() + "");
            saleModel.setSalesVolume(ur.getTotalSaleVolume().toString());
            saleModel.setSumCost(ur.getTotalCost().toString());
            saleModel.setGrossMargin(ur.getTotalGrossMargin().toString());
            saleModel.setGrossRate(ur.getTotalGrossRate().toString());
            content.add(saleModel);
        }

        item = new SalesManSaleModel();
        item.setCustName("合计: ");
        item.setBackAmount(mapData.get("backAmountTotal").toString());
        item.setGoodsNum(mapData.get("totalGoodsNum").toString());
        item.setSalesVolume(mapData.get("totalSaleVolume").toString());
        item.setSumCost(mapData.get("totalCost").toString());
        item.setGrossMargin(mapData.get("totalGrossMargin").toString());
        item.setGrossRate(mapData.get("totalGrossRate").toString());
        content.add(item);

        writer.write(content, sheet);

        List<SalesManSaleModel> dataEnd = new ArrayList<>();
        SalesManSaleModel itemEnd = new SalesManSaleModel();
        writer.write(dataEnd, sheet);

        dataEnd.add(itemEnd);
        itemEnd.setCreateTime("公司名称: 正诚文化");
//        itemEnd.setCustName("操作员: " + userVo.getRealName());
        writer.write(dataEnd, sheet);

        return writer;
    }

    public static ExcelWriter customerSaleExport (ServletOutputStream outputStream, Map<String, Object> mapData, CustomerQuery query){
        int count = 0;
        String value = ExportFileNameEnum.getExportFileNameEnum(query.getBusinessType()).getValue();

        ExcelWriter writer = new ExcelWriter(outputStream, ExcelTypeEnum.XLSX);

        Map<Integer, Integer> map = new HashMap<>();
        map.put(0,4000);
        map.put(1,4000);
        map.put(2,4000);
        map.put(3,2000);
        map.put(4,2800);
        map.put(5,4000);
        map.put(6,4000);
        map.put(7,2000);
        map.put(8,4000);
        map.put(9,4000);
        map.put(10,4000);

        String sheetName = ExportFileNameEnum.CUSTOMER_SALE_NAME.getValue() +" (" + value + ")";
        Sheet sheet = gainSheet(map, sheetName);

        Table title = new Table(1);
        title.setClazz(CustomerSaleModel.class);
        writer.write(null, sheet, title);

        List<CustomerSaleModel> data1 = new ArrayList<>();
        CustomerSaleModel item = new CustomerSaleModel();
        item.setCreateTime("日期: 由" + query.getStartDate());
        item.setSaleCode("至" + query.getEndDate());
        item.setGrossMargin("打印日期: ");
        item.setGrossRate(DateUtil.getDate(new Date()));
        data1.add(item);
        writer.write(data1, sheet);

        List<CustomerSaleModel> content = new ArrayList<>();

        List<CustomerReport> customerSupplierList = (List<CustomerReport>)mapData.get("customerReports");

        for (CustomerReport cs : customerSupplierList){

            List<SaleInfoReport> saleInfoReportList = cs.getSaleInfoReportList();

            if (CollectionUtils.isEmpty(saleInfoReportList)){
                continue;
            }

            CustomerSaleModel csm = new CustomerSaleModel();
            csm.setCreateTime("客户编号: " + cs.getCustCode());
            csm.setStockName("客户名称: " + cs.getCustName());
            content.add(csm);

            for (SaleInfoReport sir : saleInfoReportList){

                List<SaleGoodsReport> saleGoodsReports = sir.getSaleGoodsReportList();

                for (SaleGoodsReport sgr : saleGoodsReports){
                    if (count == CommonConstant.DEFAULT_VALUE_ZERO){
                        csm =  new CustomerSaleModel();
                        csm.setCreateTime(DateUtil.getDate(sir.getCreateTime()));
                        csm.setSaleCode(sir.getSaleCode());
                        csm.setStockName(sgr.getStockName());
                        csm.setGoodsNum(sgr.getGoodsNum().toString());
                        csm.setUnitPrice(sgr.getUnitPrice().toString());
                        csm.setSalesVolume(sgr.getSalesVolume().toString());
                        csm.setCost(sgr.getStandardBuyPrice().toString());
                        csm.setSumCost(sgr.getCost().toString());
                        csm.setGrossMargin(sgr.getGrossMargin().toString());
                        csm.setGrossRate(sgr.getGrossRate().toString());
                        count ++;
                    } else {
                        csm =  new CustomerSaleModel();
                        csm.setStockName(sgr.getStockName());
                        csm.setGoodsNum(sgr.getGoodsNum().toString());
                        csm.setUnitPrice(sgr.getUnitPrice().toString());
                        csm.setSalesVolume(sgr.getSalesVolume().toString());
                        csm.setCost(sgr.getStandardBuyPrice().toString());
                        csm.setSumCost(sgr.getCost().toString());
                        csm.setGrossMargin(sgr.getGrossMargin().toString());
                        csm.setGrossRate(sgr.getGrossRate().toString());
                    }
                    content.add(csm);
                }

                if (saleGoodsReports.size() > CommonConstant.DEFAULT_VALUE_ONE){
                    csm =  new CustomerSaleModel();
                    csm.setStockName("小计: ");
                    csm.setGoodsNum(sir.getSumGoodsNum() + "");
                    csm.setSalesVolume(sir.getSumSaleVolume().toString());
                    csm.setSumCost(sir.getSumCost().toString());
                    csm.setGrossMargin(sir.getSumGrossMargin().toString());
                    csm.setGrossRate(sir.getSumGrossRate().toString());
                    content.add(csm);
                }
                count = CommonConstant.DEFAULT_VALUE_ZERO;
            }

            csm =  new CustomerSaleModel();
            csm.setStockName("合计: ");
            csm.setGoodsNum(cs.getTotalGoodsNum() + "");
            csm.setSalesVolume(cs.getTotalSaleVolume().toString());
            csm.setSumCost(cs.getTotalCost().toString());
            csm.setGrossMargin(cs.getTotalGrossMargin().toString());
            csm.setGrossRate(cs.getTotalGrossRate().toString());
            content.add(csm);
        }

        CustomerSaleModel csmTotal =  new CustomerSaleModel();
        csmTotal.setStockName("总计: ");
        csmTotal.setGoodsNum(mapData.get("totalGoodsNum").toString());
        csmTotal.setSalesVolume(mapData.get("totalSaleVolume").toString());
        csmTotal.setSumCost(mapData.get("totalCost").toString());
        csmTotal.setGrossMargin(mapData.get("totalGrossMargin").toString());
        csmTotal.setGrossRate(mapData.get("totalGrossRate").toString());
        content.add(csmTotal);

        writer.write(content, sheet);

        List<CustomerSaleModel> dataEnd = new ArrayList<>();
        CustomerSaleModel itemEnd = new CustomerSaleModel();
        writer.write(dataEnd, sheet);

        dataEnd.add(itemEnd);
        itemEnd.setCreateTime("公司名称: 正诚文化");
//        itemEnd.setStockName("操作员: " + userVo.getRealName());
        writer.write(dataEnd, sheet);

        return writer;
    }

    /**
     * @description 销售员提成统计导出
     * @author yangyun
     * @date 2019/4/30 0030
     * @param outputStream
     * @param mapData
     * @param query
     * @return com.alibaba.excel.ExcelWriter
     */
    public static ExcelWriter SaleManDeductionExport (ServletOutputStream outputStream, Map<String, Object> mapData, UserInfoQuery query){
        int count = 0;
        String value = ExportFileNameEnum.getExportFileNameEnum(query.getBusinessType()).getValue();

        ExcelWriter writer = new ExcelWriter(outputStream, ExcelTypeEnum.XLSX);

        Map<Integer, Integer> map = new HashMap<>();
        map.put(0,4000);
        map.put(1,4000);
        map.put(2,4000);
        map.put(3,2000);
        map.put(4,2800);
        map.put(5,4000);
        map.put(6,4000);
        map.put(7,2000);
        map.put(8,4000);
        map.put(9,4000);


        String sheetName = ExportFileNameEnum.SALEMAN_SALE_DEDUCTION.getValue() +" (" + value + ")";
        Sheet sheet = gainSheet(map, sheetName);

        Table title = new Table(1);
        title.setClazz(SaleDeductionModel.class);
        writer.write(null, sheet, title);


        List<SaleDeductionModel> data1 = new ArrayList<>();
        SaleDeductionModel item = new SaleDeductionModel();
        item.setSaleMan("日期: 由" + query.getStartDate());
        item.setSaleGoodsNum("至" + query.getEndDate());
        item.setGrossMargin("打印日期: ");
        item.setGrossRate(DateUtil.getDate(new Date()));
        data1.add(item);
        writer.write(data1, sheet);

        List<SaleDeductionReport> saleDeductionReports = (List<SaleDeductionReport>)mapData.get("saleDeductionReports");

        List<SaleDeductionModel> content = new ArrayList<>();

        SaleDeductionModel sdm = null;

        for (SaleDeductionReport sdr : saleDeductionReports){
            sdm = new SaleDeductionModel();
            sdm.setSaleMan(sdr.getUserCode() + sdr.getRealName());
            sdm.setSaleGoodsNum(sdr.getSumGoodsNum() + "");
            sdm.setSaleVolume(sdr.getSumSaleVolume().toString());
            sdm.setSaleCost(sdr.getSumSaleCost().toString());
            sdm.setGrossMargin(sdr.getSumGrossMargin().toString());
            sdm.setGrossRate(sdr.getGrossRate().toString());
            sdm.setFee(sdr.getSumCost().toString());
            sdm.setPureProfit(sdr.getPureProfit().toString());
            sdm.setCommissionRate((sdr.getCommissionRate() == null ? 0 : sdr.getCommissionRate()) + "%");
            sdm.setCommission(sdr.getCommission().toString());
            content.add(sdm);
        }

        sdm = new SaleDeductionModel();
        sdm.setSaleMan("合计: ");
        sdm.setSaleGoodsNum(mapData.get("sumGoodsNum").toString());
        sdm.setSaleVolume(mapData.get("sumSaleVolume").toString());
        sdm.setSaleCost(mapData.get("sumSaleCost").toString());
        sdm.setGrossMargin(mapData.get("sumGrossMargin").toString());
        sdm.setFee(mapData.get("sumCost").toString());
        sdm.setPureProfit(mapData.get("sumPureProfit").toString());
        sdm.setCommission(mapData.get("sumCommission").toString());

        content.add(sdm);
        writer.write(content, sheet);

        List<SaleDeductionModel> dataEnd = new ArrayList<>();
        SaleDeductionModel itemEnd = new SaleDeductionModel();
        dataEnd.add(itemEnd);
        writer.write(dataEnd, sheet);
        itemEnd.setSaleMan("公司名称: 正诚文化");
//        itemEnd.setSaleVolume("操作员: " + userVo.getRealName());
        writer.write(dataEnd, sheet);

        return writer;
    }


}
