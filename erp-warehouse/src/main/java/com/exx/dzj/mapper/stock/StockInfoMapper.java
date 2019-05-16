package com.exx.dzj.mapper.stock;

import com.exx.dzj.entity.stock.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StockInfoMapper {
    long countByExample(StockInfoExample example);

    int deleteByExample(StockInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(StockInfo record);

    int insertSelective(StockInfo record);

    List<StockInfo> selectByExample(StockInfoExample example);

    StockInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") StockInfo record, @Param("example") StockInfoExample example);

    int updateByExample(@Param("record") StockInfo record, @Param("example") StockInfoExample example);

    int updateByPrimaryKeySelective(StockInfo record);

    int updateByPrimaryKey(StockInfo record);

    /**
     * 查询存货列表数据
     * @param param
     * @return
     */
    List<StockModel> queryStockList(StockQuery param);

    /**
     * 查询存货信息
     * @param stockCode
     * @return
     */
    StockBean queryStockInfo(@Param("stockCode") String stockCode);

    /**
     * 上架-下架 或 删除
     * @param isShelves
     * @param stockCodes
     * @return
     */
    int shelvesStock(@Param("isShelves") String isShelves, @Param("isEnable") Integer isEnable, @Param("stockCodes") List<String> stockCodes, @Param("userCode") String userCode);

    void batchInsertStockInfo (List<StockInfo> stockInfoList);

    void batchInsertStockNumPrice (List<StockNumPrice> stockNumPriceList);

    void updateStockGoodsInventory(StockBean stockInfo);

    void insertStockNumPrice(StockNumPrice stockNumPrice);
}