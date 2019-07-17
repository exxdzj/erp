package com.exx.dzj.controller.commons.dataproccess;

import com.exx.dzj.constant.CommonConstant;
import com.exx.dzj.controller.commons.DataImportUtil;
import com.exx.dzj.entity.accountatt.AccountAttributeBean;
import com.exx.dzj.entity.contactway.ContactWayBean;
import com.exx.dzj.entity.customer.CustomerSupplierBean;
import com.exx.dzj.entity.dept.DeptInfoBean;
import com.exx.dzj.entity.market.SaleGoodsDetailBean;
import com.exx.dzj.entity.market.SaleInfo;
import com.exx.dzj.entity.market.SaleReceiptsDetails;
import com.exx.dzj.entity.purchase.PurchaseGoodsDetailBean;
import com.exx.dzj.entity.purchase.PurchaseInfo;
import com.exx.dzj.entity.purchase.PurchaseReceiptsDetailsBean;
import com.exx.dzj.entity.stock.StockInfo;
import com.exx.dzj.entity.stock.StockNumPrice;
import com.exx.dzj.entity.user.UserInfo;
import com.exx.dzj.enummodel.PayStatusEnum;
import com.exx.dzj.facade.user.UserFacade;
import com.exx.dzj.model.CustomerModel;
import com.exx.dzj.model.PurchaseModel;
import com.exx.dzj.model.SaleModel;
import com.exx.dzj.model.StockModel;
import com.exx.dzj.util.enums.EnumsUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author yangyun
 * @create 2019-03-19-16:45
 */
public class ProccessImportDataUtil {

    @Autowired
    private UserFacade userFacade;

    private DeptInfoBean gainSubordinateCompanyInfo (List<DeptInfoBean> list, DeptInfoBean deptInfoBean){

        for (DeptInfoBean db : list){
            if (StringUtils.equals(db.getDeptCode(), deptInfoBean.getDeptCode())){
                deptInfoBean.setDeptCode(db.getParentCode());
                if (db.getIsCompare().equals(CommonConstant.DEFAULT_VALUE_ONE)){
                    return db;
                }
            }
        }

        return gainSubordinateCompanyInfo(list, deptInfoBean);
    }

    private static void setPurchaseInfo (Map<String, PurchaseInfo> purchaseInfoMap, Map<BigDecimal, PurchaseReceiptsDetailsBean> purchaseReceiptsMap, Map<String, PurchaseGoodsDetailBean> purchaseGoodsMap, String sharePro){
        if (!purchaseReceiptsMap.isEmpty()){
            PurchaseInfo s = purchaseInfoMap.get(sharePro);
            s.getPurchaseReceiptsDetailsBeans().addAll(purchaseReceiptsMap.values());
            purchaseReceiptsMap.clear();
        }

        if(!purchaseGoodsMap.isEmpty()){
            PurchaseInfo s = purchaseInfoMap.get(sharePro);
            s.getPurchaseGoodsDetailBeans().addAll(purchaseGoodsMap.values());
            purchaseGoodsMap.clear();
        }
    }


    private static void setPurchaseGoodsDetail (PurchaseGoodsDetailBean purchaseGoodsDetail, Map<String, String> stringMap){
        String stockAddress = purchaseGoodsDetail.getStockAddress();
        String s = stringMap.get(stockAddress);
        purchaseGoodsDetail.setStockAddressCode(s);
    }

