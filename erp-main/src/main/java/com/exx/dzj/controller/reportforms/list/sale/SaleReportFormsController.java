package com.exx.dzj.controller.reportforms.list.sale;

import com.exx.dzj.bean.SaleDetailReportQuery;
import com.exx.dzj.constant.CommonConstant;
import com.exx.dzj.entity.bean.CustomerQuery;
import com.exx.dzj.entity.bean.DeptInfoQuery;
import com.exx.dzj.entity.bean.StockInfoQuery;
import com.exx.dzj.entity.bean.UserInfoQuery;
import com.exx.dzj.entity.market.SaleInfo;
import com.exx.dzj.entity.market.SaleInfoQuery;
import com.exx.dzj.entity.statistics.sales.*;
import com.exx.dzj.enummodel.SaleListFieldEnum;
import com.exx.dzj.facade.homepage.HomePageFacade;
import com.exx.dzj.facade.reportforms.sale.SaleTicketReportFacade;
import com.exx.dzj.facade.user.UserFacade;
import com.exx.dzj.result.Result;
import com.exx.dzj.result.SelectionSaleInfo;
import com.exx.dzj.util.MathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yangyun
 * @create 2019-04-10-15:39
 */
@RestController
@RequestMapping("salereportforms/")
public class SaleReportFormsController {

    @Autowired
    private SaleTicketReportFacade saleTicketReportFacade;

    @Autowired
    private UserFacade userFacade;

    /**
     * @description 销售单
     * @author yangyun
     * @date 2019/4/18 0018
     * @param query
     * @return com.exx.dzj.result.Result
     */
    @GetMapping("inventoryreportlist")
    public Result inventoryReportList (StockInfoQuery query){
        Result result = Result.responseSuccess();
        long start = System.currentTimeMillis();
        List<StockTypeReport> stockTypeReports = saleTicketReportFacade.statisticsSaleByInventory(query);
        long end = System.currentTimeMillis();
        System.out.println(end - start);
        Map<String, Object> map = new HashMap<>();
        result.setData(map);
        map.put("stockTypeReports", stockTypeReports);
        if (stockTypeReports.size() == CommonConstant.DEFAULT_VALUE_ZERO){
            return result;
        }
        double sum = stockTypeReports.stream().mapToDouble(StockTypeReport::getCountTotal).sum(); // 数量总计
        BigDecimal salesTotal = stockTypeReports.stream().map(StockTypeReport::getSalesTotal).reduce(BigDecimal.ZERO, BigDecimal::add);// 销售额总计
        BigDecimal costTotal = stockTypeReports.stream().map(StockTypeReport::getCostTotal).reduce(BigDecimal.ZERO, BigDecimal::add); // 成本总计
        BigDecimal grossTotal = stockTypeReports.stream().map(StockTypeReport::getGrossTotal).reduce(BigDecimal.ZERO, BigDecimal::add); // 毛利总计
        BigDecimal rateTotal = MathUtil.keepTwoBigdecimal(grossTotal, salesTotal, CommonConstant.DEFAULT_VALUE_FOUR);//毛利率总计


        map.put("sum", sum);
        map.put("salesTotal", salesTotal);
        map.put("costTotal", costTotal);
        map.put("grossTotal", grossTotal);
        map.put("rateTotal", rateTotal);
        return result;
    }

    /**
     * @description 销售员tree 数据
     * @author yangyun
     * @date 2019/4/19 0019
     * @param
     * @return com.exx.dzj.result.Result
     */
    @GetMapping("getselectionsaleinfo")
    public Result getSelectionSaleInfo (){
        Result result = Result.responseSuccess();
        List<SelectionSaleInfo> selectionSaleInfos = userFacade.selectionUserInfo();
        result.setData(selectionSaleInfos);
        return result;
    }

    /**
     * @description 销售单统计依销售员
     * @author yangyun
     * @date 2019/4/20 0020
     * @param query
     * @return com.exx.dzj.result.Result
     */
    @GetMapping("querysalesticketbysaleman")
    public Result querySalesTicketBySaleMan (UserInfoQuery query){
        Result result = Result.responseSuccess();
        Map<String, Object> map = saleTicketReportFacade.statisticsSaleBySalesMan(query);

        result.setData(map);
        return result;
    }

