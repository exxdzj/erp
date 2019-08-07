package com.exx.dzj.controller.customer;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.exx.dzj.annotation.DataPermission;
import com.exx.dzj.constant.CommonConstant;
import com.exx.dzj.entity.customer.CustomerBatchBean;
import com.exx.dzj.entity.customer.CustomerSupplierBean;
import com.exx.dzj.entity.customer.CustomerSupplierInfo;
import com.exx.dzj.entity.customer.CustomerSupplierQuery;
import com.exx.dzj.facade.customer.CustomerSupplierFacade;
import com.exx.dzj.page.ERPage;
import com.exx.dzj.query.QueryGenerator;
import com.exx.dzj.result.Result;
import com.exx.dzj.util.JsonUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Author
 * @Date 2019/1/7 0007 16:15
 * @Description  客户或供应商 controller
 */
@RestController
@RequestMapping("customer/")
public class CustomerController {

    private final static Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    private CustomerSupplierFacade customerSupplierFacade;

    /**
     * 获取 客户或供应商 列表数据
     * @param request
     * @param response
     * @return
     */
    @GetMapping("queryCustomerSupplierList/{custType}")
    @DataPermission(pageComponent="market/customer/customerList")
    public Result queryCustomerSupplierList(HttpServletRequest request, HttpServletResponse response,
                                            String query, @PathVariable("custType") int custType){
        Result result = Result.responseSuccess();
        CustomerSupplierQuery queryParam = JsonUtils.jsonToPojo(query, CustomerSupplierQuery.class);
        int pageNum = queryParam != null ? queryParam.getPage() : CommonConstant.DEFAULT_PAGE_NUM;
        int pageSize = queryParam != null ? queryParam.getLimit() : CommonConstant.DEFAULT_PAGE_SIZE;
        if(null == queryParam){
            queryParam = new CustomerSupplierQuery();
        }
        queryParam.setCustType(custType);

        CustomerSupplierBean customerSupplierBean = new CustomerSupplierBean();
        QueryWrapper<CustomerSupplierBean> queryWrapper = QueryGenerator.initQueryWrapper(customerSupplierBean, request.getParameterMap());

        result = customerSupplierFacade.queryCustomerSupplierList(pageNum, pageSize, queryParam, queryWrapper);
        return result;
    }

    /**
     * 获取 客户或供应商信息 (修改页面需要)
     * @param request
     * @param response
     * @param custCode
     * @param custType 1-客户  2-供应商
     * @return
     */
    @GetMapping("queryCustomerSupplierInfo/{custType}")
    public Result queryCustomerSupplierInfo(HttpServletRequest request, HttpServletResponse response,
                                            String custCode, @PathVariable("custType") int custType){
        Result result = Result.responseSuccess();
        if(!StringUtils.isNotBlank(custCode)){
            LOGGER.info("执行方法:{}异常信息:{}", CustomerController.class.getName()+".queryCustomerSupplierInfo", "custCode为空查询不到数据!");
            result.setCode(400);
            result.setMsg("查询客户或供应商信息失败!");
            return result;
        }
        result = customerSupplierFacade.queryCustomerSupplierInfo(custType, custCode);
        return result;
    }

    /**
     * 打开新增也的时候获取下拉框数据
     * @param request
     * @param response
     * @return
     */
    @GetMapping("getDropdownBoxData/{custType}")
    public Result getDropdownBoxData(HttpServletRequest request, HttpServletResponse response, @PathVariable("custType") int custType){
        Result result = Result.responseSuccess();
        result = customerSupplierFacade.getDropdownBoxData(custType);
        return result;
    }

    /**
     * 保存 客户或供应商信息
     * @param request
     * @param response
     * @return
     */
    @PostMapping("saveCustomerSupplier/{custType}")
    public Result saveCustomerSupplier(HttpServletRequest request, HttpServletResponse response,
                                       @RequestBody CustomerSupplierInfo info,
                                       @PathVariable("custType") int custType){
        Result result = Result.responseSuccess();
        if(null != info && !StringUtils.isNotBlank(info.getCustName())){
            result.setCode(400);
            result.setMsg("名称不可为空,请填写!");
            return result;
        }
        result = customerSupplierFacade.saveCustomerSupplier(info, custType);
        return result;
    }

