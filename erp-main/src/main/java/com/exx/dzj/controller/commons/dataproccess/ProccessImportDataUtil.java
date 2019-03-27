package com.exx.dzj.controller.commons.dataproccess;

import com.exx.dzj.constant.CommonConstant;
import com.exx.dzj.entity.accountatt.AccountAttributeBean;
import com.exx.dzj.entity.contactway.ContactWayBean;
import com.exx.dzj.entity.customer.CustomerSupplierBean;
import com.exx.dzj.entity.market.SaleGoodsDetailBean;
import com.exx.dzj.entity.market.SaleInfo;
import com.exx.dzj.entity.market.SaleReceiptsDetails;
import com.exx.dzj.entity.user.UserInfo;
import com.exx.dzj.enummodel.PayStatusEnum;
import com.exx.dzj.facade.user.UserFacade;
import com.exx.dzj.model.CustomerModel;
import com.exx.dzj.model.SaleModel;
import com.exx.dzj.util.enums.EnumsUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author yangyun
 * @create 2019-03-19-16:45
 */
public class ProccessImportDataUtil {

    @Autowired
    private UserFacade userFacade;

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
                if (StringUtils.isNotEmpty(info.getStockCode())){
                    setSaleGoodsDetail(saleGoodsDetail, stringMap);
                    saleGoodsMap.put(info.getStockCode(), saleGoodsDetail);
                }

                continue;
            } else {

                setSaleInfo(saleInfoMap, saleReceiptsMap, saleGoodsMap, sharePro);

            }

            BeanUtils.copyProperties(info, saleInfo);
            setSalesInfo(saleInfo, userInfoMap, stringMap, customerSupplierBeanMap);
            saleInfoMap.put(saleCode, saleInfo);
            sharePro = saleCode;

            BeanUtils.copyProperties(info, saleReceiptsDetails);
            if (info.getCollectedAmount().compareTo(BigDecimal.ZERO) > CommonConstant.DEFAULT_VALUE_ZERO){
                saleReceiptsMap.put(info.getCollectedAmount(), saleReceiptsDetails);
            }

            BeanUtils.copyProperties(info, saleGoodsDetail);
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
            UserInfo userInfo = userInfoMap.get(saleInfo.getUserCode());
            if (userInfo != null){
                saleInfo.setUserCode(userInfo.getUserCode());
                saleInfo.setSalesmanCode(userInfo.getSalesmanCode());
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
            }
        }
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
}