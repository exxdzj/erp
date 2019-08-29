package com.exx.dzj.service.task;

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
    private String oldCode;
    private String newCode;
    private StockServiceImpl stockServiceImpl;

    public StockCodeUpdateTask(Map<String, StockCodeUpdateTask> map, String oldCode, String newCode, StockServiceImpl stockServiceImpl){
        this.map = map;
        this.oldCode = oldCode;
        this.newCode = newCode;
        this.stockServiceImpl = stockServiceImpl;
    }

    @Override
    public Object call()  {
        LOGGER.info("start execution update stockCode by {}", StockServiceImpl.class.getName() + ".saveStockInfo");
        try {
            stockServiceImpl.updateRelatedStockCode(oldCode, newCode);
//            TimeUnit.SECONDS.sleep(30);
            map.remove(oldCode);
            LOGGER.info("update stockCode success, after modify code:  {}", newCode);
        } catch (Throwable e){
            LOGGER.error("failure method: {}, reason: {}", StockServiceImpl.class.getName() + ".saveStockInfo", e.getMessage());
            return e;
        }
        return null;
    }
}
