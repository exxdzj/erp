package com.exx.dzj.service.purchaseticket;

import com.exx.dzj.entity.purchase.PurchaseHistoryInfo;
import com.exx.dzj.entity.purchase.PurchaseInfo;
import com.exx.dzj.entity.purchase.PurchaseInfoExample;
import com.exx.dzj.entity.purchase.PurchaseQuery;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author yangyun
 * @create 2019-01-12-15:54
 */
public interface PurchaseTicketService {

    long countByExample(PurchaseInfoExample example);

    void savePurchaseTicket(PurchaseInfo purchaseInfo);

    List<PurchaseInfo> queryPurchaseTickets(PurchaseQuery query);

    PurchaseInfo queryPurchaseTicketDetail(Integer id);

    void updatePurchaseTicket(PurchaseInfo purchaseInfo);

    void deletePurchaseTicket(Integer id);

    List<PurchaseHistoryInfo> queryPurchaseHistoryRecord (String stockCode);

    BigDecimal sumPurchaseSalesOnYear();
}
