package com.exx.dzj.service.stock;

import com.exx.dzj.entity.stock.StockBean;
import com.exx.dzj.entity.stock.StockInfo;
import com.exx.dzj.entity.stock.StockNumPrice;
import com.exx.dzj.entity.stock.StockQuery;
import com.exx.dzj.result.Result;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Author
 * @Date 2019/1/12 0012 17:46
 * @Description 存货 service
 */
public interface StockService {

    /**
     * 获取存货列表数据
     * @return
     */
    Result queryStockList(int pageNum, int pageSize, StockQuery query);

    /**
     * 获取 存货信息
     * @return
     */
    StockBean queryStockInfo(String stockCode);

    /**
     * 更新 存货信息
     * @return
     */
    Result saveStockInfo(StockBean bean);

    /**
     * 删除 存货
     * @return
     */
    void delStockInfo(String stockCodes, int isEnable, String userCode);

    /**
     * 上架-下架
     * @param isShelves
     * @param stockCodes
     * @return
     */
    void shelvesStock(String isShelves, String stockCodes, String userCode);

    /**
     * 保存 存货数据
     * @param stockInfo
     */
    void insertStockInfo(StockInfo stockInfo);

    void batchInventoryDataProccess (Map<String, List> map);

    void updateStockGoodsInventory(StockBean stockInfo);

    void insertStockNumPrice(StockNumPrice stockNumPrice);
}
