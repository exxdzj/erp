package com.exx.dzj.facade.homepage;

import com.exx.dzj.constant.CommonConstant;
import com.exx.dzj.entity.customer.InsuranceCustomer;
import com.exx.dzj.entity.market.SaleGoodsTop;
import com.exx.dzj.entity.market.SaleInfo;
import com.exx.dzj.entity.statistics.sales.HomePageReport;
import com.exx.dzj.entity.stock.StockBean;
import com.exx.dzj.enummodel.InsuranceCustomerLevelEnum;
import com.exx.dzj.service.customer.CustomerService;
import com.exx.dzj.service.purchaseticket.PurchaseTicketService;
import com.exx.dzj.service.salesticket.SalesTicketService;
import com.exx.dzj.service.statistics.sales.SaleTicketReportService;
import com.exx.dzj.service.stock.StockService;
import com.exx.dzj.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author yangyun
 * @create 2019-05-27-11:56
 */
@Component
public class HomePageFacade {

    @Autowired
    private CustomerService customerSupplierService;

    @Autowired
    private SalesTicketService salesTicketService;

    @Autowired
    private PurchaseTicketService purchaseTicketService;

    @Autowired
    private SaleTicketReportService saleTicketReportService;

    @Autowired
    private StockService stockInfoService;

    private static final BigDecimal ZERO = new BigDecimal(0);

    public Map<String, Object> queryTopData (){
        Map<String, Object> topData = new HashMap<>();

        // 客户数量
        int customerCount = customerSupplierService.countCustomerSupplier(CommonConstant.DEFAULT_VALUE_ONE);
        topData.put("customerCount", customerCount);

        //当日新增客户数
        int newlyCount = customerSupplierService.newlyIncreasedCutomerCount(CommonConstant.DEFAULT_VALUE_ONE);
        topData.put("newlyCount", newlyCount);

        // 今日销售额
        BigDecimal sumSalesOnDay = salesTicketService.querySumSalesOnDay();
        if (sumSalesOnDay == null){
            sumSalesOnDay = ZERO;
        }
        topData.put("sumSalesOnDay", sumSalesOnDay);

        // 当月销售额
        BigDecimal sumSalesOnMonth = salesTicketService.querySumSalesOnMonth();
        topData.put("sumSalesOnMonth", sumSalesOnMonth);

        // 今日额外支付费用 商品成本 + 商品编码以cb开头(快递费用)
        BigDecimal sumAdditionalSalesOnDay = salesTicketService.queryAdditionalSumSalesOnDay();

        if (sumAdditionalSalesOnDay == null){
            sumAdditionalSalesOnDay = ZERO;
        }

        // 当月额外支付费用
        BigDecimal sumAdditionalSalesOnMonth = salesTicketService.queryAdditionalSumSalesOnMonth();
        if (sumAdditionalSalesOnMonth == null){
            sumAdditionalSalesOnMonth = ZERO;
        }

        // 计算日利润
        BigDecimal subtractDay = sumSalesOnDay.subtract(sumAdditionalSalesOnDay);
        // 计算月利润
        BigDecimal subtractMonth = sumSalesOnMonth.subtract(sumAdditionalSalesOnMonth);

        topData.put("sumAdditionalSalesOnDay", subtractDay);
        topData.put("sumAdditionalSalesOnMonth", subtractMonth);

        //年度销售额
        BigDecimal sumSalesOnYear = salesTicketService.querySumSalesOnYear();
        topData.put("sumSalesOnYear", sumSalesOnYear);

        // 采购总金额
        BigDecimal sumPurchaseSalesOnYear = purchaseTicketService.sumPurchaseSalesOnYear();
//        topData.put("sumPurchaseSalesOnYear", sumPurchaseSalesOnYear);

        return topData;
    }

    public Map<String, Object> queryStasticsSalesForYear (){

        // 当前年份
        int currentyear = Integer.parseInt(DateUtil.getDateTime(CommonConstant.DATE_YEAR_PATTERN, new Date()));

        List<HomePageReport> homePageReports = saleTicketReportService.queryStasticsSalesForYear();

        Map<String, Map<String, List<HomePageReport>>> collect = homePageReports.stream().collect(Collectors.groupingBy(HomePageReport::getYear, Collectors.groupingBy(HomePageReport::getMonth)));

        List<String> years = new ArrayList<>();

        Object[] yearData = new Object[CommonConstant.HOME_PAGE_DEFAULT_YEAR];

        for (int i =0; i< CommonConstant.HOME_PAGE_DEFAULT_YEAR; i++){
            years.add(currentyear + "年");
            Map<String, List<HomePageReport>> stringListMap = collect.get(currentyear + "");
            --currentyear;

            if (stringListMap == null){
                List<BigDecimal> data2 = new ArrayList<>();
                for (int j = 1; j <= CommonConstant.HOME_PAGE_DEFAULT_MONTH; j++){
                    data2.add(ZERO);
                }
                yearData[i] = data2;
            } else {
                List<BigDecimal> data2 = new ArrayList<>();
                List<HomePageReport> homePageReports1 = null;
                for (int j = 1; j <= CommonConstant.HOME_PAGE_DEFAULT_MONTH; j++) {
                    homePageReports1 = stringListMap.get(j + "");
                    if (homePageReports1 == null) {
                        data2.add(ZERO);
                    } else {
                        data2.add(homePageReports1.get(0).getSumReceivableAccoun());
                    }
                }
                yearData[i] = data2;
            }
        }

        Map<String, Object> data = new HashMap<>();

        data.put("years", years);
        data.put("yearData", yearData);
        return data;
    }


