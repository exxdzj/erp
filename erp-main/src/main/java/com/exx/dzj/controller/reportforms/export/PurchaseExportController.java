package com.exx.dzj.controller.reportforms.export;

import com.exx.dzj.common.export.PurchaseExportUtils;
import com.exx.dzj.constant.CommonConstant;
import com.exx.dzj.entity.purchase.PurchaseExportFieldReport;
import com.exx.dzj.entity.purchase.PurchaseListInfo;
import com.exx.dzj.entity.purchase.PurchaseQuery;
import com.exx.dzj.facade.purchase.PurchaseTicketFacade;
import com.exx.dzj.facade.reportforms.purchase.PurchaseReportFacade;
import com.exx.dzj.result.Result;
import com.exx.dzj.util.excel.ExcelUtil;
import lombok.Cleanup;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @description: 采购相关导出
 * @author yangyun
 * @date 2019/8/5 0005
 */
@RestController
@RequestMapping("purchaseexport/")
public class PurchaseExportController {

    @Autowired
    private PurchaseReportFacade purchaseReportFacade;

    @Autowired
    private PurchaseTicketFacade purchaseTicketFacade;

    /**
     * @description: 销售单明细导出字段信息
     * @author yangyun
     * @date 2019/8/6 0006
     * @param
     * @return com.exx.dzj.result.Result
     */
    @GetMapping("queryexoprtpurchasefieldlist")
    public Result queryExoprtPurchaseFieldList (){
        Result result = Result.responseSuccess();
        final List<PurchaseExportFieldReport> data = purchaseReportFacade.queryPurchaseExportField();
        result.setData(data);
        return result;
    }

    @GetMapping("exportpurchaselistinfo")
    public void exportPurchaseListInfo (HttpServletRequest request, HttpServletResponse response, PurchaseQuery query){
        try {
            @Cleanup ServletOutputStream outputStream = null;
            String code = ExcelUtil.getCode();
            List<PurchaseListInfo> list = null;
            response.setContentType("application/x-excel");
            response.setHeader("Content-Disposition", "attachment;filename=" + new String(("Sale-" + code + ".xlsx").getBytes(), "ISO-8859-1"));

            outputStream = response.getOutputStream();
            int type = query.getType();
            switch(type) {
                // 列表原样式
                case CommonConstant.DEFAULT_VALUE_ONE:
                    break;
                // 采购单详细
                case CommonConstant.DEFAULT_VALUE_TWO:
                    list = purchaseReportFacade.queryPurchaseListInfoDetail(query);
//                    PurchaseExportUtils.exportPurchaseListInfoDetail(outputStream, list, query.getFieldList());
                    PurchaseExportUtils.exportPurchaseListInfoDetail2(outputStream, list);
                    break;
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

}
