package com.exx.dzj.facade.customer;

import com.exx.dzj.constant.CommonConstant;
import com.exx.dzj.entity.accountatt.AccountAttributeBean;
import com.exx.dzj.entity.contactway.ContactWayBean;
import com.exx.dzj.entity.customer.CustomerSupplierBean;
import com.exx.dzj.entity.customer.CustomerSupplierInfo;
import com.exx.dzj.entity.customer.CustomerSupplierQuery;
import com.exx.dzj.entity.dictionary.DictionaryInfo;
import com.exx.dzj.entity.user.UserInfo;
import com.exx.dzj.excepte.ErpException;
import com.exx.dzj.result.Result;
import com.exx.dzj.service.accountatt.AccountAttributeService;
import com.exx.dzj.service.accountatt.impl.AccountAttributeServiceImpl;
import com.exx.dzj.service.contactway.ContactWayService;
import com.exx.dzj.service.customer.CustomerService;
import com.exx.dzj.service.dictionary.DictionaryService;
import com.exx.dzj.service.user.UserService;
import com.exx.dzj.unique.DefaultIdGenerator;
import com.exx.dzj.unique.DefaultIdGeneratorConfig;
import com.exx.dzj.unique.IdGenerator;
import com.exx.dzj.unique.IdGeneratorConfig;
import com.exx.dzj.util.EntityJudgeUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author
 * @Date 2019/1/7 0007 11:44
 * @Description 客户或供应商 业务逻辑层
 */
@Component
public class CustomerSupplierFacade {
    private final static Logger LOGGER = LoggerFactory.getLogger(CustomerSupplierFacade.class);

    @Autowired
    private CustomerService customerSupplierService;
    @Autowired
    private ContactWayService contactwayService;
    @Autowired
    private AccountAttributeService accountAttService;
    @Autowired
    private DictionaryService dictService;
    @Autowired
    private UserService salesmanService;

    /**
     * 查询 客户或供应商列表数据
     * @param pageNum
     * @param pageSize
     * @return
     */
    public Result queryCustomerSupplierList(int pageNum, int pageSize, CustomerSupplierQuery queryParam) {
        return customerSupplierService.queryCustomerSupplierList(pageNum, pageSize, queryParam);
    }

    /**
     * 查询 客户或供应商详细信息数据
     * @param custCode
     * @return
     */
    public Result queryCustomerSupplierInfo(int custType, String custCode) {
        Result result = Result.responseSuccess();

        //查询详细信息
        CustomerSupplierInfo customerInfo = customerSupplierService.queryCustomerSupplierInfo(custCode);

        /**
        Map<String, Object> map = new HashMap<>();
        map.put("customerInfo", customerInfo);
        if(custType == CommonConstant.DEFAULT_VALUE_ONE){
            //查询-类别(客户)
            List<DictionaryInfo> customerClasses = dictService.queryDictionary(CommonConstant.CUSTOMER_CATEGORY);
            map.put("customerClasses", customerClasses);

            //查询-发货地点(客户)
            List<DictionaryInfo> shipAddress = dictService.queryDictionary(CommonConstant.INVENTORY_SHIP_ADDRESS);
            map.put("shipAddress", shipAddress);

            //查询-开票类型(客户)
            List<DictionaryInfo> billingTypes = dictService.queryDictionary(CommonConstant.BILLING_TYPE);
            map.put("billingTypes", billingTypes);
        }

        if(custType == CommonConstant.DEFAULT_VALUE_TWO){
            //查询-类别(供应商)
            List<DictionaryInfo> supplierClasses = dictService.queryDictionary(CommonConstant.SUPPLIER_SATEGORY);
            map.put("supplierClasses", supplierClasses);
        }

        //查询-地区
        List<DictionaryInfo> regions = dictService.queryDictionary(CommonConstant.ERP_REGION);
        map.put("regions", regions);

        //查询-收付款方式
        List<DictionaryInfo> paymentMethods = dictService.queryDictionary(CommonConstant.RECEIVABLES_PAYMENT_METHOD);
        map.put("paymentMethods", paymentMethods);

        //查询-业务员
        List<UserInfo> salesmans = salesmanService.querySalesman();
        map.put("salesmans", salesmans);
        result.setData(map);*/
        result.setData(customerInfo);
        return result;
    }

