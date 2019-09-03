package com.exx.dzj.controller.androidplatform.homepage;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.exx.dzj.annotation.DataPermission;
import com.exx.dzj.constant.CommonConstant;
import com.exx.dzj.entity.customer.CustomerSupplierBean;
import com.exx.dzj.entity.customer.CustomerSupplierQuery;
import com.exx.dzj.entity.market.SaleInfo;
import com.exx.dzj.entity.stock.StockQuery;
import com.exx.dzj.entity.user.UserQuery;
import com.exx.dzj.facade.androidfacade.homepage.AndroidFacade;
import com.exx.dzj.facade.customer.CustomerSupplierFacade;
import com.exx.dzj.facade.stock.StockFacade;
import com.exx.dzj.facade.user.UserFacade;
import com.exx.dzj.query.QueryGenerator;
import com.exx.dzj.result.Result;
import com.exx.dzj.util.JsonUtils;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @ClassName AndroidHomePageController
 * @Description: 手机端主页
 * @Author yangyun
 * @Date 2019/8/26 0026 18:02
 * @Version 1.0
 **/
@RestController
@Api(value = "手机端首页接口服务", description = "手机端首页接口服务")
@RequestMapping("android/")
public class AndroidController {

    @Autowired
    private AndroidFacade androidFacade;

    @Autowired
    private UserFacade userFacade;

    @Autowired
    private CustomerSupplierFacade customerSupplierFacade;

    @Autowired
    private StockFacade stockFacade;

    /**
     * @description: 首页个人金额
     * @author yangyun
     * @date 2019/8/27 0027
     * @param userCode
     * @return com.exx.dzj.result.Result
     */
    @GetMapping("querypersonagesalevolume/{userCode}")
    public Result queryPersonageSaleVolume (@PathVariable("userCode") String userCode){
        Result result = Result.responseSuccess();
        Map<String, SaleInfo> infoMap = androidFacade.queryPersonageSaleVolume(userCode);
        result.setData(infoMap);
        return result;
    }

    /**
     * @description: 销售员新增客户数, 客户总数
     * @author yangyun
     * @date 2019/8/27 0027
     * @param userCode
     * @return com.exx.dzj.result.Result
     */
    @GetMapping("querycustomercount/{userCode}")
    public Result queryCustomerCount (@PathVariable("userCode") String userCode){
        Result result = Result.responseSuccess();
        Map<String, Object> infoMap = androidFacade.queryCustomerCount(userCode);
        result.setData(infoMap);
        return result;
    }

    /**
     * 查询用户(公司员工)列表数据
     * @param request
     * @param response
     * @param query
     * @return
     */
    @GetMapping("queryuserlist")
    public Result queryUserList(HttpServletRequest request, HttpServletResponse response, UserQuery query) {
        Result result = Result.responseSuccess();
        int pageNum =  query.getPage();
        int pageSize = query.getLimit() == 0 ? CommonConstant.DEFAULT_PAGE_SIZE : query.getLimit();
        result = userFacade.list(pageNum, pageSize, query);
        return result;
    }

    /**
     * 获取 客户或供应商 列表数据
     * @param request
     * @param response
     * @return
     */
    @GetMapping("queryCustomerSupplierList/{custType}")
    @DataPermission(pageComponent="market/customer/customerList")
    public Result queryCustomerSupplierList(HttpServletRequest request, HttpServletResponse response,
                                            CustomerSupplierQuery query, @PathVariable("custType") int custType){
        Result result = Result.responseSuccess();
        int pageNum = query.getPage();
        int pageSize = query.getLimit() == 0 ? CommonConstant.DEFAULT_PAGE_SIZE : query.getLimit();
        if(null == query){
            query = new CustomerSupplierQuery();
        }
        query.setCustType(custType);

        CustomerSupplierBean customerSupplierBean = new CustomerSupplierBean();
        QueryWrapper<CustomerSupplierBean> queryWrapper = QueryGenerator.initQueryWrapper(customerSupplierBean, request.getParameterMap());

        result = customerSupplierFacade.queryCustomerSupplierList(pageNum, pageSize, query, queryWrapper);
        return result;
    }

    /**
     * 获取存货列表
     * @param request
     * @param response
     * @return
     */
    @GetMapping("queryStockList")
    public Result queryStockList(HttpServletRequest request, HttpServletResponse response, StockQuery query) {
        Result result = Result.responseSuccess();
        int pageNum = query.getPage();
        int pageSize = query.getLimit() == 0 ? CommonConstant.DEFAULT_PAGE_SIZE : query.getLimit();
        result = stockFacade.queryStockList(pageNum, pageSize, query);
        return result;
    }

    /**
     * @description: 库存预警
     * @author yangyun
     * @date 2019/8/27 0027
     * @param request
     * @param response
     * @param query
     * @return com.exx.dzj.result.Result
     */
    @GetMapping("queryStockWarningList")
    public Result queryStockWarningList(HttpServletRequest request, HttpServletResponse response, StockQuery query) {
        Result result = Result.responseSuccess();
        int pageNum = query.getPage();
        int pageSize = query.getLimit() == 0 ? CommonConstant.DEFAULT_PAGE_SIZE : query.getLimit();
        result = stockFacade.queryStockWarningList(pageNum, pageSize, query);
        return result;
    }
}

