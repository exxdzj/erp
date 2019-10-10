package com.exx.dzj.controller.newyeargoods.export;

import com.alibaba.excel.ExcelWriter;
import com.exx.dzj.common.export.NewYearGiftExportUtil;
import com.exx.dzj.facade.newyeargoods.NewYearGoodsFacade;
import com.exx.dzj.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.Map;

/**
 * @ClassName NewYearGiftExportController
 * @Description:
 * @Author yangyun
 * @Date 2019/10/10 0010 13:49
 * @Version 1.0
 **/
@RestController
@RequestMapping("newyeargiftexport/")
public class NewYearGiftExportController {

    @Autowired
    private NewYearGoodsFacade newYearGoodsFacade;

    @GetMapping("newyears2020giftexport")
    public void newYears2020GiftExport(HttpServletRequest request, HttpServletResponse response){
        ServletOutputStream outputStream = null;
        ExcelWriter excelWriter = null;
        try {
            String currentDate = DateUtil.getCurrentDate(new Date());

            response.setContentType("application/x-excel");
            response.setHeader("Content-Disposition", "attachment;filename=" + new String(("InventoryFlowSummary" + currentDate + ".xlsx").getBytes(), "ISO-8859-1"));
            outputStream = response.getOutputStream();
            Map<String, Object> data = newYearGoodsFacade.query2020NewYearList();
            excelWriter = NewYearGiftExportUtil.newYears2020GiftExport(data, outputStream);
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

    @GetMapping("exportGoodsListData")
    public void exportGoodsListData(HttpServletRequest request, HttpServletResponse response){
        ServletOutputStream outputStream = null;
        ExcelWriter excelWriter = null;
        try {
            String currentDate = DateUtil.getCurrentDate(new Date());

            response.setContentType("application/x-excel");
            response.setHeader("Content-Disposition", "attachment;filename=" + new String(("InventoryFlowSummary" + currentDate + ".xlsx").getBytes(), "ISO-8859-1"));
            outputStream = response.getOutputStream();
            Map<String, Object> data = newYearGoodsFacade.query2020NewYearList();
            excelWriter = NewYearGiftExportUtil.exportGoodsListData(data, outputStream);
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
