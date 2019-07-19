package com.exx.dzj.facade.stock;

import com.exx.dzj.constant.CommonConstant;
import com.exx.dzj.entity.dictionary.DictionaryInfo;
import com.exx.dzj.entity.stock.*;
import com.exx.dzj.facade.user.UserTokenFacade;
import com.exx.dzj.result.Result;
import com.exx.dzj.service.dictionary.DictionaryService;
import com.exx.dzj.service.stock.StockService;
import com.exx.dzj.util.ConvertUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
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

    /**queryStockList
     * 查询 客户或供应商列表数据
     * @param pageNum
     * @param pageSize
     * @param query
     * @return
     */
    public Result queryStockList(int pageNum, int pageSize, StockQuery query) {
        return stockInfoService.queryStockList(pageNum, pageSize, query);
    }

    public Result queryStockWarningList(int pageNum, int pageSize, StockQuery query) {
        return stockInfoService.queryStockWarningList(pageNum, pageSize, query);
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
        if(null != stockBean){
            stockBean.setDialogStatus("update");
            if(StringUtils.isNotBlank(stockBean.getPictures())){
                stockBean.setImages(stockBean.getPictures().split(","));
            }
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
    public Result shelvesStock(Integer isShelves, String stockCode) {
        Result result = Result.responseSuccess();
        try{
            StringBuilder builder = new StringBuilder();
            //先查询商品数据是否完整，没有销售价和库存为 0 的商品不可上架
            if(ConvertUtils.isNotEmpty(isShelves) && CommonConstant.DEFAULT_VALUE_ONE == isShelves) {
                List<StockModel> list = stockInfoService.queryStockList(stockCode);
                if(!CollectionUtils.isEmpty(list)) {
                    for(StockModel model : list) {
                        boolean b = null == model.getMinInventory() || model.getMinInventory() <= 0 || null == model.getStandardSellPrice() || model.getStandardSellPrice().equals(new BigDecimal(0.00));
                        if (b) {
                            builder.append("商品：").append(model.getStockNameForSpe()).append("<br>");
                        }
                    }
                }
            }
            if(ConvertUtils.isNotEmpty(builder.toString())) {
                builder.append("的信息有待完成!");
                result.setMsg(builder.toString());
                return result;
            }

            String userCode = userTokenFacade.queryUserCodeForToken(null);
            stockInfoService.shelvesStock(isShelves, stockCode, userCode);
        }catch(Exception ex){
            result.setCode(400);
            result.setMsg("操作失败!");
        }
        return result;
    }

    @Transactional
    public void insertStockInfo(StockBean stockBean){

        StockInfo stockInfo = new StockInfo();
        StockNumPrice stockNumPrice = new StockNumPrice();

        BeanUtils.copyProperties(stockBean, stockInfo);
        BeanUtils.copyProperties(stockBean, stockNumPrice);

        stockInfo.setSourceMode(CommonConstant.DEFAULT_VALUE_ONE);
        // 保存存货信息
        stockInfoService.insertStockInfo(stockInfo);

        // 存货价格信息
        stockInfoService.insertStockNumPrice(stockNumPrice);
    }

    /**
     * @description excel 存货导入数据批量操作
     * @author yangyun
     * @date 2019/3/27 0027
     * @param map
     * @return void
     */
    public void batchInventoryDataProccess (Map<String, List> map){

        stockInfoService.batchInventoryDataProccess(map);
    }

    public List<DictionaryInfo> queryMultipleStocks (List<String> stockCodes){
        List<StockNumPrice> list = stockInfoService.queryMultipleStocks(stockCodes);

        List<DictionaryInfo> data = new ArrayList<>();

        if (!CollectionUtils.isEmpty(list)){
            for (StockNumPrice snp : list){
                DictionaryInfo di = new DictionaryInfo();
                di.setDictCode(snp.getStockAddressCode());
                di.setDictName(snp.getStockAddress());
                data.add(di);
            }
        }
        return data;
    }

    public StockNumPrice queryStockNumPirck (StockNumPrice stockNumPrice) {
        return stockInfoService.queryStockNumPirck(stockNumPrice);
    }

    public Result checkStockCode(String stockCode) {
        StockInfoExample example = new StockInfoExample();
        StockInfoExample.Criteria criteria = example.createCriteria();
        criteria.andStockCodeEqualTo(stockCode);
        long count = stockInfoService.countByExample(example);
        Result result = Result.responseSuccess();
        if(count > 0) {
            result.setCode(400);
            result.setMsg("编码已存在!");
        }
        return result;
    }
}
