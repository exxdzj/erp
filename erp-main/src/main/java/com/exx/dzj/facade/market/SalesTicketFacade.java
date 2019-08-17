package com.exx.dzj.facade.market;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.exx.dzj.annotation.SaleLog;
import com.exx.dzj.constant.CommonConstant;
import com.exx.dzj.entity.customer.CustomerSupplierBean;
import com.exx.dzj.entity.customer.CustomerSupplierInfo;
import com.exx.dzj.entity.dept.DeptInfoBean;
import com.exx.dzj.entity.market.*;
import com.exx.dzj.entity.statistics.sales.VIPCustomerLevelReport;
import com.exx.dzj.entity.stock.StockBean;
import com.exx.dzj.entity.stock.StockNumPrice;
import com.exx.dzj.facade.market.task.AsyncSaleTask;
import com.exx.dzj.facade.sys.BusEncodeFacade;
import com.exx.dzj.facade.user.UserTokenFacade;
import com.exx.dzj.model.SaleReceiptModel;
import com.exx.dzj.page.ERPage;
import com.exx.dzj.service.customer.CustomerService;
import com.exx.dzj.service.dictionary.DictionaryService;
import com.exx.dzj.service.salesgoodsdetail.SalesGoodsDetailService;
import com.exx.dzj.service.salesreceiptsdetail.SaleReceiptsDetailService;
import com.exx.dzj.service.salesticket.SalesTicketService;
import com.exx.dzj.service.stock.StockService;
import com.exx.dzj.service.sys.DeptService;
import com.exx.dzj.service.user.UserService;
import com.exx.dzj.util.DateUtil;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.Executor;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author yangyun
 * @create 2019-01-08-11:09
 */
@Component
public class SalesTicketFacade {

    @Autowired
    private SalesTicketService salesTicketService;

    @Autowired
    private SalesGoodsDetailService salesGoodsDetailService;

    @Autowired
    private SaleReceiptsDetailService saleReceiptsDetailService;

    @Autowired
    private DictionaryService dictionaryService;

    @Autowired
    private UserTokenFacade userTokenFacade;

    @Autowired
    private StockService stockInfoService;

    @Autowired
    private BusEncodeFacade busEncodeFacade;

    @Autowired
    private DeptService deptService;

    @Autowired
    private UserService salesmanService;

    @Autowired
    private CustomerService customerService;

    @Transactional
    public void saveSalesTicket3(SaleInfo saleInfo){
        Optional.of(saleInfo);
        List<SaleGoodsDetailBean> goodsDetailBeanList = saleInfo.getSaleGoodsDetailBeanList();
        List<SaleReceiptsDetails> receiptsDetailsList = saleInfo.getSaleReceiptsDetailsList();
        saleInfo.setSaleTicketType(CommonConstant.DEFAULT_VALUE_ONE);
        saleInfo.setIsEnable(CommonConstant.DEFAULT_VALUE_ONE);


//        if(!CollectionUtils.isEmpty(goodsDetailBeanList)){
//            goodsDetailBeanList = setGoodsSaleCode(goodsDetailBeanList, saleInfo.getSaleCode());
//
//            // 该部分代码用于金蝶导入金额计算, 优惠后金额
//            BigDecimal receivableAccoun = new BigDecimal(0);
//            BigDecimal hundred = new BigDecimal(100);
//            BigDecimal divide = new BigDecimal(0);
//            BigDecimal price = new BigDecimal(0);
//
//            for (SaleGoodsDetailBean gg : goodsDetailBeanList){
//                // 折扣额后
//                divide = hundred.subtract(gg.getDiscountRate()).divide(hundred);
//
//                price = gg.getUnitPrice().multiply(new BigDecimal(gg.getGoodsNum()));
//
//                receivableAccoun = receivableAccoun.add(price.multiply(divide));
//            }
//            saleInfo.setReceivableAccoun(receivableAccoun.setScale(2, BigDecimal.ROUND_HALF_UP));
//            // 该部分代码用于金蝶导入金额计算, 优惠后金额
//
//            salesGoodsDetailService.batchInsertSalesGoodsDetail(goodsDetailBeanList);
//        }
//        if (!CollectionUtils.isEmpty(receiptsDetailsList)){
//            receiptsDetailsList = setReceiptsSaleCode(receiptsDetailsList, saleInfo.getSaleCode());
//            saleReceiptsDetailService.batchInsertSalesReceiptsDeail(receiptsDetailsList);
//        }
        if (StringUtils.isNotEmpty(saleInfo.getInvoiceCode()) && saleInfo.getInvoiceCode().contains("签收")){

            saleInfo.setIsReceipt(1);
        }

        salesTicketService.saveSaleInfo(saleInfo);
    }

