package com.exx.dzj.controller.homepage;

import com.exx.dzj.entity.customer.CustomerSupplierQuery;
import com.exx.dzj.entity.customer.InsuranceCustomer;
import com.exx.dzj.entity.market.SaleGoodsTop;
import com.exx.dzj.entity.market.SaleInfo;
import com.exx.dzj.entity.stock.StockBean;
import com.exx.dzj.facade.homepage.HomePageFacade;
import com.exx.dzj.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @description
 * @author yangyun
 * @date 2019/5/27 0027
 * @return
 */
@RestController
@Api(value = "首页接口服务", description = "首页接口服务")
@RequestMapping("homepage/")
public class HomePageController {

    @Autowired
    private HomePageFacade homePageFacade;

    /**
     * @description 首页顶部数据, 客户总数, 日销售额, 年销售额, 年采购额
     * @author yangyun
     * @date 2019/5/27 0027
     * @param
     * @return com.exx.dzj.result.Result
     */
    @ApiOperation(nickname = "querytopdata", value="查询首页顶部数据", notes="客户总数, 日销售额, 年销售额, 年采购额", httpMethod = "GET")
    @GetMapping("querytopdata")
    public Result queryTopData (){
        Result result = Result.responseSuccess();
        Map<String, Object> topData = homePageFacade.queryTopData();
        result.setData(topData);
        return result;
    }

    /**
     * @description 根据年份查询每月销售额
     * @author yangyun
     * @date 2019/5/28 0028
     * @return com.exx.dzj.result.Result
     */
    @ApiOperation(nickname = "querystasticssalesforyear", value="根据年份查询每月销售额", notes="根据年份查询每月销售额", httpMethod = "GET")
    @GetMapping("querystasticssalesforyear")
    public Result queryStasticsSalesForYear (){
        Result result = Result.responseSuccess();
        Map<String, Object> stringObjectMap = homePageFacade.queryStasticsSalesForYear();
        result.setData(stringObjectMap);
        return result;
    }

    /**
     * @description  每月销售额按天统计
     * @author yangyun
     * @date 2019/5/28 0028
     * @param
     * @return com.exx.dzj.result.Result
     */
    @ApiOperation(nickname = "querystasticssalesformonth", value="每月销售额按天统计", notes="每月销售额按天统计", httpMethod = "GET")
    @GetMapping("querystasticssalesformonth")
    public Result queryStatisticsSalesForMonth (){
        Result result = Result.responseSuccess();
        Object[] dataMonth = homePageFacade.queryStasticsSalesForMonth();
        result.setData(dataMonth);

        return result;
    }

    /**
     * @description 保险客户职级统计
     * @author yangyun
     * @date 2019/5/28 0028
     * @param
     * @return com.exx.dzj.result.Result
     */
    @ApiOperation(nickname = "querystatisticscustomer", value="保险客户职级统计", notes="保险客户职级统计", httpMethod = "GET")
    @GetMapping("querystatisticscustomer")
    public Result queryStatisticsCustomer (){
        Result result = Result.responseSuccess();
        List<InsuranceCustomer> insuranceCustomers = homePageFacade.queryStatisticsCustomer();
        result.setData(insuranceCustomers);
        return result;
    }

    public Result queryIncreaseCutomerForMonth (){
        Result result = Result.responseSuccess();
        homePageFacade.queryIncreaseCutomerForMonth();
        return result;
    }

    /**
     * @description 每日销售额排名
     * @author yangyun
     * @date 2019/5/28 0028
     * @param
     * @return com.exx.dzj.result.Result
     */
    @ApiOperation(nickname = "querySalesTop/{data}", value="每日销售额排名", notes="每日销售额排名", httpMethod = "GET")
    @ApiImplicitParam(name = "data", value = "日或月", allowableValues="day, month, year", required = true, dataType = "String", paramType = "path")
    @GetMapping("querySalesTop/{data}")
    public Result querySalesTop (@PathVariable("data") String data){
        Result result = Result.responseSuccess();
        List<SaleInfo> list = homePageFacade.querySalesTop(data);
        result.setData(list);
        return result;
    }

    /**
     * @description 月未收款
     * @author yangyun
     * @date 2019/5/29 0029
     * @param
     * @return com.exx.dzj.result.Result
     */
    @ApiOperation(nickname = "salesuncollectedtop", value="查询月未收款", notes="月未收款", httpMethod = "GET")
    @GetMapping("salesuncollectedtop")
    public Result salesUncollectedTop (){
        Result result = Result.responseSuccess();
        List<SaleInfo> list = homePageFacade.salesUncollectedTop();
        result.setData(list);
        return result;
    }

    @Deprecated
    @ApiOperation(nickname = "stockinventorywarning", value="查询库存警告", notes="查询库存警告", httpMethod = "GET")
    @GetMapping("stockinventorywarning")
    public Result stockInventoryWarning (CustomerSupplierQuery query){
        Result result = Result.responseSuccess();
        List<StockBean> stockBeans = homePageFacade.stockInventoryWarning();
        result.setData(stockBeans);
        return result;
    }

    /**
     * @description 今日销售件数top
     * @author yangyun
     * @date 2019/5/31 0031
     * @param
     * @return com.exx.dzj.result.Result
     */
    @ApiOperation(nickname = "querysalestickettop", value="查询今日销售件数top", notes="查询今日销售件数top", httpMethod = "GET")
    @GetMapping("querysalestickettop")
    public Result querySalesTicketTop (){
        Result result = Result.responseSuccess();
        List<SaleInfo> list = homePageFacade.querySalesTicketTop();
        result.setData(list);
        return result;
    }

    /**
     * @description 热销商品
     * @author yangyun
     * @date 2019/6/5 0005
     * @param type  月 周 日
     * @return com.exx.dzj.result.Result
     */
    @ApiOperation(nickname = "querysalegoodstop/{type}", value="查询热销商品", notes="查询热销商品", httpMethod = "GET")
    @ApiImplicitParam(name = "type", value = "月、周、日", allowableValues = "month, week, day", required = true, dataType = "String", paramType = "path")
    @GetMapping("querysalegoodstop/{type}")
    public Result querySaleGoodsTop (@PathVariable("type") String type){
        Result result = Result.responseSuccess();
        List<SaleGoodsTop> data = homePageFacade.querySaleGoodsTop(type);
        result.setData(data);
        return result;
    }

    /**
     * @description: 公司销售额
     * @author yangyun
     * @date 2019/8/27 0027
     * @param
     * @return com.exx.dzj.result.Result
     */
    @GetMapping("querycompanysalesformonth")
    public Result queryCompanySalesForMonth (){
        Result result = Result.responseSuccess();
        List<SaleInfo> list = homePageFacade.queryCompanySalesForMonth();
        result.setData(list);
        return result;
    }
}
