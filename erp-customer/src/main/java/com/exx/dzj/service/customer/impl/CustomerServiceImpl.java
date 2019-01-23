package com.exx.dzj.service.customer.impl;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.metadata.Sheet;
import com.exx.dzj.entity.customer.*;
import com.exx.dzj.excepte.ErpException;
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
import sun.util.resources.th.CalendarData_th;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
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
    public Result queryCustomerSupplierList(int pageNum, int pageSize, CustomerSupplierQuery queryParam) {
        Result result = Result.responseSuccess();
        PageHelper.startPage(pageNum, pageSize);
        List<CustomerSupplierModel> list = csMapper.queryCustomerSupplierList(queryParam);
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

    /**
     * 删除 客户或供应商数据
     * @param custCodes
     */
    @Override
    public void delCustomerSupplier(String custCodes, int isEnable){
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
            csMapper.modifyCustomerSupplierDataStatus(list, isEnable);
        }catch(Exception ex){
            LOGGER.error("异常方法:{}异常信息:{}", CustomerServiceImpl.class.getName()+".delCustomerSupplier", ex.getMessage());
            throw new ErpException(400, "删除客户或供应商基础信息失败!");
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
    public List<CustomerSupplierInfo> queryCustomerPullDownInfo() {
        return csMapper.queryCustomerPullDownInfo();
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
            EasyExcelFactory.readBySax(inputStream, new Sheet(1, 1, CustomerForExcelModel.class), excelListener);
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
}