    @Transactional
    public void saveSalesTicket2(SaleInfo saleInfo){
        Optional.of(saleInfo);
        List<SaleGoodsDetailBean> goodsDetailBeanList = saleInfo.getSaleGoodsDetailBeanList();
        List<SaleReceiptsDetails> receiptsDetailsList = saleInfo.getSaleReceiptsDetailsList();
        saleInfo.setSaleTicketType(CommonConstant.DEFAULT_VALUE_ONE);
        saleInfo.setIsEnable(CommonConstant.DEFAULT_VALUE_ONE);


        if(!CollectionUtils.isEmpty(goodsDetailBeanList)){
            goodsDetailBeanList = setGoodsSaleCode(goodsDetailBeanList, saleInfo.getSaleCode());

            // 该部分代码用于金蝶导入金额计算, 优惠后金额
            BigDecimal receivableAccoun = new BigDecimal(0);
            BigDecimal hundred = new BigDecimal(100);
            BigDecimal divide = new BigDecimal(0);
            BigDecimal price = new BigDecimal(0);

            for (SaleGoodsDetailBean gg : goodsDetailBeanList){
                // 折扣额后
                divide = hundred.subtract(gg.getDiscountRate()).divide(hundred);

                price = gg.getUnitPrice().multiply(new BigDecimal(gg.getGoodsNum()));

                receivableAccoun = receivableAccoun.add(price.multiply(divide));
            }
            saleInfo.setReceivableAccoun(receivableAccoun.setScale(2, BigDecimal.ROUND_HALF_UP));
            // 该部分代码用于金蝶导入金额计算, 优惠后金额

            salesGoodsDetailService.batchInsertSalesGoodsDetail(goodsDetailBeanList);
        }
        if (!CollectionUtils.isEmpty(receiptsDetailsList)){
            receiptsDetailsList = setReceiptsSaleCode(receiptsDetailsList, saleInfo.getSaleCode());
            saleReceiptsDetailService.batchInsertSalesReceiptsDeail(receiptsDetailsList);
        }
        if (StringUtils.isNotEmpty(saleInfo.getInvoiceCode()) && saleInfo.getInvoiceCode().contains("签收")){

            saleInfo.setIsReceipt(1);
        }

        salesTicketService.saveSaleInfo(saleInfo);
    }

    /**
     * @description 新建销售单
     * @author yangyun
     * @date 2019/1/8 0008
     * @param saleInfo
     * @return void
     */
    @Transactional
    @SaleLog(operate = "更新销售单")
    public String saveSalesTicket(SaleInfo saleInfo){
        Optional.of(saleInfo);
        List<SaleGoodsDetailBean> goodsDetailBeanList = saleInfo.getSaleGoodsDetailBeanList();
        List<SaleReceiptsDetails> receiptsDetailsList = saleInfo.getSaleReceiptsDetailsList();
        setPaymentStatus(saleInfo);
        saleInfo.setSaleTicketType(CommonConstant.DEFAULT_VALUE_ONE);
        saleInfo.setIsEnable(CommonConstant.DEFAULT_VALUE_ONE);

        // 获取部门信息
        List<DeptInfoBean> deptInfos = deptService.queryDeptList();

        // 查询销售员部门编码
        String deptCode = salesmanService.querySalesmanDeptCode(saleInfo.getUserCode());

        // 销售单编码
        //String saleCode = getCode();
        String saleCode = getCode(saleInfo.getSaleDate());
        saleInfo.setSaleCode(saleCode);
        salesTicketService.saveSaleInfo(saleInfo);
        setSubordinateCompany(saleInfo, deptInfos, deptCode);

        if(!CollectionUtils.isEmpty(goodsDetailBeanList)){
            goodsDetailBeanList = setGoodsSaleCode(goodsDetailBeanList, saleInfo.getSaleCode());
            salesGoodsDetailService.batchInsertSalesGoodsDetail(goodsDetailBeanList);
        }
        if (!CollectionUtils.isEmpty(receiptsDetailsList)){
            receiptsDetailsList = setReceiptsSaleCode(receiptsDetailsList, saleInfo.getSaleCode());
            saleReceiptsDetailService.batchInsertSalesReceiptsDeail(receiptsDetailsList);
        } else {
            String collectedAmounts = saleInfo.getCollectedAmounts();
            BigDecimal sumCollectedAmount = saleInfo.getSumCollectedAmount();
            boolean b = sumCollectedAmount.compareTo(BigDecimal.ZERO) > 0;
            if (b){
                SaleReceiptsDetails srd = new SaleReceiptsDetails();
                srd.setCollectionAccount(collectedAmounts);
                srd.setCollectedAmount(sumCollectedAmount);
                srd.setSaleCode(saleInfo.getSaleCode());
                receiptsDetailsList.add(srd);
                saleReceiptsDetailService.batchInsertSalesReceiptsDeail(receiptsDetailsList);
            }
        }

        CustomerSupplierInfo customerSupplierInfo = customerService.queryVIPCustomerSupplierInfo(saleInfo.getCustCode());
        if (customerSupplierInfo == null){
            customerSupplierInfo = new CustomerSupplierInfo();
            customerSupplierInfo.setTotalVolume(saleInfo.getReceivableAccoun());
            customerSupplierInfo.setBuyCount(CommonConstant.DEFAULT_VALUE_ONE);
            setCustomerLevel(customerSupplierInfo);
        } else {
            customerSupplierInfo.setTotalVolume(customerSupplierInfo.getTotalVolume().add(saleInfo.getReceivableAccoun()));
            customerSupplierInfo.setBuyCount(CommonConstant.DEFAULT_VALUE_ONE + customerSupplierInfo.getBuyCount());
            setCustomerLevel(customerSupplierInfo);
        }
        customerSupplierInfo.setCustCode(saleInfo.getCustCode());
        customerService.updateBuyCountAndTotalVolume(customerSupplierInfo);
        return saleCode;
    }

