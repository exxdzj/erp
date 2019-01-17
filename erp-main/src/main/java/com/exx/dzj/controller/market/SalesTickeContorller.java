package com.exx.dzj.controller.market;

import com.exx.dzj.constant.CommonConstant;
import com.exx.dzj.entity.market.SaleInfo;
import com.exx.dzj.entity.market.SaleReceiptsDetails;
import com.exx.dzj.excepte.ErpException;
import com.exx.dzj.facade.market.SalesTicketFacade;
import com.exx.dzj.page.ERPage;
import com.exx.dzj.result.Result;
import com.exx.dzj.unique.SingletonGeneratorConfig;
import com.exx.dzj.util.MathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
     * @description 销售单查询
     * @author yangyun
     * @date 2019/1/8 0008
     * @param request
     * @param response
     * @param saleInfo 查询条件
     * @return com.exx.dzj.result.Result
     */
    @GetMapping("querysalesticket")
    public Result querySalesTickets(HttpServletRequest request, HttpServletResponse response, SaleInfo saleInfo){
        Result result = Result.responseSuccess();
        int pageNum = MathUtil.toInt(request.getParameter("pageNum"), CommonConstant.DEFAULT_VALUE_ZERO);
        int pageSize = MathUtil.toInt(request.getParameter("pageSize"), CommonConstant.DEFAULT_PAGE_SIZE);
        ERPage<SaleInfo> saleInfoPage = salesTicketFacade.querySalesTicketList(saleInfo, pageNum, pageSize);
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
        String saleCode = "SC" + SingletonGeneratorConfig.getSingleton().next();
        result.setData(saleCode);
        return result;
    }

}