    public static List<PurchaseInfo> proccessPurchaseInfo(List<Object> purchaseList, Map<String, UserInfo> userInfoMap, Map<String, String> stringMap, Map<String, CustomerSupplierBean> customerSupplierBeanMap){
        List<PurchaseInfo> list = new ArrayList<>();
        Map<String, PurchaseInfo> purchaseInfoMap = new ConcurrentHashMap<>();
        Map<BigDecimal, PurchaseReceiptsDetailsBean> purchaseReceiptsMap = new ConcurrentHashMap<>();
        Map<String, PurchaseGoodsDetailBean> purchaseGoodsMap = new ConcurrentHashMap<>();

        PurchaseModel info = null;
        String sharePro = "";
        Date purhcaseDate = null;

        for (Object o : purchaseList){
            info = (PurchaseModel)o;

            PurchaseInfo purchaseInfo = new PurchaseInfo();
            PurchaseReceiptsDetailsBean purchaseReceiptsDetails = new PurchaseReceiptsDetailsBean();
            PurchaseGoodsDetailBean purchaseGoodsDetail = new PurchaseGoodsDetailBean();

            String purhcaseCode = info.getPurchaseCode();

            if (purchaseInfoMap.containsKey(purhcaseCode)){
                BeanUtils.copyProperties(info, purchaseReceiptsDetails);

                if (info.getCollectedAmount().compareTo(BigDecimal.ZERO) > CommonConstant.DEFAULT_VALUE_ZERO){
                    purchaseReceiptsMap.put(info.getCollectedAmount(), purchaseReceiptsDetails);
                }

                BeanUtils.copyProperties(info, purchaseGoodsDetail);
                if (StringUtils.isNotEmpty(info.getStockCode())){
                    setPurchaseGoodsDetail(purchaseGoodsDetail, stringMap);
                    purchaseGoodsMap.put(info.getStockCode(), purchaseGoodsDetail);
                }

                continue;
            } else {
                setPurchaseInfo(purchaseInfoMap, purchaseReceiptsMap, purchaseGoodsMap, sharePro);
            }

            BeanUtils.copyProperties(info, purchaseInfo);


            purhcaseDate = info.getPurchaseDate();
            if (purhcaseDate != null){
                purchaseInfo.setPurchaseDate(new Timestamp(purhcaseDate.getTime()));
            }

            setPurchaseInfo(purchaseInfo, userInfoMap, stringMap, customerSupplierBeanMap);

            purchaseInfoMap.put(purhcaseCode, purchaseInfo);

            sharePro = purhcaseCode;


            BeanUtils.copyProperties(info, purchaseReceiptsDetails);
            if (info.getCollectedAmount().compareTo(BigDecimal.ZERO) > CommonConstant.DEFAULT_VALUE_ZERO){
                purchaseReceiptsMap.put(info.getCollectedAmount(), purchaseReceiptsDetails);
            }

            BeanUtils.copyProperties(info, purchaseGoodsDetail);
            if (StringUtils.isNotEmpty(info.getStockCode())){
                setPurchaseGoodsDetail(purchaseGoodsDetail, stringMap);
                purchaseGoodsMap.put(info.getStockCode(), purchaseGoodsDetail);
            }
        }

        setPurchaseInfo(purchaseInfoMap, purchaseReceiptsMap, purchaseGoodsMap, sharePro);
        list.addAll(purchaseInfoMap.values());

        return list;
    }

    private static void setPurchaseInfo (PurchaseInfo purchaseInfo, Map<String, UserInfo> userInfoMap, Map<String, String> stringMap,Map<String, CustomerSupplierBean> customerSupplierBeanMap){
        String custCode = purchaseInfo.getCustCode();
        if (StringUtils.isNotEmpty(custCode)){
            CustomerSupplierBean supplierBean = customerSupplierBeanMap.get(custCode);

            if (supplierBean != null){

                purchaseInfo.setCustCode(supplierBean.getCustCode());
            }
        }

        String userCode = purchaseInfo.getUserCode();
        if (StringUtils.isNotEmpty(userCode)){
            boolean flag = DataImportUtil.strIshasNum(userCode);
            if (flag){
                int index = 0;
                String substring = "";
                if (userCode.matches(".*" + REG + ".*")) {
                    index = userCode.split(REG).length;
                    if (index > 0){
                        index = userCode.split(REG)[0].length();
                        substring = userCode.substring(0, index);
                        purchaseInfo.setUserCode(userInfoMap.get(substring)  == null ? substring : userInfoMap.get(substring).getUserCode());
                        purchaseInfo.setSalesmanCode(substring);
                    }
                }
            } else {
                purchaseInfo.setSalesmanCode(purchaseInfo.getUserCode());
            }
        }
    }