    @Autowired
    private Executor asyncSaleExecutr;

    private void setSubordinateCompany (SaleInfo saleInfo, List<DeptInfoBean> deptInfos, String deptCode){
        AsyncSaleTask  asyncSaleTask = new AsyncSaleTask(saleInfo, deptInfos, deptCode, salesTicketService);
        asyncSaleExecutr.execute(asyncSaleTask);
    }

    /**
     * 最初的未使用数据权限规则查询销售单列表数据
     * @param query
     * @param pageNum
     * @param pageSize
     * @return
     */
    public ERPage<SaleInfo> querySalesTicketList(SaleInfoQuery query, int pageNum, int pageSize){
        PageHelper.startPage(pageNum, pageSize);
        List<SaleInfo> saleInfoList = salesTicketService.querySalesTicketList(query);
        ERPage<SaleInfo> saleInfoPage = new ERPage<>(saleInfoList);
        return saleInfoPage;
    }

    /**
     * 使用数据权限规则查询销售单列表数据
     * @param query
     * @param pageNum
     * @param pageSize
     * @return
     */
    public ERPage<SaleInfo> getSalesTicketList(SaleInfoQuery query, QueryWrapper queryWrapper, int pageNum, int pageSize){
        PageHelper.startPage(pageNum, pageSize);
        List<SaleInfo> saleInfoList = salesTicketService.getSalesTicketList(query, queryWrapper);
        ERPage<SaleInfo> saleInfoPage = new ERPage<>(saleInfoList);
        saleInfoList = null;
        return saleInfoPage;
    }

    public SaleInfo querySalesTicketById(Integer id){
        return salesTicketService.querySalesTicketById(id);
    }

    /**
     * 打印销售单数据
     * @param saleCodes
     * @return
     */
    public List<SaleInfo> printSalesTicket(List<String> saleCodes) {
        return salesTicketService.printSalesTicket(saleCodes);
    }

    private  List<SaleGoodsDetailBean> setGoodsSaleCode(List<SaleGoodsDetailBean> saleGoodsDetailBeans, String saleCode){
        for (SaleGoodsDetailBean s:saleGoodsDetailBeans) {
            s.setSaleCode(saleCode);
        }
        return saleGoodsDetailBeans;
    }

    private  List<SaleReceiptsDetails> setReceiptsSaleCode(List<SaleReceiptsDetails> saleReceiptsDetails, String saleCode){
        for (SaleReceiptsDetails s:saleReceiptsDetails) {
            s.setSaleCode(saleCode);
        }
        return saleReceiptsDetails;
    }

    private SaleInfo setPaymentStatus (SaleInfo saleInfo){
        if (StringUtils.isEmpty(saleInfo.getPaymentStatus()) && saleInfo.getSumCollectedAmount() == null){
            saleInfo.setPaymentStatus("cs01");
        } else {
            if (saleInfo.getSumCollectedAmount().doubleValue() == saleInfo.getReceivableAccoun().doubleValue()){
                saleInfo.setPaymentStatus("cs03");
            } else if (saleInfo.getSumCollectedAmount().doubleValue() == 0){
                saleInfo.setPaymentStatus("cs01");
            } else {
                saleInfo.setPaymentStatus("cs02");
            }
        }
        return saleInfo;
    }

