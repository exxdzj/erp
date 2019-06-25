package com.exx.dzj.facade.reportforms.sale;

import com.exx.dzj.constant.CommonConstant;
import com.exx.dzj.entity.bean.CustomerQuery;
import com.exx.dzj.entity.bean.DeptInfoQuery;
import com.exx.dzj.entity.bean.StockInfoQuery;
import com.exx.dzj.entity.bean.UserInfoQuery;
import com.exx.dzj.entity.market.SaleInfoQuery;
import com.exx.dzj.entity.market.SaleListInfo;
import com.exx.dzj.entity.statistics.sales.*;
import com.exx.dzj.enummodel.SaleListFieldEnum;
import com.exx.dzj.service.salesticket.SalesTicketService;
import com.exx.dzj.service.statistics.sales.SaleTicketReportService;
import com.exx.dzj.util.MathUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author yangyun
 * @create 2019-04-11-9:05
 */
@Component
public class SaleTicketReportFacade {

    @Autowired
    private SaleTicketReportService stockTypeReportService;

    @Autowired
    private SalesTicketService salesTicketService;

    /**
     * @description 销售单依库存统计
     * @author yangyun
     * @date 2019/4/16 0016
     * @param query
     * @return java.util.List<com.exx.dzj.entity.statistics.sales.StockTypeReport>
     */
    public List<StockTypeReport> statisticsSaleByInventory (StockInfoQuery query){
        List<StockBaseReport> stockTypeReports = stockTypeReportService.statisticsSaleByInventory(query);

        List<StockTypeReport> data = new ArrayList<>();
        if (stockTypeReports.size() <= 0) {
            return data;
        }

        Map<String, Map<String, List<StockBaseReport>>> collect = stockTypeReports.stream().collect(Collectors.groupingBy(StockBaseReport::getStockClassName, Collectors.groupingBy(StockBaseReport::getStockCode)));

        collect.keySet().stream().forEach(
                first -> {
                    StockTypeReport str = new StockTypeReport();
                    data.add(str);
                    str.setStockTypeName(first);
                    Map<String, List<StockBaseReport>> stringListMap = collect.get(first);
                    stringListMap.keySet().stream().forEach(
                            second -> {
                                StockInfoReport sir = new StockInfoReport();
                                sir.setStockCode(second);
                                sir.setStockName(stringListMap.get(second).get(0).getStockName());
                                List<StockBaseReport> stockBaseReports = stringListMap.get(second);
                                str.getStockReportList().add(sir);
                                stockBaseReports.stream().forEach(
                                        end -> {
                                            SaleGoodsReport sgr = new SaleGoodsReport();
                                            sgr.setSaleCode(end.getSaleCode());
                                            sgr.setSaleDate(end.getSaleDate());
                                            sgr.setStockAddress(end.getStockAddress());
                                            sgr.setGoodsNum(end.getGoodsNum());
                                            sgr.setUnitPrice(end.getUnitPrice());
                                            sgr.setStandardBuyPrice(end.getStandardBuyPrice());
                                            BigDecimal bigDecimal = new BigDecimal(end.getGoodsNum() == null ?0  : end.getGoodsNum());
                                            sgr.setSalesVolume(end.getUnitPrice().multiply(bigDecimal));
                                            sgr.setCost(end.getStandardBuyPrice().multiply(bigDecimal));
                                            sgr.setRealName(end.getRealName());
                                            sgr.setGrossMargin(sgr.getSalesVolume().subtract(sgr.getCost()));
                                            BigDecimal decimal = MathUtil.keepTwoBigdecimal(sgr.getGrossMargin(), sgr.getSalesVolume(), CommonConstant.DEFAULT_VALUE_FOUR);
                                            sgr.setGrossRate(decimal);
                                            sir.getSaleInfoReports().add(sgr);
                                        }
                                );
                            }
                    );
                }
        );

        for (StockTypeReport stockType : data) {
            List<StockInfoReport> stockReportList = stockType.getStockReportList();
                if (!CollectionUtils.isEmpty(stockReportList)) {
                for (StockInfoReport stockReport : stockReportList) {
                    List<SaleGoodsReport> saleInfoReports = stockReport.getSaleInfoReports();
                    if (!CollectionUtils.isEmpty(saleInfoReports)) {
                        double sum = saleInfoReports.stream().mapToDouble(SaleGoodsReport::getGoodsNum).sum();
                        BigDecimal saleIncome = saleInfoReports.stream().map(SaleGoodsReport::getSalesVolume).reduce(BigDecimal.ZERO, BigDecimal::add);
                        BigDecimal costTotal = saleInfoReports.stream().map(SaleGoodsReport::getCost).reduce(BigDecimal.ZERO, BigDecimal::add);
                        BigDecimal grossTotal = saleInfoReports.stream().map(SaleGoodsReport::getGrossMargin).reduce(BigDecimal.ZERO, BigDecimal::add);
                        stockReport.setCountTotal(sum);
                        stockReport.setSalesTotal(saleIncome);
                        stockReport.setCostTotal(costTotal);
                        stockReport.setGrossTotal(grossTotal);
                        double v =  MathUtil.keepTwoBigdecimal(grossTotal, saleIncome, CommonConstant.DEFAULT_VALUE_FOUR).doubleValue();
                        stockReport.setGrossRateTotal(v);
                    }
                }
            }
        }

        for (StockTypeReport stockType:data){
            List<StockInfoReport> stockReportList = stockType.getStockReportList();
            if (!CollectionUtils.isEmpty(stockReportList)) {
                double sum = 0.0;
                BigDecimal saleIncomeTotal = new BigDecimal(0);
                BigDecimal costTotal = new BigDecimal(0);
                BigDecimal grossTotal = new BigDecimal(0);
                for (StockInfoReport stockReport : stockReportList){
                    List<SaleGoodsReport> saleInfoReports = stockReport.getSaleInfoReports();
                    if (!CollectionUtils.isEmpty(saleInfoReports) && saleInfoReports.size() > 0){
                        if (stockReport.getCountTotal() == null){
                            continue;
                        }
                        sum += stockReport.getCountTotal();
                        saleIncomeTotal = saleIncomeTotal.add(stockReport.getSalesTotal());
                        costTotal = costTotal.add(stockReport.getCostTotal());
                        grossTotal = grossTotal.add(stockReport.getGrossTotal());
                    }
                }

                stockType.setCountTotal(sum);
                stockType.setSalesTotal(saleIncomeTotal);
                stockType.setCostTotal(costTotal);
                stockType.setGrossTotal(grossTotal);
                double v =  MathUtil.keepTwoBigdecimal(grossTotal, saleIncomeTotal, CommonConstant.DEFAULT_VALUE_FOUR).doubleValue();
                stockType.setGrossRateTotal(v);
            }

            filterStockType(data);
        }
        return data;
    }

