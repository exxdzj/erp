package com.exx.dzj.facade.market.task;

import com.exx.dzj.constant.CommonConstant;
import com.exx.dzj.entity.purchase.PurchaseGoodsDetailBean;
import com.exx.dzj.entity.purchase.PurchaseInfo;
import com.exx.dzj.entity.stock.StockNumPrice;
import com.exx.dzj.service.purchasegoods.PurchaseGoodsService;
import com.exx.dzj.service.stock.StockService;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @ClassName PurchaseGoodsAvgPirceCalculateTask
 * @Description: 用于采购单增删改时, 采购商品平均价格计算, 用于存货收发汇总统计
 * @Author yangyun
 * @Date 2019/9/27 0027 10:12
 * @Version 1.0
 **/
public class PurchaseGoodsAvgPirceCalculateTask implements Runnable {

    private String type;
    private PurchaseInfo info;
    private PurchaseInfo oldInfo;
    private PurchaseGoodsService purchaseGoodsService;
    private StockService stockService;

    public PurchaseGoodsAvgPirceCalculateTask (String type, PurchaseInfo info, PurchaseInfo oldInfo, PurchaseGoodsService purchaseGoodsService, StockService stockService){
        this.type = type;
        this.info = info;
        this.oldInfo = oldInfo;
        this.purchaseGoodsService = purchaseGoodsService;
        this.stockService = stockService;
    }

    @Override
    public void run() {
        try {
            TimeUnit.MILLISECONDS.sleep(100);

            // 获取商品列表
            List<PurchaseGoodsDetailBean> goodsList = info.getPurchaseGoodsDetailBeans();
            switch (type){
                case CommonConstant.UPDATE:
                    List<PurchaseGoodsDetailBean> oldList = oldInfo.getPurchaseGoodsDetailBeans();
                    for (PurchaseGoodsDetailBean temp : goodsList){
                        updateAvgPrice(temp);
                    }

                    for (PurchaseGoodsDetailBean temp : oldList){
                        updateAvgPrice(temp);
                    }

                    break;
                case CommonConstant.INSERT:
                    for (PurchaseGoodsDetailBean temp : goodsList){
                        updateAvgPrice(temp);
                    }
                    break;
                case CommonConstant.DELETE:
                    for (PurchaseGoodsDetailBean temp : goodsList){
                        updateAvgPrice(temp);
                    }
                    break;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void updateAvgPrice (PurchaseGoodsDetailBean temp){
        PurchaseGoodsDetailBean goodsBean = purchaseGoodsService.queryGoodsPriceAndNum(temp.getStockCode(), temp.getStockAddressCode());
        double sumNum = goodsBean.getSumNum(); // 数量
        BigDecimal sumPrice = goodsBean.getSumPrice();// 总价格
        BigDecimal avgPrice = sumPrice.divide(new BigDecimal(sumNum), CommonConstant.DEFAULT_VALUE_FOUR, BigDecimal.ROUND_HALF_UP);

        StockNumPrice stockNumPrice = new StockNumPrice();
        stockNumPrice.setStockCode(temp.getStockCode()).setStockAddressCode(temp.getStockAddressCode()).setAvgPrice(avgPrice);
        stockService.updateStockAvgPrice(stockNumPrice);
    }
}
