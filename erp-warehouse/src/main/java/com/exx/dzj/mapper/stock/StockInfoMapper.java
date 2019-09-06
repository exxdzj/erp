package com.exx.dzj.mapper.stock;

import com.exx.dzj.entity.stock.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StockInfoMapper {
    long countByExample(StockInfoExample example);

    int insertSelective(StockInfo record);

    List<StockInfo> selectByExample(StockInfoExample example);

    StockInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") StockInfo record, @Param("example") StockInfoExample example);

    int updateByPrimaryKeySelective(StockInfo record);

    /**
     * 查询存货列表数据
     * @param param
     * @return
     */
    List<StockModel> queryStockList(StockQuery param);
    List<StockModel> queryStockWarningList(StockQuery param);

    /**
     * 查询存货信息
     * @param stockCode
     * @return
     */
    StockBean queryStockInfo(@Param("stockCode") String stockCode);

    /**
     * 查询 根据存货编码 list, 查询存货数据
     * @param stockCodes
     * @return
     */
    List<StockModel> queryStockSet(@Param("stockCodes") List<String> stockCodes);

    /**
     * 上架-下架 或 删除
     * @param isShelves
     * @param stockCodes
     * @return
     */
    int shelvesStock(@Param("isShelves") Integer isShelves, @Param("isEnable") Integer isEnable, @Param("stockCodes") List<String> stockCodes, @Param("userCode") String userCode);

    void batchInsertStockInfo (List<StockInfo> stockInfoList);

    void batchInsertStockNumPrice (List<StockNumPrice> stockNumPriceList);

    void updateStockGoodsInventory(StockBean stockInfo);

    void insertStockNumPrice(StockNumPrice stockNumPrice);

    void updateStockInfoSourceModel(StockBean stockInfo);

    List<StockBean> stockInventoryWarning(@Param("warningNum") int warningNum);

    List<StockBean> queryStockInfoForPurchaseAudit(@Param("stockCode") String stockCode);

    void insertStockNumPriceForPurchaseAudit(StockNumPrice stockNumPrice);

    List<StockNumPrice> queryMultipleStocks(List<String> stockCodes);

    StockNumPrice queryStockNumPirck(StockNumPrice stockNumPrice);

    List<StockNumPrice> queryStockNumPirckList(StockNumPrice stockNumPrice);

    List<StockInfo> queryStockGoodsInfoForImportData();

    void updateStockCode (@Param("oldCode") String oldCode, @Param("newCode") String newCode);
    void upateStockCodeForStockPriceTable (@Param("oldCode") String oldCode, @Param("newCode") String newCode);
    void upateStockCodeForSaleGoodsTable (@Param("oldCode") String oldCode, @Param("newCode") String newCode);
    void upateStockCodeForPurchaseGoodsTable (@Param("oldCode") String oldCode, @Param("newCode") String newCode);

    void modifyStockInventory(StockNumPrice stockNumPrice);
}