    /**
     * @description 销售单excel 数据导入
     * @author yangyun
     * @date 2019/3/20 0020
     * @param data
     * @return java.util.List<com.exx.dzj.entity.market.SaleInfo>
     */
    public static List<SaleInfo> proccessSaleInfo (List<Object> data, Map<String, UserInfo> userInfoMap, Map<String, String> stringMap, Map<String, CustomerSupplierBean> customerSupplierBeanMap){
        List<SaleInfo> list = new ArrayList<>();
        Map<String, SaleInfo> saleInfoMap = new ConcurrentHashMap<>();
        Map<BigDecimal, SaleReceiptsDetails> saleReceiptsMap = new ConcurrentHashMap<>();
        Map<String, SaleGoodsDetailBean> saleGoodsMap = new ConcurrentHashMap<>();

        SaleModel info = null;
        String sharePro ="";
        Date saleDate = null;
        for (Object o : data) {
            info = (SaleModel)o;

            SaleInfo saleInfo = new SaleInfo();
            SaleReceiptsDetails saleReceiptsDetails = new SaleReceiptsDetails();
            SaleGoodsDetailBean saleGoodsDetail = new SaleGoodsDetailBean();

            String saleCode = info.getSaleCode();

            if (saleInfoMap.containsKey(saleCode)){
                BeanUtils.copyProperties(info, saleReceiptsDetails);
                if (info.getCollectedAmount().compareTo(BigDecimal.ZERO) > CommonConstant.DEFAULT_VALUE_ZERO){
                    saleReceiptsMap.put(info.getCollectedAmount(), saleReceiptsDetails);
                }

                BeanUtils.copyProperties(info, saleGoodsDetail);
                saleGoodsDetail.setRealSellPrice(saleGoodsDetail.getUnitPrice());
                saleGoodsDetail.setRemarks(info.getGoodsRemark());
                if (StringUtils.isNotEmpty(info.getStockCode())){
                    setSaleGoodsDetail(saleGoodsDetail, stringMap);
                    saleGoodsMap.put(info.getStockCode(), saleGoodsDetail);
                }

                continue;
            } else {

                setSaleInfo(saleInfoMap, saleReceiptsMap, saleGoodsMap, sharePro);

            }

            BeanUtils.copyProperties(info, saleInfo);


            saleInfo.setIsReceipt(2);
            saleDate = info.getSaleDate();
            if (saleDate != null){
                saleInfo.setSaleDate(new Timestamp(saleDate.getTime()));
            }
            setSalesInfo(saleInfo, userInfoMap, stringMap, customerSupplierBeanMap);

            saleInfoMap.put(saleCode, saleInfo);
            sharePro = saleCode;

            BeanUtils.copyProperties(info, saleReceiptsDetails);
            if (info.getCollectedAmount().compareTo(BigDecimal.ZERO) > CommonConstant.DEFAULT_VALUE_ZERO){
                saleReceiptsMap.put(info.getCollectedAmount(), saleReceiptsDetails);
            }

            BeanUtils.copyProperties(info, saleGoodsDetail);
            saleGoodsDetail.setRealSellPrice(saleGoodsDetail.getUnitPrice());
            saleGoodsDetail.setRemarks(info.getGoodsRemark());
            if (StringUtils.isNotEmpty(info.getStockCode())){
                setSaleGoodsDetail(saleGoodsDetail, stringMap);
                saleGoodsMap.put(info.getStockCode(), saleGoodsDetail);
            }
        }

        setSaleInfo(saleInfoMap, saleReceiptsMap, saleGoodsMap, sharePro);
        list.addAll(saleInfoMap.values());

        return list;
    }

    /**
     * @description 设置销售单相关信息
     * @author yangyun
     * @date 2019/3/25 0025
     * @param saleInfoMap
     * @param saleReceiptsMap
     * @param saleGoodsMap
     * @param sharePro
     * @return void
     */
    private static void setSaleInfo(Map<String, SaleInfo> saleInfoMap, Map<BigDecimal, SaleReceiptsDetails> saleReceiptsMap, Map<String, SaleGoodsDetailBean> saleGoodsMap, String sharePro){
        if (!saleReceiptsMap.isEmpty()){
            SaleInfo s = saleInfoMap.get(sharePro);
            s.getSaleReceiptsDetailsList().addAll(saleReceiptsMap.values());
            saleReceiptsMap.clear();
        }

        if(!saleGoodsMap.isEmpty()){
            SaleInfo s = saleInfoMap.get(sharePro);
            s.getSaleGoodsDetailBeanList().addAll(saleGoodsMap.values());
            saleGoodsMap.clear();
        }
    }

    /**
     * @description 设置商品地址信息
     * @author yangyun
     * @date 2019/3/25 0025
     * @param saleGoodsDetail
     * @param stringMap
     * @return void
     */
    private static void setSaleGoodsDetail (SaleGoodsDetailBean saleGoodsDetail, Map<String, String> stringMap){
        String stockAddress = saleGoodsDetail.getStockAddress();
        String stockAddressCode = stringMap.get(stockAddress);
        saleGoodsDetail.setStockAddressCode(stockAddressCode);
    }

