package com.exx.dzj.mapper.customer;

import com.exx.dzj.entity.customer.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CustomerSupplierBeanMapper {

    /**
     * 保存
     * @param record
     * @return
     */
    int insertSelective(CustomerSupplierBean record);

    List<CustomerSupplierBean> selectByExample(CustomerSupplierBeanExample example);

    CustomerSupplierBean selectByPrimaryKey(Integer id);

    /**
     * 更新
     * @param record
     * @param example
     * @return
     */
    int updateByExampleSelective(@Param("record") CustomerSupplierBean record, @Param("example") CustomerSupplierBeanExample example);

    /**
     * 更新
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(CustomerSupplierBean record);

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
    CustomerSupplierInfo queryCustomerSupplierInfo(@Param("custType") int custType, @Param("custCode") String custCode);

    /**
     * 修改  数据状态
     * @param custCodes
     * @param isEnable
     * @return
     */
    int modifyCustomerSupplierDataStatus(@Param("custCodes")List<String> custCodes, @Param("isEnable")int isEnable, @Param("userCode") String userCode);

    /**
     * 查询 需要导出的 excel 数据
     * @return
     */
    List<CustomerSupplierInfo> getCustomerSupplierExcelList(CustomerSupplierQuery query);

    List<CustomerSupplierInfo> queryCustomerPullDownInfo(@Param("type") Integer type);

    void batchInsertCustomerSupplier(List<CustomerSupplierBean> customerSupplierList);

    List<CustomerSupplierBean> queryCustomerSupplierBeanList ();

    List<CustomerSupplierBean> selectionCustomer(CustomerSupplierQuery query);

    int countCustomerSupplier(@Param("type") int type);

    int newlyIncreasedCutomerCount(@Param("type") int type);

    List<InsuranceCustomer> queryStatisticsCustomer();

    List<InsuranceCustomer> queryIncreaseCutomerForMonth();

    List<CustomerSupplierBean> queryCustomerSelect(@Param("custName") String custName);
}