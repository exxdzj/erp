package com.exx.dzj.facade.market.task;

import com.exx.dzj.entity.market.SaleGoodsDetailBean;
import com.exx.dzj.entity.market.SaleInfo;
import com.exx.dzj.entity.stock.StockNumPrice;
import com.exx.dzj.service.stock.StockService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ClassName ModifyStockInventoryTask
 * @Description: 新增销售单库存修改
 * @Author yangyun
 * @Date 2019/9/5 0005 11:29
 * @Version 1.0
 **/
public class ModifyStockInventoryTask implements Runnable {

    private static final Logger LOGGER = LoggerFactory.getLogger(ModifyStockInventoryTask.class);

    private SaleInfo old;
    private SaleInfo fresh;
    private StockService stockService;

    public ModifyStockInventoryTask (SaleInfo old, SaleInfo fresh, StockService stockService){
        this.old = old;
        this.fresh = fresh;
        this.stockService = stockService;
    }

    @Override
    public void run() {
        List<SaleGoodsDetailBean> oldGoods = null;
        List<SaleGoodsDetailBean> freshGoods = null;
        StockNumPrice stockNumPrice = null;
        try {
            // 新增
            if (old == null){
                freshGoods = fresh.getSaleGoodsDetailBeanList();
                for (SaleGoodsDetailBean goods : freshGoods){
                    if (StringUtils.startsWithIgnoreCase(goods.getStockCode(), "cb")){
                        continue;
                    }
                    stockNumPrice = new StockNumPrice();
                    setValue("insert", stockNumPrice, goods);
                    stockService.modifyStockInventory(stockNumPrice);
                }
            // 删除
            } else if (fresh == null) {
                oldGoods = old.getSaleGoodsDetailBeanList();
                for (SaleGoodsDetailBean goods : oldGoods){
                    if (StringUtils.startsWithIgnoreCase(goods.getStockCode(), "cb")){
                        continue;
                    }
                    stockNumPrice = new StockNumPrice();
                    setValue("delete", stockNumPrice, goods);
                    stockService.modifyStockInventory(stockNumPrice);
                }
            // 更新
            } else {
                oldGoods = old.getSaleGoodsDetailBeanList();
                freshGoods = fresh.getSaleGoodsDetailBeanList();
                int res = 0;
                Map<String, List<SaleGoodsDetailBean>> collect = freshGoods.stream().collect(Collectors.groupingBy(SaleGoodsDetailBean::getStockCode));
                List<SaleGoodsDetailBean> saleGoodsDetailBeans = null;
                for (SaleGoodsDetailBean goods : oldGoods){
                    if (StringUtils.startsWithIgnoreCase(goods.getStockCode(), "cb")){
                        continue;
                    }
                    saleGoodsDetailBeans = collect.get(goods.getStockCode());
                    stockNumPrice = new StockNumPrice();

                    // 改商品已删除
                    if (saleGoodsDetailBeans == null){
                        res = goods.getGoodsNum().intValue();
                    } else {
                        res = -(saleGoodsDetailBeans.get(0).getGoodsNum().intValue() - goods.getGoodsNum().intValue());
                    }
                    System.out.println(res);
                    stockNumPrice.setMinInventory(res);
                    stockNumPrice.setStockCode(goods.getStockCode());
                    stockNumPrice.setStockAddressCode(goods.getStockAddressCode());
                    stockService.modifyStockInventory(stockNumPrice);
                }

                for (SaleGoodsDetailBean goods : freshGoods){
                    if (!oldGoods.contains(goods)){
                        stockNumPrice = new StockNumPrice();
                        setValue("insert", stockNumPrice, goods);
                        stockService.modifyStockInventory(stockNumPrice);
                    }
                }

            }
        } catch (Exception e){
            LOGGER.error("销售单: {}库存修改失败: {}", old.getSaleCode(),e.getMessage());
            e.printStackTrace();
        }
    }

    private void setValue (String type, StockNumPrice stockNumPrice, SaleGoodsDetailBean goods){
        stockNumPrice.setStockCode(goods.getStockCode());
        stockNumPrice.setStockAddressCode(goods.getStockAddressCode());
        stockNumPrice.setMinInventory(StringUtils.equalsIgnoreCase(type, "delete") ? (goods.getGoodsNum()).intValue() : -(goods.getGoodsNum()).intValue());
    }
}
