package com.exx.dzj.service.customer.impl;

import com.exx.dzj.entity.customer.CustomerSupplierBean;
import com.exx.dzj.entity.customer.CustomerSupplierInfo;
import com.exx.dzj.entity.customer.CustomerSupplierModel;
import com.exx.dzj.excepte.ErpException;
import com.exx.dzj.mapper.customer.CustomerSupplierBeanMapper;
import com.exx.dzj.page.ERPage;
import com.exx.dzj.result.Result;
import com.exx.dzj.service.customer.CustomerService;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author
 * @Date 2019/1/5 0005 17:03
 * @Description 客户或供应商 service
 */
@Service("customerSupplierService")
public class CustomerServiceImpl implements CustomerService {

    Class clazz;
    private final static Logger LOGGER = LoggerFactory.getLogger(CustomerServiceImpl.class);

    @Autowired
    private CustomerSupplierBeanMapper csMapper;

    /**
     * 查询 客户或供应商列表数据
     * @return
     */
    @Override
    public Result queryCustomerSupplierList(int pageNum, int pageSize) {
        Result result = Result.responseSuccess();
        PageHelper.startPage(pageNum, pageSize);
        List<CustomerSupplierModel> list = csMapper.queryCustomerSupplierList(null);
        ERPage<CustomerSupplierModel> pages = new ERPage<>(list);
        result.setData(pages);
        return result;
    }

    /**
     * 查询 客户或供应商详细信息数据(同时查出所有信息)
     * @param custCode
     * @return
     */
    @Override
    public CustomerSupplierInfo queryCustomerSupplierInfo(String custCode) {
        return csMapper.queryCustomerSupplierInfo(custCode);
    }

    /**
     * 保存 客户或供应商基础信息数据
     * @param bean
     */
    @Override
    public void saveCustomerSupplier(CustomerSupplierBean bean) {
        try{
            csMapper.insertSelective(bean);
        }catch(Exception ex){
            LOGGER.error("异常方法:{}异常信息:{}", CustomerServiceImpl.class.getName()+".saveCustomerSupplier", ex.getMessage());
            throw new ErpException(400, "保存客户或供应商基础信息失败!");
        }
    }

    /**
     * 修改 客户或供应商基础信息数据
     * @param bean
     */
    @Override
    public void modifyCustomerSupplier(CustomerSupplierBean bean){
        try{
            csMapper.updateByPrimaryKeySelective(bean);
        }catch(Exception ex){
            LOGGER.error("异常方法:{}异常信息:{}", CustomerServiceImpl.class.getName()+".modifyCustomerSupplier", ex.getMessage());
            throw new ErpException(400, "修改客户或供应商基础信息失败!");
        }
    }
}