    /**
     * @description 更新
     * @author yangyun
     * @date 2019/1/11 0011
     * @param saleInfo
     * @return void
     */
    @Transactional
    @SaleLog(operate = "更新销售单")
    public void updateSalesTicket(SaleInfo saleInfo){
//        saleInfo = calculatePrice(saleInfo);
        Optional.ofNullable(saleInfo);
        setPaymentStatus(saleInfo);

        if (saleInfo.getDiscountAmount() == null){
            saleInfo.setDiscountAmount(new BigDecimal(0));
        }

        SaleInfo oldSaleInfo = querySalesTicketById(saleInfo.getId());
        salesTicketService.updateSalesTicketById(saleInfo);


//        if (StringUtils.isEmpty(oldSaleInfo.getSubordinateCompanyCode())){
            List<DeptInfoBean> deptInfos = deptService.queryDeptList();

            // 查询销售员部门编码
            String deptCode = salesmanService.querySalesmanDeptCode(saleInfo.getUserCode());

            setSubordinateCompany(saleInfo, deptInfos, deptCode);
//        }

        List<SaleGoodsDetailBean> saleGoodsDetailBeanList = oldSaleInfo.getSaleGoodsDetailBeanList();
        List<SaleGoodsDetailBean> centreGoods = null;

        if (!CollectionUtils.isEmpty(saleGoodsDetailBeanList)){
            centreGoods = new ArrayList<>(saleGoodsDetailBeanList);
        }

        List<SaleGoodsDetailBean> goodsDetailBeanList = saleInfo.getSaleGoodsDetailBeanList();

        if(!CollectionUtils.isEmpty(goodsDetailBeanList)){
            if (!CollectionUtils.isEmpty(centreGoods)){
                // 获取修改的记录,并修改
                List<SaleGoodsDetailBean> finalCentreGoods = centreGoods;
                List<SaleGoodsDetailBean> collect = goodsDetailBeanList.stream().filter(o -> finalCentreGoods.stream().anyMatch(b -> StringUtils.equals(o.getId() + "", b.getId() + ""))).collect(Collectors.toList());
                if (!CollectionUtils.isEmpty(collect)){
                    collect = setGoodsSaleCode(collect, saleInfo.getSaleCode());
                    salesGoodsDetailService.batchUpdateSalesGoodsDetail(collect);
                }

                List<Integer> deleteSaleGoods = new ArrayList<>();
                List<SaleGoodsDetailBean> insertSaleGoods = new ArrayList<>();
                // 获取已删除元素,并删除数据库元素
                for(SaleGoodsDetailBean s : centreGoods){
                    if(!collect.contains(s)){
                        deleteSaleGoods.add(s.getId());
                    }
                }
                if (!CollectionUtils.isEmpty(deleteSaleGoods)){
                    salesGoodsDetailService.batchDeleteSalesGoodsDetail(deleteSaleGoods);
                }

                for(SaleGoodsDetailBean s : goodsDetailBeanList){
                    if(!collect.contains(s)){
                        insertSaleGoods.add(s);
                    }
                }

                if (!CollectionUtils.isEmpty(insertSaleGoods)){
                    insertSaleGoods = setGoodsSaleCode(insertSaleGoods, saleInfo.getSaleCode());
                    salesGoodsDetailService.batchInsertSalesGoodsDetail(insertSaleGoods);
                }
            } else{
                goodsDetailBeanList = setGoodsSaleCode(goodsDetailBeanList, saleInfo.getSaleCode());
                salesGoodsDetailService.batchInsertSalesGoodsDetail(goodsDetailBeanList);
            }
        } else {
            if (!CollectionUtils.isEmpty(saleGoodsDetailBeanList)){
                List<Integer> collect = saleGoodsDetailBeanList.stream().map(o -> o.getId()).collect(Collectors.toList());
                salesGoodsDetailService.batchDeleteSalesGoodsDetail(collect);
            }
        }


        List<SaleReceiptsDetails> saleReceiptsDetailsList = oldSaleInfo.getSaleReceiptsDetailsList();
        List<SaleReceiptsDetails> receiptsDetailsList = saleInfo.getSaleReceiptsDetailsList();
        List<SaleReceiptsDetails> centreReceipts = null;

        if (!CollectionUtils.isEmpty(saleReceiptsDetailsList)){
            centreReceipts = new ArrayList<>(saleReceiptsDetailsList);
        }

        if (!CollectionUtils.isEmpty(receiptsDetailsList)){
            if (!CollectionUtils.isEmpty(centreReceipts)){
                List<SaleReceiptsDetails> finalCentreReceipts = centreReceipts;
                List<SaleReceiptsDetails> collects = receiptsDetailsList.stream().filter(o -> finalCentreReceipts.stream().anyMatch(b -> StringUtils.equals(o.getId() + "", b.getId() + ""))).collect(Collectors.toList());
                if (!CollectionUtils.isEmpty(collects)){
                    collects = setReceiptsSaleCode(collects, saleInfo.getSaleCode());
                    saleReceiptsDetailService.batchUpdateSalesReceiptsDeail(collects);
                }

                List<Integer> deleteSaleReceipts = new ArrayList<>();
                List<SaleReceiptsDetails> insertSaleReceipts = new ArrayList<>();
                for(SaleReceiptsDetails e: centreReceipts){
                    if (!collects.contains(e)){
                        deleteSaleReceipts.add(e.getId());
                    }
                }
                if (!CollectionUtils.isEmpty(deleteSaleReceipts)){
                    saleReceiptsDetailService.batchDeleteSalesReceiptsDeail(deleteSaleReceipts);
                }

                for(SaleReceiptsDetails e: receiptsDetailsList){
                    if (!collects.contains(e)){
                        insertSaleReceipts.add(e);
                    }
                }
                if(!CollectionUtils.isEmpty(insertSaleReceipts)){
                    insertSaleReceipts = setReceiptsSaleCode(insertSaleReceipts, saleInfo.getSaleCode());
                    saleReceiptsDetailService.batchInsertSalesReceiptsDeail(insertSaleReceipts);
                }
            }else {
                receiptsDetailsList = setReceiptsSaleCode(receiptsDetailsList, saleInfo.getSaleCode());
                saleReceiptsDetailService.batchInsertSalesReceiptsDeail(receiptsDetailsList);
            }
        } else {
            if (!CollectionUtils.isEmpty(saleReceiptsDetailsList)){
                List<Integer> collect = saleReceiptsDetailsList.stream().map(o -> o.getId()).collect(Collectors.toList());
                saleReceiptsDetailService.batchDeleteSalesReceiptsDeail(collect);
            }
            String collectedAmounts = saleInfo.getCollectedAmounts();
            BigDecimal sumCollectedAmount = saleInfo.getSumCollectedAmount();
            boolean b = sumCollectedAmount.compareTo(BigDecimal.ZERO) > 0;
            if (b){
                SaleReceiptsDetails srd = new SaleReceiptsDetails();
                srd.setCollectionAccount(collectedAmounts);
                srd.setCollectedAmount(sumCollectedAmount);
                srd.setSaleCode(saleInfo.getSaleCode());
                receiptsDetailsList.add(srd);
                saleReceiptsDetailService.batchInsertSalesReceiptsDeail(receiptsDetailsList);
            }
        }
        CustomerSupplierInfo customerSupplierInfo = customerService.queryVIPCustomerSupplierInfo(saleInfo.getCustCode());
        if (customerSupplierInfo == null){
            customerSupplierInfo = new CustomerSupplierInfo();
            customerSupplierInfo.setTotalVolume(saleInfo.getReceivableAccoun());
            customerSupplierInfo.setBuyCount(CommonConstant.DEFAULT_VALUE_ONE);
            setCustomerLevel(customerSupplierInfo);
        } else {
            customerSupplierInfo.setTotalVolume(customerSupplierInfo.getTotalVolume().add(saleInfo.getReceivableAccoun()).subtract(oldSaleInfo.getReceivableAccoun()));
            setCustomerLevel(customerSupplierInfo);
        }
        customerSupplierInfo.setCustCode(saleInfo.getCustCode());
        customerService.updateBuyCountAndTotalVolume(customerSupplierInfo);
        /*if(null != saleInfo && ConvertUtils.isNotEmpty(saleInfo.getPaymentStatus())
                && (saleInfo.getPaymentStatus().equals("cs03")) || (saleInfo.getPaymentStatus().equals("cs02"))) {
            salesTicketService.syncSaleData(saleInfo);
        }*/
    }

