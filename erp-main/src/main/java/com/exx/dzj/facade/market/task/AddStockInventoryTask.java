package com.exx.dzj.facade.market.task;

import com.exx.dzj.entity.market.SaleGoodsDetailBean;
import com.exx.dzj.entity.purchase.PurchaseGoodsDetailBean;
import com.exx.dzj.entity.purchase.PurchaseInfo;
import com.exx.dzj.entity.stock.StockNumPrice;
import com.exx.dzj.service.stock.StockService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ClassName AddStockInventoryTask
 * @Description:
 * @Author yangyun
 * @Date 2019/9/25 0025 8:48
 * @Version 1.0
 **/
public class AddStockInventoryTask implements Runnable {

    private static final Logger LOGGER = LoggerFactory.getLogger(AddStockInventoryTask.class);

    private PurchaseInfo old;
    private PurchaseInfo fresh;
    private StockService stockService;

    public AddStockInventoryTask(PurchaseInfo old, PurchaseInfo fresh, StockService stockService){
        this.old = old;
        this.fresh = fresh;
        this.stockService = stockService;
    }

    @Override
    public void run() {
        StockNumPrice stockNumPrice = null;
        List<PurchaseGoodsDetailBean> oldGoods = null;
        List<PurchaseGoodsDetailBean> freshGoods = null;
        try {
            // 新增
            if (old == null){
                freshGoods = fresh.getPurchaseGoodsDetailBeans();
                for (PurchaseGoodsDetailBean goods : freshGoods){
                    if (StringUtils.startsWithIgnoreCase(goods.getStockCode(), "cb")){
                        continue;
                    }
                    stockNumPrice = new StockNumPrice();
                    setValue("insert", stockNumPrice, goods);
                    stockService.modifyStockInventory(stockNumPrice);
                }

            // 删除
            } else if (fresh == null) {
                oldGoods = old.getPurchaseGoodsDetailBeans();
                for (PurchaseGoodsDetailBean goods : oldGoods){
                    if (StringUtils.startsWithIgnoreCase(goods.getStockCode(), "cb")){
                        continue;
                    }
                    stockNumPrice = new StockNumPrice();
                    setValue("delete", stockNumPrice, goods);
                    stockService.modifyStockInventory(stockNumPrice);
                }
            // 更新
            } else {
                freshGoods = fresh.getPurchaseGoodsDetailBeans();
                oldGoods = old.getPurchaseGoodsDetailBeans();
                updatePurchaseTicketGoods(freshGoods, oldGoods);
                /*int res = 0;
                Map<String, List<PurchaseGoodsDetailBean>> collect = freshGoods.stream().collect(Collectors.groupingBy(PurchaseGoodsDetailBean::getStockCode));
                List<PurchaseGoodsDetailBean> purchaseGoodsDetailBeans = null;

                for (PurchaseGoodsDetailBean goods: oldGoods){
                    if (StringUtils.startsWithIgnoreCase(goods.getStockCode(), "cb")){
                        continue;
                    }

                    purchaseGoodsDetailBeans = collect.get(goods.getStockCode());
                    stockNumPrice = new StockNumPrice();
                    // 改商品已删除
                    if (purchaseGoodsDetailBeans == null){
                        res = -goods.getGoodsNum().intValue();
                    } else {
                        if (purchaseGoodsDetailBeans.get(0).getGoodsNum().intValue() - goods.getGoodsNum().intValue() == 0){
                            res = 0;
                        } else {
                            if (purchaseGoodsDetailBeans.get(0).getGoodsNum().intValue() - goods.getGoodsNum().intValue() < 0){
                                res = -(goods.getGoodsNum().intValue() - purchaseGoodsDetailBeans.get(0).getGoodsNum().intValue());
                            } else {
                                res = (purchaseGoodsDetailBeans.get(0).getGoodsNum().intValue() - goods.getGoodsNum().intValue());
                            }
                        }
                    }

                    stockNumPrice.setMinInventory(res);
                    stockNumPrice.setStockCode(goods.getStockCode());
                    stockNumPrice.setStockAddressCode(goods.getStockAddressCode());
                    stockService.modifyStockInventory(stockNumPrice);
                }


                final List<PurchaseGoodsDetailBean> temp = oldGoods;

                List<PurchaseGoodsDetailBean> collect1 = freshGoods.stream().filter(o -> temp.stream().noneMatch(b -> StringUtils.equals(o.getStockCode(),b.getStockCode()))).collect(Collectors.toList());

                for (PurchaseGoodsDetailBean goods: collect1){
                    stockNumPrice = new StockNumPrice();
                    setValue("insert", stockNumPrice, goods);
                    stockService.modifyStockInventory(stockNumPrice);
                }*/
            }

        } catch (Exception e) {
            LOGGER.error("采购单: {}库存修改失败: {}", old.getPurchaseCode(),e.getMessage());
            e.printStackTrace();
        }
    }

