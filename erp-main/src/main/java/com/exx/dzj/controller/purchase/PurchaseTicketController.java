package com.exx.dzj.controller.purchase;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.exx.dzj.constant.CommonConstant;
import com.exx.dzj.entity.purchase.PurchaseGoodsDetailBean;
import com.exx.dzj.entity.purchase.PurchaseHistoryInfo;
import com.exx.dzj.entity.purchase.PurchaseInfo;
import com.exx.dzj.entity.purchase.PurchaseReceiptsDetailsBean;
import com.exx.dzj.excepte.ErpException;
import com.exx.dzj.facade.purchase.PurchaseTicketFacade;
import com.exx.dzj.page.ERPage;
import com.exx.dzj.result.Result;
import com.exx.dzj.unique.SingletonGeneratorConfig;
import com.exx.dzj.util.JsonUtils;
import com.exx.dzj.util.MathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author yangyun
 * @create 2019-01-04-11:18
 *  采购单
 */
@RestController
@RequestMapping("purchaseticket/")
public class PurchaseTicketController {

    private static final Map<String, Integer> MAP_INTEGER = new HashMap<>();

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
        int pageNum = MathUtil.toInt(request.getParameter("pageNum"), CommonConstant.DEFAULT_VALUE_ZERO);
        int pageSize = MathUtil.toInt(request.getParameter("pageSize"), CommonConstant.DEFAULT_PAGE_SIZE);
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
    @PutMapping("savepurchaseTicket")
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


    /**
     * @description 根据采购单好查询对应采购单收款信息
     * @author yangyun
     * @date 2019/1/23 0023
     * @param purchaseCode
     * @return com.exx.dzj.result.Result
     */
    @GetMapping("queryPurchaseReceviptDetailList/{purchaseCode}")
    public Result queryPurchaseReceviptDetailList (@PathVariable("purchaseCode") String purchaseCode){
        Result result = Result.responseSuccess();
        List<PurchaseReceiptsDetailsBean> purchaseReceviptDetailList = purchaseTicketFacade.queryPurchaseReceviptDetailList(purchaseCode);
        result.setData(purchaseReceviptDetailList);
        return result;
    }

    /**
     * @description 采购单编号生成
     * @author yangyun
     * @date 2019/1/24 0024
     * @param
     * @return com.exx.dzj.result.Result
     */
    @GetMapping("purchasecodegenerator")
    public Result queryPurchaseTicketCode(){
        Result result = Result.responseSuccess();
        String saleCode = "PCODE" + SingletonGeneratorConfig.getSingleton().next();
        result.setData(saleCode);
        return result;
    }

    /**
     * @description
     * @author yangyun
     * @date 2019/5/15 0015
     * @param type
     * @param idList
     * @return com.exx.dzj.result.Result
     */
    @PostMapping("checkpurchaseticket/{type}")
    public Result checkPurchaseTicket (@PathVariable("type") int type,@RequestParam("idList") String idList){
        Result result = Result.responseSuccess();
        Class<? extends Map> aClass = MAP_INTEGER.getClass();
        try {
            Map<String, Integer> map = JSON.parseObject(idList, aClass);
            List<Integer> ids = new ArrayList<>(map.values());

            purchaseTicketFacade.checkPurchaseTicket(type, ids);
        } catch (JSONException e){
            e.printStackTrace();
            result.setCode(CommonConstant.FAIL_CODE);
            result.setMsg("请选择需要审核采购单, 或重新审核");
        }
        return result;
    }

    /**
     * @description 商品采购历史记录查看
     * @author yangyun
     * @date 2019/5/22 0022
     * @param stockCode
     * @return com.exx.dzj.result.Result
     */
    @GetMapping("querypurchasehistoryrecord/{stockCode}")
    public Result queryPurchaseHistoryRecord (@PathVariable("stockCode") String stockCode){
        Result result = Result.responseSuccess();
        List<PurchaseHistoryInfo> purchaseHistoryInfoList = purchaseTicketFacade.queryPurchaseHistoryRecord(stockCode);
        result.setData(purchaseHistoryInfoList);
        return result;
    }

    @PostMapping("checkpurchaseticketforwarehouse")
    public Result checkPurchaseTicketForWarehouse (HttpServletRequest request, HttpServletResponse response, @RequestBody PurchaseInfo purchaseInfo){
        Result result = Result.responseSuccess();
        try{
            purchaseTicketFacade.warehouseCheckPurchaseTicket(purchaseInfo);
        } catch (ErpException e){
            e.printStackTrace();
            result.setCode(CommonConstant.FAIL_CODE);
            result.setMsg("审核失败, 请重新审核");
        }
        return result;
    }
}