    public Object[] queryStasticsSalesForMonth (){
        List<HomePageReport> homePageReports = saleTicketReportService.queryStasticsSalesForMonth();

        Map<String, List<HomePageReport>> collect = homePageReports.stream().collect(Collectors.groupingBy(HomePageReport::getDay));
        int day = DateUtil.getDayForMonth();

        Object[] monthData = new Object[1];
        List<BigDecimal> data2 = new ArrayList<>();
        for (int i = 1; i <= day; i++){
            List<HomePageReport> homePageReports1 = collect.get(i + "");
            if (homePageReports1 == null){
                data2.add(ZERO);
            } else {
                data2.add(homePageReports1.get(0).getSumReceivableAccoun());
            }
        }
        monthData[0]=data2;
        return monthData;
    }

    public List<InsuranceCustomer> queryStatisticsCustomer (){
        List<InsuranceCustomer> insuranceCustomers = customerSupplierService.queryStatisticsCustomer();


//        Map<String, List<InsuranceCustomer>> collect = insuranceCustomers.stream().collect(Collectors.groupingBy(InsuranceCustomer::getRankName));

        Map<String, List<InsuranceCustomer>> collect2 = insuranceCustomers.stream().collect(Collectors.groupingBy(InsuranceCustomer::getRankCode));

        List<InsuranceCustomer> dataList = new ArrayList<>();

        InsuranceCustomer ic = new InsuranceCustomer();
        ic.setCount(insuranceCustomers.stream().mapToInt(InsuranceCustomer::getCount).sum());
        ic.setRankName("保险类客户");
        ic.setRankCode("AAAA");

        dataList.add(ic);

//        for (){
//
//        }


        InsuranceCustomerLevelEnum[] values = InsuranceCustomerLevelEnum.values();
        String[] strs = new String[values.length];
        String code = null;
        String name = null;
        for (int i = 0; i< values.length; i++){
            code = values[i].getCode();
            name = values[i].getValue();
            strs[i] = name;
            List<InsuranceCustomer> insuranceCustomers2 = collect2.get(code);

            if (insuranceCustomers2 == null){
                ic = new InsuranceCustomer();
                ic.setRankName(name);
                ic.setRankCode(code);
                ic.setCount(CommonConstant.DEFAULT_VALUE_ZERO);
                dataList.add(ic);
            } else {
                InsuranceCustomer insuranceCustomer = insuranceCustomers2.get(0);
                insuranceCustomer.setRankName(name);
                insuranceCustomer.setRankCode(code);
                dataList.add(insuranceCustomer);
            }

//            List<InsuranceCustomer> insuranceCustomers1 = collect.get(name);
//            if (insuranceCustomers1 == null){
//                ic = new InsuranceCustomer();
//                ic.setCount(CommonConstant.DEFAULT_VALUE_ZERO);
//                ic.setRankName(name);
//                ic.setRankCode(values[i].getCode());
//                dataList.add(ic);
//            } else {
//                dataList.add(insuranceCustomers1.get(0));
//            }
        }

        return dataList;
    }

    public void queryIncreaseCutomerForMonth (){
        List<InsuranceCustomer> insuranceCustomers = customerSupplierService.queryIncreaseCutomerForMonth();

    }

    public List<SaleInfo> querySalesTop(){
        List<SaleInfo> list = salesTicketService.querySalesTop();
        return list;
    }
    public List<SaleInfo> salesUncollectedTop(){
        List<SaleInfo> list = salesTicketService.salesUncollectedTop();
        Iterator<SaleInfo> iterator = list.iterator();
        while (iterator.hasNext()){
            if (iterator.next().getReceivableAccoun().compareTo(ZERO) <= CommonConstant.DEFAULT_VALUE_ZERO){
                iterator.remove();
            }
        }
        return list;
    }

    public List<StockBean> stockInventoryWarning (){
        List<StockBean> stockBeanList = stockInfoService.stockInventoryWarning(CommonConstant.STOCK_INVENTORY_WARNING);
        return stockBeanList;
    }

    public List<SaleInfo> querySalesTicketTop (){
        List<SaleInfo> list = salesTicketService.querySalesTicketTop();

//        Iterator<SaleInfo> iterator = list.iterator();
//        while (iterator.hasNext()){
//            if (iterator.next().getCountTicket() ){
//
//            }
//        }
        return list;
    }

    public List<SaleGoodsTop> querySaleGoodsTop (String type){
        List<SaleGoodsTop> saleGoodsTops = salesTicketService.querySaleGoodsTop(type);
        return saleGoodsTops;
    }


}