    private void setCustomerLevel (CustomerSupplierInfo b){
        if (b.getBuyCount() >= 20 || b.getTotalVolume().subtract(new BigDecimal(60000)).intValue() >= 0){
            b.setGradeCode("vip003");
            b.setCustGrade("钻石客户");
        } else if(b.getBuyCount() >= 8 || b.getTotalVolume().subtract(new BigDecimal(30000)).intValue() >= 0) {
            b.setGradeCode("vip002");
            b.setCustGrade("铂金客户");
        } else if(b.getBuyCount() >= 5 || b.getTotalVolume().subtract(new BigDecimal(10000)).intValue() >= 0) {
            b.setGradeCode("vip001");
            b.setCustGrade("黄金客户");
        } else {
            b.setGradeCode("pt0001");
            b.setCustGrade("普通客户");
        }
    }

    /**
     * @description 用于判断状态
     * @author yangyun
     * @date 2019/1/11 0011
     * @param saleInfo
     * @return com.exx.dzj.entity.market.SaleInfo
     */
    private SaleInfo calculatePrice(SaleInfo saleInfo){
        // 计算已收款金额
        List<SaleReceiptsDetails> saleReceiptsDetailsList = saleInfo.getSaleReceiptsDetailsList();
        double sumCollectedAmount = 0.00;
        if(Optional.ofNullable(saleReceiptsDetailsList).isPresent()) {
            sumCollectedAmount = saleReceiptsDetailsList.stream()
                    .mapToDouble((o) -> o.getCollectedAmount().doubleValue())
                    .sum();
//            if
        }
        List<SaleGoodsDetailBean> saleGoodsDetailBeanList = saleInfo.getSaleGoodsDetailBeanList();
        return saleInfo;
    }

