package com.exx.dzj.facade.reportforms.sale;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.exx.dzj.bean.SaleDetailReportQuery;
import com.exx.dzj.constant.CommonConstant;
import com.exx.dzj.entity.bean.CustomerQuery;
import com.exx.dzj.entity.bean.DeptInfoQuery;
import com.exx.dzj.entity.bean.StockInfoQuery;
import com.exx.dzj.entity.bean.UserInfoQuery;
import com.exx.dzj.entity.market.SaleInfoQuery;
import com.exx.dzj.entity.market.SaleListInfo;
import com.exx.dzj.entity.statistics.sales.*;
import com.exx.dzj.entity.user.UserInfo;
import com.exx.dzj.enummodel.SaleListFieldEnum;
import com.exx.dzj.service.salesticket.SalesTicketService;
import com.exx.dzj.service.statistics.sales.SaleTicketReportService;
import com.exx.dzj.service.user.UserService;
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

    @Autowired
    private UserService userService;

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
                                            sgr.setCustName(end.getCustName());
                                            sgr.setStandardBuyPrice(end.getStandardBuyPrice());
                                            BigDecimal bigDecimal = new BigDecimal(end.getGoodsNum() == null ? 0 : end.getGoodsNum());
                                            sgr.setSalesVolume(end.getUnitPrice().multiply(bigDecimal).subtract(end.getDiscountAmount()));
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

        Comparator<SaleInfoReport> com = (a, b) -> a.getSaleDate().compareTo(b.getSaleDate());

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
                                sir.setId(baseReports.get(0).getId());
                                BigDecimal reduce = baseReports.stream().map(SalesmanBaseReport::getDiscountAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
                                sir.setCollectedAmountTotal(baseReports.get(0).getCollectedAmount().subtract(reduce));
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

                                uir.setSaleInfoList(uir.getSaleInfoList().stream().sorted(com).collect(Collectors.toList()));
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

                ur.setTotalGoodsNum(MathUtil.keepTwoAccurate(BigDecimal.valueOf(totalGoodsNum)).doubleValue());
                ur.setTotalCost(MathUtil.keepTwoAccurate(totalCost));
                ur.setTotalGrossMargin(MathUtil.keepTwoAccurate(totalGrossMargin));
                ur.setTotalSaleVolume(MathUtil.keepTwoAccurate(totalSaleVolume));
                ur.setTotalGrossRate(MathUtil.keepTwoBigdecimal(totalGrossMargin, totalSaleVolume, CommonConstant.DEFAULT_VALUE_FOUR));
            }


        filterUserInfoReports(data);


        double totalGoodsNum = data.stream().mapToDouble(UserInfoReport::getTotalGoodsNum).sum();
        BigDecimal totalSaleVolume = data.stream().map(UserInfoReport::getTotalSaleVolume).reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal totalCost = data.stream().map(UserInfoReport::getTotalCost).reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal totalGrossMargin = data.stream().map(UserInfoReport::getTotalGrossMargin).reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal totalGrossRate = MathUtil.keepTwoBigdecimal(totalGrossMargin, totalSaleVolume, CommonConstant.DEFAULT_VALUE_FOUR);//毛利率总计
        BigDecimal backAmountTotal = data.stream().map(UserInfoReport::getBackAmountTotal).reduce(BigDecimal.ZERO, BigDecimal::add);

        map.put("totalGoodsNum", MathUtil.keepTwoAccurate(BigDecimal.valueOf(totalGoodsNum)).doubleValue());
        map.put("totalSaleVolume", MathUtil.keepTwoAccurate(totalSaleVolume));
        map.put("totalCost", MathUtil.keepTwoAccurate(totalCost));
        map.put("totalGrossMargin", MathUtil.keepTwoAccurate(totalGrossMargin));
        map.put("totalGrossRate", MathUtil.keepTwoAccurate(totalGrossRate));
        map.put("backAmountTotal", MathUtil.keepTwoAccurate(backAmountTotal));

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
                                            sgr.setSalesVolume(salesVolume.subtract(goods.getDiscountAmount()));
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

    private List<UserInfo> querySalemanIdentityInfo (){
        List<UserInfo> userInfoList = userService.querySalemanIdentityInfo("1");
        return userInfoList;
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

        // 查询部分收款
        List<SaleDeductionReport> partiallySaleDeductionReports = null;
        Map<String, List<SaleDeductionReport>> collect = null;
        if (StringUtils.equals(String.valueOf(CommonConstant.DEFAULT_VALUE_TWO), query.getPaymentStatu())){
            partiallySaleDeductionReports = stockTypeReportService.queryPartiallySalesDeductionBySaleman(query);
            collect = partiallySaleDeductionReports.stream().collect(Collectors.groupingBy(SaleDeductionReport::getUserCode));
        }

        SaleDeductionReport report = null;
        List<SaleDeductionReport> data = new ArrayList<>();
        if (collect == null){
            for (SaleDeductionReport next : saleDeductionReports){
                report = new SaleDeductionReport();
                report.setUserCode(next.getUserCode());
                report.setSalesmanCode(next.getSalesmanCode());
                report.setRealName(next.getRealName());

                report.setSumGoodsNum(next.getSumGoodsNum());
                report.setSumSaleVolume(next.getSumSaleVolume());
                report.setSumSaleCost(next.getSumSaleCost());
                report.setGrossRate(next.getGrossRate());
                BigDecimal commissionRate = next.getCommissionRate();
                commissionRate = (commissionRate == null ? BigDecimal.ZERO : commissionRate).divide(BigDecimal.valueOf(100));
                BigDecimal sumCost = next.getSumCost() == null ? BigDecimal.ZERO : next.getSumCost();
                report.setSumCost(sumCost);
                BigDecimal sumGrossMargin = next.getSumGrossMargin() == null ? BigDecimal.ZERO : next.getSumGrossMargin();
                report.setSumGrossMargin(sumGrossMargin);
                report.setPureProfit(sumGrossMargin.subtract(sumCost));
                report.setCommission(commissionRate.multiply(next.getSumSaleVolume()));
                report.setCommissionRate(next.getCommissionRate());
                data.add(report);
            }
        } else { // 部分收款信息
            List<SaleDeductionReport> partailly = null;
            BigDecimal grossRate = null;
            BigDecimal sumSaleVolume = null;
            BigDecimal grossMargin = null;
            for (SaleDeductionReport next : saleDeductionReports){
                report = new SaleDeductionReport();
                partailly = collect.get(next.getUserCode());
                data.add(report);

                // 不管全部还是部分, 这部分信息不变
                report.setSumGoodsNum(next.getSumGoodsNum());
                report.setUserCode(next.getUserCode());
                report.setSalesmanCode(next.getSalesmanCode());
                report.setRealName(next.getRealName());

                //
                if (CollectionUtils.isEmpty(partailly)){
                    report.setSumSaleVolume(BigDecimal.ZERO);
                    report.setSumSaleCost(BigDecimal.ZERO);
                    report.setGrossRate(BigDecimal.ZERO);
                    report.setSumCost(BigDecimal.ZERO);
                    report.setSumGrossMargin(BigDecimal.ZERO);
                    report.setPureProfit(BigDecimal.ZERO);
                    report.setCommission(BigDecimal.ZERO);
                    report.setCommissionRate(BigDecimal.ZERO);
                    continue;
                } else {
                    // 销售收入
                    sumSaleVolume = partailly.get(0).getSumSaleVolume();
                    report.setSumSaleVolume(sumSaleVolume);

                    // 计算获取毛利率
                    grossRate = sumSaleVolume.intValue() == CommonConstant.DEFAULT_VALUE_ZERO ? BigDecimal.ZERO : next.getGrossRate();
                    report.setGrossRate(grossRate);

                    // 额外费用
                    report.setSumCost(partailly.get(0).getSumCost());

                    // 毛利润
                    grossMargin = sumSaleVolume.multiply(grossRate).divide(BigDecimal.valueOf(100));
                    report.setSumGrossMargin(grossMargin.divide(BigDecimal.valueOf(CommonConstant.DEFAULT_VALUE_ONE), CommonConstant.DEFAULT_VALUE_TWO, BigDecimal.ROUND_HALF_UP));

                    // 销售成本
                    report.setSumSaleCost(sumSaleVolume.subtract(grossMargin).divide(BigDecimal.valueOf(CommonConstant.DEFAULT_VALUE_ONE), CommonConstant.DEFAULT_VALUE_TWO, BigDecimal.ROUND_HALF_UP));

                    // 纯利润
                    report.setPureProfit(grossMargin.subtract(partailly.get(0).getSumCost()).divide(BigDecimal.valueOf(CommonConstant.DEFAULT_VALUE_ONE), CommonConstant.DEFAULT_VALUE_TWO, BigDecimal.ROUND_HALF_UP));

                    // 佣金率
                    report.setCommissionRate(partailly.get(0).getCommissionRate());

                    // 佣金
                    report.setCommission(partailly.get(0).getCommissionRate().multiply(report.getPureProfit().divide(BigDecimal.valueOf(100))));

                }

//                report.setSumSaleCost(next.getSumSaleCost());
//                BigDecimal commissionRate = next.getCommissionRate();
//                commissionRate = (commissionRate == null ? BigDecimal.ZERO : commissionRate).divide(BigDecimal.valueOf(100));
//                BigDecimal sumCost = next.getSumCost() == null ? BigDecimal.ZERO : next.getSumCost();
//                report.setSumCost(sumCost);
//                BigDecimal sumGrossMargin = next.getSumGrossMargin() == null ? BigDecimal.ZERO : next.getSumGrossMargin();
//                report.setSumGrossMargin(sumGrossMargin);
//
//                report.setCommission(commissionRate.multiply(next.getSumSaleVolume()));
            }
        }

        double sumGoodsNum = data.stream().mapToDouble(SaleDeductionReport::getSumGoodsNum).sum();
        BigDecimal sumSaleVolume = data.stream().map(SaleDeductionReport::getSumSaleVolume).reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal sumSaleCost = data.stream().map(SaleDeductionReport::getSumSaleCost).reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal sumGrossMargin = data.stream().map(SaleDeductionReport::getSumGrossMargin).reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal sumGrossRate = MathUtil.keepTwoBigdecimal(sumGrossMargin, sumSaleVolume, CommonConstant.DEFAULT_VALUE_FOUR);//毛利率总计
        BigDecimal sumCost = data.stream().map(SaleDeductionReport::getSumCost).reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal sumPureProfit = data.stream().map(SaleDeductionReport::getPureProfit).reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal sumCommission = data.stream().map(SaleDeductionReport::getCommission).reduce(BigDecimal.ZERO, BigDecimal::add);
        Map<String, Object> map = new HashMap<>();
//        data.addAll(dataEmpty);
        map.put("saleDeductionReports", data);
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

    public List<SaleListInfo> querySalesListForIds(SaleInfoQuery query, QueryWrapper queryWrapper){

        List<SaleListInfo> saleInfoList = salesTicketService.querySalesListForIds(query, queryWrapper);

//        for(SaleInfo si : saleInfoList){
//            SalesClassesEnum.getSalesClassesEnum(si.getIsReceipt()).
//        }

        return saleInfoList;
    }

    public List<SaleListInfo> exportSaleList (SaleInfoQuery query, QueryWrapper queryWrapper){
        List<SaleListInfo> saleInfoList = salesTicketService.exportSaleList(query, queryWrapper);
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

    public Map<String, Object> querySaleDetailList (SaleDetailReportQuery query) {
        List<SaleDetailBaseReport> saleDetails = stockTypeReportService.querySaleDetailList(query);

        Map<String, Object> map = new HashMap<>();
        List<SaleInfoReport> data = new ArrayList<>();
        map.put("listData", data);
        if (saleDetails.size() <= CommonConstant.DEFAULT_VALUE_ZERO){
            return map;
        }
        Map<String, List<SaleDetailBaseReport>> collect = saleDetails.stream().collect(Collectors.groupingBy(SaleDetailBaseReport::getSaleCode));


        collect.keySet().stream().forEach(
                first -> {
                    SaleInfoReport sir = new SaleInfoReport();
                    data.add(sir);
                    sir.setSaleCode(first);
                    List<SaleDetailBaseReport> saleDetailBaseReports = collect.get(first);
                    sir.setSaleDate(saleDetailBaseReports.get(0).getSaleDate());
                    sir.setCustCode(saleDetailBaseReports.get(0).getCustCode());
                    sir.setCustName(saleDetailBaseReports.get(0).getCustName());
                    sir.setUserCode(saleDetailBaseReports.get(0).getSalesmanCode());
                    sir.setRealName(saleDetailBaseReports.get(0).getRealName());
                    sir.setSumGoodsNum(saleDetailBaseReports.stream().mapToDouble(SaleDetailBaseReport::getGoodsNum).sum());
                    saleDetailBaseReports.stream().forEach(
                            goods -> {
                                SaleGoodsReport sgr = new SaleGoodsReport();
                                sir.getSaleGoodsReportList().add(sgr);
                                sgr.setStockCode(goods.getStockCode());
                                sgr.setStockName(goods.getStockName());
                                sgr.setStockAddress(goods.getStockAddress());
                                sgr.setGoodsNum(goods.getGoodsNum());
                                sgr.setUnitPrice(goods.getUnitPrice());

                                BigDecimal multiply = goods.getUnitPrice().multiply(new BigDecimal(goods.getGoodsNum() * goods.getExchangeRate()));

//                                sgr.setSalesVolume(multiply.subtract(goods.getDiscountAmount()));
                                sgr.setSalesVolume(multiply);
                            }
                    );

                }
        );

        for (SaleInfoReport s: data){
            s.setSumSaleVolume(s.getSaleGoodsReportList().stream().map(SaleGoodsReport::getSalesVolume).reduce(BigDecimal.ZERO, BigDecimal::add));
        }

        double sum = data.stream().mapToDouble(SaleInfoReport::getSumGoodsNum).sum();
        BigDecimal reduce = data.stream().map(SaleInfoReport::getSumSaleVolume).reduce(BigDecimal.ZERO, BigDecimal::add);
        map.put("sum", sum);
        map.put("reduce", reduce);


        return map;
    }

    public List<VIPCustomerLevelReport> queryVipCustomerlevelList (){
//        List<VIPCustomerLevelReport> vipCustomerLevelReports = stockTypeReportService.queryVipCustomerlevelList();
        List<VIPCustomerLevelReport> vipCustomerLevelReports = stockTypeReportService.queryVipCustomerlevelList2();

//        if (vipCustomerLevelReports.size() < CommonConstant.DEFAULT_VALUE_ONE){
//            return vipCustomerLevelReports;
//        }
//
//        BigDecimal grossMargin = BigDecimal.ZERO;
//        BigDecimal saleVolume = BigDecimal.ZERO;
//        for (VIPCustomerLevelReport b : vipCustomerLevelReports){
//            saleVolume = b.getSaleVolume();
//            grossMargin = saleVolume.subtract(b.getCost());
//            // 毛利
//            b.setGrossMargin(grossMargin);
//            // 毛利率
//            b.setGrossRate(MathUtil.keepTwoBigdecimal(grossMargin, saleVolume, 2));
//
//            // 纯利
//            b.setPrfit(grossMargin.subtract(b.getDiscountAmount()));
//            setCustomerLevel(b);
//        }
        List<VIPCustomerLevelReport> data = vipCustomerLevelReports.stream().filter(o -> !StringUtils.contains(o.getRealName(), "成本中心")).collect(Collectors.toList());

        return data;
    }

    private void setCustomerLevel (VIPCustomerLevelReport b){
        if (b.getBuyCount() >= 20 || b.getSaleVolume().subtract(new BigDecimal(60000)).intValue() >= 0){
            b.setCustGrade("砖石客户");
        } else if(b.getBuyCount() >= 8 || b.getSaleVolume().subtract(new BigDecimal(30000)).intValue() >= 0) {
            b.setCustGrade("铂金客户");
        } else if(b.getBuyCount() >= 4 || b.getSaleVolume().subtract(new BigDecimal(15000)).intValue() >= 0) {
            b.setCustGrade("黄金客户");
        }
    }

}