    private static final String REG = "[\u4e00-\u9fa5]";

    /**
     * @description 设置关联销售员信息, 项目信息
     * @author yangyun
     * @date 2019/3/27 0027
     * @param saleInfo 销售单信息
     * @param userInfoMap 用户信息
     * @param stringMap 客户信息
     * @param customerSupplierBeanMap 客户供应商信息
     * @return void
     */
    public static void setSalesInfo (SaleInfo saleInfo, Map<String, UserInfo> userInfoMap, Map<String, String> stringMap, Map<String, CustomerSupplierBean> customerSupplierBeanMap){
        if (StringUtils.isNotEmpty(saleInfo.getSaleCode())){
            if (StringUtils.isNotEmpty(saleInfo.getUserCode())){
                switch (saleInfo.getUserCode()){
                    case "E行销深圳成本中心":
                        saleInfo.setUserCode("CB");
                        saleInfo.setSalesmanCode("CB");
                        break;
                    case "家庭成本中心":
                        saleInfo.setUserCode("CB1");
                        saleInfo.setSalesmanCode("CB1");
                        break;
                    case "E行销培训成本中心":
                        saleInfo.setUserCode("CB2");
                        saleInfo.setSalesmanCode("CB2");
                        break;
                    case "E行销北京成本中心":
                        saleInfo.setUserCode("CB3");
                        saleInfo.setSalesmanCode("CB3");
                        break;
                    case "E行销上海成本中心":
                        saleInfo.setUserCode("CB4");
                        saleInfo.setSalesmanCode("CB4");
                        break;
                    case "E行销温州成本中心":
                        saleInfo.setUserCode("CB5");
                        saleInfo.setSalesmanCode("CB5");
                        break;
                    case "E行销APP成本中心":
                        saleInfo.setUserCode("CB6");
                        saleInfo.setSalesmanCode("CB6");
                        break;
                    case "E行销西安成本中心":
                        saleInfo.setUserCode("CB7");
                        saleInfo.setSalesmanCode("CB7");
                        break;
                    default:
                        int index = -1;
                        String substring = "";
                        if (saleInfo.getUserCode().matches(".*" + REG + ".*")){

                            index = saleInfo.getUserCode().split(REG).length;
                            if (index > 0){
                                index = saleInfo.getUserCode().split(REG)[0].length();
                                substring = saleInfo.getUserCode().substring(0, index);
                                saleInfo.setUserCode(userInfoMap.get(substring)  == null ? substring : userInfoMap.get(substring).getUserCode());
                                saleInfo.setSalesmanCode(substring);
                            }

                        }
//                        String salesmanCode = getSalesmanCode(saleInfo.getUserCode());
//                        saleInfo.setUserCode(userInfoMap.get(salesmanCode)  == null ? salesmanCode : userInfoMap.get(salesmanCode).getUserCode());
//                        saleInfo.setSalesmanCode(salesmanCode);
                        break;
                }
            }

            PayStatusEnum payStatusEnum = EnumsUtils.getEnumObject(PayStatusEnum.class, e -> e.getValue().equals(saleInfo.getPaymentStatus())).get();
            saleInfo.setPaymentStatus(payStatusEnum.getKey());

            String saleProjectName = saleInfo.getSaleProject();
            String s = stringMap.get(saleProjectName);
            saleInfo.setSaleProject(s);
            saleInfo.setSaleProjectName(saleProjectName);

            CustomerSupplierBean customerSupplierBean = customerSupplierBeanMap.get(saleInfo.getCustCode());
            if (customerSupplierBean != null){
                saleInfo.setCustCode(customerSupplierBean.getCustCode());
                saleInfo.setCustPhoneNum(customerSupplierBean.getPhoneNum());
            }
        }
    }

    private static String getSalesmanCode (String data){
        int index = -1;
        String substring = "";
        if (data.matches(".*" + REG + ".*")){

            index = data.split(REG).length;
            if (index > 0){
                index = data.split(REG)[0].length();
                substring = data.substring(0, index);
//                saleInfo.setUserCode(userInfoMap.get(substring)  == null ? substring : userInfoMap.get(substring).getUserCode());
//                saleInfo.setSalesmanCode(substring);
            }
        }
        return substring;
    }

