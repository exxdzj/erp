package com.exx.dzj.facade.customer;

import com.exx.dzj.constant.CommonConstant;
import com.exx.dzj.entity.customer.CustomerSupplierBean;
import com.exx.dzj.entity.customer.CustomerSupplierInfo;
import com.exx.dzj.entity.dictionary.DictionaryInfo;
import com.exx.dzj.entity.user.UserInfo;
import com.exx.dzj.result.Result;
import com.exx.dzj.service.accountatt.AccountAttributeService;
import com.exx.dzj.service.contactway.ContactWayService;
import com.exx.dzj.service.customer.CustomerService;
import com.exx.dzj.service.dictionary.DictionaryService;
import com.exx.dzj.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
    private ContactWayService contactWayService;
    @Autowired
    private AccountAttributeService accountAttributeService;
    @Autowired
    private DictionaryService dictionaryService;
    @Autowired
    private UserService userService;

    /**@Autowired
    public  CustomerSupplierFacade(CustomerService customerService,
                                        ContactWayService contactWayService,
                                        AccountAttributeService accountAttributeService,
                                        DictionaryService dictionaryService,
                                        UserService userService){
        this.customerService = customerService;
        this.contactWayService = contactWayService;
        this.accountAttributeService = accountAttributeService;
        this.dictionaryService = dictionaryService;
        this.userService = userService;
    }*/

    /**
     * 查询 客户或供应商列表数据
     * @param pageNum
     * @param pageSize
     * @return
     */
    public Result queryCustomerSupplierList(int pageNum, int pageSize) {
        return customerSupplierService.queryCustomerSupplierList(pageNum, pageSize);
    }

    /**
     * 查询 客户或供应商详细信息数据
     * @param custCode
     * @return
     */
    public Result queryCustomerSupplierInfo(int custType, String custCode) {
        Result result = Result.responseSuccess();

        Map<String, Object> map = new HashMap<>();
        //查询详细信息
        CustomerSupplierInfo customerInfo = customerSupplierService.queryCustomerSupplierInfo(custCode);
        map.put("customerInfo", customerInfo);

        if(custType == CommonConstant.DEFAULT_VALUE_ONE){
            //查询-类别(客户)
            List<DictionaryInfo> customerClasses = dictionaryService.queryDictionary(CommonConstant.CUSTOMER_CATEGORY);
            map.put("customerClasses", customerClasses);

            //查询-发货地点(客户)<暂时没有数据>
            List<DictionaryInfo> shipAddress = dictionaryService.queryDictionary(CommonConstant.INVENTORY_SHIP_ADDRESS);
            map.put("shipAddress", shipAddress);

            //查询-开票类型(客户)
            List<DictionaryInfo> billingTypes = dictionaryService.queryDictionary(CommonConstant.BILLING_TYPE);
            map.put("billingTypes", billingTypes);
        }

        if(custType == CommonConstant.DEFAULT_VALUE_TWO){
            //查询-类别(供应商)
            List<DictionaryInfo> supplierClasses = dictionaryService.queryDictionary(CommonConstant.SUPPLIER_SATEGORY);
            map.put("supplierClasses", supplierClasses);
        }

        //查询-地区
        List<DictionaryInfo> regions = dictionaryService.queryDictionary(CommonConstant.ERP_REGION);
        map.put("regions", regions);

        //查询-收付款方式
        List<DictionaryInfo> paymentMethods = dictionaryService.queryDictionary(CommonConstant.RECEIVABLES_PAYMENT_METHOD);
        map.put("paymentMethods", paymentMethods);

        //查询-业务员
        List<UserInfo> salesmans = userService.querySalesman();
        map.put("salesmans", salesmans);

        result.setData(map);
        return result;
    }

    /**
     * 保存 客户或供应商基础信息数据
     * @param bean
     */
    public void saveCustomerSupplier(CustomerSupplierBean bean) {

    }
}
