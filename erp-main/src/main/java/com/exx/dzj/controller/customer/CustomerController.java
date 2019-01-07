package com.exx.dzj.controller.customer;

import com.exx.dzj.constant.CommonConstant;
import com.exx.dzj.facade.customer.CustomerSupplierFacade;
import com.exx.dzj.result.Result;
import com.exx.dzj.util.MathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author
 * @Date 2019/1/7 0007 16:15
 * @Description  客户或供应商 controller
 */
@RestController
@RequestMapping("customer/")
public class CustomerController {

    @Autowired
    private CustomerSupplierFacade customerSupplierFacade;

    /**
     * 获取 客户或供应商 列表数据
     * @param request
     * @param response
     * @return
     */
    @GetMapping("queryCustomerSupplierList")
    public Result queryCustomerSupplierList(HttpServletRequest request, HttpServletResponse response){
        Result result = Result.responseSuccess();
        int pageNum = MathUtil.toInt(request.getParameter("pageNum"), CommonConstant.DEFAULT_PAGE_NUM);
        int pageSize = MathUtil.toInt(request.getParameter("pageSize"), CommonConstant.DEFAULT_PAGE_SIZE);
        result = customerSupplierFacade.queryCustomerSupplierList(pageNum, pageSize);
        return result;
    }

    /**
     * 获取 客户或供应商信息 (修改页面需要)
     * @param request
     * @param response
     * @param custCode
     * @param custType
     * @return
     */
    @GetMapping("queryCustomerSupplierInfo/{custType}")
    public Result queryCustomerSupplierInfo(HttpServletRequest request, HttpServletResponse response,
                                            String custCode, @PathVariable("custType") int custType){
        Result result = Result.responseSuccess();
        result = customerSupplierFacade.queryCustomerSupplierInfo(custType, custCode);
        return result;
    }
}
