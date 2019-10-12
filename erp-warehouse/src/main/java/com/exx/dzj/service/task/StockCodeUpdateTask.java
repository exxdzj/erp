package com.exx.dzj.service.task;

import com.exx.dzj.entity.stock.StockInfo;
import com.exx.dzj.service.stock.impl.StockServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.Callable;

/**
 * @ClassName StockCodeUpdateTask
 * @Description:
 * @Author yangyun
 * @Date 2019/8/29 0029 11:46
 * @Version 1.0
 **/
public class StockCodeUpdateTask implements Callable<Object> {
    private final static Logger LOGGER = LoggerFactory.getLogger(StockCodeUpdateTask.class);

    private Map<String, StockCodeUpdateTask> map;
    private StockInfo oldStockInfo;
    private StockInfo stockInfo;
    private StockServiceImpl stockServiceImpl;

    public StockCodeUpdateTask(Map<String, StockCodeUpdateTask> map, StockInfo oldStockInfo, StockInfo stockInfo, StockServiceImpl stockServiceImpl){
        this.map = map;
        this.oldStockInfo = oldStockInfo;
        this.stockInfo = stockInfo;
        this.stockServiceImpl = stockServiceImpl;
    }

    @Override
    public Object call()  {
        LOGGER.info("start execution update stockCode by {}", StockServiceImpl.class.getName() + ".saveStockInfo");
        try {
            stockServiceImpl.updateRelatedStockCode(oldStockInfo, stockInfo);

//            TimeUnit.SECONDS.sleep(30);
            map.remove(oldStockInfo.getStockCode());
            LOGGER.info("update stockCode success, after modify code:  {}", stockInfo.getStockCode());
        } catch (Throwable e){
            LOGGER.error("failure method: {}, reason: {}", StockServiceImpl.class.getName() + ".saveStockInfo", e.getMessage());
            return e;
        }
        return null;
    }
}
