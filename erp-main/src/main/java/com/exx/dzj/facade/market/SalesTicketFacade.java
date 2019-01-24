package com.exx.dzj.facade.market;

import com.exx.dzj.entity.market.SaleGoodsDetailBean;
import com.exx.dzj.entity.market.SaleInfo;
import com.exx.dzj.entity.market.SaleReceiptsDetails;
import com.exx.dzj.page.ERPage;
import com.exx.dzj.service.dictionary.DictionaryService;
import com.exx.dzj.service.salesgoodsdetail.SalesGoodsDetailService;
import com.exx.dzj.service.salesreceiptsdetail.SaleReceiptsDetailService;
import com.exx.dzj.service.salesticket.SalesTicketService;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author yangyun
 * @create 2019-01-08-11:09
 */
@Component
public class SalesTicketFacade {

    @Autowired
    private SalesTicketService salesTicketService;

    @Autowired
    private SalesGoodsDetailService salesGoodsDetailService;

    @Autowired
    private SaleReceiptsDetailService saleReceiptsDetailService;

    @Autowired
    private DictionaryService dictionaryService;

    /**
     * @description 新建销售单
     * @author yangyun
     * @date 2019/1/8 0008
     * @param saleInfo
     * @return void
     */
    @Transactional
    public void saveSalesTicket(SaleInfo saleInfo){
        Optional.of(saleInfo);
        List<SaleGoodsDetailBean> goodsDetailBeanList = saleInfo.getSaleGoodsDetailBeanList();
        List<SaleReceiptsDetails> receiptsDetailsList = saleInfo.getSaleReceiptsDetailsList();
        salesTicketService.saveSaleInfo(saleInfo);
        if(!CollectionUtils.isEmpty(goodsDetailBeanList)){
            goodsDetailBeanList = setGoodsSaleCode(goodsDetailBeanList, saleInfo.getSaleCode());
            salesGoodsDetailService.batchInsertSalesGoodsDetail(goodsDetailBeanList);
        }
        if (!CollectionUtils.isEmpty(receiptsDetailsList)){
            receiptsDetailsList = setReceiptsSaleCode(receiptsDetailsList, saleInfo.getSaleCode());
            saleReceiptsDetailService.batchInsertSalesReceiptsDeail(receiptsDetailsList);
        }
    }

    public ERPage<SaleInfo> querySalesTicketList(SaleInfo saleInfo, int pageNum, int pageSize){
        PageHelper.startPage(pageNum, pageSize);
        List<SaleInfo> saleInfoList = salesTicketService.querySalesTicketList(saleInfo);
        ERPage<SaleInfo> saleInfoPage = new ERPage<>(saleInfoList);
        return saleInfoPage;
    }

    public SaleInfo querySalesTicketById(Integer id){
        return salesTicketService.querySalesTicketById(id);
    }

    private  List<SaleGoodsDetailBean> setGoodsSaleCode(List<SaleGoodsDetailBean> saleGoodsDetailBeans, String saleCode){
        for (SaleGoodsDetailBean s:saleGoodsDetailBeans) {
            s.setSaleCode(saleCode);
        }
        return saleGoodsDetailBeans;
    }

    private  List<SaleReceiptsDetails> setReceiptsSaleCode(List<SaleReceiptsDetails> saleReceiptsDetails, String saleCode){
        for (SaleReceiptsDetails s:saleReceiptsDetails) {
            s.setSaleCode(saleCode);
        }
        return saleReceiptsDetails;
    }

