package com.exx.dzj.facade.customer;

import com.exx.dzj.constant.CommonConstant;
import com.exx.dzj.entity.accountatt.AccountAttributeBean;
import com.exx.dzj.entity.contactway.ContactWayBean;
import com.exx.dzj.entity.customer.CustomerSupplierBean;
import com.exx.dzj.entity.customer.CustomerSupplierBeanExample;
import com.exx.dzj.entity.customer.CustomerSupplierInfo;
import com.exx.dzj.entity.customer.CustomerSupplierQuery;
import com.exx.dzj.entity.dictionary.DictionaryInfo;
import com.exx.dzj.entity.user.UserInfo;
import com.exx.dzj.excepte.ErpException;
import com.exx.dzj.facade.sys.BusEncodeFacade;
import com.exx.dzj.facade.user.UserTokenFacade;
import com.exx.dzj.page.ERPage;
import com.exx.dzj.result.Result;
import com.exx.dzj.service.accountatt.AccountAttributeService;
import com.exx.dzj.service.contactway.ContactWayService;
import com.exx.dzj.service.customer.CustomerService;
import com.exx.dzj.service.dictionary.DictionaryService;
import com.exx.dzj.service.user.UserService;
import com.exx.dzj.unique.SeqGenerator;
import com.exx.dzj.util.EntityJudgeUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    @Autowired
    private UserTokenFacade userTokenFacade;
    @Autowired
    private BusEncodeFacade busEncodeFacade;

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
        result.setData(customerSupplierService.queryCustomerSupplierInfo(custCode));
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

            //查询-发货地点(客户)<暂时没有数据>
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
     */
    @Transactional(rollbackFor=ErpException.class)
    public Result saveCustomerSupplier(CustomerSupplierInfo bean,
                                       int custType) throws ErpException{
        Result result = Result.responseSuccess();
        try{
            String userCode = userTokenFacade.queryUserCodeForToken(null);
            CustomerSupplierBean customerBean = new CustomerSupplierBean();
            ContactWayBean contactWayBean = new ContactWayBean();
            AccountAttributeBean accountBean = new AccountAttributeBean();

            BeanUtils.copyProperties(bean, customerBean);
            BeanUtils.copyProperties(bean, contactWayBean);
            BeanUtils.copyProperties(bean, accountBean);
            customerBean.setSource(custType);
            if(StringUtils.isNotBlank(customerBean.getCustCode())){
                customerBean.setCreateUser(userCode);
                customerBean.setUpdateUser(userCode);
                //修改
                customerSupplierService.modifyCustomerSupplier(customerBean);

                contactWayBean.setCustCode(null);
                accountBean.setCustCode(null);
                if(!EntityJudgeUtil.checkObjAllFieldsIsNull(contactWayBean)){
                    contactWayBean.setCustCode(customerBean.getCustCode());
                    contactWayBean.setCreateUser(userCode);
                    contactWayBean.setUpdateUser(userCode);
                    contactWayBean.setSource(custType);
                    int count = contactwayService.modifyContactWay(contactWayBean);
                    if(count == 0){
                        contactwayService.saveContactWay(contactWayBean);
                    }
                }
                if(!EntityJudgeUtil.checkObjAllFieldsIsNull(accountBean)){
                    accountBean.setCustCode(customerBean.getCustCode());
                    accountBean.setCreateUser(userCode);
                    accountBean.setUpdateUser(userCode);
                    accountBean.setSource(custType);
                    int count = accountAttService.modifyAccountAttribute(accountBean);
                    if(count == 0) {
                        accountAttService.saveAccountAttribute(accountBean);
                    }
                }
            }else{
                /**String prefix = "";
                IdGeneratorConfig config = new DefaultIdGeneratorConfig(prefix);
                IdGenerator idGenerator = new DefaultIdGenerator(config);
                String custCode = idGenerator.next();*/

                String custCode = getCode(custType, bean.getPrefix());
                customerBean.setCustCode(custCode);
                customerBean.setCreateUser(userCode);
                customerBean.setUpdateUser(userCode);
                //新增
                customerSupplierService.saveCustomerSupplier(customerBean);
                if(!EntityJudgeUtil.checkObjAllFieldsIsNull(contactWayBean)){
                    contactWayBean.setCreateUser(userCode);
                    contactWayBean.setUpdateUser(userCode);
                    contactWayBean.setCustCode(custCode);
                    contactWayBean.setSource(custType);
                    contactwayService.saveContactWay(contactWayBean);
                }
                if(!EntityJudgeUtil.checkObjAllFieldsIsNull(accountBean)){
                    accountBean.setCreateUser(userCode);
                    accountBean.setUpdateUser(userCode);
                    accountBean.setCustCode(custCode);
                    accountBean.setSource(custType);
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
     * 查询 导出 excel 数据
     *
     * @return
     */
    public Result getCustomerSupplierExcelList(CustomerSupplierQuery query){
        Result result = Result.responseSuccess();
        result.setData(customerSupplierService.getCustomerSupplierExcelList(query));
        return result;
    }

    /**
     * 删除 客户或供应商数据
     * @param custCodes
     * @return
     */
    public Result delCustomerSupplier(String custCodes){
        Result result = Result.responseSuccess();
        try {
            String userCode = userTokenFacade.queryUserCodeForToken(null);
            customerSupplierService.delCustomerSupplier(custCodes, CommonConstant.DEFAULT_VALUE_ZERO, userCode);
        } catch(Exception ex) {
            result.setCode(400);
            result.setMsg("删除客户或供应商基础信息失败");
        }

        return result;
    }

    /**
     * 获取业务员编码
     * @param custType
     * @param prefix
     * @return
     */
    private String getCode(Integer custType, String prefix) {
        String custCode = "";
        if(custType == CommonConstant.DEFAULT_VALUE_ONE){
            String busType = "customer";
            custCode = busEncodeFacade.nextBusCode(busType, prefix);

        }
        if(custType == CommonConstant.DEFAULT_VALUE_TWO){
            custCode = SeqGenerator.nextCode();
        }

        CustomerSupplierBeanExample example = new CustomerSupplierBeanExample();
        CustomerSupplierBeanExample.Criteria criteria =example.createCriteria();
        criteria.andCustCodeEqualTo(custCode);
        int count = customerSupplierService.countCustomer(example);
        while (count > 0) {
            getCode(custType, prefix);
        }
        return custCode;
    }

    public List<CustomerSupplierInfo> queryCustomerPullDownInfo(Integer type){
        return customerSupplierService.queryCustomerPullDownInfo(type);
    }

    /**
     * 导入客户或供应商的数据
     * @param file
     * @return
     */
    public Result importCustomerSupplier(MultipartFile file) {
        Result result = Result.responseSuccess();
        try {
            result = customerSupplierService.importCustomerSupplier(file.getInputStream());
        } catch(IOException ex) {
            LOGGER.error("异常方法:{}异常信息:{}", CustomerSupplierFacade.class.getName()+".importCustomerSupplier", ex.getMessage());
            result.setCode(400);
            result.setMsg("导入数据失败!");
        }
        return result;
    }

    @Transactional
    public void batchInsertCustomerSupplier (Map<String, List> map){
        try {
            List<CustomerSupplierBean> customerSupplierList = map.get("customerSupplierList");
            customerSupplierService.batchInsertCustomerSupplier(customerSupplierList);

            List<AccountAttributeBean> accountAttributeList = map.get("accountAttributeList");
            accountAttService.batchAccountAttribute(accountAttributeList);

            List<ContactWayBean> contactWayList = map.get("contactWayList");
            contactwayService.batchContactWay(contactWayList);

        } catch (Exception ex){
            LOGGER.error("异常方法:{}异常信息:{}", CustomerSupplierFacade.class.getName()+".importCustomerSupplier", ex.getMessage());
            throw new ErpException(CommonConstant.FAIL_CODE, "excel import data fail");
        }
    }

    public Map<String, CustomerSupplierBean> queryCustomerSupplierBeanList (){
        List<CustomerSupplierBean> customerSuppliers = customerSupplierService.queryCustomerSupplierBeanList();
        Map<String, CustomerSupplierBean> collect = customerSuppliers.stream().collect(Collectors.toMap(CustomerSupplierBean::getCustName, u -> u, (k1, k2) -> k1));
        return collect;
    }


    public ERPage<CustomerSupplierBean> selectionCustomer (CustomerSupplierQuery query){
        ERPage<CustomerSupplierBean> customerSupplierBeanERPage = customerSupplierService.selectionCustomer(query);

        return customerSupplierBeanERPage;
    }
}
