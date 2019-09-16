package com.exx.dzj.service.stock;

import com.exx.dzj.entity.stock.*;
import com.exx.dzj.result.Result;

import java.util.List;
import java.util.Map;

/**
 * @Author
 * @Date 2019/1/12 0012 17:46
 * @Description 存货 service
 */
public interface StockService {

    long countByExample(StockInfoExample example);

    /**
     * 获取存货列表数据
     * @return
     */
    Result queryStockList(int pageNum, int pageSize, StockQuery query);
    Result querySelectStockList(int pageNum, int pageSize, StockQuery query);
    Result queryStockWarningList(int pageNum, int pageSize, StockQuery query);

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
    void shelvesStock(Integer isShelves, String stockCodes, String userCode);

    /**
     * 查询要上架的商品是否有数据为完善
     * @param stockCodes
     * @return
     */
    List<StockModel> queryStockList(String stockCodes);

    /**
     * 保存 存货数据
     * @param stockInfo
     */
    void insertStockInfo(StockInfo stockInfo);

    void batchInventoryDataProccess (Map<String, List> map);

    void updateStockGoodsInventory(StockBean stockInfo);

    void insertStockNumPrice(StockNumPrice stockNumPrice);

    void updateStockInfoSourceModel(StockBean stockInfo);

    List<StockBean> stockInventoryWarning (int warningNum);

    List<StockBean> queryStockInfoForPurchaseAudit(String stockCode);

    void insertStockNumPriceForPurchaseAudit(StockNumPrice stockNumPrice);

    List<StockNumPrice> queryMultipleStocks (List<String> stockCodes);

    StockNumPrice queryStockNumPirck (StockNumPrice stockNumPrice);

    List<StockNumPrice> queryStockNumPirckList (StockNumPrice stockNumPrice);

    /**
     * @description: 查询商品信息, 用于数据导入
     * @author yangyun
     * @date 2019/7/24 0024
     * @param
     * @return java.util.List<com.exx.dzj.entity.stock.StockInfo>
     */
    List<StockInfo> queryStockGoodsInfoForImportData();

    void modifyStockInventory(StockNumPrice stockNumPrice);

    StockInfo queryStockInfoById(Integer id);
}
