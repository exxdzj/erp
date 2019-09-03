package com.exx.dzj.facade.androidfacade.homepage;

import com.exx.dzj.entity.market.SaleInfo;
import com.exx.dzj.service.androidservice.AndroidService;
import com.exx.dzj.service.customer.CustomerService;
import com.exx.dzj.service.salesticket.SalesTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName AndroidHomePageFacade
 * @Description:
 * @Author yangyun
 * @Date 2019/8/26 0026 18:05
 * @Version 1.0
 **/
@Component
public class AndroidFacade {

    @Autowired
    private AndroidService androidService;

    @Autowired
    private CustomerService customerSupplierService;

    @Autowired
    private SalesTicketService salesTicketService;

    public Map<String, SaleInfo> queryPersonageSaleVolume(String userCode){
        Map<String, SaleInfo> data = new HashMap<>();

        // 个人今日销售额
        SaleInfo day = androidService.queryPersonageSaleVolume(userCode, "day");
        data.put("day", day);
        // 个人当月销售额
        SaleInfo month = androidService.queryPersonageSaleVolume(userCode, "month");
        data.put("month", month);
        // 个人今年销售额
        SaleInfo year = androidService.queryPersonageSaleVolume(userCode, "year");
        data.put("year", year);

        return data;
    }

    public Map<String, Object> queryCustomerCount(String userCode) {
        Map<String, Object> data = new HashMap<>();
        // 今日新增客户数
        int newly = androidService.queryCustomerCount(userCode, "newly");
        data.put("newly", newly);
        // 客户总数
        int sum = androidService.queryCustomerCount(userCode, null);
        data.put("sum", sum);
        return data;
    }
}
