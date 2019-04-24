package com.exx.dzj.facade.reportforms.sale;

import com.exx.dzj.constant.CommonConstant;
import com.exx.dzj.entity.bean.CustomerQuery;
import com.exx.dzj.entity.bean.StockInfoQuery;
import com.exx.dzj.entity.bean.UserInfoQuery;
import com.exx.dzj.entity.customer.CustomerSupplierBean;
import com.exx.dzj.entity.statistics.sales.*;
import com.exx.dzj.service.statistics.sales.SaleTicketReportService;
import com.exx.dzj.util.MathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

/**
 * @author yangyun
 * @create 2019-04-11-9:05
 */
@Component
public class SaleTicketReportFacade {

    @Autowired
    private SaleTicketReportService stockTypeReportService;

    /**
     * @description 销售单依库存统计
     * @author yangyun
     * @date 2019/4/16 0016
     * @param query
     * @return java.util.List<com.exx.dzj.entity.statistics.sales.StockTypeReport>
     */
    public List<StockTypeReport> statisticsSaleByInventory (StockInfoQuery query){
        List<StockTypeReport> stockTypeReports = stockTypeReportService.statisticsSaleByInventory(query);

        if (!CollectionUtils.isEmpty(stockTypeReports)) {
            for (StockTypeReport stockType:stockTypeReports) {
                List<StockInfoReport> stockReportList = stockType.getStockReportList();
                    if (!CollectionUtils.isEmpty(stockReportList)) {
                    for (StockInfoReport stockReport : stockReportList) {
                        List<SaleGoodsReport> saleInfoReports = stockReport.getSaleInfoReports();
                        if (!CollectionUtils.isEmpty(saleInfoReports)) {
                            double sum = saleInfoReports.stream().mapToDouble(SaleGoodsReport::getGoodsNum).sum();
                            BigDecimal saleIncome = saleInfoReports.stream().map(SaleGoodsReport::getSaleIncome).reduce(BigDecimal.ZERO, BigDecimal::add);
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

            for (StockTypeReport stockType:stockTypeReports){
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
            }
            filterStockType(stockTypeReports);
        }
        return stockTypeReports;
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
        List<UserInfoReport> userInfoReports = stockTypeReportService.statisticsSaleBySalesMan(query);

        if (!CollectionUtils.isEmpty(userInfoReports)){
            for (UserInfoReport ur : userInfoReports){

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
        }

        filterUserInfoReports(userInfoReports);


        double totalGoodsNum = userInfoReports.stream().mapToDouble(UserInfoReport::getTotalGoodsNum).sum();
        BigDecimal totalSaleVolume = userInfoReports.stream().map(UserInfoReport::getTotalSaleVolume).reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal totalCost = userInfoReports.stream().map(UserInfoReport::getTotalCost).reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal totalGrossMargin = userInfoReports.stream().map(UserInfoReport::getTotalGrossMargin).reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal totalGrossRate = MathUtil.keepTwoBigdecimal(totalGrossMargin, totalSaleVolume, CommonConstant.DEFAULT_VALUE_FOUR);//毛利率总计
        BigDecimal backAmountTotal = userInfoReports.stream().map(UserInfoReport::getBackAmountTotal).reduce(BigDecimal.ZERO, BigDecimal::add);

        Map<String, Object> map = new HashMap<>();
        map.put("userInfoReports", userInfoReports);
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
        List<CustomerReport> customerSupplierBeanList = stockTypeReportService.querySalesTicketByCust(query);
        if (!CollectionUtils.isEmpty(customerSupplierBeanList)){
            for (CustomerReport cr : customerSupplierBeanList){
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
        }

        filterCustomerReports(customerSupplierBeanList);

        double totalGoodsNum = customerSupplierBeanList.stream().mapToDouble(CustomerReport::getTotalGoodsNum).sum();
        BigDecimal totalSaleVolume = customerSupplierBeanList.stream().map(CustomerReport::getTotalSaleVolume).reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal totalCost = customerSupplierBeanList.stream().map(CustomerReport::getTotalCost).reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal totalGrossMargin = customerSupplierBeanList.stream().map(CustomerReport::getTotalGrossMargin).reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal totalGrossRate = MathUtil.keepTwoBigdecimal(totalGrossMargin, totalSaleVolume, CommonConstant.DEFAULT_VALUE_FOUR);//毛利率总计

        Map<String, Object> map = new HashMap<>();
        map.put("customerReports", customerSupplierBeanList);
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
}
