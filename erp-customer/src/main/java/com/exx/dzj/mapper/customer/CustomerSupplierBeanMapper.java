package com.exx.dzj.mapper.customer;

import com.exx.dzj.entity.customer.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CustomerSupplierBeanMapper {
    long countByExample(CustomerSupplierBeanExample example);

    int deleteByExample(CustomerSupplierBeanExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CustomerSupplierBean record);

    int insertSelective(CustomerSupplierBean record);

    List<CustomerSupplierBean> selectByExample(CustomerSupplierBeanExample example);

    CustomerSupplierBean selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CustomerSupplierBean record, @Param("example") CustomerSupplierBeanExample example);

    int updateByExample(@Param("record") CustomerSupplierBean record, @Param("example") CustomerSupplierBeanExample example);

    int updateByPrimaryKeySelective(CustomerSupplierBean record);

    int updateByPrimaryKey(CustomerSupplierBean record);

    /**
     * 查询  客户或供应商列表数据
     * @param queryParam
     * @return
     */
    List<CustomerSupplierModel> queryCustomerSupplierList(CustomerSupplierQuery queryParam);

    /**
     * 查询 客户或供应商的详细信息
     * @param custCode
     * @return
     */
    CustomerSupplierInfo queryCustomerSupplierInfo(@Param("custCode") String custCode);

    /**
     * 修改  数据状态
     * @param custCodes
     * @param isEnable
     * @return
     */
    int modifyCustomerSupplierDataStatus(@Param("custCodes")List<String> custCodes, @Param("isEnable")int isEnable);

    /**
     * 查询 需要导出的 excel 数据
     * @return
     */
    List<CustomerSupplierInfo> getCustomerSupplierExcelList();
}