    /**
     * @description excel 数据导入, 客户 或 供应商 数据处理
     * @author yangyun
     * @date 2019/3/27 0027
     * @param data excel 解析后数据
     * @param stringMap 客户或供应商 等级信息
     * @param userInfoMap 用户销售员信息
     * @return java.util.Map<java.lang.String,java.util.List>
     */
    public static Map<String, List> proccessCustomerData(List<Object> data, Map<String, String> stringMap, Map<String, UserInfo> userInfoMap){
        Map<String, List> map = new HashMap<>();

        List<CustomerSupplierBean> customerSupplierList = new ArrayList<>();
        List<AccountAttributeBean> accountAttributeList = new ArrayList<>();
        List<ContactWayBean> contactWayList = new ArrayList<>();

        CustomerModel cm = null;
        for (Object o : data){
            cm = (CustomerModel)o;
            CustomerSupplierBean customerSupplier = new CustomerSupplierBean();
            AccountAttributeBean accountAttribute = new AccountAttributeBean();
            ContactWayBean contactWay = new ContactWayBean();

            BeanUtils.copyProperties(cm, customerSupplier);
            String salesmanCode = customerSupplier.getSalesmanCode();
            if (StringUtils.isNotEmpty(salesmanCode)){
                String code = getSalesmanCode(salesmanCode);
                UserInfo userInfo = userInfoMap.get(code);
                if (userInfo == null){
                    customerSupplier.setSalesmanCode(code);
                    customerSupplier.setUserCode(code);
                } else {
                    customerSupplier.setSalesmanCode(code);
                    customerSupplier.setUserCode(userInfo.getUserCode());
                }

            }
            customerSupplier.setCustLevel(stringMap.get(cm.getLevelName()));

            customerSupplier(customerSupplier, cm.getSalesmanCode(), userInfoMap, cm);

            customerSupplierList.add(customerSupplier);

            BeanUtils.copyProperties(cm, accountAttribute);
            accountAttributeList.add(accountAttribute);

            BeanUtils.copyProperties(cm, contactWay);
            contactWayList.add(contactWay);

            setTypeSource(customerSupplier, accountAttribute, contactWay, cm.getCustType());
        }

        map.put("customerSupplierList", customerSupplierList);
        map.put("accountAttributeList", accountAttributeList);
        map.put("contactWayList", contactWayList);
        return map;
    }

    /**
     * @description 设置类别: 供应商或客户, 设置所属关系
     * @author yangyun
     * @date 2019/3/27 0027
     * @param customerSupplier 客户或供应商
     * @param accountAttribute 关联账号信息
     * @param contactWay 关联联系信息
     * @param type excel 中每行数据类别
     * @return void
     */
    private static void setTypeSource (CustomerSupplierBean customerSupplier, AccountAttributeBean accountAttribute, ContactWayBean contactWay, String type){
        int custType = 0;
        int source = 0;
        if (StringUtils.isNotEmpty(type)){
            switch (type){
                case "既是供应商又是客户" :
                    custType = CommonConstant.CUST_TYPE_OF_ALL;
                    source = CommonConstant.CUST_TYPE_OF_ALL;
                    break;
                case "供应商" :
                    custType = CommonConstant.CUST_TYPE_OF_SUPPLIER;
                    source = CommonConstant.CUST_TYPE_OF_SUPPLIER;
                    break;
                case "客户" :
                    custType = CommonConstant.CUST_TYPE_OF_CUSTOMER;
                    source = CommonConstant.CUST_TYPE_OF_CUSTOMER;
                    break;
            }
        }
        customerSupplier.setCustType(custType);
        customerSupplier.setSource(source);

        accountAttribute.setSource(source);
        contactWay.setSource(source);
    }

    /**
     * @description 设置客户供应商 地区编码, 关联用户和销售员
     * @author yangyun
     * @date 2019/3/27 0027
     * @param customerSupplier
     * @param salesMan
     * @param userInfoMap
     * @param cm
     * @return void
     */
    private static void customerSupplier(CustomerSupplierBean customerSupplier, String salesMan, Map<String, UserInfo> userInfoMap, CustomerModel cm){
        customerSupplier.setIsEnable(CommonConstant.DEFAULT_VALUE_ONE);

        if (StringUtils.isNotEmpty(cm.getRegionCodeName())){
            customerSupplier.setRegionCode(cm.getRegionCodeName().substring(0, 3));
            customerSupplier.setRegionName(cm.getRegionCodeName().substring(3, cm.getRegionCodeName().length()));
        }
        if (StringUtils.isNotEmpty(salesMan)){
            UserInfo userInfo = userInfoMap.get(salesMan);
            if (userInfo != null){
                customerSupplier.setUserCode(userInfo.getUserCode());
                customerSupplier.setSalesmanCode(userInfo.getSalesmanCode());
            }
        }
    }

