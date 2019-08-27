package com.exx.dzj.service.customer.impl;

//import com.alibaba.excel.EasyExcelFactory;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.exx.dzj.annotation.SysLog;
import com.exx.dzj.constant.CommonConstant;
import com.exx.dzj.constant.LogLevel;
import com.exx.dzj.constant.LogType;
import com.exx.dzj.entity.customer.*;
import com.exx.dzj.listen.ExcelListener;
import com.exx.dzj.mapper.customer.CustomerSupplierBeanMapper;
import com.exx.dzj.page.ERPage;
import com.exx.dzj.result.Result;
import com.exx.dzj.service.customer.CustomerService;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.*;

/**
 * @Author
 * @Date 2019/1/5 0005 17:03
 * @Description 客户或供应商 service
 */
@Service("customerSupplierService")
public class CustomerServiceImpl extends ServiceImpl<CustomerSupplierBeanMapper, CustomerSupplierBean> implements CustomerService {

    Class clazz;
    private final static Logger LOGGER = LoggerFactory.getLogger(CustomerServiceImpl.class);

    @Autowired
    private CustomerSupplierBeanMapper csMapper;

    @Override
    public int countCustomer(CustomerSupplierBeanExample example) {
        List<CustomerSupplierBean> list = csMapper.selectByExample(example);
        if(null == list) {
            return 0;
        }
        return list.size();
    }

    /**
     * 查询 客户或供应商列表数据
     * @return
     */
    @Override
    public Result queryCustomerSupplierList(int pageNum, int pageSize, CustomerSupplierQuery queryParam, QueryWrapper queryWrapper) {
        Result result = Result.responseSuccess();
        PageHelper.startPage(pageNum, pageSize);
        List<CustomerSupplierModel> list = csMapper.queryCustomerSupplierList(queryParam, queryWrapper);
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
    public CustomerSupplierInfo queryCustomerSupplierInfo(int custType, String custCode) {
        return csMapper.queryCustomerSupplierInfo(custType, custCode);
    }

    /**
     * 保存 客户或供应商基础信息数据
     * @param bean
     */
    @Override
    @SysLog(operate = "保存客户或供应商信息", logType = LogType.LOG_TYPE_OPERATE, logLevel = LogLevel.LOG_LEVEL_INFO)
    public void saveCustomerSupplier(CustomerSupplierBean bean) {
        try{
            csMapper.insertSelective(bean);
        }catch(Exception ex){
            LOGGER.error("异常方法:{}异常信息:{}", CustomerServiceImpl.class.getName()+".saveCustomerSupplier", ex.getMessage());
            throw ex;
        }
    }

    /**
     * 修改 客户或供应商基础信息数据
     * @param bean
     */
    @Override
    @SysLog(operate = "修改客户或供应商信息", logType = LogType.LOG_TYPE_OPERATE, logLevel = LogLevel.LOG_LEVEL_INFO)
    public void modifyCustomerSupplier(CustomerSupplierBean bean){
        try{
            csMapper.updateByPrimaryKeySelective(bean);
        }catch(Exception ex){
            LOGGER.error("异常方法:{}异常信息:{}", CustomerServiceImpl.class.getName()+".modifyCustomerSupplier", ex.getMessage());
            throw ex;
        }
    }

    /**
     * 删除 客户或供应商数据
     * @param custCodes
     */
    @Override
    @SysLog(operate = "删除客户或供应商信息", logType = LogType.LOG_TYPE_OPERATE, logLevel = LogLevel.LOG_LEVEL_INFO)
    public void delCustomerSupplier(String custCodes, int isEnable, String userCode){
        try{
            String comma = ",";
            List<String> list = new ArrayList<String>();
            if(custCodes.contains(comma)) {
                String[]  custCodeTemps = custCodes.split(",");
                list = new ArrayList<>(custCodeTemps.length);
                Collections.addAll(list, custCodeTemps);
            } else {
                list.add(custCodes);
            }
            csMapper.modifyCustomerSupplierDataStatus(list, isEnable, userCode);
        }catch(Exception ex){
            LOGGER.error("异常方法:{}异常信息:{}", CustomerServiceImpl.class.getName()+".delCustomerSupplier", ex.getMessage());
            throw ex;
        }
    }

    /**
     * 查询 导出 excel 的数据
     * @return
     */
    @Override
    public List<CustomerSupplierInfo> getCustomerSupplierExcelList(CustomerSupplierQuery query) {
        return csMapper.getCustomerSupplierExcelList(query);
    }

    @Override
    public List<CustomerSupplierInfo> queryCustomerPullDownInfo(Integer type, String custName) {
        return csMapper.queryCustomerPullDownInfo(type, custName);
    }

    /**
     * 导入 excel 数据
     * @param inputStream
     * @return
     */
    @Override
    public Result importCustomerSupplier(InputStream inputStream) {
        Result result = Result.responseSuccess();
        try {
            ExcelListener excelListener = new ExcelListener();
//            EasyExcelFactory.readBySax(inputStream, new Sheet(1, 1, CustomerForExcelModel.class), excelListener);
        } catch(Exception ex) {
            LOGGER.error("异常方法:{}异常信息:{}", CustomerServiceImpl.class.getName()+".importCustomerSupplier", ex.getMessage());
            result.setCode(400);
            result.setMsg("导入数据失败!");
        } finally {
            if(null != inputStream){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    LOGGER.error("异常方法:{}异常信息:{}", CustomerServiceImpl.class.getName()+".importCustomerSupplier", e.getMessage());
                }
            }
        }
        return result;
    }