    /**
     * @description 销售单统计依客户
     * @author yangyun
     * @date 2019/4/22 0022
     * @param query
     * @return com.exx.dzj.result.Result
     */
    @GetMapping("querysalesticketbycust")
    public Result querySalesTicketByCust (CustomerQuery query){
        Result result = Result.responseSuccess();
        Map<String, Object> map = saleTicketReportFacade.statisticsSalesTicketByCust(query);

        result.setData(map);
        return result;
    }

    /**
     * @description 销售员提成统计
     * @author yangyun
     * @date 2019/4/29 0029
     * @param query
     * @return com.exx.dzj.result.Result
     */
    @GetMapping("querysalesdeductionbysaleman")
    public Result querySalesDeductionBySaleman (UserInfoQuery query){
        Result result = Result.responseSuccess();

        Map<String, Object> stringObjectMap = saleTicketReportFacade.statisticSalesDeductionBySaleman(query);
        result.setData(stringObjectMap);
        return result;
    }

    /**
     * @description 部门下拉
     * @author yangyun
     * @date 2019/5/7 0007
     * @param
     * @return com.exx.dzj.result.Result
     */
    @GetMapping("selectiondeptinfo")
    public Result selectionDeptInfo (){
        Result result = Result.responseSuccess();
        List<DeptSaleReport> deptSaleReports = saleTicketReportFacade.selectionDeptInfo("");
        result.setData(deptSaleReports);
        return result;
    }

    @GetMapping("querydeptsalereport")
    public Result queryDeptSaleReport (DeptInfoQuery query){
        Result result = Result.responseSuccess();
        List<DeptSaleReport> deptSaleReports = saleTicketReportFacade.queryDeptSaleReport(query);
        result.setData(deptSaleReports);
        return result;
    }

    /**
     * @description 销售单导出字段
     * @author yangyun
     * @date 2019/6/17 0017
     * @param
     * @return com.exx.dzj.result.Result
     */
    @GetMapping("queryexoprtfieldlist")
    public Result queryExoprtFieldList (){
        Result result = Result.responseSuccess();
        List<SaleExportFieldReport> fieldReports = saleTicketReportFacade.queryExoprtFieldList();
        result.setData(fieldReports);
        return result;
    }

    /**
     * @description 销售单明细
     * @author yangyun
     * @date 2019/6/26 0026
     * @param query
     * @return com.exx.dzj.result.Result
     */
    @GetMapping("querysaledetaillist")
    public Result querySaleDetailList (SaleDetailReportQuery query){
        Result result = Result.responseSuccess();
        Map<String, Object> map = saleTicketReportFacade.querySaleDetailList(query);

        result.setData(map);
        return result;
    }


    /**
     * @description: 客户vip等级
     * @author yangyun
     * @date 2019/8/13 0013
     * @param
     * @return com.exx.dzj.result.Result
     */
    @GetMapping("queryvipcustomerlevellist")
    public Result queryVipCustomerlevelList (VipCustomerQueryCondition query){
        Result result = Result.responseSuccess();
        int pageNum = MathUtil.toInt(query.getPageNum(), CommonConstant.DEFAULT_VALUE_ZERO);
        int pageSize = MathUtil.toInt(query.getPageSize(), CommonConstant.DEFAULT_PAGE_SIZE);
        Map<String, Object> map = saleTicketReportFacade.queryVipCustomerlevelList(query, pageNum, pageSize);
        result.setData(map);
        return result;
    }

    /**
     * @description: 今日销售件数统计
     * @author yangyun
     * @date 2019/8/20 0020
     * @param
     * @return com.exx.dzj.result.Result
     */
    @GetMapping("querysaleticketcount")
    public Result querySaleTicketCount(SaleInfoQuery query){
        Result result = Result.responseSuccess();
        List<SaleInfo> list = saleTicketReportFacade.querySalesTicketCount(query);
        result.setData(list);
        return result;
    }
}
