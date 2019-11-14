package com.exx.dzj.service.customer;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.exx.dzj.entity.customer.*;
import com.exx.dzj.page.ERPage;
import com.exx.dzj.result.Result;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.List;

/**
 * @Author
 * @Date 2019/1/5 0005 16:29
 * @Description 客户或供应商 service
 */
public interface CustomerService extends IService<CustomerSupplierBean> {

    /**
     * 编辑是否有效
     * @param example
     * @return
     */
    int countCustomer(CustomerSupplierBeanExample example);

    /**
     * 查询 客户或供应商列表数据
     * @return
     */
    Result queryCustomerSupplierList(int pageNum, int pageSize, CustomerSupplierQuery queryParam, QueryWrapper queryWrapper);

    /**
     * 查询 客户或供应商详细信息数据
     * @param custCode
     * @return
     */
    CustomerSupplierInfo queryCustomerSupplierInfo(int custType, String custCode);

    /**
     * 保存 客户或供应商基础信息数据
     * @param bean
     */
    void saveCustomerSupplier(CustomerSupplierBean bean);

    /**
     * 修改 客户或供应商基础信息数据
     * @param bean
     */
    void modifyCustomerSupplier(CustomerSupplierBean bean);

    /**
     * 删除 客户或供应商数据
     * @param custCodes
     */
    void delCustomerSupplier(String custCodes, int isEnable, String userCode);

    /**
     * 查询 导出 excel 的数据
     * @return
     */
    List<CustomerSupplierInfo> getCustomerSupplierExcelList(CustomerSupplierQuery query);

    List<CustomerSupplierInfo> queryCustomerPullDownInfo(Integer type, String custName);

    /**
     * 导入 excel 数据
     * @param inputStream
     * @return
     */
    Result importCustomerSupplier(InputStream inputStream);

    public void batchInsertCustomerSupplier(List<CustomerSupplierBean> customerSupplierList);

    public List<CustomerSupplierBean> queryCustomerSupplierBeanList ();

    ERPage<CustomerSupplierBean> selectionCustomer (CustomerSupplierQuery query);

    int countCustomerSupplier (int type);

    int newlyIncreasedCutomerCount (int type);

    List<InsuranceCustomer> queryStatisticsCustomer();

    List<InsuranceCustomer> queryIncreaseCutomerForMonth ();

    List<CustomerSupplierBean> queryCustomerSelect(String custName, Integer type);

    /**
     * 批量修改客户信息
     * @param bean
     * @return
     */
    Result batchUpdateCustomer(CustomerBatchBean bean);

    int updateBuyCountAndTotalVolume (CustomerSupplierInfo customerSupplierInfo);

    CustomerSupplierInfo queryVIPCustomerSupplierInfo(String custCode);

    List<CustomerSupplierInfo> queryCustBirthday (String userCode);

    List<CustomerSupplierBean> queryNewAddCustomer ();
}
