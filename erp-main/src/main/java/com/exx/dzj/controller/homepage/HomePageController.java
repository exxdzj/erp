package com.exx.dzj.controller.homepage;

import com.exx.dzj.entity.customer.CustomerSupplierQuery;
import com.exx.dzj.entity.customer.InsuranceCustomer;
import com.exx.dzj.entity.market.SaleGoodsTop;
import com.exx.dzj.entity.market.SaleInfo;
import com.exx.dzj.entity.stock.StockBean;
import com.exx.dzj.facade.homepage.HomePageFacade;
import com.exx.dzj.result.Result;
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
    @GetMapping("salesuncollectedtop")
    public Result salesUncollectedTop (){
        Result result = Result.responseSuccess();
        List<SaleInfo> list = homePageFacade.salesUncollectedTop();
        result.setData(list);
        return result;
    }

    @Deprecated
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
    @GetMapping("querysalegoodstop/{type}")
    public Result querySaleGoodsTop (@PathVariable("type") String type){
        Result result = Result.responseSuccess();
        List<SaleGoodsTop> data = homePageFacade.querySaleGoodsTop(type);
        result.setData(data);
        return result;
    }

    @GetMapping("querycompanysalesformonth")
    public Result queryCompanySalesForMonth (){
        Result result = Result.responseSuccess();
        List<SaleInfo> list = homePageFacade.queryCompanySalesForMonth();
        result.setData(list);
        return result;
    }
}
