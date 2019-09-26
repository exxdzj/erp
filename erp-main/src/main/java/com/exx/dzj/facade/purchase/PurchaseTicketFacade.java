package com.exx.dzj.facade.purchase;

import com.exx.dzj.constant.CommonConstant;
import com.exx.dzj.entity.purchase.*;
import com.exx.dzj.entity.stock.StockBean;
import com.exx.dzj.entity.stock.StockNumPrice;
import com.exx.dzj.excepte.ErpException;
import com.exx.dzj.facade.market.task.AddStockInventoryTask;
import com.exx.dzj.facade.sys.BusEncodeFacade;
import com.exx.dzj.facade.user.UserTokenFacade;
import com.exx.dzj.page.ERPage;
import com.exx.dzj.service.purchasegoods.PurchaseGoodsService;
import com.exx.dzj.service.purchasereceipts.PurchaseReceiptsService;
import com.exx.dzj.service.purchaseticket.PurchaseTicketService;
import com.exx.dzj.service.stock.StockService;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
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

    private static final String REG = "[\u4e00-\u9fa5]";

    @Autowired
    private PurchaseTicketService purchaseTicketService;

    @Autowired
    private PurchaseGoodsService purchaseGoodsService;

    @Autowired
    private PurchaseReceiptsService purchaseReceiptsService;

    @Autowired
    private StockService stockInfoService;

    @Autowired
    UserTokenFacade userTokenFacade;

    @Autowired
    private BusEncodeFacade busEncodeFacade;

    @Autowired
    private StockService stockService;

    public void importData (List<PurchaseInfo> purchaseInfoList){
        for (PurchaseInfo p : purchaseInfoList){
            try{
                savePurchaseTicket2(p);
            } catch (ErpException e){
                e.printStackTrace();
            }
        }
    }

    @Transactional
    public void savePurchaseTicket2(PurchaseInfo purchaseInfo){
        Optional.of(purchaseInfo);
        List<PurchaseGoodsDetailBean> purchaseGoodsDetailBeans = purchaseInfo.getPurchaseGoodsDetailBeans();
        List<PurchaseReceiptsDetailsBean> purchaseReceiptsDetailsBeans = purchaseInfo.getPurchaseReceiptsDetailsBeans();
        setStatus2(purchaseInfo);

        purchaseInfo.setIsEnable(CommonConstant.DEFAULT_VALUE_ONE);


        if (!CollectionUtils.isEmpty(purchaseGoodsDetailBeans)){
            // 该部分代码用于金蝶导入金额计算, 优惠后金额
            BigDecimal receivableAccoun = new BigDecimal(0);
            BigDecimal hundred = new BigDecimal(100);
            BigDecimal divide = new BigDecimal(0);
            BigDecimal price = new BigDecimal(0);

            for (PurchaseGoodsDetailBean pdb : purchaseGoodsDetailBeans){
                divide = hundred.subtract(pdb.getDiscountRate()).divide(hundred);
                price = pdb.getRealSellPrice().multiply(new BigDecimal(pdb.getGoodsNum()));

                receivableAccoun = receivableAccoun.add(price.multiply(divide));

            }
            purchaseInfo.setReceivableAccoun(receivableAccoun.setScale(2, BigDecimal.ROUND_HALF_UP));

            purchaseGoodsDetailBeans = setGoodsPurchaseCode(purchaseGoodsDetailBeans, purchaseInfo.getPurchaseCode());
            purchaseGoodsService.batchInsertPurchaseGoods(purchaseGoodsDetailBeans);
        }

        if (!CollectionUtils.isEmpty(purchaseReceiptsDetailsBeans)){
            purchaseReceiptsDetailsBeans = setReceiptsPurchaseCode(purchaseReceiptsDetailsBeans, purchaseInfo.getPurchaseCode());
            purchaseReceiptsService.batchInsertPurchaseReceipts(purchaseReceiptsDetailsBeans);
        }

        String userCode = purchaseInfo.getUserCode();

        if (StringUtils.isNotEmpty(userCode)){
            int index = -1;
            if (userCode.matches(".*" + REG + ".*")){

                index = userCode.split(REG).length;
                if (index > 0){
                    index = userCode.split(REG)[0].length();
                    String substring = userCode.substring(0, index);
                    purchaseInfo.setUserCode(substring);
                }

            }
        }

        purchaseTicketService.savePurchaseTicket(purchaseInfo);
    }

    public void setStatus2 (PurchaseInfo purchaseInfo){
        String paymentStatus = purchaseInfo.getPaymentStatus();
        switch (paymentStatus){
            case "未付款" :
                purchaseInfo.setPaymentStatus("cs01");
                break;
            case "全部已付款" :
                purchaseInfo.setPaymentStatus("cs03");
                break;
            case "部分付款" :
                purchaseInfo.setPaymentStatus("cs02");
                break;
        }
    }

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
        setStatus(purchaseInfo);

        purchaseInfo.setIsEnable(CommonConstant.DEFAULT_VALUE_ONE);
        purchaseTicketService.savePurchaseTicket(purchaseInfo);

        if (!CollectionUtils.isEmpty(purchaseGoodsDetailBeans)){
            purchaseGoodsDetailBeans = setGoodsPurchaseCode(purchaseGoodsDetailBeans, purchaseInfo.getPurchaseCode());
            purchaseGoodsService.batchInsertPurchaseGoods(purchaseGoodsDetailBeans);
        }

        if (!CollectionUtils.isEmpty(purchaseReceiptsDetailsBeans)){
            purchaseReceiptsDetailsBeans = setReceiptsPurchaseCode(purchaseReceiptsDetailsBeans, purchaseInfo.getPurchaseCode());
            purchaseReceiptsService.batchInsertPurchaseReceipts(purchaseReceiptsDetailsBeans);
        } else {
            BigDecimal sumCollectedAmount = purchaseInfo.getSumCollectedAmount();
            boolean b = sumCollectedAmount.compareTo(BigDecimal.ZERO) > 0;
            String collectedAmounts = purchaseInfo.getCollectedAmounts();
            if (b){
                PurchaseReceiptsDetailsBean prd = new PurchaseReceiptsDetailsBean();
                prd.setCollectionAccount(collectedAmounts);
                prd.setCollectedAmount(sumCollectedAmount);
                prd.setPurchaseCode(purchaseInfo.getPurchaseCode());
                purchaseReceiptsDetailsBeans.add(prd);
                purchaseReceiptsService.batchInsertPurchaseReceipts(purchaseReceiptsDetailsBeans);
            }
        }

        // 库存处理
        updateStockInventory(null, purchaseInfo);
    }

    @Autowired
    private AsyncTaskExecutor asyncSaleExecutr;

    private void updateStockInventory (PurchaseInfo old, PurchaseInfo fresh){

        AddStockInventoryTask task = new AddStockInventoryTask(old, fresh, stockService);
        asyncSaleExecutr.execute(task);
    }

    /**
     * @description 采购商品关联仓库
     * @author yangyun
     * @date 2019/5/31 0031
     * @param purchaseGoodsDetailBeans
     * @return void
     */
    private void purcahseGoodsRelevanceStock (List<PurchaseGoodsDetailBean> purchaseGoodsDetailBeans){
        for (PurchaseGoodsDetailBean pdb : purchaseGoodsDetailBeans){

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

    private PurchaseInfo setStatus (PurchaseInfo purchaseInfo){
        if (purchaseInfo.getSumCollectedAmount().doubleValue() == 0){
            purchaseInfo.setPaymentStatus("cs01");
        } else if (purchaseInfo.getSumCollectedAmount().doubleValue() == purchaseInfo.getReceivableAccoun().doubleValue()){
            purchaseInfo.setPaymentStatus("cs03");
        } else {
            purchaseInfo.setPaymentStatus("cs02");
        }
        return purchaseInfo;
    }

    /**
     * @description 采购单列表查询
     * @author yangyun
     * @date 2019/1/12 0012
     * @param pageNum
     * @param pageSize
     * @param query
     * @return com.exx.dzj.page.ERPage<com.exx.dzj.entity.purchase.PurchaseInfo>
     */
    public ERPage<PurchaseInfo> queryPurchaseTickets(int pageNum, int pageSize, PurchaseQuery query){
        PageHelper.startPage(pageNum, pageSize);
        List<PurchaseInfo> purchaseInfoList = purchaseTicketService.queryPurchaseTickets(query);
        ERPage<PurchaseInfo> purchasePage = new ERPage<>(purchaseInfoList);
        return purchasePage;
    }

    public PurchaseInfo queryPurchaseTicketDetail(Integer id){
        return purchaseTicketService.queryPurchaseTicketDetail(id);
    }

    @Transactional
    public void updatePurchaseTicket(PurchaseInfo purchaseInfo){
        Optional.of(purchaseInfo);
        setStatus(purchaseInfo);
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
        } else {
            if (!CollectionUtils.isEmpty(purchaseGoodsDetailBeanList)){
                List<Integer> collect = purchaseGoodsDetailBeanList.stream().map(o -> o.getId()).collect(Collectors.toList());
                purchaseGoodsService.batchDeletePurchaseGoods(collect);
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

//                List<Integer> collect1 = collect.stream().map(PurchaseReceiptsDetailsBean::getId).collect(Collectors.toList());
//                for (PurchaseReceiptsDetailsBean e : finalCentreGoods){
//                    boolean contains = collect1.contains(e.getId());
//                    if (!contains){
//                        deletePurchaseReceipts.add(e.getId());
//                    }
//
//                }


                for (PurchaseReceiptsDetailsBean e : finalCentreGoods){
                    boolean contains = collect.contains(e);
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
        } else {
            if (!CollectionUtils.isEmpty(receiptsDetailsBeans)){
                List<Integer> collect = receiptsDetailsBeans.stream().map(o -> o.getId()).collect(Collectors.toList());
                purchaseReceiptsService.batchDeletePurchaseReceipts(collect);
            }
        }

        updateStockInventory(oldPurchaseInfo, purchaseInfo);
    }

    @Transactional
    public void deletePurchaseTicket(Integer id){
        PurchaseInfo purchaseInfo = purchaseTicketService.queryPurchaseTicketDetail(id);
        Optional.of(purchaseInfo);
        purchaseTicketService.deletePurchaseTicket(id);

        List<PurchaseGoodsDetailBean> purchaseGoodsDetailBeans = purchaseInfo.getPurchaseGoodsDetailBeans();
        if (!CollectionUtils.isEmpty(purchaseGoodsDetailBeans)){
            List<Integer> goodsIds = purchaseGoodsDetailBeans.stream().map((o) -> o.getId()).collect(Collectors.toList());
            purchaseGoodsService.batchDeletePurchaseGoods(goodsIds);
        }

        List<PurchaseReceiptsDetailsBean> purchaseReceiptsDetailsBeans = purchaseInfo.getPurchaseReceiptsDetailsBeans();
        if (!CollectionUtils.isEmpty(purchaseReceiptsDetailsBeans)){
            List<Integer> receoptIds = purchaseReceiptsDetailsBeans.stream().map(o -> o.getId()).collect(Collectors.toList());
            purchaseReceiptsService.batchDeletePurchaseReceipts(receoptIds);
        }

        updateStockInventory(purchaseInfo, null);
    }

    public List<PurchaseReceiptsDetailsBean> queryPurchaseReceviptDetailList(String purchaseCode){
        return purchaseReceiptsService.queryPurchaseReceviptDetailList(purchaseCode);
    }

    public void checkPurchaseTicket (int type, List<Integer> ids){
        switch (type){
            case CommonConstant.DEFAULT_VALUE_ONE: // 财务审核
                purchaseReceiptsService.financeCheckPurchaseTicet(ids);
                break;
            case CommonConstant.DEFAULT_VALUE_TWO: // 仓库审核
//                warehouseCheckPurchaseTicket(ids);
                break;
        }
    }

    @Transactional
    public void warehouseCheckPurchaseTicket (PurchaseInfo purchaseInfo){
        List<Integer> ids = new ArrayList<>();
        ids.add(purchaseInfo.getId());
        // 采购单审核状态修改
        purchaseReceiptsService.warehouseCheckPurchaseTicet(ids);


        // 更新采购单实际采购数量
        List<PurchaseGoodsDetailBean> purchaseGoodsDetailBeans = purchaseInfo.getPurchaseGoodsDetailBeans();
        purchaseGoodsService.batchUpdatePurchaseGoodsDetails(purchaseGoodsDetailBeans);

        // 获取采购单采购商品信息
//        List<PurchaseGoodsDetailBean> purchaseGoodsDetailBeans = purchaseReceiptsService.queryPurchaseGoodsDetail(ids);

        if (!CollectionUtils.isEmpty(purchaseGoodsDetailBeans)){
            String userCode = userTokenFacade.queryUserCodeForToken(null);
            for (PurchaseGoodsDetailBean purchaseGoods : purchaseGoodsDetailBeans){
                // 根据存货编号查询多仓库商品
                List<StockBean> stockInfo = stockInfoService.queryStockInfoForPurchaseAudit(purchaseGoods.getStockCode());
                for (StockBean sb : stockInfo){
                    if (StringUtils.equals(purchaseGoods.getStockAddressCode(), sb.getStockAddressCode())) {
                        int standardSellPrice = sb.getStandardSellPrice() == null ? 0 : sb.getStandardSellPrice().intValue();
                        if (CommonConstant.DEFAULT_VALUE_ZERO == standardSellPrice){
                            // 标准卖价没定, 新商品为入库
                            sb.setIsShelves(CommonConstant.DEFAULT_VALUE_TWO);
                        }
                        sb.setMinInventory(purchaseGoods.getRealGoodsNum());
                        stockInfoService.updateStockGoodsInventory(sb);
                        sb.setUpdateUser(userCode);
                        sb.setSourceMode(CommonConstant.DEFAULT_VALUE_ZERO);
                        stockInfoService.updateStockInfoSourceModel(sb);
                    } else {
                        StockNumPrice stockNumPrice = new StockNumPrice();
                        BeanUtils.copyProperties(sb, stockNumPrice);
                        stockNumPrice.setStockAddressCode(purchaseGoods.getStockAddressCode());
                        stockNumPrice.setStockAddress(purchaseGoods.getStockAddress());
                        stockNumPrice.setMinInventory(purchaseGoods.getRealGoodsNum());
                        stockNumPrice.setIsDefault(CommonConstant.DEFAULT_VALUE_ZERO);
                        stockNumPrice.setCreateUser(userCode);
                        stockInfoService.insertStockNumPriceForPurchaseAudit(stockNumPrice);
                    }
                }
                /*if (stockInfo != null){ // 只需在原始基础上累加库存数
                    // 添加库存
                    int standardSellPrice = stockInfo.getStandardSellPrice() == null ? 0 : stockInfo.getStandardSellPrice().intValue();
                    if (CommonConstant.DEFAULT_VALUE_ZERO == standardSellPrice){
                        // 标准卖价没定, 新商品为入库
                        stockInfo.setIsShelves(CommonConstant.DEFAULT_VALUE_TWO);
                    }
                    stockInfo.setMinInventory(purchaseGoods.getRealGoodsNum());
                    stockInfoService.updateStockGoodsInventory(stockInfo);
                    stockInfo.setUpdateUser(userCode);
                    stockInfo.setSourceMode(CommonConstant.DEFAULT_VALUE_ZERO);
                    stockInfoService.updateStockInfoSourceModel(stockInfo);
                } else {
                    // 需要新增一条记录, 分不同仓库
                    stockInfo = new StockBean();
                    stockInfo.setStockCode(purchaseGoods.getStockCode());
                    stockInfo.setStockAddressCode(purchaseGoods.getStockAddressCode());
                    stockInfo.setStockAddress(purchaseGoods.getStockAddress());
                    stockInfo.setStockName(purchaseGoods.getStockName());
                    stockInfo.setMinInventory(purchaseGoods.getRealGoodsNum());
                    stockInfo.setStandardBuyPrice(purchaseGoods.getRealSellPrice());

                }*/
            }
        }
    }

    public List<PurchaseHistoryInfo> queryPurchaseHistoryRecord (String stockCode){
        List<PurchaseHistoryInfo> purchaseHistoryInfoList  = purchaseTicketService.queryPurchaseHistoryRecord(stockCode);
        return purchaseHistoryInfoList;
    }

    public String getCode() {
        String busType = "purchase_ticket";
        String prefix = "ZC";
        String code = busEncodeFacade.nextBusCode(busType, prefix);

        PurchaseInfoExample example = new PurchaseInfoExample();
        PurchaseInfoExample.Criteria criteria =example.createCriteria();
        criteria.andPurchaseCodeEqualTo(code);
        long count = purchaseTicketService.countByExample(example);
        while (count > 0) {
            getCode();
        }
        return code;
    }
}