    @Override
    public void batchInsertCustomerSupplier(List<CustomerSupplierBean> customerSupplierList) {
        csMapper.batchInsertCustomerSupplier(customerSupplierList);
    }

    @Override
    public List<CustomerSupplierBean> queryCustomerSupplierBeanList () {
        return csMapper.queryCustomerSupplierBeanList();
    }

    @Override
    public ERPage<CustomerSupplierBean> selectionCustomer(CustomerSupplierQuery query) {
        PageHelper.startPage(query.getPage(), query.getLimit());
        List<CustomerSupplierBean> list = csMapper.selectionCustomer(query);
        ERPage<CustomerSupplierBean> pages = new ERPage<>(list);
        return pages;
    }

    @Override
    public int countCustomerSupplier(int type) {
        return csMapper.countCustomerSupplier(type);
    }

    @Override
    public int newlyIncreasedCutomerCount(int type) {
        return csMapper.newlyIncreasedCutomerCount(type);
    }

    @Override
    public List<InsuranceCustomer> queryStatisticsCustomer() {
        return csMapper.queryStatisticsCustomer();
    }

    @Override
    public List<InsuranceCustomer> queryIncreaseCutomerForMonth() {
        return csMapper.queryIncreaseCutomerForMonth();
    }

    @Override
    public List<CustomerSupplierBean> queryCustomerSelect(String custName, Integer type) {
        return csMapper.queryCustomerSelect(custName, type);
    }

    @Override
    @SysLog(operate = "批量修改客户信息", logType = LogType.LOG_TYPE_OPERATE, logLevel = LogLevel.LOG_LEVEL_INFO)
    public Result batchUpdateCustomer(CustomerBatchBean bean) {
        Result result = Result.responseSuccess();
        try {
            CustomerSupplierBean csbean = new CustomerSupplierBean();
            QueryWrapper<CustomerSupplierBean> queryWrapper = new QueryWrapper();
            if(null != bean.getRegion() && bean.getRegion().equals(CommonConstant.DEFAULT_VALUE_ONE)) {
                queryWrapper.in("cust_code", bean.getCustCodes());
            }
            if(null != bean.getRegion() && bean.getRegion().equals(CommonConstant.DEFAULT_VALUE_TWO)) {
                queryWrapper.notIn("cust_code", bean.getCustCodes());
            }

            String updateItem = bean.getUpdateItem();
            switch (updateItem) {
                case "CUST_LEVEL":
                    csbean.setCustLevel(bean.getCustLevel());
                    csbean.setLevelName(bean.getLevelName());
                    break;
                case "REGION_CODE":
                    csbean.setRegionCode(bean.getRegionCode());
                    csbean.setRegionName(bean.getRegionName());
                    break;
                case "USER_CODE":
                    csbean.setSalesmanCode(bean.getSalesmanCode());
                    csbean.setUserCode(bean.getUserCode());
                    break;
                case "SHIP_CODE":
                    csbean.setShipCode(bean.getShipCode());
                    csbean.setShipAddress(bean.getShipAddress());
                    break;
                case "RANK_CODE":
                    csbean.setRankCode(bean.getRankCode());
                    csbean.setRankName(bean.getRankName());
                    break;
                case "COMPANY_CODE":
                    csbean.setCompanyCode(bean.getCompanyCode());
                    csbean.setCompanyName(bean.getCompanyName());
                    break;
                default :
                    //params.clear();
                    csbean = null;
                    break;
            }

            if(null != csbean) {
                this.update(csbean, queryWrapper);
            }
        } catch(Exception ex) {
            LOGGER.error("异常方法:{}异常信息:{}", CustomerServiceImpl.class.getName()+".batchUpdateCustomer", ex.getMessage());
            result.setCode(400);
            result.setMsg("批量修改客户数据失败!");
        }
        return result;
    }

    @Override
    public int updateBuyCountAndTotalVolume(CustomerSupplierInfo customerSupplierInfo) {
        return csMapper.updateBuyCountAndTotalVolume(customerSupplierInfo);
    }

    @Override
    public CustomerSupplierInfo queryVIPCustomerSupplierInfo(String custCode) {
        return csMapper.queryVIPCustomerSupplierInfo(custCode);
    }
}