    @PostMapping("saveCustomerData/{custType}")
    public Result saveCustomerData(HttpServletRequest request, HttpServletResponse response,
                                       @RequestBody CustomerSupplierInfo info,
                                       @PathVariable("custType") int custType){
        Result result = Result.responseSuccess();
        if(null != info && !StringUtils.isNotBlank(info.getCustName())){
            result.setCode(400);
            result.setMsg("名称不可为空,请填写!");
            return result;
        }
        result = customerSupplierFacade.saveCustomerData(info, custType);
        return result;
    }

    /**
     * 删除 客户或供应商的数据
     * @param request
     * @param response
     * @param custCode
     * @return
     */
    @PostMapping("delCustomerSupplier")
    public Result delCustomerSupplier(HttpServletRequest request, HttpServletResponse response, @RequestParam String custCode){
        Result result = Result.responseSuccess();
        if(!StringUtils.isNotBlank(custCode)){
            result.setCode(400);
            result.setMsg("请选择要删除的数据!");
            return result;
        }
        result = customerSupplierFacade.delCustomerSupplier(custCode);
        return result;
    }

    /**
     * 查询导出数据
     * @param request
     * @param response
     * @param custType
     * @return
     */
    @GetMapping("getCustomerSupplierExcelList/{custType}")
    public Result getCustomerSupplierExcelList(HttpServletRequest request, HttpServletResponse response,
                                               String query, @PathVariable("custType") int custType){
        CustomerSupplierQuery queryParam = JsonUtils.jsonToPojo(query, CustomerSupplierQuery.class);
        if(null == queryParam){
            queryParam = new CustomerSupplierQuery();
        }
        queryParam.setCustType(custType);
        return customerSupplierFacade.getCustomerSupplierExcelList(queryParam);
    }

    /**
     * @description 客户下拉数据
     * @author yangyun
     * @date 2019/1/16 0016
     * @param
     * @return com.exx.dzj.result.Result
     */
    @GetMapping("querycustomerpulldowninfo/{type}")
    public Result queryCustomerPullDownInfo(@PathVariable("type") Integer type){
        Result result = Result.responseSuccess();
        List<CustomerSupplierInfo> customers = customerSupplierFacade.queryCustomerPullDownInfo(type);
        result.setData(customers);
        return result;
    }

    @GetMapping("queryCustomers/{type}")
    public Result queryCustomers(@PathVariable("type") Integer type, String custName) {
        Result result = Result.responseSuccess();
        result.setData(customerSupplierFacade.queryCustomers(type, custName));
        return result;
    }

    /**
     * 导入客户或供应商的数据
     * @param request
     * @param file
     * @return
     */
    public Result importCustomerSupplier(HttpServletRequest request, @RequestParam(value="file") MultipartFile file) {
        Result result = Result.responseSuccess();
        //获取上传的文件名称；
        String name = file.getOriginalFilename();
        //判断是否为excel类型文件
        if(!name.endsWith(CommonConstant.EXCEL_XLS) && !name.endsWith(CommonConstant.EXCEL_XLSX)){
            LOGGER.info("执行方法:{}异常信息:{}", CustomerController.class.getName()+".importCustomerSupplier", "导入的文件不是excel类型!");
            result.setCode(400);
            result.setMsg("导入的文件不是excel类型");
            return result;
        }
        result = customerSupplierFacade.importCustomerSupplier(file);
        return result;
    }

    /**
     * @description 客户列表
     * @author yangyun
     * @date 2019/4/22 0022
     * @param query
     * @return com.exx.dzj.result.Result
     */
    @GetMapping("selectioncustomer")
    public Result selectionCustomer (CustomerSupplierQuery query){
        Result result = Result.responseSuccess();
        ERPage<CustomerSupplierBean> customerSupplierBeanERPage = customerSupplierFacade.selectionCustomer(query);
        result.setData(customerSupplierBeanERPage);
        return result;
    }

    /**
     * 批量修改客户数据
     * @param bean
     * @return
     */
    @PostMapping("batchUpdateCustomer")
    public Result batchUpdateCustomer(@RequestBody CustomerBatchBean bean) {
        Result result = Result.responseSuccess();
        if(null == bean || CollectionUtils.isEmpty(bean.getCustCodes())) {
            result.setCode(400);
            result.setMsg("修改的数据不可为空!");
            return result;
        }
        result = customerSupplierFacade.batchUpdateCustomer(bean);
        return result;
    }
}