    private void filterStockType (List<StockTypeReport> StockTypeReportList){
        Iterator<StockTypeReport> stockType = StockTypeReportList.iterator();
        BigDecimal zero = new BigDecimal(0);
        while (stockType.hasNext()){
            StockTypeReport stockTypeNext = stockType.next();
            BigDecimal costTotal = stockTypeNext.getCostTotal();
            if (zero.equals(costTotal)){
                stockType.remove();
            }
        }
    }

    /**
     * @description 销售单分析依销售员
     * @author yangyun
     * @date 2019/4/22 0022
     * @param query
     * @return java.util.List<com.exx.dzj.entity.statistics.sales.UserInfoReport>
     */
    public Map<String, Object> statisticsSaleBySalesMan (UserInfoQuery query){
        List<SalesmanBaseReport> salesmanBaseList = stockTypeReportService.statisticsSaleBySalesMan(query);
        List<UserInfoReport> data = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        map.put("userInfoReports", data);
        if (salesmanBaseList.size() <= CommonConstant.DEFAULT_VALUE_ZERO){
            return map;
        }
        Map<String,Map<String, List<SalesmanBaseReport>>> collect = salesmanBaseList.stream().collect(Collectors.groupingBy(SalesmanBaseReport::getUserCode, Collectors.groupingBy(SalesmanBaseReport::getSaleCode)));


        collect.keySet().stream().forEach(
                first -> {
                    UserInfoReport uir = new UserInfoReport();
                    data.add(uir);
                    uir.setUserCode(first);
                    Map<String, List<SalesmanBaseReport>> stringListMap = collect.get(first);
                    stringListMap.keySet().stream().forEach(
                            second -> {
                                List<SalesmanBaseReport> baseReports = stringListMap.get(second);
                                if (StringUtils.isEmpty(uir.getSalesmanCode())){
                                    uir.setSalesmanCode(baseReports.get(0).getSalesmanCode());
                                    uir.setRealName(baseReports.get(0).getRealName());
                                }

                                SaleInfoReport sir = new SaleInfoReport();
                                uir.getSaleInfoList().add(sir);
                                sir.setSaleCode(second);
                                sir.setSaleDate(baseReports.get(0).getSaleDate());
                                sir.setCustCode(baseReports.get(0).getCustCode());
                                sir.setCustName(baseReports.get(0).getCustName());
                                sir.setCollectedAmountTotal(baseReports.get(0).getCollectedAmount());
                                baseReports.stream().forEach(
                                        goods -> {
                                            SaleGoodsReport sgr = new SaleGoodsReport();
                                            sir.getSaleGoodsReportList().add(sgr);
                                            sgr.setStockName(goods.getStockName());
                                            sgr.setGoodsNum(goods.getGoodsNum());
                                            sgr.setUnitPrice(goods.getUnitPrice());
                                            BigDecimal salesVolume = goods.getUnitPrice().multiply(new BigDecimal(goods.getGoodsNum()));
                                            sgr.setSalesVolume(salesVolume);
                                            sgr.setStandardBuyPrice(goods.getStandardBuyPrice());
                                            BigDecimal cost = goods.getStandardBuyPrice().multiply(new BigDecimal(goods.getGoodsNum()));
                                            sgr.setCost(cost);
                                            BigDecimal subtract = salesVolume.subtract(cost);
                                            sgr.setGrossMargin(subtract);
                                            if (salesVolume.intValue() <= CommonConstant.DEFAULT_VALUE_ZERO){
                                                sgr.setGrossRate(new BigDecimal(CommonConstant.BIGDECIMAL_ZERO_STR));
                                            } else {
//                                                sgr.setGrossRate(subtract.divide(salesVolume, 2, BigDecimal.ROUND_HALF_UP));
                                                sgr.setGrossRate(MathUtil.keepTwoBigdecimal(subtract, salesVolume, CommonConstant.DEFAULT_VALUE_FOUR));
                                            }
                                        }
                                );
                            }
                    );
                }
        );

            for (UserInfoReport ur : data){

                double totalGoodsNum = 0.0;
                BigDecimal totalSaleVolume = new BigDecimal(0.0);
                BigDecimal totalCost = new BigDecimal(0.0);
                BigDecimal totalGrossMargin = new BigDecimal(0.0);

                List<SaleInfoReport> saleInfoList = ur.getSaleInfoList();
                if (!CollectionUtils.isEmpty(saleInfoList)){
                    BigDecimal collectedAmountTotal = saleInfoList.stream().map(SaleInfoReport::getCollectedAmountTotal).reduce(BigDecimal.ZERO, BigDecimal::add);
                    ur.setBackAmountTotal(collectedAmountTotal);
                    for (SaleInfoReport sr: saleInfoList){
                        List<SaleGoodsReport> saleGoodsReportList = sr.getSaleGoodsReportList();
                        if (!CollectionUtils.isEmpty(saleGoodsReportList)){
                            double sumGoodsNum = saleGoodsReportList.stream().mapToDouble(SaleGoodsReport::getGoodsNum).sum();
                            totalGoodsNum += sumGoodsNum;
                            sr.setSumGoodsNum(sumGoodsNum);

                            BigDecimal sumSaleVolume = saleGoodsReportList.stream().map(SaleGoodsReport::getSalesVolume).reduce(BigDecimal.ZERO, BigDecimal::add);
                            totalSaleVolume = totalSaleVolume.add(sumSaleVolume);
                            sr.setSumSaleVolume(sumSaleVolume);

                            BigDecimal sumCost = saleGoodsReportList.stream().map(SaleGoodsReport::getCost).reduce(BigDecimal.ZERO, BigDecimal::add);
                            totalCost = totalCost.add(sumCost);
                            sr.setSumCost(sumCost);

                            BigDecimal sumGrossMargin = saleGoodsReportList.stream().map(SaleGoodsReport::getGrossMargin).reduce(BigDecimal.ZERO, BigDecimal::add);
                            totalGrossMargin = totalGrossMargin.add(sumGrossMargin);
                            sr.setSumGrossMargin(sumGrossMargin);

                            sr.setSumGrossRate(MathUtil.keepTwoBigdecimal(sumGrossMargin, sumSaleVolume, CommonConstant.DEFAULT_VALUE_FOUR));
                        }
                    }
                }

                ur.setTotalGoodsNum(totalGoodsNum);
                ur.setTotalCost(totalCost);
                ur.setTotalGrossMargin(totalGrossMargin);
                ur.setTotalSaleVolume(totalSaleVolume);
                ur.setTotalGrossRate(MathUtil.keepTwoBigdecimal(totalGrossMargin, totalSaleVolume, CommonConstant.DEFAULT_VALUE_FOUR));
            }


        filterUserInfoReports(data);


        double totalGoodsNum = data.stream().mapToDouble(UserInfoReport::getTotalGoodsNum).sum();
        BigDecimal totalSaleVolume = data.stream().map(UserInfoReport::getTotalSaleVolume).reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal totalCost = data.stream().map(UserInfoReport::getTotalCost).reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal totalGrossMargin = data.stream().map(UserInfoReport::getTotalGrossMargin).reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal totalGrossRate = MathUtil.keepTwoBigdecimal(totalGrossMargin, totalSaleVolume, CommonConstant.DEFAULT_VALUE_FOUR);//毛利率总计
        BigDecimal backAmountTotal = data.stream().map(UserInfoReport::getBackAmountTotal).reduce(BigDecimal.ZERO, BigDecimal::add);

        map.put("totalGoodsNum", totalGoodsNum);
        map.put("totalSaleVolume", totalSaleVolume);
        map.put("totalCost", totalCost);
        map.put("totalGrossMargin", totalGrossMargin);
        map.put("totalGrossRate", totalGrossRate);
        map.put("backAmountTotal", backAmountTotal);

        return map;
    }

