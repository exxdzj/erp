package com.exx.dzj.controller.market;

import com.exx.dzj.constant.CommonConstant;
import com.exx.dzj.entity.customer.CustomerSupplierBean;
import com.exx.dzj.entity.market.*;
import com.exx.dzj.excepte.ErpException;
import com.exx.dzj.facade.market.SalesTicketFacade;
import com.exx.dzj.page.ERPage;
import com.exx.dzj.result.Result;
import com.exx.dzj.util.MathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author yangyun
 * @create 2019-01-08-11:16
 * 销售单
 */
@RestController
@RequestMapping("salesticket/")
public class SalesTickeContorller {

    @Autowired
    private SalesTicketFacade salesTicketFacade;

    /**
     * @description 新增销售单
     * @author yangyun
     * @date 2019/1/8 0008
     * @param request
     * @param response
     * @param saleInfo
     * @return com.exx.dzj.result.Result
     */
    @PostMapping("saveSalesTicket")
    public Result saveSalesTicket(HttpServletRequest request, HttpServletResponse response, @RequestBody SaleInfo saleInfo){
        Result result = Result.responseSuccess();
        try {
            salesTicketFacade.saveSalesTicket(saleInfo);
        } catch (ErpException e){
            e.printStackTrace();
            result.setCode(Result.FAIL_CODE);
            result.setMsg(Result.FAIL_MSG + ", by " + e.getMessage());
        }
        return result;
    }

    /**
     * @description 销售单列表查询
     * @author yangyun
     * @date 2019/1/8 0008
     * @param request
     * @param response
     * @param query 查询条件
     * @return com.exx.dzj.result.Result
     */
    @GetMapping("querysalesticket")
    public Result querySalesTickets(HttpServletRequest request, HttpServletResponse response, SaleInfoQuery query){
        Result result = Result.responseSuccess();
        int pageNum = MathUtil.toInt(query.getPageNum(), CommonConstant.DEFAULT_VALUE_ZERO);
        int pageSize = MathUtil.toInt(query.getPageSize(), CommonConstant.DEFAULT_PAGE_SIZE);
        ERPage<SaleInfo> saleInfoPage = salesTicketFacade.querySalesTicketList(query, pageNum, pageSize);
        result.setData(saleInfoPage);
        return result;
    }

    /**
     * @description 销售单详情查询
     * @author yangyun
     * @date 2019/1/9 0009
     * @param request
     * @param response
     * @param id
     * @return com.exx.dzj.result.Result
     */
    @GetMapping("querysalesticket/{id}")
    public Result querySalesTicket(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") Integer id){
        Result result = Result.responseSuccess();
        SaleInfo saleInfo = salesTicketFacade.querySalesTicketById(id);
        result.setData(saleInfo);
        return result;
    }

    /**
     * @description 更新销售单
     * @author yangyun
     * @date 2019/1/11 0011
     * @param request
     * @param response
     * @param saleInfo
     * @return com.exx.dzj.result.Result
     */
    @PutMapping("saveSalesTicket")
    public Result updateSaleTicket(HttpServletRequest request, HttpServletResponse response, @RequestBody SaleInfo saleInfo){
        Result result = Result.responseSuccess();
        try {
            salesTicketFacade.updateSalesTicket(saleInfo);

        } catch (ErpException e){
            e.printStackTrace();
            result.setCode(Result.FAIL_CODE);
            result.setMsg(Result.FAIL_MSG + ", by " + e.getMessage());
        }
        return result;
    }