    /**
     * @description 更新
     * @author yangyun
     * @date 2019/1/11 0011
     * @param saleInfo
     * @return void
     */
    @Transactional
    public void updateSalesTicket(SaleInfo saleInfo){
//        saleInfo = calculatePrice(saleInfo);
        Optional.ofNullable(saleInfo);
        salesTicketService.updateSalesTicketById(saleInfo);

        SaleInfo oldSaleInfo = querySalesTicketById(saleInfo.getId());

        List<SaleGoodsDetailBean> saleGoodsDetailBeanList = oldSaleInfo.getSaleGoodsDetailBeanList();
        List<SaleGoodsDetailBean> centreGoods = null;

        if (!CollectionUtils.isEmpty(saleGoodsDetailBeanList)){
            centreGoods = new ArrayList<>(saleGoodsDetailBeanList);
        }

        List<SaleGoodsDetailBean> goodsDetailBeanList = saleInfo.getSaleGoodsDetailBeanList();

        if(!CollectionUtils.isEmpty(goodsDetailBeanList)){
            if (!CollectionUtils.isEmpty(centreGoods)){
                // 获取修改的记录,并修改
                List<SaleGoodsDetailBean> finalCentreGoods = centreGoods;
                List<SaleGoodsDetailBean> collect = goodsDetailBeanList.stream().filter(o -> finalCentreGoods.stream().anyMatch(b -> StringUtils.equals(o.getId() + "", b.getId() + ""))).collect(Collectors.toList());
                if (!CollectionUtils.isEmpty(collect)){
                    collect = setGoodsSaleCode(collect, saleInfo.getSaleCode());
                    salesGoodsDetailService.batchUpdateSalesGoodsDetail(collect);
                }

                List<Integer> deleteSaleGoods = new ArrayList<>();
                List<SaleGoodsDetailBean> insertSaleGoods = new ArrayList<>();
                // 获取已删除元素,并删除数据库元素
                for(SaleGoodsDetailBean s : centreGoods){
                    if(!collect.contains(s)){
                        deleteSaleGoods.add(s.getId());
                    }
                }
                if (!CollectionUtils.isEmpty(deleteSaleGoods)){
                    salesGoodsDetailService.batchDeleteSalesGoodsDetail(deleteSaleGoods);
                }

                for(SaleGoodsDetailBean s : goodsDetailBeanList){
                    if(!collect.contains(s)){
                        insertSaleGoods.add(s);
                    }
                }

                if (!CollectionUtils.isEmpty(insertSaleGoods)){
                    insertSaleGoods = setGoodsSaleCode(insertSaleGoods, saleInfo.getSaleCode());
                    salesGoodsDetailService.batchInsertSalesGoodsDetail(insertSaleGoods);
                }
            } else{
                goodsDetailBeanList = setGoodsSaleCode(goodsDetailBeanList, saleInfo.getSaleCode());
                salesGoodsDetailService.batchInsertSalesGoodsDetail(goodsDetailBeanList);
            }
        }


        List<SaleReceiptsDetails> saleReceiptsDetailsList = oldSaleInfo.getSaleReceiptsDetailsList();
        List<SaleReceiptsDetails> receiptsDetailsList = saleInfo.getSaleReceiptsDetailsList();
        List<SaleReceiptsDetails> centreReceipts = null;

        if (!CollectionUtils.isEmpty(saleReceiptsDetailsList)){
            centreReceipts = new ArrayList<>(saleReceiptsDetailsList);
        }

        if (!CollectionUtils.isEmpty(receiptsDetailsList)){
            if (!CollectionUtils.isEmpty(centreReceipts)){
                List<SaleReceiptsDetails> finalCentreReceipts = centreReceipts;
                List<SaleReceiptsDetails> collects = receiptsDetailsList.stream().filter(o -> finalCentreReceipts.stream().anyMatch(b -> StringUtils.equals(o.getId() + "", b.getId() + ""))).collect(Collectors.toList());
                if (!CollectionUtils.isEmpty(collects)){
                    collects = setReceiptsSaleCode(collects, saleInfo.getSaleCode());
                    saleReceiptsDetailService.batchUpdateSalesReceiptsDeail(collects);
                }

                List<Integer> deleteSaleReceipts = new ArrayList<>();
                List<SaleReceiptsDetails> insertSaleReceipts = new ArrayList<>();
                for(SaleReceiptsDetails e: centreReceipts){
                    if (!collects.contains(e)){
                        deleteSaleReceipts.add(e.getId());
                    }
                }
                if (!CollectionUtils.isEmpty(deleteSaleReceipts)){
                    saleReceiptsDetailService.batchDeleteSalesReceiptsDeail(deleteSaleReceipts);
                }

                for(SaleReceiptsDetails e: receiptsDetailsList){
                    if (!collects.contains(e)){
                        insertSaleReceipts.add(e);
                    }
                }
                if(!CollectionUtils.isEmpty(insertSaleReceipts)){
                    insertSaleReceipts = setReceiptsSaleCode(insertSaleReceipts, saleInfo.getSaleCode());
                    saleReceiptsDetailService.batchInsertSalesReceiptsDeail(insertSaleReceipts);
                }
            }else {
                receiptsDetailsList = setReceiptsSaleCode(receiptsDetailsList, saleInfo.getSaleCode());
                saleReceiptsDetailService.batchInsertSalesReceiptsDeail(receiptsDetailsList);
            }
        }
    }

    /**
     * @description 用于价格计算
     * @author yangyun
     * @date 2019/1/11 0011
     * @param saleInfo
     * @return com.exx.dzj.entity.market.SaleInfo
     */
    private SaleInfo calculatePrice(SaleInfo saleInfo){
        // 计算已收款金额
        List<SaleReceiptsDetails> saleReceiptsDetailsList = saleInfo.getSaleReceiptsDetailsList();
        double sumCollectedAmount = 0.00;
        if(Optional.of(saleReceiptsDetailsList).isPresent()) {
            sumCollectedAmount = saleReceiptsDetailsList.stream()
                    .mapToDouble((o) -> o.getCollectedAmount().doubleValue())
                    .sum();
            saleInfo.setReceivedAmoun(new BigDecimal(sumCollectedAmount));
        }
        List<SaleGoodsDetailBean> saleGoodsDetailBeanList = saleInfo.getSaleGoodsDetailBeanList();
        return saleInfo;
    }

    /**
     * @description 销售单删除
     * @author yangyun
     * @date 2019/1/11 0011
     * @param id
     * @return void
     */
    @Transactional
    public void deleteSalesTicket(Integer id){
        SaleInfo saleInfo = salesTicketService.querySalesTicketById(id);
        Optional<SaleInfo> saleInfoOptional = Optional.of(saleInfo);
        salesTicketService.deleteSaleinfo(id);

        List<SaleReceiptsDetails> saleReceiptsDetails = Optional.of(saleInfo.getSaleReceiptsDetailsList()).get();
        Stream<SaleReceiptsDetails> stream = saleReceiptsDetails.stream();
        List<Integer> srdIds = stream.map(s -> s.getId()).collect(Collectors.toList());
        if(!CollectionUtils.isEmpty(srdIds)){
            saleReceiptsDetailService.batchDeleteSalesReceiptsDeail(srdIds);
        }

        List<SaleGoodsDetailBean> saleGoodsDetailBeans = Optional.of(saleInfo.getSaleGoodsDetailBeanList()).get();
        List<Integer> sgbIds = saleGoodsDetailBeans.stream().map((s) -> s.getId()).collect(Collectors.toList());
        if(!CollectionUtils.isEmpty(sgbIds)){
            salesGoodsDetailService.batchDeleteSalesGoodsDetail(sgbIds);
        }
    }

    public List<SaleReceiptsDetails> querySaleReceviptDetailList(String saleCode){
        return saleReceiptsDetailService.querySaleReceviptDetailList(saleCode);
    }
}