    /**
     * @description 销售单删除
     * @author yangyun
     * @date 2019/1/11 0011
     * @param id
     * @return void
     */
    @Transactional
    public void deleteSalesTicket(Integer id){
        SaleInfo saleInfo = salesTicketService.querySalesTicketById(id);
        Optional<SaleInfo> saleInfoOptional = Optional.of(saleInfo);
        salesTicketService.deleteSaleinfo(id);

        List<SaleReceiptsDetails> saleReceiptsDetails = Optional.of(saleInfo.getSaleReceiptsDetailsList()).get();
        Stream<SaleReceiptsDetails> stream = saleReceiptsDetails.stream();
        List<Integer> srdIds = stream.map(s -> s.getId()).collect(Collectors.toList());
        if(!CollectionUtils.isEmpty(srdIds)){
            saleReceiptsDetailService.batchDeleteSalesReceiptsDeail(srdIds);
        }

        List<SaleGoodsDetailBean> saleGoodsDetailBeans = Optional.of(saleInfo.getSaleGoodsDetailBeanList()).get();
        List<Integer> sgbIds = saleGoodsDetailBeans.stream().map((s) -> s.getId()).collect(Collectors.toList());
        if(!CollectionUtils.isEmpty(sgbIds)){
            salesGoodsDetailService.batchDeleteSalesGoodsDetail(sgbIds);
        }

        CustomerSupplierInfo customerSupplierInfo = customerService.queryVIPCustomerSupplierInfo(saleInfo.getCustCode());
        if (customerSupplierInfo == null){
            customerSupplierInfo = new CustomerSupplierInfo();
            customerSupplierInfo.setTotalVolume(BigDecimal.ZERO);
            customerSupplierInfo.setBuyCount(CommonConstant.DEFAULT_VALUE_ZERO);
            setCustomerLevel(customerSupplierInfo);
        } else {
            BigDecimal b = customerSupplierInfo.getTotalVolume().subtract(saleInfo.getReceivableAccoun()).doubleValue() >= 0 ? customerSupplierInfo.getTotalVolume().subtract(saleInfo.getReceivableAccoun()) : BigDecimal.ZERO;
            customerSupplierInfo.setTotalVolume(b);
            int i = customerSupplierInfo.getBuyCount() - CommonConstant.DEFAULT_VALUE_ONE >= 0 ? customerSupplierInfo.getBuyCount() - CommonConstant.DEFAULT_VALUE_ONE : 0;
            customerSupplierInfo.setBuyCount(i);
            setCustomerLevel(customerSupplierInfo);
        }
        customerSupplierInfo.setCustCode(saleInfo.getCustCode());
        customerService.updateBuyCountAndTotalVolume(customerSupplierInfo);
    }

    public List<SaleReceiptsDetails> querySaleReceviptDetailList(String saleCode){
        return saleReceiptsDetailService.querySaleReceviptDetailList(saleCode);
    }