    private void filterUserInfoReports (List<UserInfoReport> userInfoReports){
        Iterator<UserInfoReport> iterator = userInfoReports.iterator();
        BigDecimal zero = new BigDecimal(0);
        while (iterator.hasNext()){
            UserInfoReport next = iterator.next();
            BigDecimal costTotal = next.getTotalCost();
            if (zero.equals(costTotal)){
                iterator.remove();
            }
        }
    }

    /**
     * @description 根据客户统计销售单
     * @author yangyun
     * @date 2019/4/24 0024
     * @param query
     * @return java.util.Map<java.lang.String,java.lang.Object>
     */
    public Map<String, Object> statisticsSalesTicketByCust (CustomerQuery query){
        List<CustomerBaseReport> customerBaselist = stockTypeReportService.querySalesTicketByCust(query);

        List<CustomerReport> data = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        map.put("customerReports", data);
        if (customerBaselist.size() <= CommonConstant.DEFAULT_VALUE_ZERO){
            return map;
        }

        Map<String, Map<String, List<CustomerBaseReport>>> collect = customerBaselist.stream().collect(Collectors.groupingBy(CustomerBaseReport::getCustCode, Collectors.groupingBy(CustomerBaseReport::getSaleCode)));

        collect.keySet().stream().forEach(
                first -> {
                    CustomerReport cr = new CustomerReport();

                    cr.setCustCode(first);

                    data.add(cr);
                    Map<String, List<CustomerBaseReport>> stringListMap = collect.get(first);
                    stringListMap.keySet().stream().forEach(
                            siReport -> {
                                List<CustomerBaseReport> customerBases = stringListMap.get(siReport);
                                SaleInfoReport sir = new SaleInfoReport();
                                cr.getSaleInfoReportList().add(sir);
                                sir.setSaleCode(siReport);
                                sir.setSaleDate(customerBases.get(0).getSaleDate());

                                if (StringUtils.isEmpty(cr.getCustName())){
                                    cr.setCustName(customerBases.get(0).getCustName());
                                }

                                customerBases.stream().forEach(
                                        goods -> {
                                            SaleGoodsReport sgr = new SaleGoodsReport();
                                            sir.getSaleGoodsReportList().add(sgr);
                                            sgr.setStockName(goods.getStockName());
                                            sgr.setGoodsNum(goods.getGoodsNum());
                                            sgr.setUnitPrice(goods.getUnitPrice());
                                            BigDecimal salesVolume = goods.getUnitPrice().multiply(new BigDecimal(goods.getGoodsNum()));
                                            sgr.setSalesVolume(salesVolume);
                                            sgr.setStandardBuyPrice(goods.getStandardBuyPrice());
                                            BigDecimal cost = goods.getStandardBuyPrice().multiply(new BigDecimal(goods.getGoodsNum()));
                                            sgr.setCost(cost);

                                            BigDecimal subtract = salesVolume.subtract(cost);
                                            sgr.setGrossMargin(subtract);
                                            if (salesVolume.intValue() <= CommonConstant.DEFAULT_VALUE_ZERO){
                                                sgr.setGrossRate(new BigDecimal(CommonConstant.BIGDECIMAL_ZERO_STR));
                                            } else {
                                                sgr.setGrossRate(MathUtil.keepTwoBigdecimal(subtract, salesVolume, CommonConstant.DEFAULT_VALUE_FOUR));
                                            }
                                        }
                                );
                            }
                    );

                }
        );

        for (CustomerReport cr : data){
            double totalGoodsNum = 0.0;
            BigDecimal totalSaleVolume = new BigDecimal(0.0);
            BigDecimal totalCost = new BigDecimal(0.0);
            BigDecimal totalGrossMargin = new BigDecimal(0.0);

            List<SaleInfoReport> saleInfoList = cr.getSaleInfoReportList();

            if (!CollectionUtils.isEmpty(saleInfoList)){
                for (SaleInfoReport sr: saleInfoList){
                    List<SaleGoodsReport> saleGoodsReportList = sr.getSaleGoodsReportList();
                    if (!CollectionUtils.isEmpty(saleGoodsReportList)){
                        double sumGoodsNum = saleGoodsReportList.stream().mapToDouble(SaleGoodsReport::getGoodsNum).sum();
                        totalGoodsNum += sumGoodsNum;
                        sr.setSumGoodsNum(sumGoodsNum);

                        BigDecimal sumSaleVolume = saleGoodsReportList.stream().map(SaleGoodsReport::getSalesVolume).reduce(BigDecimal.ZERO, BigDecimal::add);
                        totalSaleVolume = totalSaleVolume.add(sumSaleVolume);
                        sr.setSumSaleVolume(sumSaleVolume);

                        BigDecimal sumCost = saleGoodsReportList.stream().map(SaleGoodsReport::getCost).reduce(BigDecimal.ZERO, BigDecimal::add);
                        totalCost = totalCost.add(sumCost);
                        sr.setSumCost(sumCost);

                        BigDecimal sumGrossMargin = saleGoodsReportList.stream().map(SaleGoodsReport::getGrossMargin).reduce(BigDecimal.ZERO, BigDecimal::add);
                        totalGrossMargin = totalGrossMargin.add(sumGrossMargin);
                        sr.setSumGrossMargin(sumGrossMargin);

                        sr.setSumGrossRate(MathUtil.keepTwoBigdecimal(sumGrossMargin, sumSaleVolume, CommonConstant.DEFAULT_VALUE_FOUR));
                    }
                }
            }
            cr.setTotalGoodsNum(totalGoodsNum);
            cr.setTotalCost(totalCost);
            cr.setTotalGrossMargin(totalGrossMargin);
            cr.setTotalSaleVolume(totalSaleVolume);
            cr.setTotalGrossRate(MathUtil.keepTwoBigdecimal(totalGrossMargin, totalSaleVolume, CommonConstant.DEFAULT_VALUE_FOUR));
        }


        filterCustomerReports(data);

        double totalGoodsNum = data.stream().mapToDouble(CustomerReport::getTotalGoodsNum).sum();
        BigDecimal totalSaleVolume = data.stream().map(CustomerReport::getTotalSaleVolume).reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal totalCost = data.stream().map(CustomerReport::getTotalCost).reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal totalGrossMargin = data.stream().map(CustomerReport::getTotalGrossMargin).reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal totalGrossRate = MathUtil.keepTwoBigdecimal(totalGrossMargin, totalSaleVolume, CommonConstant.DEFAULT_VALUE_FOUR);//毛利率总计


        map.put("totalGoodsNum", totalGoodsNum);
        map.put("totalSaleVolume", totalSaleVolume);
        map.put("totalCost", totalCost);
        map.put("totalGrossMargin", totalGrossMargin);
        map.put("totalGrossRate", totalGrossRate);


        return map;
    }

