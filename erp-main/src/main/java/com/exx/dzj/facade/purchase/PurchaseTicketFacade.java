package com.exx.dzj.facade.purchase;

import com.exx.dzj.entity.purchase.PurchaseGoodsDetailBean;
import com.exx.dzj.entity.purchase.PurchaseInfo;
import com.exx.dzj.entity.purchase.PurchaseReceiptsDetailsBean;
import com.exx.dzj.page.ERPage;
import com.exx.dzj.service.purchasegoods.PurchaseGoodsService;
import com.exx.dzj.service.purchasereceipts.PurchaseReceiptsService;
import com.exx.dzj.service.purchaseticket.PurchaseTicketService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
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
        List<PurchaseGoodsDetailBean> purchaseGoodsDetailBeans = purchaseInfo.getPurchaseGoodsDetailBeans();
        List<PurchaseReceiptsDetailsBean> purchaseReceiptsDetailsBeans = purchaseInfo.getPurchaseReceiptsDetailsBeans();
        Optional.of(purchaseInfo);
        purchaseTicketService.savePurchaseTicket(purchaseInfo);

        if (Optional.of(purchaseGoodsDetailBeans).isPresent()){
            purchaseGoodsService.batchInsertPurchaseGoods(purchaseGoodsDetailBeans);
        }

        if (Optional.of(purchaseReceiptsDetailsBeans).isPresent()){
            purchaseReceiptsService.batchInsertPurchaseReceipts(purchaseReceiptsDetailsBeans);
        }
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

        List<PurchaseGoodsDetailBean> purchaseGoodsDetailBeans = purchaseInfo.getPurchaseGoodsDetailBeans();
        if (Optional.of(purchaseGoodsDetailBeans).isPresent()){
            purchaseGoodsService.batchUpdatePurchaseGoodsDetails(purchaseGoodsDetailBeans);
        }

        List<PurchaseReceiptsDetailsBean> purchaseReceiptsDetailsBeans = purchaseInfo.getPurchaseReceiptsDetailsBeans();
        if (Optional.of(purchaseReceiptsDetailsBeans).isPresent()){
            purchaseReceiptsService.batchUpdatePurchaseReceiptsDetails(purchaseReceiptsDetailsBeans);
        }
    }

    @Transactional
    public void deletePurchaseTicket(Integer id){
        PurchaseInfo purchaseInfo = purchaseTicketService.queryPurchaseTicketDetail(id);
        Optional.of(purchaseInfo);
        purchaseTicketService.deletePurchaseTicket(id);

        List<PurchaseGoodsDetailBean> purchaseGoodsDetailBeans = purchaseInfo.getPurchaseGoodsDetailBeans();
        if (Optional.of(purchaseGoodsDetailBeans).isPresent()){
            List<Integer> goodsIds = purchaseGoodsDetailBeans.stream().map((o) -> o.getId()).collect(Collectors.toList());
            purchaseGoodsService.batchDeletePurchaseGoods(goodsIds);
        }

        List<PurchaseReceiptsDetailsBean> purchaseReceiptsDetailsBeans = purchaseInfo.getPurchaseReceiptsDetailsBeans();
        if (Optional.of(purchaseReceiptsDetailsBeans).isPresent()){
            List<Integer> receoptIds = purchaseReceiptsDetailsBeans.stream().map(o -> o.getId()).collect(Collectors.toList());
            purchaseReceiptsService.batchDeletePurchaseReceipts(receoptIds);
        }
    }
}
