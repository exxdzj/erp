package com.exx.dzj.facade.stock;

import com.exx.dzj.constant.CommonConstant;
import com.exx.dzj.entity.dictionary.DictionaryInfo;
import com.exx.dzj.entity.stock.StockBean;
import com.exx.dzj.entity.stock.StockInfo;
import com.exx.dzj.entity.stock.StockQuery;
import com.exx.dzj.facade.user.UserTokenFacade;
import com.exx.dzj.result.Result;
import com.exx.dzj.service.dictionary.DictionaryService;
import com.exx.dzj.service.stock.StockService;
import org.apache.commons.lang3.StringUtils;
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
    @Autowired
    UserTokenFacade userTokenFacade;

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
        if(null != stockBean && StringUtils.isNotBlank(stockBean.getPictures())){
            stockBean.setImages(stockBean.getPictures().split(","));
        }

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
        try{
            String userCode = userTokenFacade.queryUserCodeForToken(null);
            bean.setCreateUser(userCode);
            bean.setUpdateUser(userCode);
            stockInfoService.saveStockInfo(bean);
        } catch (Exception ex){
            result.setCode(400);
            result.setMsg("数据更新失败!");
        }
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
            String userCode = userTokenFacade.queryUserCodeForToken(null);
            stockInfoService.delStockInfo(stockCode, CommonConstant.DEFAULT_VALUE_ZERO, userCode);
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
            String userCode = userTokenFacade.queryUserCodeForToken(null);
            stockInfoService.shelvesStock(isShelves, stockCode, userCode);
        }catch(Exception ex){
            result.setCode(400);
            result.setMsg("操作失败!");
        }
        return result;
    }

    public void insertStockInfo(StockInfo stockInfo){
        stockInfoService.insertStockInfo(stockInfo);
    }
}
