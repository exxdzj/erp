package com.exx.dzj.facade.market.task;

import com.exx.dzj.entity.market.SaleGoodsDetailBean;
import com.exx.dzj.entity.market.SaleInfo;
import com.exx.dzj.entity.stock.StockNumPrice;
import com.exx.dzj.service.stock.StockService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
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
                updatePurchaseTicketGoods(freshGoods, oldGoods);
                /*int res = 0;
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
                        if (saleGoodsDetailBeans.get(0).getGoodsNum().intValue() - goods.getGoodsNum().intValue() == 0){
                            res = 0;
                        } else {
                            if (saleGoodsDetailBeans.get(0).getGoodsNum().intValue() - goods.getGoodsNum().intValue() < 0) {
                                res = (goods.getGoodsNum().intValue() - saleGoodsDetailBeans.get(0).getGoodsNum().intValue());
                            } else {
                                res = -(saleGoodsDetailBeans.get(0).getGoodsNum().intValue() - goods.getGoodsNum().intValue());
                            }
                        }
                    }

                    stockNumPrice.setMinInventory(res);
                    stockNumPrice.setStockCode(goods.getStockCode());
                    stockNumPrice.setStockAddressCode(goods.getStockAddressCode());
                    stockService.modifyStockInventory(stockNumPrice);
                }

                final List<SaleGoodsDetailBean> temp = oldGoods;

                List<SaleGoodsDetailBean> collect1 = freshGoods.stream().filter(a -> temp.stream().noneMatch(b -> StringUtils.equals(a.getStockCode(), b.getStockCode()))).collect(Collectors.toList());

                for (SaleGoodsDetailBean goods : collect1){
                    stockNumPrice = new StockNumPrice();
                    setValue("insert", stockNumPrice, goods);
                    stockService.modifyStockInventory(stockNumPrice);
                }*/

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

    private void updatePurchaseTicketGoods (List<SaleGoodsDetailBean> freshGoods, List<SaleGoodsDetailBean> oldGoods){
        // 更新销售单, 需要更新的商品
        List<SaleGoodsDetailBean> updateList = new ArrayList<>();
        StockNumPrice stockNumPrice = null;

        for (SaleGoodsDetailBean temp : freshGoods){
            if (StringUtils.startsWithIgnoreCase(temp.getStockCode(), "cb")){
                continue;
            }
            // 新增的商品
            if (temp.getId() == null){
                stockNumPrice = new StockNumPrice();
                setValue(stockNumPrice, temp);
                stockNumPrice.setMinInventory(-temp.getGoodsNum().intValue());
                stockService.modifyStockInventory(stockNumPrice);
            } else {
                updateList.add(temp);
            }
        }

        Map<Integer, List<SaleGoodsDetailBean>> collect = updateList.stream().collect(Collectors.groupingBy(SaleGoodsDetailBean::getId));
        SaleGoodsDetailBean p = null;
        SaleGoodsDetailBean sgd = null;

        // 为什么用 oldGoods 做循环体, 因为不管什么情况下 oldGoods 总比 updateList 大或等于, 不会出现漏的情况
        int res = 0;

        for (SaleGoodsDetailBean temp : oldGoods){
            // 需要更新的商品
            p = temp;
            if (updateList.contains(temp)){
                sgd = collect.get(temp.getId()).get(0);
                // 原始商品, 数量的改变
                if (StringUtils.equals(sgd.getStockCode(), temp.getStockCode())){
                    res = getRes(sgd, p);
                    if (res == 0){
                        continue;
                    }
                    stockNumPrice = new StockNumPrice();
                    setValue(stockNumPrice, p);
                    stockNumPrice.setMinInventory(res);
                    stockService.modifyStockInventory(stockNumPrice);
                } else {// 商品改变
                    // 已经删除的商品, 需要加库存
                    stockNumPrice = new StockNumPrice();
                    setValue(stockNumPrice, p);
                    stockNumPrice.setMinInventory(p.getGoodsNum().intValue());
                    stockService.modifyStockInventory(stockNumPrice);

                    // 被替换的商品为新商品, 需要减库存
                    stockNumPrice = new StockNumPrice();
                    setValue(stockNumPrice, sgd);
                    stockNumPrice.setMinInventory(-sgd.getGoodsNum().intValue());
                    stockService.modifyStockInventory(stockNumPrice);
                }
            } else { // 直接删除的商品
                stockNumPrice = new StockNumPrice();
                setValue(stockNumPrice, p);
                stockNumPrice.setMinInventory(p.getGoodsNum().intValue());
                stockService.modifyStockInventory(stockNumPrice);
            }
        }
    }

    private void setValue (StockNumPrice stockNumPrice, SaleGoodsDetailBean p){
        stockNumPrice.setStockCode(p.getStockCode());
        stockNumPrice.setStockAddressCode(p.getStockAddressCode());
    }

    public int getRes (SaleGoodsDetailBean pgd, SaleGoodsDetailBean p){
        int res = 0;
        if (pgd.getGoodsNum().intValue() - p.getGoodsNum().intValue() == 0){
            return res;
        } else {
            if (pgd.getGoodsNum().intValue() - p.getGoodsNum().intValue() > 0){
                res = - (pgd.getGoodsNum().intValue() - p.getGoodsNum().intValue());
            } else {
                res = p.getGoodsNum().intValue() - pgd.getGoodsNum().intValue();
            }
        }

        return res;
    }
}