    private void filterCustomerReports (List<CustomerReport> customerSupplierBeanList){
        Iterator<CustomerReport> iterator = customerSupplierBeanList.iterator();
        BigDecimal zero = new BigDecimal(0);
        while (iterator.hasNext()){
            CustomerReport next = iterator.next();
            BigDecimal costTotal = next.getTotalCost();
            if (zero.equals(costTotal)){
                iterator.remove();
            }
        }
    }

    /**
     * @description 销售员提成统计
     * @author yangyun
     * @date 2019/4/30 0030
     * @param query
     * @return java.util.Map<java.lang.String,java.lang.Object>
     */
    public Map<String, Object> statisticSalesDeductionBySaleman (UserInfoQuery query) {
        List<SaleDeductionReport> saleDeductionReports = stockTypeReportService.querySalesDeductionBySaleman(query);
        List<SaleDeductionReport> collect = saleDeductionReports.stream().filter(o -> !StringUtils.isEmpty(o.getUserCode())).collect(Collectors.toList());
        double sumGoodsNum = collect.stream().mapToDouble(SaleDeductionReport::getSumGoodsNum).sum();
        BigDecimal sumSaleVolume = collect.stream().map(SaleDeductionReport::getSumSaleVolume).reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal sumSaleCost = collect.stream().map(SaleDeductionReport::getSumSaleCost).reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal sumGrossMargin = collect.stream().map(SaleDeductionReport::getSumGrossMargin).reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal sumGrossRate = MathUtil.keepTwoBigdecimal(sumGrossMargin, sumSaleVolume, CommonConstant.DEFAULT_VALUE_FOUR);//毛利率总计
        BigDecimal sumCost = collect.stream().map(SaleDeductionReport::getSumCost).reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal sumPureProfit = collect.stream().map(SaleDeductionReport::getPureProfit).reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal sumCommission = collect.stream().map(SaleDeductionReport::getCommission).reduce(BigDecimal.ZERO, BigDecimal::add);

        Map<String, Object> map = new HashMap<>();
        map.put("saleDeductionReports", collect);
        map.put("sumGoodsNum", sumGoodsNum);
        map.put("sumSaleVolume", sumSaleVolume);
        map.put("sumSaleCost", sumSaleCost);
        map.put("sumGrossMargin", sumGrossMargin);
        map.put("sumGrossRate", sumGrossRate);
        map.put("sumCost", sumCost);
        map.put("sumPureProfit", sumPureProfit);
        map.put("sumCommission", sumCommission);

        return map;
    }

