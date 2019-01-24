package com.exx.dzj.facade.purchase;

import com.exx.dzj.entity.purchase.PurchaseGoodsDetailBean;
import com.exx.dzj.entity.purchase.PurchaseInfo;
import com.exx.dzj.entity.purchase.PurchaseReceiptsDetailsBean;
import com.exx.dzj.page.ERPage;
import com.exx.dzj.service.purchasegoods.PurchaseGoodsService;
import com.exx.dzj.service.purchasereceipts.PurchaseReceiptsService;
import com.exx.dzj.service.purchaseticket.PurchaseTicketService;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author yangyun
 * @create 2019-01-12-15:39
 */
@Component
public class PurchaseTicketFacade {

    @Autowired
    private PurchaseTicketService purchaseTicketService;

    @Autowired
    private PurchaseGoodsService purchaseGoodsService;

    @Autowired
    private PurchaseReceiptsService purchaseReceiptsService;

    /**
     * @description 新增采购单
     * @author yangyun
     * @date 2019/1/12 0012
     * @param purchaseInfo
     * @return void
     */
    @Transactional
    public void savePurchaseTicket(PurchaseInfo purchaseInfo){
        Optional.of(purchaseInfo);
        List<PurchaseGoodsDetailBean> purchaseGoodsDetailBeans = purchaseInfo.getPurchaseGoodsDetailBeans();
        List<PurchaseReceiptsDetailsBean> purchaseReceiptsDetailsBeans = purchaseInfo.getPurchaseReceiptsDetailsBeans();
        purchaseTicketService.savePurchaseTicket(purchaseInfo);

        if (!CollectionUtils.isEmpty(purchaseGoodsDetailBeans)){
            purchaseGoodsDetailBeans = setGoodsPurchaseCode(purchaseGoodsDetailBeans, purchaseInfo.getPurchaseCode());
            purchaseGoodsService.batchInsertPurchaseGoods(purchaseGoodsDetailBeans);
        }

        if (!CollectionUtils.isEmpty(purchaseReceiptsDetailsBeans)){
            purchaseReceiptsDetailsBeans = setReceiptsPurchaseCode(purchaseReceiptsDetailsBeans, purchaseInfo.getPurchaseCode());
            purchaseReceiptsService.batchInsertPurchaseReceipts(purchaseReceiptsDetailsBeans);
        }
    }

    private List<PurchaseGoodsDetailBean> setGoodsPurchaseCode(List<PurchaseGoodsDetailBean> purchaseGoodsDetailBeans, String purchaseCode){
        for (PurchaseGoodsDetailBean e : purchaseGoodsDetailBeans){
            e.setPurchaseCode(purchaseCode);
        }
        return purchaseGoodsDetailBeans;
    }

    private List<PurchaseReceiptsDetailsBean> setReceiptsPurchaseCode (List<PurchaseReceiptsDetailsBean> purchaseReceiptsDetailsBeans, String purchaseCode){
        for (PurchaseReceiptsDetailsBean  e : purchaseReceiptsDetailsBeans){
            e.setPurchaseCode(purchaseCode);
        }
        return purchaseReceiptsDetailsBeans;
    }

    /**
     * @description 采购单列表查询
     * @author yangyun
     * @date 2019/1/12 0012
     * @param pageNum
     * @param pageSize
     * @param purchaseInfo
     * @return com.exx.dzj.page.ERPage<com.exx.dzj.entity.purchase.PurchaseInfo>
     */
    public ERPage<PurchaseInfo> queryPurchaseTickets(int pageNum, int pageSize, PurchaseInfo purchaseInfo){
        PageHelper.startPage(pageNum, pageSize);
        List<PurchaseInfo> purchaseInfoList = purchaseTicketService.queryPurchaseTickets(purchaseInfo);
        ERPage<PurchaseInfo> purchasePage = new ERPage<>(purchaseInfoList);
        return purchasePage;
    }

    public PurchaseInfo queryPurchaseTicketDetail(Integer id){
        return purchaseTicketService.queryPurchaseTicketDetail(id);
    }