    /**
     * 获取下拉框数据
     * @param custType
     * @return
     */
    public Result getDropdownBoxData(int custType){
        Result result = Result.responseSuccess();

        Map<String, Object> map = new HashMap<>();
        if(custType == CommonConstant.DEFAULT_VALUE_ONE){
            //查询-类别(客户)
            List<DictionaryInfo> customerClasses = dictService.queryDictionary(CommonConstant.CUSTOMER_CATEGORY);
            map.put("customerClasses", customerClasses);

            //查询-发货地点(客户)
            List<DictionaryInfo> shipAddress = dictService.queryDictionary(CommonConstant.INVENTORY_SHIP_ADDRESS);
            map.put("shipAddress", shipAddress);

            //查询-开票类型(客户)
            List<DictionaryInfo> billingTypes = dictService.queryDictionary(CommonConstant.BILLING_TYPE);
            map.put("billingTypes", billingTypes);
        }

        if(custType == CommonConstant.DEFAULT_VALUE_TWO){
            //查询-类别(供应商)
            List<DictionaryInfo> supplierClasses = dictService.queryDictionary(CommonConstant.SUPPLIER_SATEGORY);
            map.put("supplierClasses", supplierClasses);
        }

        //查询-地区
        List<DictionaryInfo> regions = dictService.queryDictionary(CommonConstant.ERP_REGION);
        map.put("regions", regions);

        //查询-收付款方式
        List<DictionaryInfo> paymentMethods = dictService.queryDictionary(CommonConstant.RECEIVABLES_PAYMENT_METHOD);
        map.put("paymentMethods", paymentMethods);

        //查询-业务员
        List<UserInfo> salesmans = salesmanService.querySalesman();
        map.put("salesmans", salesmans);

        result.setData(map);
        return result;
    }

    /**
     * 保存 客户或供应商基础信息数据
     * @param info
     */
    @Transactional(rollbackFor=ErpException.class)
    public Result saveCustomerSupplier(CustomerSupplierInfo info,
                                       int custType) throws ErpException{
        Result result = Result.responseSuccess();
        try{
            CustomerSupplierBean customerBean = new CustomerSupplierBean();
            ContactWayBean contactWayBean = new ContactWayBean();
            AccountAttributeBean accountBean = new AccountAttributeBean();
            BeanUtils.copyProperties(info, customerBean);
            BeanUtils.copyProperties(info, contactWayBean);
            BeanUtils.copyProperties(info, accountBean);

            if(StringUtils.isNotBlank(customerBean.getCustCode())){
                //修改
                customerSupplierService.modifyCustomerSupplier(customerBean);

                contactWayBean.setCustCode(null);
                accountBean.setCustCode(null);
                if(!EntityJudgeUtil.checkObjAllFieldsIsNull(contactWayBean)){
                    contactWayBean.setCustCode(customerBean.getCustCode());
                    int count = contactwayService.modifyContactWay(contactWayBean);
                    if(count == 0){
                        contactwayService.saveContactWay(contactWayBean);
                    }
                }
                if(!EntityJudgeUtil.checkObjAllFieldsIsNull(accountBean)){
                    accountBean.setCustCode(customerBean.getCustCode());
                    int count = accountAttService.modifyAccountAttribute(accountBean);
                    if(count == 0){
                        accountAttService.saveAccountAttribute(accountBean);
                    }
                }
            }else{
                String prefix = "";
                if(custType == CommonConstant.DEFAULT_VALUE_ONE){
                    prefix = "KH";
                }
                if(custType == CommonConstant.DEFAULT_VALUE_TWO){
                    prefix = "GYS";
                }
                IdGeneratorConfig config = new DefaultIdGeneratorConfig(prefix);
                IdGenerator idGenerator = new DefaultIdGenerator(config);
                String custCode = idGenerator.next();
                customerBean.setCustCode(custCode);
                //新增
                customerSupplierService.saveCustomerSupplier(customerBean);
                if(!EntityJudgeUtil.checkObjAllFieldsIsNull(contactWayBean)){
                    contactWayBean.setCustCode(custCode);
                    contactwayService.saveContactWay(contactWayBean);
                }
                if(!EntityJudgeUtil.checkObjAllFieldsIsNull(accountBean)){
                    accountBean.setCustCode(custCode);
                    accountAttService.saveAccountAttribute(accountBean);
                }
            }
        }catch(Exception ex){
            LOGGER.error("异常方法:{}异常信息:{}", CustomerSupplierFacade.class.getName()+".saveCustomerSupplier", ex.getMessage());
            throw new ErpException(400, "保存数据失败!");
        }
        return result;
    }

    /**
     * 删除 客户或供应商数据
     * @param custCodes
     * @return
     */
    public Result delCustomerSupplier(String custCodes){
        Result result = Result.responseSuccess();
        customerSupplierService.delCustomerSupplier(custCodes, CommonConstant.DEFAULT_VALUE_ZERO);
        return result;
    }

    /**
     * 获取需要导出的excel数据
     * @param custType
     * @param query
     * @return
     */
    public Result getCustomerSupplierExcelList(int custType, CustomerSupplierQuery query){
        Result result = Result.responseSuccess();
        result = customerSupplierService.getCustomerSupplierExcelList(custType, query);
        return result;
    }
}
