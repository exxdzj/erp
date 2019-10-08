package com.exx.dzj.controller.reportforms.export;

import com.alibaba.excel.ExcelWriter;
import com.exx.dzj.common.export.WareHouseExportUtils;
import com.exx.dzj.entity.bean.StockInfoQuery;
import com.exx.dzj.facade.reportforms.warehouse.StockReceiptOutInventoryReportFacade;
import com.exx.dzj.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.Map;

/**
 * @Author yangyun
 * @Description:
 * @Date 2019/10/8 0008 8:59
 **/
@RestController
@RequestMapping("WarehouseExport/")
public class WarehouseExportContorller {

    @Autowired
    private StockReceiptOutInventoryReportFacade stockReceiptOutInventoryReportFacade;

    /**
     * @description: 存货收发货导出
     * @author yangyun
     * @date 2019/10/8 0008
     * @param request
     * @param response
     * @param realName
     * @param query
     * @return void
     */
    @GetMapping("exportreceiptoutinventory/{realName}")
    public void exportReceiptOutInventory (HttpServletRequest request, HttpServletResponse response, @PathVariable("realName") String realName, StockInfoQuery query){
        ServletOutputStream outputStream = null;
        ExcelWriter excelWriter = null;
        try{
            String currentDate = DateUtil.getCurrentDate(new Date());

            response.setContentType("application/x-excel");
            response.setHeader("Content-Disposition", "attachment;filename=" + new String(("InventoryFlowSummary" + currentDate + ".xlsx").getBytes(), "ISO-8859-1"));
            outputStream = response.getOutputStream();

            Map<String, Object> map = stockReceiptOutInventoryReportFacade.queryReceiptOutInventoryList(query);
            excelWriter = WareHouseExportUtils.exportReceiptOutInventory(outputStream, map, realName, query);
            excelWriter.finish();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            if (outputStream != null){
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
