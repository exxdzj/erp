package com.exx.dzj.facade.homepage;

import com.exx.dzj.constant.CommonConstant;
import com.exx.dzj.entity.customer.CustomerSupplierBean;
import com.exx.dzj.entity.customer.InsuranceCustomer;
import com.exx.dzj.entity.market.CompanySaleAccounYearOnYearInfo;
import com.exx.dzj.entity.market.CompanySumSaleAccounInfo;
import com.exx.dzj.entity.market.SaleGoodsTop;
import com.exx.dzj.entity.market.SaleInfo;
import com.exx.dzj.entity.statistics.sales.HomePageReport;
import com.exx.dzj.entity.stock.StockBean;
import com.exx.dzj.enummodel.CompanyEnum;
import com.exx.dzj.enummodel.InsuranceCustomerLevelEnum;
import com.exx.dzj.service.customer.CustomerService;
import com.exx.dzj.service.purchaseticket.PurchaseTicketService;
import com.exx.dzj.service.salesticket.SalesTicketService;
import com.exx.dzj.service.statistics.sales.SaleTicketReportService;
import com.exx.dzj.service.stock.StockService;
import com.exx.dzj.util.DateUtil;
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

    private void getCustomerCount (Map<String, Object> topData){
        // 客户数量
        int customerCount = customerSupplierService.countCustomerSupplier(CommonConstant.DEFAULT_VALUE_ONE);
        topData.put("customerCount", customerCount);

        //当日新增客户数
        int newlyCount = customerSupplierService.newlyIncreasedCutomerCount(CommonConstant.DEFAULT_VALUE_ONE);
        topData.put("newlyCount", newlyCount);
    }

    private void getCompanySaleVolumeOnDay (Map<String, Object> topData){
        BigDecimal sumSalesOnDay = new BigDecimal(0);
        List<SaleInfo> salesOnDayList = salesTicketService.querySumSalesOnDay();
        if (!CollectionUtils.isEmpty(salesOnDayList)){
            sumSalesOnDay = salesOnDayList.stream().map(SaleInfo::getReceivableAccoun).reduce(BigDecimal.ZERO, BigDecimal::add);
        }
        // 今日总销售额
        topData.put("sumSalesOnDay", sumSalesOnDay);

        // 各分公司今日销售额
        List<SaleInfo> companySalesOnDay = salesOnDayList.stream().filter(o -> StringUtils.isNotEmpty(o.getSubordinateCompanyCode())).collect(Collectors.toList());
        topData.put("companySalesOnDay", companySalesOnDay);
    }

    public Map<String, Object> queryTopData (){
        Map<String, Object> topData = new HashMap<>();

        // 客户总数和今日新增数
        getCustomerCount(topData);

        getCompanySaleVolumeOnDay(topData);

        // 当月分公司销售额
        List<SaleInfo> salesOnMonthList = salesTicketService.querySumSalesOnMonth("month");
        BigDecimal sumSalesOnMonth = new BigDecimal(0);
        if (!CollectionUtils.isEmpty(salesOnMonthList)){
            sumSalesOnMonth = salesOnMonthList.stream().map(SaleInfo::getReceivableAccoun).reduce(BigDecimal.ZERO, BigDecimal::add);
        }
        // 当月销售额
        topData.put("sumSalesOnMonth", sumSalesOnMonth);

        // 各分公司当月销售额处理
        List<SaleInfo> collect = salesOnMonthList.stream().filter(o -> StringUtils.isNotEmpty(o.getSubordinateCompanyCode())).collect(Collectors.toList());
        topData.put("companySalesOnMonth", collect);

        // 今日额外支付费用 商品成本 + 商品编码以cb开头(快递费用)
        List<SaleInfo> additionalSalesOnDayList = salesTicketService.queryAdditionalSumSalesOnDay();

        BigDecimal sumAdditionalSalesOnDay = new BigDecimal(0);
        if (!CollectionUtils.isEmpty(additionalSalesOnDayList)){
            sumAdditionalSalesOnDay = additionalSalesOnDayList.stream().map(SaleInfo::getReceivableAccoun).reduce(BigDecimal.ZERO, BigDecimal::add);
        }

        List<SaleInfo> companyAddionalSalesOnDay = additionalSalesOnDayList.stream().filter(o -> StringUtils.isNotEmpty(o.getSubordinateCompanyCode())).collect(Collectors.toList());

        List<SaleInfo> companyProfitSaleListOnDay = new ArrayList<>();

        List<SaleInfo> companySalesOnDay = (List<SaleInfo>)topData.get("companySalesOnDay");
        companySalesOnDay.stream().forEach(
                o -> {
                    companyAddionalSalesOnDay.stream().forEach(
                            b -> {
                                if (StringUtils.equals(o.getSubordinateCompanyCode(), b.getSubordinateCompanyCode())){
                                    SaleInfo s = new SaleInfo();
                                    BigDecimal subtract = o.getReceivableAccoun().subtract(b.getReceivableAccoun());
                                    s.setReceivableAccoun(subtract);
                                    s.setSubordinateCompanyName(o.getSubordinateCompanyName());
                                    s.setSubordinateCompanyCode(o.getSubordinateCompanyCode());
                                    companyProfitSaleListOnDay.add(s);
                                }
                            }
                    );
                }
        );

        // 各分公司今日利润
        topData.put("companyProfitSaleListOnDay", companyProfitSaleListOnDay);

        BigDecimal sumSalesOnDay = (BigDecimal)topData.get("sumSalesOnDay");
        // 各分公司今日总利润
        BigDecimal subtractDay = sumSalesOnDay.subtract(sumAdditionalSalesOnDay);
        topData.put("sumAdditionalSalesOnDay", subtractDay);

        // 当月额外支付费用
        List<SaleInfo> additionalSalesOnMonthList = salesTicketService.queryAdditionalSumSalesOnMonth();

        BigDecimal sumAdditionalSalesOnMonth = new BigDecimal(0);
        if (!CollectionUtils.isEmpty(additionalSalesOnMonthList)){
            sumAdditionalSalesOnMonth = additionalSalesOnMonthList.stream().map(SaleInfo::getReceivableAccoun).reduce(BigDecimal.ZERO, BigDecimal::add);
        }

        // 各公司额外费用
        List<SaleInfo> companyAddionalSalesOnMonth = additionalSalesOnMonthList.stream().filter(o -> StringUtils.isNotEmpty(o.getSubordinateCompanyCode())).collect(Collectors.toList());

        // 各分公司月利润
        List<SaleInfo> companyProfitSaleListOnMonth = new ArrayList<>();

        salesOnMonthList.stream().forEach(
                o -> {
                    companyAddionalSalesOnMonth.stream().forEach(
                            b -> {
                                if (StringUtils.equals(o.getSubordinateCompanyCode(), b.getSubordinateCompanyCode())){
                                    SaleInfo s = new SaleInfo();
                                    BigDecimal subtract = o.getReceivableAccoun().subtract(b.getReceivableAccoun());
                                    s.setReceivableAccoun(subtract);
                                    s.setSubordinateCompanyName(o.getSubordinateCompanyName());
                                    s.setSubordinateCompanyCode(o.getSubordinateCompanyCode());
                                    companyProfitSaleListOnMonth.add(s);
                                }
                            }
                    );
                }
        );

        // 各分公司当月利润
        topData.put("companyProfitSaleListOnMonth", companyProfitSaleListOnMonth);

        // 公司总月利润
        BigDecimal subtractMonth = sumSalesOnMonth.subtract(sumAdditionalSalesOnMonth);

        // 当月总利润
        topData.put("sumAdditionalSalesOnMonth", subtractMonth);

        // 各分公司年度销售额
        List<SaleInfo> sumSalesOnYearList = salesTicketService.querySumSalesOnYear();

        BigDecimal sumSalesOnYear = new BigDecimal(0);
        if (!CollectionUtils.isEmpty(sumSalesOnYearList)){
            sumSalesOnYear = sumSalesOnYearList.stream().map(SaleInfo::getReceivableAccoun).reduce(BigDecimal.ZERO, BigDecimal::add);
        }

        // 各分公司年度销售额
        List<SaleInfo> companySalesOnYearList = sumSalesOnYearList.stream().filter(o -> StringUtils.isNotEmpty(o.getSubordinateCompanyCode())).collect(Collectors.toList());
        topData.put("companySalesOnYearList", companySalesOnYearList);

        // 年度销售额
        topData.put("sumSalesOnYear", sumSalesOnYear);

        Map<String, Object> map = salesTicketService.queryYearGrowth();
        topData.put("yearToYearGrowth", "0.00%");
        if(map.size() > 0 && null != map.get("yearToYearGrowth")) {
            // 同比增长率
            topData.put("yearToYearGrowth", map.get("yearToYearGrowth"));
        }

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
//            if (i == CommonConstant.HOME_PAGE_DEFAULT_YEAR - 1){
//                years.add(currentyear + "年(同比销售额折线图)");
//
//            } else {
                years.add(currentyear + "年");
//            }

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

        Map<String, List<InsuranceCustomer>> collect2 = insuranceCustomers.stream().collect(Collectors.groupingBy(InsuranceCustomer::getRankCode));

        List<InsuranceCustomer> dataList = new ArrayList<>();

        InsuranceCustomer ic = new InsuranceCustomer();
        ic.setCount(insuranceCustomers.stream().mapToInt(InsuranceCustomer::getCount).sum());
        ic.setRankName("保险类客户");
        ic.setRankCode("AAAA");

//        dataList.add(ic);

        InsuranceCustomerLevelEnum[] values = InsuranceCustomerLevelEnum.values();

//        Arrays.stream(values).collect(Collectors.groupingBy(InsuranceCustomerLevelEnum::getCode));

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
        }

        Map<String, List<CustomerSupplierBean>> collect = queryNewAddCustomer().stream().collect(Collectors.groupingBy(CustomerSupplierBean::getRankCode));

        List<CustomerSupplierBean> b = null;
        for (InsuranceCustomer temp : dataList){
            b = collect.get(temp.getRankCode());
            if (org.apache.commons.collections.CollectionUtils.isNotEmpty(b)){
                temp.setNewCount(b.get(0).getBuyCount());
            }
        }

        return dataList;
    }

    public void queryIncreaseCutomerForMonth (){
        List<InsuranceCustomer> insuranceCustomers = customerSupplierService.queryIncreaseCutomerForMonth();

    }

    public List<SaleInfo> querySalesTop(String data){
        return salesTicketService.querySalesTop(data);
    }

    public List<SaleInfo> salesUncollectedTop(){
        List<SaleInfo> list = salesTicketService.salesUncollectedTop();
//        Iterator<SaleInfo> iterator = list.iterator();
//        while (iterator.hasNext()){
//            if (iterator.next().getReceivableAccoun().compareTo(ZERO) <= CommonConstant.DEFAULT_VALUE_ZERO){
//                iterator.remove();
//            }
//        }
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
//            if (iterator.next().getCountTicket() <= CommonConstant.DEFAULT_VALUE_ZERO){
//
//            }
//        }
        return list;
    }

    public List<SaleGoodsTop> querySaleGoodsTop (String type){
        List<SaleGoodsTop> saleGoodsTops = salesTicketService.querySaleGoodsTop(type);

        return saleGoodsTops;
    }

    public List<SaleInfo> queryCompanySalesForMonth (String type){
        List<SaleInfo> salesOnMonthList = salesTicketService.querySumSalesOnMonth(type);

        Map<String, SaleInfo> map = new HashMap<>();
        salesOnMonthList.stream().forEach(
                o -> {
                    map.put(o.getSubordinateCompanyCode(), o);
                }
        );

        CompanyEnum[] values = CompanyEnum.values();
        List<SaleInfo> data = new ArrayList<>();

        String code = "";
        SaleInfo saleInfo = null;
        for (CompanyEnum ce : values){
            code = ce.getCode();
            saleInfo = map.get(code);
            if (saleInfo == null){
                saleInfo = new SaleInfo();
                saleInfo.setSubordinateCompanyCode(code);
                saleInfo.setSubordinateCompanyName(ce.getValue());
                saleInfo.setReceivableAccoun(ZERO);
                data.add(saleInfo);
            } else {
                data.add(saleInfo);
            }
        }


        return data;
    }

    private void addCode (List<CompanySaleAccounYearOnYearInfo> data, Map<String, String> map){
        for (CompanySaleAccounYearOnYearInfo temp : data){
            map.put(temp.getUserCode(), temp.getRealName());
        }
    }

    public List<CompanySumSaleAccounInfo> queryCompanySaleAccounOnYearOnYear (){
        Map<String, String> userCodeMap = new HashMap<>();
        List<CompanySaleAccounYearOnYearInfo> thisYear = salesTicketService.queryCompanySaleAccounOnYearOnYear("thisYear");
        addCode(thisYear, userCodeMap);

        List<CompanySaleAccounYearOnYearInfo> lastYear = salesTicketService.queryCompanySaleAccounOnYearOnYear("lastYear");
        addCode(thisYear, userCodeMap);

        List<CompanySaleAccounYearOnYearInfo> beforeLastYear = salesTicketService.queryCompanySaleAccounOnYearOnYear("beforeLastYear");
        addCode(thisYear, userCodeMap);

        Set<String> strings = userCodeMap.keySet();

        Map<String, List<CompanySaleAccounYearOnYearInfo>> thisYearMap = thisYear.stream().collect(Collectors.groupingBy(CompanySaleAccounYearOnYearInfo::getUserCode));
        Map<String, List<CompanySaleAccounYearOnYearInfo>> lastYearMap = lastYear.stream().collect(Collectors.groupingBy(CompanySaleAccounYearOnYearInfo::getUserCode));
        Map<String, List<CompanySaleAccounYearOnYearInfo>> beforeLastYearMap = beforeLastYear.stream().collect(Collectors.groupingBy(CompanySaleAccounYearOnYearInfo::getUserCode));



        List<CompanySaleAccounYearOnYearInfo> list = new ArrayList<>();

        CompanySaleAccounYearOnYearInfo info = null;
        List<CompanySaleAccounYearOnYearInfo> thiss = null;
        List<CompanySaleAccounYearOnYearInfo> last = null;
        List<CompanySaleAccounYearOnYearInfo> beforeLast = null;
        for (String s : strings){

            thiss = thisYearMap.get(s);
            last = lastYearMap.get(s);
            beforeLast = beforeLastYearMap.get(s);

            if (thiss != null || last != null || beforeLast != null){
                info = new CompanySaleAccounYearOnYearInfo();
                list.add(info);
                info.setUserCode(s).setRealName(userCodeMap.get(s));

                if (org.apache.commons.collections.CollectionUtils.isNotEmpty(thiss)){
                    info.setThisYearAccoun(thiss.get(0).getThisYearAccoun())
                            .setSubordinateCompanyName(thiss.get(0).getSubordinateCompanyName()).setSubordinateCompanyCode(thiss.get(0).getSubordinateCompanyCode());
                } else {
                    info.setThisYearAccoun(BigDecimal.ZERO);
                }

                if (org.apache.commons.collections.CollectionUtils.isNotEmpty(last)){
                    info.setLastYearAccoun(last.get(0).getLastYearAccoun())
                            .setSubordinateCompanyName(last.get(0).getSubordinateCompanyName()).setSubordinateCompanyCode(last.get(0).getSubordinateCompanyCode());
                } else {
                    info.setLastYearAccoun(BigDecimal.ZERO);
                }

                if (org.apache.commons.collections.CollectionUtils.isNotEmpty(beforeLast)){
                    info.setBeforeLastYearAccoun(beforeLast.get(0).getBeforeLastYearAccoun())
                            .setSubordinateCompanyName(thiss.get(0).getSubordinateCompanyName()).setSubordinateCompanyCode(beforeLast.get(0).getSubordinateCompanyCode());
                } else {
                    info.setBeforeLastYearAccoun(BigDecimal.ZERO);
                }
            }
        }


        List<CompanySumSaleAccounInfo> listData = new ArrayList<>();

        CompanyEnum[] values = CompanyEnum.values();

        String code = "";


        List<CompanySaleAccounYearOnYearInfo> companySaleAccounYearOnYearInfos = null;
        List<CompanySaleAccounYearOnYearInfo> companySaleAccounYearOnYearInfos1 = null;
        List<CompanySaleAccounYearOnYearInfo> companySaleAccounYearOnYearInfos2 = null;
        List<CompanySaleAccounYearOnYearInfo> companySaleAccounYearOnYearInfos3 = null;
        CompanySumSaleAccounInfo sumInfo = null;

        BigDecimal sumThisYearAccoun = BigDecimal.ZERO;
        BigDecimal sumLastYearAccoun = BigDecimal.ZERO;
        BigDecimal sumBeforeLastYearAccoun = BigDecimal.ZERO;

        Map<String, List<CompanySaleAccounYearOnYearInfo>> collect = list.stream().collect(Collectors.groupingBy(CompanySaleAccounYearOnYearInfo::getSubordinateCompanyCode));

        Map<String, List<CompanySaleAccounYearOnYearInfo>> collect1 = thisYear.stream().collect(Collectors.groupingBy(CompanySaleAccounYearOnYearInfo::getSubordinateCompanyCode));
        Map<String, List<CompanySaleAccounYearOnYearInfo>> collect2 = lastYear.stream().collect(Collectors.groupingBy(CompanySaleAccounYearOnYearInfo::getSubordinateCompanyCode));
        Map<String, List<CompanySaleAccounYearOnYearInfo>> collect3 = beforeLastYear.stream().collect(Collectors.groupingBy(CompanySaleAccounYearOnYearInfo::getSubordinateCompanyCode));

        for (CompanyEnum ce : values){
            code = ce.getCode();
            companySaleAccounYearOnYearInfos1 = collect1.get(code);

            sumInfo = new CompanySumSaleAccounInfo();
            listData.add(sumInfo);
            sumInfo.setSubordinateCompanyCode(code).setSubordinateCompanyName(ce.getValue());
            if (org.apache.commons.collections4.CollectionUtils.isNotEmpty(companySaleAccounYearOnYearInfos1)){
                sumThisYearAccoun = MathUtil.keepTwoAccurate(companySaleAccounYearOnYearInfos1.stream().map(CompanySaleAccounYearOnYearInfo::getThisYearAccoun).reduce(BigDecimal.ZERO, BigDecimal::add));
            } else {
                sumThisYearAccoun = BigDecimal.ZERO;
            }

            companySaleAccounYearOnYearInfos2 = collect2.get(code);
            if (org.apache.commons.collections4.CollectionUtils.isNotEmpty(companySaleAccounYearOnYearInfos2)){
                sumLastYearAccoun = MathUtil.keepTwoAccurate(companySaleAccounYearOnYearInfos2.stream().map(CompanySaleAccounYearOnYearInfo::getLastYearAccoun).reduce(BigDecimal.ZERO, BigDecimal::add));
            } else {
                sumLastYearAccoun = BigDecimal.ZERO;
            }

            companySaleAccounYearOnYearInfos3 = collect3.get(code);
            if (org.apache.commons.collections4.CollectionUtils.isNotEmpty(companySaleAccounYearOnYearInfos3)){
                sumBeforeLastYearAccoun = MathUtil.keepTwoAccurate(companySaleAccounYearOnYearInfos3.stream().map(CompanySaleAccounYearOnYearInfo::getBeforeLastYearAccoun).reduce(BigDecimal.ZERO, BigDecimal::add));
            } else {
                sumBeforeLastYearAccoun = BigDecimal.ZERO;
            }

            companySaleAccounYearOnYearInfos = collect.get(code);

            if (org.apache.commons.collections4.CollectionUtils.isNotEmpty(companySaleAccounYearOnYearInfos)){
                Comparator<CompanySaleAccounYearOnYearInfo> com = (b, a) -> a.getThisYearAccoun().compareTo(b.getThisYearAccoun());

                List<CompanySaleAccounYearOnYearInfo> collect4 = companySaleAccounYearOnYearInfos.stream().sorted(com).collect(Collectors.toList());
                sumInfo.setList(collect4);
            }

            sumInfo.setSumThisYearAccoun(sumThisYearAccoun).setSumLastYearAccoun(sumLastYearAccoun).
                        setSumBeforeLastYearAccoun(sumBeforeLastYearAccoun);
        }

        return listData;
    }

    private static final String [] STR = {"zj01", "jl02"};
    public List<CustomerSupplierBean> queryNewAddCustomer (){

        List<CustomerSupplierBean> list = customerSupplierService.queryNewAddCustomer();
        Map<String, List<CustomerSupplierBean>> collect = list.stream().collect(Collectors.groupingBy(CustomerSupplierBean::getRankCode));
        List<CustomerSupplierBean> temp = null;
        CustomerSupplierBean bean = null;
        List<CustomerSupplierBean> data = new ArrayList<>();
        for (String s : STR){
            temp = collect.get(s);
            if (temp == null){
                bean = new CustomerSupplierBean();
                bean.setRankCode(s);
                bean.setRankName(InsuranceCustomerLevelEnum.getInsuranceCustomerLevelEnum(s).getValue());
                bean.setBuyCount(0);
                data.add(bean);
            } else {
                data.add(temp.get(0));
            }
        }
        return data;
    }

    public List<CompanySaleAccounYearOnYearInfo> queryCustomerCompanySaleAccoun(){
        List<CompanySaleAccounYearOnYearInfo> companySaleAccounYearOnYearInfos = salesTicketService.queryCustomerCompanySaleAccoun();
//        Comparator<CompanySaleAccounYearOnYearInfo> com = (b, a) -> a.getSubordinateCompanyCode().compareTo(b.getSubordinateCompanyCode());
//        List<CompanySaleAccounYearOnYearInfo> collect = companySaleAccounYearOnYearInfos.stream().sorted(com).collect(Collectors.toList());
        return companySaleAccounYearOnYearInfos;
    }
}
