package com.exx.dzj.controller.reportforms.purchase;

import com.exx.dzj.bean.PurchaseReportQuery;
import com.exx.dzj.constant.CommonConstant;
import com.exx.dzj.facade.reportforms.purchase.PurchaseReportFacade;
import com.exx.dzj.result.Result;
import com.exx.dzj.util.MathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
/**
 * @description 采购报表
 * @author yangyun
 * @date 2019/7/9 0009
 */
@RestController
@RequestMapping("purchasereportforms")
public class PurchaseReportFormsController {

    @Autowired
    private PurchaseReportFacade purchaseReportFacade;

    /**
     * @description: 采购单金额明细
     * @author yangyun
     * @date 2019/7/9 0009
     * @param query
     * @return com.exx.dzj.result.Result
     */
    @GetMapping("querypurchasefeepaydetail")
    public Result queryPurchaseFeePayDetail (PurchaseReportQuery query){
        Result result = Result.responseSuccess();
        Map<String, Object> map = purchaseReportFacade.queryPurchaseFeePayDetail(query);
        result.setData(map);
        return result;
    }
}