    public List<DeptSaleReport> selectionDeptInfo (String parentCode){
        List<DeptSaleReport> deptInfoList = stockTypeReportService.selectionDeptInfo(parentCode);

        return deptInfoList;
    }

    public List<DeptSaleReport> queryDeptSaleReport (DeptInfoQuery query){
        List<DeptSaleReport> deptSaleReports = stockTypeReportService.queryDeptSaleReport(query);
//        Map<String, List<DeptSaleReport>> map = deptSaleReports.stream().collect(Collectors.groupingBy(DeptSaleReport::getParentCode));
//        List<DeptSaleReport> dom = map.remove("");
        /*dom.stream().forEach(
            p -> {
                if (map.size() > 0){
                    List<DeptSaleReport> child = map.remove(p.getDeptCode());
                    p.getChildrenList().addAll(child);
                    if (map.size() > 0) {
                        child.stream().forEach(
                            p2 -> {
                                List<DeptSaleReport> child2 = map.remove(p2.getDeptCode());
                                p2.getChildrenList().addAll(child2);
                            }
                        );
                    }
                }
            }
        );*/

        BigDecimal sumCost = deptSaleReports.stream().map(DeptSaleReport::getSumCost).reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal sumSaleVolume = deptSaleReports.stream().map(DeptSaleReport::getSumSaleVolume).reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal sumSaleCost = deptSaleReports.stream().map(DeptSaleReport::getSumSaleCost).reduce(BigDecimal.ZERO, BigDecimal::add);
//        BigDecimal sumCost = deptSaleReports.stream().map(DeptSaleReport::getSumCost).reduce(BigDecimal.ZERO, BigDecimal::add);

        Map<String, Object> map = new HashMap<>();
        map.put("deptSaleReports", deptSaleReports);



        return deptSaleReports;
    }

    public List<SaleListInfo> querySalesListForIds(SaleInfoQuery query){

        List<SaleListInfo> saleInfoList = salesTicketService.querySalesListForIds(query);

//        for(SaleInfo si : saleInfoList){
//            SalesClassesEnum.getSalesClassesEnum(si.getIsReceipt()).
//        }

        return saleInfoList;
    }

    public List<SaleListInfo> exportSaleList (SaleInfoQuery query){
        List<SaleListInfo> saleInfoList = salesTicketService.exportSaleList(query);
        return saleInfoList;
    }

    public List<SaleExportFieldReport> queryExoprtFieldList (){
        List<SaleExportFieldReport> fieldList = new ArrayList<>();
        SaleExportFieldReport sep = null;
        for (SaleListFieldEnum temp : SaleListFieldEnum.values()){
            sep = new SaleExportFieldReport();
            sep.setKey(temp.getKey());
            sep.setName(temp.getName());
            fieldList.add(sep);
        }

        return fieldList;
    }

}
