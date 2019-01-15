package com.exx.dzj.controller.stock;

import com.exx.dzj.constant.CommonConstant;
import com.exx.dzj.entity.stock.StockBean;
import com.exx.dzj.entity.stock.StockQuery;
import com.exx.dzj.facade.stock.StockFacade;
import com.exx.dzj.result.Result;
import com.exx.dzj.util.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author
 * @Date 2019/1/12 0012 17:17
 * @Description 存货
 */
@RestController
@RequestMapping("stock/")
public class StockController {

    @Autowired
    private StockFacade stockFacade;

    /**
     * 获取存货列表
     * @param request
     * @param response
     * @return
     */
    @GetMapping("queryStockList")
    public Result queryStockList(HttpServletRequest request, HttpServletResponse response, String query) {
        Result result = Result.responseSuccess();
        StockQuery queryParam = JsonUtils.jsonToPojo(query, StockQuery.class);
        int pageNum = queryParam != null ? queryParam.getPage() : CommonConstant.DEFAULT_PAGE_NUM;
        int pageSize = queryParam != null ? queryParam.getLimit() : CommonConstant.DEFAULT_PAGE_SIZE;
        result = stockFacade.queryStockList(pageNum, pageSize, queryParam);
        return result;
    }

    /**
     * 获取存货信息
     * @param request
     * @param response
     * @param stockCode
     * @return
     */
    @GetMapping("queryStockInfo")
    public Result queryStockInfo(HttpServletRequest request, HttpServletResponse response, @RequestParam String stockCode) {
        Result result = Result.responseSuccess();
        result = stockFacade.queryStockInfo(stockCode);
        return result;
    }

    /**
     * 更新存货信息
     * @param request
     * @param response
     * @return
     */
    @PostMapping("saveStockInfo")
    public Result saveStockInfo(HttpServletRequest request, HttpServletResponse response, @RequestBody StockBean bean) {
        Result result = Result.responseSuccess();
        stockFacade.saveStockInfo(bean);
        return result;
    }

    /**
     * 删除存货信息
     * @return
     */
    @PostMapping("delStockInfo")
    public Result delStockInfo(HttpServletRequest request, HttpServletResponse response, @RequestParam String stockCode) {
        return stockFacade.delStockInfo(stockCode);
    }

    /**
     * 上架-下架
     * @param request
     * @param response
     * @param stockCode
     * @param isShelves
     * @return
     */
    @PostMapping("shelvesStock/{isShelves}")
    public Result shelvesStock(HttpServletRequest request, HttpServletResponse response,
                               @RequestParam String stockCode,
                               @PathVariable("isShelves") String isShelves) {
        return stockFacade.shelvesStock(isShelves, stockCode);
    }
}
