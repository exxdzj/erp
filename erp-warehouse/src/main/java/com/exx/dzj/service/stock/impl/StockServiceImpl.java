package com.exx.dzj.service.stock.impl;

import com.exx.dzj.annotation.SysLog;
import com.exx.dzj.constant.CommonConstant;
import com.exx.dzj.constant.LogLevel;
import com.exx.dzj.constant.LogType;
import com.exx.dzj.entity.stock.*;
import com.exx.dzj.excepte.ErpException;
import com.exx.dzj.mapper.stock.StockInfoMapper;
import com.exx.dzj.mapper.stock.StockNumPriceMapper;
import com.exx.dzj.page.ERPage;
import com.exx.dzj.result.Result;
import com.exx.dzj.service.stock.StockService;
import com.exx.dzj.service.task.StockCodeUpdateTask;
import com.exx.dzj.util.ConvertUtils;
import com.exx.dzj.util.EntityJudgeUtil;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.*;

/**
 * @Author
 * @Date 2019/1/14 0014 11:25
 * @Description 存货
 */
@Service("stockInfoService")
public class

StockServiceImpl implements StockService {

    private final static Logger LOGGER = LoggerFactory.getLogger(StockServiceImpl.class);

    @Autowired
    private StockInfoMapper stockMapper;

    @Autowired
    private StockNumPriceMapper priceMapper;

    @Override
    public long countByExample(StockInfoExample example) {
        return stockMapper.countByExample(example);
    }

    /**
     * 获取存货列表数据
     * @param pageNum
     * @param pageSize
     * @param query
     * @return
     */
    @Override
    public Result queryStockList(int pageNum, int pageSize, StockQuery query) {
        Result result = Result.responseSuccess();
        PageHelper.startPage(pageNum, pageSize);
        List<StockModel> list = stockMapper.queryStockList(query);
        ERPage<StockModel> page = new ERPage<>(list);
        result.setData(page);
        return result;
    }

    @Override
    public Result querySelectStockList(int pageNum, int pageSize, StockQuery query) {
        Result result = Result.responseSuccess();
        PageHelper.startPage(pageNum, pageSize);
        List<StockModel> list = stockMapper.querySelectStockList(query);
        ERPage<StockModel> page = new ERPage<>(list);
        result.setData(page);
        return result;
    }

    @Override
    public Result querySelectStockList2(int pageNum, int pageSize, StockQuery query) {
        Result result = Result.responseSuccess();
        PageHelper.startPage(pageNum, pageSize);
        List<StockModel> list = stockMapper.querySelectStockList2(query);
        ERPage<StockModel> page = new ERPage<>(list);
        result.setData(page);
        return result;
    }

    @Override
    public Result queryStockWarningList(int pageNum, int pageSize, StockQuery query) {
        Result result = Result.responseSuccess();
        PageHelper.startPage(pageNum, pageSize);
        List<StockModel> list = stockMapper.queryStockWarningList(query);

        ERPage<StockModel> page = new ERPage<>(list);
        result.setData(page);
        return result;
    }

    /**
     * 获取 存货信息
     * @param stockCode
     * @return
     */
    @Override
    public StockBean queryStockInfo(String stockCode) {
        return stockMapper.queryStockInfo(stockCode);
    }

    /**
     * 更新 存货信息
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    @SysLog(operate = "更新存货数据", logType = LogType.LOG_TYPE_OPERATE, logLevel = LogLevel.LOG_LEVEL_INFO)
    public Result saveStockInfo(StockBean bean) {
        Result result = Result.responseSuccess();
        try{
            if(StringUtils.isNotBlank(bean.getPictures()) && bean.getPictures().endsWith(",")){
                bean.setPictures(bean.getPictures().substring(0, bean.getPictures().length() - 1));
            }
            StockInfo stockInfo = new StockInfo();
            StockNumPrice stockNumPrice = new StockNumPrice();
            BeanUtils.copyProperties(bean, stockInfo);
            BeanUtils.copyProperties(bean, stockNumPrice);
            //状态为入库，则修改为待上架
            if(null != stockInfo.getIsShelves() && stockInfo.getIsShelves().equals(CommonConstant.DEFAULT_VALUE_TWO)) {
                stockInfo.setIsShelves(CommonConstant.DEFAULT_VALUE_THREE);
            }

            if(stockInfo.getId() != null || (!ConvertUtils.isEmpty(bean.getDialogStatus()) && bean.getDialogStatus().equals("update"))){
                StockInfo oldStockInfo = stockMapper.selectByPrimaryKey(stockInfo.getId());

                stockInfo.setOldStockCode(oldStockInfo.getStockCode());

                stockMapper.updateByPrimaryKeySelective(stockInfo);

                stockNumPrice.setStockCode(null);
                if(!EntityJudgeUtil.checkObjAllFieldsIsNull(stockNumPrice)){
                    stockNumPrice.setStockCode(stockInfo.getStockCode());
                    stockNumPrice.setOldStockCode(oldStockInfo.getStockCode());
                    int count = priceMapper.updateByPrimaryKeySelective(stockNumPrice);
                    if(count == 0){
                        priceMapper.insertSelective(stockNumPrice);
                    }
                }
                updateStockCode(oldStockInfo, stockInfo);
            } else {
                if(null == stockInfo.getIsShelves()) {
                    //待上架状态
                    stockInfo.setIsShelves(CommonConstant.DEFAULT_VALUE_THREE);
                }
                stockMapper.insertSelective(stockInfo);
                if(!EntityJudgeUtil.checkObjAllFieldsIsNull(stockNumPrice)){
                    stockNumPrice.setStockCode(stockInfo.getStockCode());
                    stockNumPrice.setIsDefault(CommonConstant.DEFAULT_VALUE_ONE);
                    priceMapper.insertSelective(stockNumPrice);
                }
            }
        } catch(Exception ex) {
            LOGGER.error("异常方法:{}异常信息:{}", StockServiceImpl.class.getName()+".saveStockInfo", ex.getMessage());
            throw ex;
        }
        return result;
    }

    // 用于保存修改的编码和任务线程
    private static final Map<String, StockCodeUpdateTask> map =  new HashMap<>();

    @Autowired
    private AsyncTaskExecutor asyncSaleExecutr;

    private void updateStockCode (StockInfo oldStockInfo, StockInfo stockInfo) {

//        if (oldStockInfo != null && !StringUtils.equals(stockInfo.getStockCode(), oldStockInfo.getStockCode())){
            StockCodeUpdateTask temp = map.get(oldStockInfo.getStockCode());

            // 第一次
            if (temp == null){
                execution(oldStockInfo, stockInfo);
            } else {
                // 当同时修改同一个编码时
                do {
                    temp = map.get(oldStockInfo.getStockCode());
                    if (temp == null){
                        execution(oldStockInfo, stockInfo);
                    }
                } while (temp != null);
            }
//        }
    }

    private void execution (StockInfo oldStockInfo, StockInfo stockInfo ) {
        StockCodeUpdateTask task = new StockCodeUpdateTask(map, oldStockInfo, stockInfo,this);
        map.put(oldStockInfo.getStockCode(), task);

        Future future = asyncSaleExecutr.submit(task);
//        do {
//            if (future.isDone()){
//                try {
//                    Object o = future.get();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                } catch (ExecutionException e) {
//                    e.printStackTrace();
//                }
//            }
//        } while (!future.isDone());
    }

    @SysLog(operate = "更新存货编码", logType = LogType.LOG_TYPE_OPERATE, logLevel = LogLevel.LOG_LEVEL_INFO)
    @Transactional(rollbackFor = Exception.class)
    public void updateRelatedStockCode (StockInfo oldStockInfo, StockInfo stockInfo){
        try {
            // 存货主表
            //stockMapper.updateStockCode(oldCode, newCode);

            // 存货价格表
            //stockMapper.upateStockCodeForStockPriceTable(oldCode, newCode);

            // 只有新的编码和老的编码不一致时需要更新编码
            if (oldStockInfo != null && !StringUtils.equals(stockInfo.getStockCode(), oldStockInfo.getStockCode())){

                // 销售单商品
                stockMapper.upateStockCodeForSaleGoodsTable(oldStockInfo.getStockCode(), stockInfo.getStockCode());

                // 采购单商品
                stockMapper.upateStockCodeForPurchaseGoodsTable(oldStockInfo.getStockCode(), stockInfo.getStockCode());

                // 名称需要同步修改, 使用修改后的编码
                updateRelatedStockName(oldStockInfo, stockInfo, CommonConstant.USE_NEW_CDOE);
            } else {
                // 名称需要同步修改, 使用旧原code
                updateRelatedStockName(oldStockInfo, stockInfo, CommonConstant.USE_OLD_CDOE);
            }


        } catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * @Author yangyun
     * @Description:  更新存货名称
     * @Date 2019/10/12 0012 10:16
     * @Param [oldStockInfo, stockInfo, type]
     * @returnm void
     **/
    @Transactional(rollbackFor = Exception.class)
    public void updateRelatedStockName (StockInfo oldStockInfo, StockInfo stockInfo, String type){
        try {
            if (!StringUtils.equals(oldStockInfo.getStockName(), stockInfo.getStockName())) {
                String stockCode = null;
                switch (type) {
                    case CommonConstant.USE_NEW_CDOE:
                        stockCode = stockInfo.getStockCode();
                        break;
                    case CommonConstant.USE_OLD_CDOE:
                        stockCode = oldStockInfo.getStockCode();
                        break;

                }
                updatePurchaseAndSaleTickeStockName(stockCode, stockInfo.getStockName());
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @SysLog(operate = "更新存货编码", logType = LogType.LOG_TYPE_OPERATE, logLevel = LogLevel.LOG_LEVEL_INFO)
    private void updatePurchaseAndSaleTickeStockName (String stockCode, String stockName){
        stockMapper.upateStockNameForSaleGoodsTable(stockCode, stockName);
        stockMapper.upateStockNameForPurchaseGoodsTable(stockCode, stockName);
    }

    /**
     * 删除 存货
     * @param stockCodes
     * @return
     */
    @Override
    @SysLog(operate = "删除存货数据", logType = LogType.LOG_TYPE_OPERATE, logLevel = LogLevel.LOG_LEVEL_INFO)
    public void delStockInfo(String stockCodes, int isEnable, String userCode) {
        try{
            List<String> list = new ArrayList<>();
            strToList(stockCodes, list);
            stockMapper.shelvesStock(null, isEnable, list, userCode);
        }catch(Exception ex){
            LOGGER.error("异常方法:{}异常信息:{}", StockServiceImpl.class.getName()+".delStockInfo", ex.getMessage());
            throw ex;
        }
    }

    /**
     * 上架-下架
     * @param isShelves
     * @param stockCodes
     * @return
     */
    @Override
    @Transactional(rollbackFor = ErpException.class)
    @SysLog(operate = "存货上架或下架", logType = LogType.LOG_TYPE_OPERATE, logLevel = LogLevel.LOG_LEVEL_INFO)
    public void shelvesStock(Integer isShelves, String stockCodes, String userCode){
        try{
            List<String> list = new ArrayList<String>();
            strToList(stockCodes, list);
            stockMapper.shelvesStock(isShelves, null, list, userCode);
        }catch(Exception ex){
            LOGGER.error("异常方法:{}异常信息:{}", StockServiceImpl.class.getName()+".shelvesStock", ex.getMessage());
            throw ex;
        }
    }

    /**
     * 查询要上架的商品是否有数据为完善
     * @param stockCodes
     * @return
     */
    @Override
    public List<StockModel> queryStockList(String stockCodes) {
        try {
            List<String> list = new ArrayList<String>();
            strToList(stockCodes, list);
            List<StockModel> stocks = stockMapper.queryStockSet(list);
            return stocks;
        } catch(Exception ex) {
            LOGGER.error("执行方法:{}异常信息:{}", StockServiceImpl.class.getName()+".queryStockList", ex.getMessage());
            return null;
        }
    }

    private void strToList(String stockCodes, List<String> list) {
        String comma = ",";
        if(stockCodes.contains(comma)){
            String[]  stockCodeTemps = stockCodes.split(",");
            list = new ArrayList<>(stockCodeTemps.length);
            Collections.addAll(list, stockCodeTemps);
        } else {
            list.add(stockCodes);
        }
    }

    /**
     * 保存 存货数据
     * @param stockInfo
     */
    @Override
    @SysLog(operate = "保存存货数据", logType = LogType.LOG_TYPE_OPERATE, logLevel = LogLevel.LOG_LEVEL_INFO)
    public void insertStockInfo(StockInfo stockInfo) {
        stockMapper.insertSelective(stockInfo);
    }

    @Override
    @Transactional
    public void batchInventoryDataProccess (Map<String, List> map) {
        List<StockInfo> stockInfoList = map.get("stockInfoList");
        List<StockNumPrice> stockNumPriceList = map.get("stockNumPriceList");

        batchInsertStockInfo(stockInfoList);
        batchInsertStockNumPrice(stockNumPriceList);
    }

    @Override
    @SysLog(operate = "更新存货库存", logType = LogType.LOG_TYPE_OPERATE, logLevel = LogLevel.LOG_LEVEL_INFO)
    public void updateStockGoodsInventory(StockBean stockInfo) {
        stockMapper.updateStockGoodsInventory(stockInfo);
    }

    @Override
    @SysLog(operate = "更新存货存量与价格", logType = LogType.LOG_TYPE_OPERATE, logLevel = LogLevel.LOG_LEVEL_INFO)
    public void insertStockNumPrice(StockNumPrice stockNumPrice) {
        stockMapper.insertStockNumPrice(stockNumPrice);
    }

    @Override
    public void updateStockInfoSourceModel(StockBean stockInfo) {
        stockMapper.updateStockInfoSourceModel(stockInfo);
    }

    @Override
    public List<StockBean> stockInventoryWarning(int warningNum) {
        return stockMapper.stockInventoryWarning(warningNum);
    }

    public void batchInsertStockInfo (List<StockInfo> stockInfoList){
        stockMapper.batchInsertStockInfo(stockInfoList);
    }

    public void batchInsertStockNumPrice (List<StockNumPrice> stockNumPriceList){
        stockMapper.batchInsertStockNumPrice(stockNumPriceList);
    }

    @Override
    public List<StockBean> queryStockInfoForPurchaseAudit(String stockCode) {
        return stockMapper.queryStockInfoForPurchaseAudit(stockCode);
    }

    @Override
    public void insertStockNumPriceForPurchaseAudit(StockNumPrice stockNumPrice) {
        stockMapper.insertStockNumPriceForPurchaseAudit(stockNumPrice);
    }

    @Override
    public List<StockNumPrice> queryMultipleStocks(List<String> stockCodes) {
        return stockMapper.queryMultipleStocks(stockCodes);
    }

    @Override
    public StockNumPrice queryStockNumPirck(StockNumPrice stockNumPrice) {
        return stockMapper.queryStockNumPirck(stockNumPrice);
    }

    @Override
    public List<StockNumPrice> queryStockNumPirckList(StockNumPrice stockNumPrice) {
        return stockMapper.queryStockNumPirckList(stockNumPrice);
    }

    @Override
    public List<StockInfo> queryStockGoodsInfoForImportData() {
        return stockMapper.queryStockGoodsInfoForImportData();
    }

    @Override
    @SysLog(operate = "库存修改,销量累加", logType = LogType.LOG_TYPE_OPERATE, logLevel = LogLevel.LOG_LEVEL_INFO)
    @Transactional
    public synchronized void modifyStockInventory(StockNumPrice stockNumPrice) {
        StockNumPrice old = stockMapper.queryStockNumPirck(stockNumPrice);

        int i = old.getMinInventory() + stockNumPrice.getMinInventory();

        old.setMinInventory(i);

        BigDecimal add = BigDecimal.ZERO.add(old.getCumulativeSales()).subtract(new BigDecimal(stockNumPrice.getMinInventory()));

        old.setCumulativeSales(add);

        stockMapper.modifyStockInventory(old);
    }

    @SysLog(operate = "销量累加", logType = LogType.LOG_TYPE_OPERATE, logLevel = LogLevel.LOG_LEVEL_INFO)
    public void addUpSalesNum(StockNumPrice stockNumPrice){
        stockMapper.addUpSalesNum(stockNumPrice);
    }

    @Override
    public StockInfo queryStockInfoById(Integer id) {
        return stockMapper.selectByPrimaryKey(id);
    }

    @Override
    public void updateStockAvgPrice(StockNumPrice stockNumPrice) {
        stockMapper.updateStockAvgPrice(stockNumPrice);
    }
}