    /**
     * @description 存货数据导入处理
     * @author yangyun
     * @date 2019/3/27 0027
     * @param stockList
     * @return java.util.Map<java.lang.String,java.util.List>
     */
    public static Map<String, List> proccessStockInfo (List<Object> stockList, Map<String, String> stringMap){
        Map<String, List> map = new ConcurrentHashMap<>();
        List<StockInfo> stockInfoList = new ArrayList<>();
        List<StockNumPrice> stockNumPriceList = new ArrayList<>();

        StockModel model = null;
        for (Object o : stockList) {
            model = (StockModel) o;
            StockInfo stockInfo = new StockInfo();
            StockNumPrice stockNumPrice = new StockNumPrice();

            BeanUtils.copyProperties(model, stockInfo);
            BeanUtils.copyProperties(model, stockNumPrice);
            setPrice(stockNumPrice);
            setStockNatureStatu(model, stockInfo);
            setStockClass(stockInfo, stringMap);
            setStockAddress(stockNumPrice, stringMap);

            stockInfoList.add(stockInfo);
            stockNumPriceList.add(stockNumPrice);
        }

        map.put("stockInfoList", stockInfoList);
        map.put("stockNumPriceList", stockNumPriceList);

        return map;
    }

    /**
     * @description 设置存货性质和状态
     * @author yangyun
     * @date 2019/3/27 0027
     * @param model
     * @param stockInfo
     * @return void
     */
    private static void setStockNatureStatu (StockModel model, StockInfo stockInfo){
        String modelNature = model.getNature();
        String nature = null;
        if (StringUtils.isNotEmpty(modelNature)){
            switch (modelNature){
                case "原材料" :
                    nature = "1";
                    break;
                case "在产品" :
                    nature = "2";
                    break;
                case "产成品" :
                    nature = "3";
                    break;
                case "服务项目" :
                    nature = "4";
                    break;
            }

            stockInfo.setNature(nature);
        }

        String status = model.getStatus();
        if (StringUtils.isNotEmpty(status)){
            int isEnable = "使用中".equals(status) ? CommonConstant.DEFAULT_VALUE_ONE : CommonConstant.DEFAULT_VALUE_ZERO;
            stockInfo.setIsEnable(isEnable);
            stockInfo.setIsShelves(isEnable);
        }
    }

    private static void setStockClass(StockInfo stockInfo, Map<String, String> stringMap) {
        String aClass = stockInfo.getStockClass();
        String stockAddress = stockInfo.getStockAddress();
        if (StringUtils.isNotEmpty(aClass)){
            String value = stringMap.get(aClass);
            stockInfo.setStockClass(value);
            stockInfo.setStockClassName(aClass);
        }
        if (StringUtils.isNotEmpty(stockAddress)){
            String s = stringMap.get(stockAddress);
            stockInfo.setStockAddressCode(s);
            stockInfo.setStockAddress(stockAddress);
        }

    }

    private static void setStockAddress(StockNumPrice stockNumPrice, Map<String, String> stringMap) {
        String stockAddress = stockNumPrice.getStockAddress();
        if (StringUtils.isNotEmpty(stockAddress)){
            String stockAddressCode = stringMap.get(stockAddress);
            stockNumPrice.setStockAddress(stockAddress);
            stockNumPrice.setStockAddressCode(stockAddressCode);
        }
    }

    private static final BigDecimal  ZERO = new BigDecimal(0);

    private static void setPrice (StockNumPrice stockNumPrice){
        BigDecimal minSellPrice = stockNumPrice.getMinSellPrice();
        if (minSellPrice == null || ZERO.equals(minSellPrice)){
            stockNumPrice.setMinSellPrice(stockNumPrice.getStandardBuyPrice());
        }

        BigDecimal maxPurchasePrice = stockNumPrice.getMaxPurchasePrice();
        if (maxPurchasePrice == null || ZERO.equals(maxPurchasePrice)){
            stockNumPrice.setMaxPurchasePrice(stockNumPrice.getStandardSellPrice());
        }
    }
}
