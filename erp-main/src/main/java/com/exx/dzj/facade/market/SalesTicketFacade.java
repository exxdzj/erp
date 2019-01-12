package com.exx.dzj.facade.market;

import com.exx.dzj.entity.market.SaleGoodsDetailBean;
import com.exx.dzj.entity.market.SaleInfo;
import com.exx.dzj.entity.market.SaleReceiptsDetails;
import com.exx.dzj.page.ERPage;
import com.exx.dzj.service.salesgoodsdetail.SalesGoodsDetailService;
import com.exx.dzj.service.salesreceiptsdetail.SaleReceiptsDetailService;
import com.exx.dzj.service.salesticket.SalesTicketService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
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

    /**
     * @description 新建销售单
     * @author yangyun
     * @date 2019/1/8 0008
     * @param saleInfo
     * @return void
     */
    @Transactional
    public void saveSalesTicket(SaleInfo saleInfo){
        List<SaleGoodsDetailBean> goodsDetailBeanList = saleInfo.getSaleGoodsDetailBeanList();
        List<SaleReceiptsDetails> receiptsDetailsList = saleInfo.getSaleReceiptsDetailsList();
        salesTicketService.saveSaleInfo(saleInfo);
        salesGoodsDetailService.batchInsertSalesGoodsDetail(goodsDetailBeanList);
        saleReceiptsDetailService.batchInsertSalesReceiptsDeail(receiptsDetailsList);
    }

    public ERPage<SaleInfo> querySalesTicketList(SaleInfo saleInfo, int pageNo, int pageSize){
        PageHelper.startPage(pageNo, pageSize);
        List<SaleInfo> saleInfoList = salesTicketService.querySalesTicketList(saleInfo);
        ERPage<SaleInfo> saleInfoPage = new ERPage<>(saleInfoList);
        return saleInfoPage;
    }

    public SaleInfo querySalesTicketById(Integer id){
        return salesTicketService.querySalesTicketById(id);
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
        List<SaleGoodsDetailBean> goodsDetailBeanList = saleInfo.getSaleGoodsDetailBeanList();
        List<SaleReceiptsDetails> receiptsDetailsList = saleInfo.getSaleReceiptsDetailsList();
        salesTicketService.updateSalesTicketById(saleInfo);
        salesGoodsDetailService.batchUpdateSalesGoodsDetail(goodsDetailBeanList);
        saleReceiptsDetailService.batchUpdateSalesReceiptsDeail(receiptsDetailsList);
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
        if(Optional.of(srdIds).isPresent()){
            saleReceiptsDetailService.batchDeleteSalesReceiptsDeail(srdIds);
        }

        List<SaleGoodsDetailBean> saleGoodsDetailBeans = Optional.of(saleInfo.getSaleGoodsDetailBeanList()).get();
        List<Integer> sgbIds = saleGoodsDetailBeans.stream().map(s -> s.getId()).collect(Collectors.toList());
        if(Optional.of(sgbIds).isPresent()){
            salesGoodsDetailService.batchDeleteSalesGoodsDetail(sgbIds);
        }
    }
}
