package com.exx.dzj.facade.stock;

import com.exx.dzj.constant.CommonConstant;
import com.exx.dzj.entity.dictionary.DictionaryInfo;
import com.exx.dzj.entity.stock.StockBean;
import com.exx.dzj.entity.stock.StockQuery;
import com.exx.dzj.result.Result;
import com.exx.dzj.service.dictionary.DictionaryService;
import com.exx.dzj.service.stock.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author
 * @Date 2019/1/12 0012 17:38
 * @Description 存货
 */
@Component
public class StockFacade {

    @Autowired
    private StockService stockInfoService;
    @Autowired
    private DictionaryService dictService;

    /**
     * 查询 客户或供应商列表数据
     * @param pageNum
     * @param pageSize
     * @param query
     * @return
     */
    public Result queryStockList(int pageNum, int pageSize, StockQuery query) {
        return stockInfoService.queryStockList(pageNum, pageSize, query);
    }

    /**
     * 获取 存货信息
     * @param stockCode
     * @return
     */
    public Result queryStockInfo(String stockCode) {
        Result result = Result.responseSuccess();
        Map<String, Object> map = new HashMap<>();

        /**
         * 存货信息
         */
        StockBean stockBean = stockInfoService.queryStockInfo(stockCode);

        /**
         * 存货类别
         */
        //List<DictionaryInfo> inventoryTypes = dictService.queryDictionary(CommonConstant.INVENTORY_TYPE);
        /**
         * 存货地址
         */
        //List<DictionaryInfo> inventoryAdds = dictService.queryDictionary(CommonConstant.INVENTORY_SHIP_ADDRESS);

        /*map.put("stockBean", stockBean);
        map.put("inventoryTypes", inventoryTypes);
        map.put("inventoryAdds", inventoryAdds);
        result.setData(map);*/
        result.setData(stockBean);
        return result;
    }

    /**
     * 更新  存货信息
     * @param bean
     * @return
     */
    public Result saveStockInfo(StockBean bean) {
        Result result = Result.responseSuccess();
        stockInfoService.saveStockInfo(bean);
        return result;
    }

    /**
     * 删除数据
     * @param stockCode
     * @return
     */
    public Result delStockInfo(String stockCode) {
        Result result = Result.responseSuccess();
        try{
            stockInfoService.delStockInfo(stockCode, CommonConstant.DEFAULT_VALUE_ZERO);
        }catch(Exception ex){
            result.setCode(400);
            result.setMsg("删除数据失败!");
        }
        return result;
    }

    /**
     * 上架-下架
     * @param isShelves
     * @param stockCode
     * @return
     */
    public Result shelvesStock(String isShelves, String stockCode) {
        Result result = Result.responseSuccess();
        try{
            stockInfoService.shelvesStock(isShelves, stockCode);
        }catch(Exception ex){
            result.setCode(400);
            result.setMsg("操作失败!");
        }
        return result;
    }
}
