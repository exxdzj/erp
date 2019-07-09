package com.exx.dzj.facade.reportforms.purchase;

import com.exx.dzj.bean.PurchaseReportQuery;
import com.exx.dzj.constant.CommonConstant;
import com.exx.dzj.entity.purchase.PurchaseGoodsReport;
import com.exx.dzj.entity.purchase.PurchaseInfoBaseReport;
import com.exx.dzj.entity.purchase.PurchaseInfoReport;
import com.exx.dzj.service.report.PurchaseReportService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class PurchaseReportFacade {
    @Autowired
    private PurchaseReportService purchaseReportService;

    public Map<String, Object> queryPurchaseFeePayDetail (PurchaseReportQuery query){
        List<PurchaseInfoBaseReport> purchaseInfoBaseReports = purchaseReportService.queryPurchaseFeePayDetail(query);

        List<PurchaseInfoReport> data = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();

        if (CollectionUtils.isEmpty(purchaseInfoBaseReports)){
            map.put("listData", data);
            return map;
        }

        Map<String, List<PurchaseInfoBaseReport>> collect = purchaseInfoBaseReports.stream().collect(Collectors.groupingBy(PurchaseInfoBaseReport::getPurchaseCode));
        collect.keySet().stream().forEach(
                key -> {
                    PurchaseInfoReport pir = new PurchaseInfoReport();
                    data.add(pir);
                    List<PurchaseInfoBaseReport> purchaseInfoBaseReports1 = collect.get(key);
                    pir.setPurchaseCode(key);
                    double sumGoods = 0;
                    BigDecimal sumPurchaseVolume = new BigDecimal(0);
                    PurchaseGoodsReport pgr = null;
                    for (int i = 0; i < purchaseInfoBaseReports1.size(); i++){
                        sumGoods = sumGoods + purchaseInfoBaseReports1.get(i).getGoodsNum();
                        BigDecimal price = purchaseInfoBaseReports1.get(i).getDiscountRate().multiply(purchaseInfoBaseReports1.get(i).getRealSellPrice()).multiply(new BigDecimal(purchaseInfoBaseReports1.get(i).getGoodsNum()));
                        sumPurchaseVolume = sumPurchaseVolume.add(price);
                        if (i == CommonConstant.DEFAULT_VALUE_ZERO){
                            pir.setPurchaseDate(purchaseInfoBaseReports1.get(i).getPurchaseDate());
                            pir.setCustCode(purchaseInfoBaseReports1.get(i).getCustCode());
                            pir.setCustName(purchaseInfoBaseReports1.get(i).getCustName());
                            pir.setUserCode(purchaseInfoBaseReports1.get(i).getUserCode());
                            pir.setRealName(purchaseInfoBaseReports1.get(i).getRealName() == null ? " " : purchaseInfoBaseReports1.get(i).getRealName());
                        }
                        pgr = new PurchaseGoodsReport();
                        pgr.setGoodsNum(purchaseInfoBaseReports1.get(i).getGoodsNum());
                        pgr.setStockAddress(purchaseInfoBaseReports1.get(i).getStockAddress());
                        pgr.setStockCode(purchaseInfoBaseReports1.get(i).getStockCode());
                        pgr.setStockName(purchaseInfoBaseReports1.get(i).getStockName());
                        pgr.setRealSellPrice(purchaseInfoBaseReports1.get(i).getRealSellPrice());
                        pgr.setPurchaseVolume(price);
                        pir.getGoodsReports().add(pgr);
                    }
                    pir.setGoodsNumTotal(sumGoods);
                    pir.setPurchaseVolumeTotal(sumPurchaseVolume);
                }
        );

        // 订制排序, 按采购时间降序
        Comparator<PurchaseInfoReport> com = (a,b) -> b.getPurchaseDate().compareTo(a.getPurchaseDate());

        List<PurchaseInfoReport> coll = data.stream().sorted(com).collect(Collectors.toList());

        // 合计
        double sum = data.stream().mapToDouble(PurchaseInfoReport::getGoodsNumTotal).sum();
        map.put("goodsSum", sum);

        BigDecimal purchaseVolumeSum = data.stream().map(PurchaseInfoReport::getPurchaseVolumeTotal).reduce(BigDecimal.ZERO, BigDecimal::add);
        map.put("purchaseVolumeSum", purchaseVolumeSum);
        map.put("listData", coll);
        return map;
    }
}