    private void setValue (String type, StockNumPrice stockNumPrice, PurchaseGoodsDetailBean goods){
        stockNumPrice.setStockCode(goods.getStockCode());
        stockNumPrice.setStockAddressCode(goods.getStockAddressCode());
        stockNumPrice.setMinInventory(StringUtils.equalsIgnoreCase(type, "delete") ? -(goods.getGoodsNum()).intValue() : (goods.getGoodsNum()).intValue());
    }

    private void updatePurchaseTicketGoods (List<PurchaseGoodsDetailBean> freshGoods, List<PurchaseGoodsDetailBean> oldGoods){
        // 更新采购单, 需要更新的商品
        List<PurchaseGoodsDetailBean> updateList = new ArrayList<>();
        StockNumPrice stockNumPrice = null;

        for (PurchaseGoodsDetailBean temp : freshGoods){
            if (StringUtils.startsWithIgnoreCase(temp.getStockCode(), "cb")){
                continue;
            }
            // 新增的商品
            if (temp.getId() == null){
                stockNumPrice = new StockNumPrice();
                setValue(stockNumPrice, temp);
                stockNumPrice.setMinInventory(temp.getGoodsNum().intValue());
                stockService.modifyStockInventory(stockNumPrice);
            } else {
                updateList.add(temp);
            }
        }


        Map<Integer, List<PurchaseGoodsDetailBean>> collect = updateList.stream().collect(Collectors.groupingBy(PurchaseGoodsDetailBean::getId));
        PurchaseGoodsDetailBean p = null;
        PurchaseGoodsDetailBean pgd = null;

        // 为什么用 oldGoods 做循环体, 因为不管什么情况下 oldGoods 总比 updateList 大或等于, 不会出现漏的情况

        int res = 0;
        for (PurchaseGoodsDetailBean temp : oldGoods){

            p = temp;
            // 需要更新的商品
            if (updateList.contains(temp)){
                pgd = collect.get(temp.getId()).get(0);
                // 原始商品, 数量的改变
                if (StringUtils.equals(pgd.getStockCode(), temp.getStockCode())){
                    res = getRes(pgd, p);
                    if (res == 0){
                        continue;
                    }
                } else {// 商品改变
                    // 已经删除的商品, 需要减去库存
                    stockNumPrice = new StockNumPrice();
                    setValue(stockNumPrice, p);
                    stockNumPrice.setMinInventory(-p.getGoodsNum().intValue());
                    stockService.modifyStockInventory(stockNumPrice);

                    // 被替换的商品为新商品, 需要新增库存
                    stockNumPrice = new StockNumPrice();
                    setValue(stockNumPrice, pgd);
                    stockNumPrice.setMinInventory(pgd.getGoodsNum().intValue());
                    stockService.modifyStockInventory(stockNumPrice);
                }
            } else { // 直接删除的商品
                stockNumPrice = new StockNumPrice();
                setValue(stockNumPrice, p);
                stockNumPrice.setMinInventory(-p.getGoodsNum().intValue());
                stockService.modifyStockInventory(stockNumPrice);
            }
        }
    }

    private void setValue (StockNumPrice stockNumPrice, PurchaseGoodsDetailBean p){
        stockNumPrice.setStockCode(p.getStockCode());
        stockNumPrice.setStockAddressCode(p.getStockAddressCode());
    }

    public int getRes (PurchaseGoodsDetailBean pgd, PurchaseGoodsDetailBean p){
        int res = 0;
        if (pgd.getGoodsNum().intValue() - p.getGoodsNum().intValue() == 0){
            return res;
        } else {
            if (pgd.getGoodsNum().intValue() - p.getGoodsNum().intValue() > 0){
                res = pgd.getGoodsNum().intValue() - p.getGoodsNum().intValue();
            } else {
                res = - (pgd.getGoodsNum().intValue() - p.getGoodsNum().intValue());
            }
        }

        return res;
    }
}