    @Transactional
    public void importData (List<SaleInfo> saleInfos){
        List<String> importFailData = new ArrayList<>();
        String filename = "";
        for (SaleInfo s : saleInfos){
            try {
                filename = DateUtil.convertDateToString(s.getSaleDate(), "yyyy-MM");
                // 获取部门信息
                List<DeptInfoBean> deptInfos = deptService.queryDeptList();

                saveSalesTicket3(s);
                // 查询销售员部门编码
                List<String> strings = salesmanService.querySalesmanDeptCode2(s.getSalesmanCode());
                if (strings != null && strings.size() > 0){

                    String deptCode = strings.get(0);
                    setSubordinateCompany(s, deptInfos, deptCode);
                }



            } catch (Exception e){
                e.printStackTrace();
                importFailData.add(s.getSaleCode() + "\t" + e.getMessage());
            }
        }


        StringBuilder data = new StringBuilder("");
        importFailData.stream().forEach(
                o ->{
                    data.append(o).append("&");

                    System.out.println(o);
                }
        );
        try {
            writeString(data.toString(), filename);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void writeString (String str, String filename) throws Exception{
        FileWriter fw = new FileWriter("e://"+filename+".txt");
        BufferedWriter bw = new BufferedWriter(fw);
        bw.append(str);
        bw.close();
        fw.close();
    }

    public void addLogisticsInfo (LogisticsInfo logisticsInfo){
        if (StringUtils.isBlank(logisticsInfo.getChargeMethod())){
            logisticsInfo.setChargeMethod(CommonConstant.DEFAULT_VALUE_ZERO + "");
        }

        Integer id = logisticsInfo.getId();
        String token = userTokenFacade.queryUserCodeForToken(null);
        logisticsInfo.setCreateUser(token);
        if (id == null){
            addLogisticsInfoAndUpdateStockInventory(logisticsInfo);
//            saleReceiptsDetailService.addLogisticsInfo(logisticsInfo);
        } else {
            updateLogisticsGoodsInfo(logisticsInfo);
            saleReceiptsDetailService.updateLogisticsInfo(logisticsInfo);
        }
    }

    private void updateLogisticsGoodsInfo (LogisticsInfo logisticsInfo){
        List<String> stockCodeList = Arrays.asList(logisticsInfo.getStockCode().split("&"));
        List<SaleGoodsDetailBean> saleGoods = salesGoodsDetailService.queryGoodsForStock(logisticsInfo.getSaleCode(), stockCodeList);
        if (!CollectionUtils.isEmpty(saleGoods)) {
            StringBuilder stockName = new StringBuilder("");
            for (int i = 0; i < saleGoods.size(); i++) {
                stockName.append(saleGoods.get(i).getStockName());
                if (i != saleGoods.size() - 1) {
                    stockName.append("; ");
                }
            }

            logisticsInfo.setStockName(stockName.toString());
        }

    }

    @Transactional(rollbackFor = Exception.class)
    public void addLogisticsInfoAndUpdateStockInventory (LogisticsInfo logisticsInfo) {
        try {


            // 查询对应仓库商品信息
            StockNumPrice snp = new StockNumPrice();

            // 商品编号信息
            List<String> stockCodeList = Arrays.asList(logisticsInfo.getStockCode().split("&"));
            snp.setStockCodeList(stockCodeList);
//            snp.setStockAddressCode(logisticsInfo.getStockAddressCode());

            List<StockNumPrice> stockNumPriceList = stockInfoService.queryStockNumPirckList(snp);


            // 根据 销售单编号和商品编号,获取销售单卖出商品数量, 并对库存做修改
            // 查询销售单销售商品数量
            List<SaleGoodsDetailBean> saleGoods = salesGoodsDetailService.queryGoodsForStock(logisticsInfo.getSaleCode(), stockCodeList);


            if (!CollectionUtils.isEmpty(saleGoods)){
                StringBuilder stockName = new StringBuilder("");
                for (int i = 0; i < saleGoods.size(); i++){
                    stockName.append(saleGoods.get(i).getStockName());
                    if (i != saleGoods.size() - 1){
                        stockName.append("; ");
                    }
                }

                logisticsInfo.setStockName(stockName.toString());

                saleReceiptsDetailService.addLogisticsInfo(logisticsInfo);

                // 未减库存
                List<SaleGoodsDetailBean> noSubtract = new ArrayList<>();
                int isSubtractInventory = 1;
                for (SaleGoodsDetailBean sgdb : saleGoods){
                    isSubtractInventory = sgdb.getIsSubtractInventory();
                    if (CommonConstant.DEFAULT_VALUE_ZERO == isSubtractInventory){
                        noSubtract.add(sgdb);
                    }
                }

                // 还没减过库存
                if (!CollectionUtils.isEmpty(noSubtract)){
                    // 修改减库存状态为已减
                    List<Integer> collect = noSubtract.stream().map(o -> o.getId()).collect(Collectors.toList());
                    salesGoodsDetailService.batchUpdateSalesGoodsSubtractStatus(collect);

                    // 根据 存货编号获取商品信息, 减库存
                    if (stockNumPriceList != null && stockNumPriceList.size() > 0) {

                        String userCode = userTokenFacade.queryUserCodeForToken(null);
                        for (StockNumPrice snps : stockNumPriceList){
                            for (SaleGoodsDetailBean sgd : noSubtract){
                                if (StringUtils.equals(snps.getStockCode(), sgd.getStockCode())){
                                    // 减少库存
                                    StockBean stockInfo = new StockBean();
                                    stockInfo.setStockAddressCode(sgd.getStockAddressCode());
                                    stockInfo.setStockCode(sgd.getStockCode());
                                    stockInfo.setMinInventory(-sgd.getGoodsNum().intValue());
                                    stockInfoService.updateStockGoodsInventory(stockInfo);
                                    stockInfo.setUpdateUser(userCode);
                                    stockInfo.setSourceMode(CommonConstant.DEFAULT_VALUE_ZERO);
                                    stockInfoService.updateStockInfoSourceModel(stockInfo);
                                }
                            }
                        }
                    }
                }

            }
        } catch (Exception e){
            throw e;
        }

    }

    public List<LogisticsInfo> getLogisticsInfo (String saleCode) {
        List<LogisticsInfo> logisticsInfo = saleReceiptsDetailService.getLogisticsInfo(saleCode);
        List<LogisticsInfo> list = new ArrayList<>();
        for(LogisticsInfo li : logisticsInfo){
            if (li.getChargeMethod() != null && CommonConstant.DEFAULT_VALUE_ZERO == Integer.parseInt(li.getChargeMethod())){
                li.setChargeMethod("");
                list.add(li);
            } else {
                list.add(li);
            }
        }

        return list;
    }

    public List<SaleGoodsSelected> getSaleGoodsSelected (String saleCode){
        List<SaleGoodsSelected> saleGoodsSelected = saleReceiptsDetailService.getSaleGoodsSelected(saleCode);
        List<SaleGoodsSelected> saleGoodsSelecteds = new ArrayList<>();
        if (!CollectionUtils.isEmpty(saleGoodsSelected)){
//            saleGoodsSelecteds = saleGoodsSelected.stream().filter(o -> !StringUtils.startsWith(o.getStockCode(), "cb") ).collect(Collectors.toList());
            for (SaleGoodsSelected o : saleGoodsSelected){
                if (o == null){
                    continue;
                }
                if (StringUtils.isEmpty(o.getStockCode())){
                    saleGoodsSelecteds.add(o);
                    continue;
                }
                if (!StringUtils.startsWith(o.getStockCode().toUpperCase(), "CB")){
                    saleGoodsSelecteds.add(o);
                    continue;
                }
            }
        }
        return saleGoodsSelecteds;
    }

    public void logisticsInfoDel (Integer id){
        salesTicketService.logisticsInfoDel(id);
    }

    public String getCode() {
        String busType = "sale_ticket";
        String prefix = "Z";
        String code = busEncodeFacade.nextBusCode(busType, prefix);

        SaleInfoExample example = new SaleInfoExample();
        SaleInfoExample.Criteria criteria =example.createCriteria();
        criteria.andSaleCodeEqualTo(code);
        long count = salesTicketService.countByExample(example);
        while (count > 0) {
            getCode();
        }
        return code;
    }

    public String getCode(Date date) {
        String busType = "sale_ticket";
        String prefix = "Z";
        String code = busEncodeFacade.nextBusCode(busType, prefix, date);

        SaleInfoExample example = new SaleInfoExample();
        SaleInfoExample.Criteria criteria =example.createCriteria();
        criteria.andSaleCodeEqualTo(code);
        long count = salesTicketService.countByExample(example);
        while (count > 0) {
            getCode(date);
        }
        return code;
    }

    public List<SaleInfo> queryCustomerSalesToday (SaleInfo saleInfo){
        List<SaleInfo> list = salesTicketService.queryCustomerSalesToday(saleInfo);
        return list;
    }

    public List<SaleInfo> querySubordinateCompanySelect() {
        return salesTicketService.querySubordinateCompanySelect();
    }

    public ERPage<CustomerSupplierBean> queryCustomerSelect (int pageNum, int pageSize, String custName, Integer type){
        PageHelper.startPage(pageNum, pageSize);
        List<CustomerSupplierBean> customerSupplierBeans = customerService.queryCustomerSelect(custName, type);
        ERPage<CustomerSupplierBean> saleInfoPage = new ERPage<>(customerSupplierBeans);
        return saleInfoPage;
    }

    public int updatReceiptStatus (SaleInfo saleInfo){

        return salesTicketService.updatReceiptStatus(saleInfo);
    }

    public void insertImportGoodsData (List<SaleGoodsDetailBean> list, String dateStr){
//        salesGoodsDetailService.batchInsertSalesGoodsDetail(list);

        StringBuilder data = new StringBuilder("");
        StringBuilder failData = new StringBuilder("");

        String fileName = "e://sale-goods-insert/" + dateStr + ".txt";
        for (SaleGoodsDetailBean o : list){
            int count = salesTicketService.querySaleBySaleCode(o.getSaleCode());
            if (count > CommonConstant.DEFAULT_VALUE_ZERO){
                try {
                    salesGoodsDetailService.insertGoodsInfo(o);
                } catch (Exception e){
                    failData.append(o.getSaleCode() + e.getMessage() + "&");
                }
            } else {
                data.append(o.getSaleCode() + "&");
            }
        }

        if (StringUtils.isNotBlank(failData.toString())){
            writeString2(failData.toString(), fileName);
        }

        if (StringUtils.isNotBlank(data.toString())){
            fileName = "e://no-have-saleticket/" + dateStr + ".txt";
            writeString2(data.toString(), fileName);
        }


    }

    private void writeString2 (String str, String filename) {
        try {
            File file = new File(filename);
            if (!file.exists()){
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.append(str);
            bw.close();
            fw.close();
        } catch (Exception e){

        }
    }


    public void exportReceiptData(List<Object> objList){

        SaleReceiptModel model = null;
        SaleReceiptsDetails receipt = null;
        SaleInfo saleInfo = null;
        // 记录
        StringBuilder nonReceipt = new StringBuilder("");

//        String fileName = "e://";
        String dateStr = null;
        StringBuilder exeptData = new StringBuilder("");
        for (Object obj : objList){
            model = (SaleReceiptModel)obj;
            if (StringUtils.isEmpty(model.getSaleCode()) && model.getSaleDate() == null){
                continue;
            }
            if (StringUtils.isBlank(dateStr)){

                dateStr = DateUtil.convertDateToString(model.getSaleDate(), "yyyy-MM");
            }

            // 查询是否存在对应销售单
            int count = salesTicketService.querySaleBySaleCode(model.getSaleCode());
            if (count > CommonConstant.DEFAULT_VALUE_ZERO){
                try {
                    // 收款信息
                    receipt = new SaleReceiptsDetails();
                    receipt.setSaleCode(model.getSaleCode());
                    receipt.setCollectedAmount(model.getCollectedAmount());
                    // 插入收款信息
                    saleReceiptsDetailService.insertImportReceiptData(receipt);

                    // 修改销售单优惠后金额
//                    saleInfo = new SaleInfo();
//                    saleInfo.setSaleCode(model.getSaleCode());
//                    saleInfo.setReceivableAccoun(model.getReceivableAccoun());

                    salesTicketService.updateReceivableAccoun(model.getSaleCode(), model.getReceivableAccoun());
                } catch (Exception e) {
                    // 记录插入错误的数据
                    exeptData.append(model.getSaleCode()).append(e.getMessage()).append("&");
                }
                continue;
            }
            // 记录没有销售单的收款信息的销售单编码
            nonReceipt.append(model.getSaleCode() + "&");
        }

        // 将没有对应销售单收款信息的销售单编码写入本地, 以便导入后查看
        if (StringUtils.isNotBlank(nonReceipt.toString())){
            String fileName = "non-receipt-salecode/" + dateStr + ".txt";
            writeString2(nonReceipt.toString(), fileName);
        }

        if (StringUtils.isNotBlank(exeptData.toString())){
            String fileName = "exec-receipt-salecode/" + dateStr + ".txt";
            writeString2(exeptData.toString(), fileName);
        }
    }

    public void batchInsertLogistics (List<LogisticsInfo> list){
        salesTicketService.batchInsertLogistics(list);
    }
}