    @Transactional
    public void updatePurchaseTicket(PurchaseInfo purchaseInfo){
        Optional.of(purchaseInfo);
        purchaseTicketService.updatePurchaseTicket(purchaseInfo);

        PurchaseInfo oldPurchaseInfo = purchaseTicketService.queryPurchaseTicketDetail(purchaseInfo.getId());

        List<PurchaseGoodsDetailBean> purchaseGoodsDetailBeanList = oldPurchaseInfo.getPurchaseGoodsDetailBeans();
        List<PurchaseGoodsDetailBean> centreGoods = null;

        if (!CollectionUtils.isEmpty(purchaseGoodsDetailBeanList)){
            centreGoods = new ArrayList<>(purchaseGoodsDetailBeanList);
        }

        List<PurchaseGoodsDetailBean> goodsDetailBeanList = purchaseInfo.getPurchaseGoodsDetailBeans();

        if (!CollectionUtils.isEmpty(goodsDetailBeanList)){
            if (!CollectionUtils.isEmpty(centreGoods)){
                List<PurchaseGoodsDetailBean> finalCentreGoods = centreGoods;
                List<PurchaseGoodsDetailBean> collect = goodsDetailBeanList.stream().filter(o -> finalCentreGoods.stream().anyMatch(b -> StringUtils.equals(o.getId() + "", b.getId() + ""))).collect(Collectors.toList());
                if (!CollectionUtils.isEmpty(collect)){
                    collect = setGoodsPurchaseCode(collect, purchaseInfo.getPurchaseCode());
                    purchaseGoodsService.batchUpdatePurchaseGoodsDetails(collect);
                }

                List<Integer> deletePurchaseGoods = new ArrayList<>();
                List<PurchaseGoodsDetailBean> insertPurchaseGoods = new ArrayList<>();
                for (PurchaseGoodsDetailBean e : finalCentreGoods){
                    if (!collect.contains(e)){
                        deletePurchaseGoods.add(e.getId());
                    }
                }
                if (!CollectionUtils.isEmpty(deletePurchaseGoods)){
                    purchaseGoodsService.batchDeletePurchaseGoods(deletePurchaseGoods);
                }

                for (PurchaseGoodsDetailBean e : goodsDetailBeanList){
                    if (!collect.contains(e)){
                        insertPurchaseGoods.add(e);
                    }
                }
                if (!CollectionUtils.isEmpty(insertPurchaseGoods)){
                    insertPurchaseGoods = setGoodsPurchaseCode(insertPurchaseGoods, purchaseInfo.getPurchaseCode());
                    purchaseGoodsService.batchInsertPurchaseGoods(insertPurchaseGoods);
                }
            } else {
                goodsDetailBeanList = setGoodsPurchaseCode(goodsDetailBeanList, purchaseInfo.getPurchaseCode());
                purchaseGoodsService.batchInsertPurchaseGoods(goodsDetailBeanList);
            }
        }

        List<PurchaseReceiptsDetailsBean> receiptsDetailsBeans = oldPurchaseInfo.getPurchaseReceiptsDetailsBeans();
        List<PurchaseReceiptsDetailsBean> detailsBeanList = purchaseInfo.getPurchaseReceiptsDetailsBeans();
        List<PurchaseReceiptsDetailsBean> centreReceipts = null;
        if (!CollectionUtils.isEmpty(receiptsDetailsBeans)){
            centreReceipts = new ArrayList<>(receiptsDetailsBeans);
        }

        if (!CollectionUtils.isEmpty(detailsBeanList)){
            if (!CollectionUtils.isEmpty(centreReceipts)){
                List<PurchaseReceiptsDetailsBean> finalCentreGoods = centreReceipts;
                List<PurchaseReceiptsDetailsBean> collect = detailsBeanList.stream().filter(o -> finalCentreGoods.stream().anyMatch(b -> StringUtils.equals(o.getId() + "", b.getId() + ""))).collect(Collectors.toList());
                if (!CollectionUtils.isEmpty(collect)){
                    collect = setReceiptsPurchaseCode(collect, purchaseInfo.getPurchaseCode());
                    purchaseReceiptsService.batchUpdatePurchaseReceiptsDetails(collect);
                }

                List<Integer> deletePurchaseReceipts = new ArrayList<>();
                List<PurchaseReceiptsDetailsBean> insertPurchaseReceipts = new ArrayList<>();

                for (PurchaseReceiptsDetailsBean e : finalCentreGoods){
                    if (!collect.contains(e)){
                        deletePurchaseReceipts.add(e.getId());
                    }
                }
                if (!CollectionUtils.isEmpty(deletePurchaseReceipts)){
                    purchaseReceiptsService.batchDeletePurchaseReceipts(deletePurchaseReceipts);
                }

                for (PurchaseReceiptsDetailsBean e : detailsBeanList){
                    if (!collect.contains(e)){
                        insertPurchaseReceipts.add(e);
                    }
                }
                if (!CollectionUtils.isEmpty(insertPurchaseReceipts)){
                    insertPurchaseReceipts = setReceiptsPurchaseCode(insertPurchaseReceipts, purchaseInfo.getPurchaseCode());
                    purchaseReceiptsService.batchInsertPurchaseReceipts(insertPurchaseReceipts);
                }
            } else {
                detailsBeanList = setReceiptsPurchaseCode(detailsBeanList, purchaseInfo.getPurchaseCode());
                purchaseReceiptsService.batchInsertPurchaseReceipts(detailsBeanList);
            }
        }
    }

    @Transactional
    public void deletePurchaseTicket(Integer id){
        PurchaseInfo purchaseInfo = purchaseTicketService.queryPurchaseTicketDetail(id);
        Optional.of(purchaseInfo);
        purchaseTicketService.deletePurchaseTicket(id);

        List<PurchaseGoodsDetailBean> purchaseGoodsDetailBeans = purchaseInfo.getPurchaseGoodsDetailBeans();
        if (Optional.ofNullable(purchaseGoodsDetailBeans).isPresent()){
            List<Integer> goodsIds = purchaseGoodsDetailBeans.stream().map((o) -> o.getId()).collect(Collectors.toList());
            purchaseGoodsService.batchDeletePurchaseGoods(goodsIds);
        }

        List<PurchaseReceiptsDetailsBean> purchaseReceiptsDetailsBeans = purchaseInfo.getPurchaseReceiptsDetailsBeans();
        if (Optional.ofNullable(purchaseReceiptsDetailsBeans).isPresent()){
            List<Integer> receoptIds = purchaseReceiptsDetailsBeans.stream().map(o -> o.getId()).collect(Collectors.toList());
            purchaseReceiptsService.batchDeletePurchaseReceipts(receoptIds);
        }
    }

    public List<PurchaseReceiptsDetailsBean> queryPurchaseReceviptDetailList(String purchaseCode){
        return purchaseReceiptsService.queryPurchaseReceviptDetailList(purchaseCode);
    }
}