    /**
     * @description 销售单删除
     * @author yangyun
     * @date 2019/1/11 0011
     * @param request
     * @param response
     * @param id
     * @return com.exx.dzj.result.Result
     */
    @DeleteMapping("deletesaleticket/{id}")
    public Result deleteSaleTicket(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "id", required = true) Integer id){
        Result result = Result.responseSuccess();
        try {
            salesTicketFacade.deleteSalesTicket(id);
        } catch (ErpException e){
            e.printStackTrace();
            result.setCode(Result.FAIL_CODE);
            result.setMsg(Result.FAIL_MSG + ", by " + e.getMessage());
        }
        return result;
    }

    /**
     * @description 销售单编码生成器
     * @author yangyun
     * @date 2019/1/16 0016
     * @param
     * @return com.exx.dzj.result.Result
     */
    @GetMapping("salecodegenerator")
    public Result querySaleTicketCode(){
        Result result = Result.responseSuccess();
        //String saleCode = "SCODE" + SingletonGeneratorConfig.getSingleton().next();
        String saleCode = salesTicketFacade.getCode();
        result.setData(saleCode);
        return result;
    }

    /**
     * @description 根据销售单号查询收款详情
     * @author yangyun
     * @date 2019/1/21 0021
     * @param request
     * @param response
     * @param saleCode
     * @return com.exx.dzj.result.Result
     */
    @GetMapping("querySaleReceviptDetailList/{saleCode}")
    public Result querySaleReceviptDetailList (HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "saleCode", required = true) String saleCode){
        Result result = Result.responseSuccess();
        List<SaleReceiptsDetails> saleReceiptsDetailsList = salesTicketFacade.querySaleReceviptDetailList(saleCode);
        result.setData(saleReceiptsDetailsList);
        return result;
    }

    /**
     * @description 新增或修改物流信息
     * @author yangyun
     * @date 2019/5/11 0011
     * @param logisticsInfo
     * @return com.exx.dzj.result.Result
     */
    @PostMapping("addlogisticsinfo")
    public Result addLogisticsInfo (LogisticsInfo logisticsInfo){
        Result result = Result.responseSuccess();
        try {

            salesTicketFacade.addLogisticsInfo(logisticsInfo);
        } catch (Exception e){
            e.printStackTrace();
            result.setCode(CommonConstant.FAIL_CODE);
            result.setMsg("操作失败");
        }
        return result;
    }

    /**
     * @description  物流详情
     * @author yangyun
     * @date 2019/5/11 0011
     * @param saleCode
     * @return com.exx.dzj.result.Result
     */
    @GetMapping("getlogisticsinfo/{saleCode}")
    public Result getLogisticsInfo (@PathVariable(value = "saleCode", required = true) String saleCode){
        Result result = Result.responseSuccess();
        List<LogisticsInfo> logisticsInfos = salesTicketFacade.getLogisticsInfo(saleCode);
        result.setData(logisticsInfos);
        return result;
    }

    /**
     * @description 销售单关联商品下拉数据
     * @author yangyun
     * @date 2019/5/17 0017
     * @param saleCode
     * @return com.exx.dzj.result.Result
     */
    @GetMapping("getsalegoodsselected/{saleCode}")
    public Result getSaleGoodsSelected (@PathVariable("saleCode") String saleCode){
        Result result = Result.responseSuccess();
        List<SaleGoodsSelected> saleGoodsSelected = salesTicketFacade.getSaleGoodsSelected(saleCode);
        result.setData(saleGoodsSelected);
        return result;
    }

    /**
     * @description 物流信息删除
     * @author yangyun
     * @date 2019/5/20 0020
     * @param id
     * @return com.exx.dzj.result.Result
     */
    @DeleteMapping("logisticsinfodel/{id}")
    public Result logisticsInfoDel (@PathVariable("id") Integer id){
        Result result = Result.responseSuccess();
        salesTicketFacade.logisticsInfoDel(id);
        return result;
    }

    /**
     * @description 当前客户销售单录单查询
     * @author yangyun
     * @date 2019/6/3 0003
     * @param saleInfo
     * @return com.exx.dzj.result.Result
     */
    @PostMapping("querycustomersalestoday")
    public Result queryCustomerSalesToday (@RequestBody SaleInfo saleInfo){
        Result result = Result.responseSuccess();
        List<SaleInfo> list = salesTicketFacade.queryCustomerSalesToday(saleInfo);
        result.setData(list);
        return result;
    }

    /**
     * @description 查询销售单分公司下拉
     * @author yangyun
     * @date 2019/6/6 0006
     * @param
     * @return com.exx.dzj.result.Result
     */
    @PostMapping("querysubordinatecompanyselect")
    public Result querySubordinateCompanySelect(){
        Result result = Result.responseSuccess();
        List<SaleInfo> list = salesTicketFacade.querySubordinateCompanySelect();
        result.setData(list);
        return result;
    }

    /**
     * @description 客户下拉
     * @author yangyun
     * @date 2019/6/10 0010
     * @param
     * @return com.exx.dzj.result.Result
     */
    @GetMapping("querycustomerselect")
    public Result queryCustomerSelect (HttpServletRequest request){
        Result result = Result.responseSuccess();
        int pageNum = MathUtil.toInt(request.getParameter("pageNum"), CommonConstant.DEFAULT_VALUE_ONE);
        int pageSize = MathUtil.toInt(request.getParameter("pageSize"), CommonConstant.DEFAULT_PAGE_SIZE);
        String custName = request.getParameter("custName");
        ERPage<CustomerSupplierBean> page = salesTicketFacade.queryCustomerSelect(pageNum, pageSize, custName);
        result.setData(page);
        return result;
    }
}
