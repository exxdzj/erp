package com.exx.dzj.controller.purchase;

import com.exx.dzj.entity.purchase.PurchaseInfo;
import com.exx.dzj.excepte.ErpException;
import com.exx.dzj.facade.purchase.PurchaseTicketFacade;
import com.exx.dzj.page.ERPage;
import com.exx.dzj.result.Result;
import com.exx.dzj.util.MathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author yangyun
 * @create 2019-01-04-11:18
 *  采购单
 */
@RestController
@RequestMapping("purchaseticket/")
public class PurchaseTicketController {

    @Autowired
    private PurchaseTicketFacade purchaseTicketFacade;

    /**
     * @description 新增采购单
     * @author yangyun
     * @date 2019/1/12 0012
     * @param request
     * @param response
     * @param purchaseInfo
     * @return com.exx.dzj.result.Result
     */
    @PostMapping("savepurchaseTicket")
    public Result savePurchaseTicket(HttpServletRequest request, HttpServletResponse response, @RequestBody PurchaseInfo purchaseInfo){
        Result result = Result.responseSuccess();
        try {
            purchaseTicketFacade.savePurchaseTicket(purchaseInfo);
        } catch (ErpException e){
            e.printStackTrace();
            result.setCode(Result.FAIL_CODE);
            result.setMsg(Result.FAIL_MSG + ", by " + e.getMessage());
        }
        return result;
    }

    /**
     * @description 采购单列表查询
     * @author yangyun
     * @date 2019/1/14 0014
     * @param request
     * @param response
     * @param purchaseInfo
     * @return com.exx.dzj.result.Result
     */
    @GetMapping("querypurchaseticket")
    public Result queryPurchaseTickets(HttpServletRequest request, HttpServletResponse response, PurchaseInfo purchaseInfo){
        Result result = Result.responseSuccess();
        int pageNum = MathUtil.toInt(request.getParameter("pageNum"));
        int pageSize = MathUtil.toInt(request.getParameter("pageSize"));
        ERPage<PurchaseInfo> purchaseInfoERPage = purchaseTicketFacade.queryPurchaseTickets(pageNum, pageSize, purchaseInfo);
        result.setData(purchaseInfoERPage);
        return result;
    }

    /**
     * @description 采购单详情查询
     * @author yangyun
     * @date 2019/1/14 0014
     * @param request
     * @param response
     * @param id
     * @return com.exx.dzj.result.Result
     */
    @GetMapping("querypurchaseticket/{id}")
    public Result queryPurchaseTicketDetail(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "id", required = true) Integer id){
        Result result = Result.responseSuccess();
        PurchaseInfo purchaseInfo = purchaseTicketFacade.queryPurchaseTicketDetail(id);
        result.setData(purchaseInfo);
        return result;
    }

    /**
     * @description 采购单更新
     * @author yangyun
     * @date 2019/1/14 0014
     * @param request
     * @param response
     * @param purchaseInfo
     * @return com.exx.dzj.result.Result
     */
    @PutMapping("updatepurchaseticket")
    public Result updatePurchaseTicket(HttpServletRequest request, HttpServletResponse response, @RequestBody PurchaseInfo purchaseInfo){
        Result result = Result.responseSuccess();
        try {
            purchaseTicketFacade.updatePurchaseTicket(purchaseInfo);
        } catch (ErpException e){
            e.printStackTrace();
            result.setCode(Result.FAIL_CODE);
            result.setMsg(Result.FAIL_MSG + ", by " + e.getMessage());
        }
        return result;
    }

    /**
     * @description 采购单删除
     * @author yangyun
     * @date 2019/1/14 0014
     * @param request
     * @param response
     * @param id
     * @return com.exx.dzj.result.Result
     */
    @DeleteMapping("deletepurchaseticket/{id}")
    public Result deletePurchaseTikcet(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "id", required = true) Integer id){
        Result result = Result.responseSuccess();
        try {
            purchaseTicketFacade.deletePurchaseTicket(id);
        } catch (ErpException e){
            e.printStackTrace();
            result.setCode(Result.FAIL_CODE);
            result.setMsg(Result.FAIL_MSG + ", by " + e.getMessage());
        }
        return result;
    }
}
