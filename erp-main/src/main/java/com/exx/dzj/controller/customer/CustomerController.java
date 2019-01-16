package com.exx.dzj.controller.customer;

import com.exx.dzj.constant.CommonConstant;
import com.exx.dzj.entity.accountatt.AccountAttributeBean;
import com.exx.dzj.entity.contactway.ContactWayBean;
import com.exx.dzj.entity.customer.CustomerSupplierBean;
import com.exx.dzj.entity.customer.CustomerSupplierInfo;
import com.exx.dzj.entity.customer.CustomerSupplierQuery;
import com.exx.dzj.facade.customer.CustomerSupplierFacade;
import com.exx.dzj.result.Result;
import com.exx.dzj.util.JsonUtils;
import com.exx.dzj.util.MathUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("queryCustomerSupplierList")
    public Result queryCustomerSupplierList(HttpServletRequest request, HttpServletResponse response, String query){
        Result result = Result.responseSuccess();
        CustomerSupplierQuery queryParam = JsonUtils.jsonToPojo(query, CustomerSupplierQuery.class);
        int pageNum = queryParam != null ? queryParam.getPage() : CommonConstant.DEFAULT_PAGE_NUM;
        int pageSize = queryParam != null ? queryParam.getLimit() : CommonConstant.DEFAULT_PAGE_SIZE;
        result = customerSupplierFacade.queryCustomerSupplierList(pageNum, pageSize, queryParam);
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
     * @param customerBean
     * @param contactWayBean
     * @param accountBean
     * @return
     */
    @PostMapping("saveCustomerSupplier/{custType}")
    public Result saveCustomerSupplier(HttpServletRequest request, HttpServletResponse response,
                                       CustomerSupplierBean customerBean, ContactWayBean contactWayBean,
                                       AccountAttributeBean accountBean, @PathVariable("custType") int custType){
        Result result = Result.responseSuccess();
        if(null != customerBean && StringUtils.isNotBlank(customerBean.getCustName())){
            result.setCode(400);
            result.setMsg("名称不可为空,请填写!");
            return result;
        }
        result = customerSupplierFacade.saveCustomerSupplier(customerBean, contactWayBean, accountBean, custType);
        return result;
    }

    /**
     * 删除 客户或供应商的数据
     * @param request
     * @param response
     * @param custCodes
     * @return
     */
    @PostMapping("delCustomerSupplier")
    public Result delCustomerSupplier(HttpServletRequest request, HttpServletResponse response, String custCodes){
        Result result = Result.responseSuccess();
        if(!StringUtils.isNotBlank(custCodes)){
            result.setCode(400);
            result.setMsg("请选择要删除的数据!");
            return result;
        }
        result = customerSupplierFacade.delCustomerSupplier(custCodes);
        return result;
    }

    /**
     * @description 客户下拉数据
     * @author yangyun
     * @date 2019/1/16 0016
     * @param
     * @return com.exx.dzj.result.Result
     */
    @GetMapping("querycustomerpulldowninfo")
    public Result queryCustomerPullDownInfo(){
        Result result = Result.responseSuccess();
        List<CustomerSupplierInfo> customers = customerSupplierFacade.queryCustomerPullDownInfo();
        result.setData(customers);
        return result;
    }
}
