package com.exx.dzj.service.purchaseticket;

import com.exx.dzj.entity.purchase.PurchaseGoodsDetailBean;
import com.exx.dzj.entity.purchase.PurchaseHistoryInfo;
import com.exx.dzj.entity.purchase.PurchaseInfo;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author yangyun
 * @create 2019-01-12-15:54
 */
public interface PurchaseTicketService {

    void savePurchaseTicket(PurchaseInfo purchaseInfo);

    List<PurchaseInfo> queryPurchaseTickets(PurchaseInfo purchaseInfo);

    PurchaseInfo queryPurchaseTicketDetail(Integer id);

    void updatePurchaseTicket(PurchaseInfo purchaseInfo);

    void deletePurchaseTicket(Integer id);

    List<PurchaseHistoryInfo> queryPurchaseHistoryRecord (String stockCode);

    BigDecimal sumPurchaseSalesOnYear();
}
