package com.exx.dzj.facade.androidfacade.homepage;

import com.exx.dzj.constant.CommonConstant;
import com.exx.dzj.entity.market.SaleInfo;
import com.exx.dzj.service.androidservice.AndroidService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.*;

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

    private List<String> checkUserCode (String userCode){
        List<String> list = new ArrayList<>();
        for (String code : CommonConstant.USERCODE){
            if (StringUtils.equals(code, userCode)){
                list = CommonConstant.USERCODE;
                return list;
            }
        }
        list.add(userCode);
        return list;
    }

    public Map<String, SaleInfo> queryPersonageSaleVolume(String userCode){
        Map<String, SaleInfo> data = new HashMap<>();
        List<String> list = checkUserCode(userCode);

        SaleInfo s = null;
        // 个人今日销售额
        List<SaleInfo> day = androidService.queryPersonageSaleVolume(list, "day");
        s = new SaleInfo();
        s.setReceivableAccoun(BigDecimal.ZERO);
        if (!CollectionUtils.isEmpty(day)){
            s.setReceivableAccoun(day.stream().map(SaleInfo::getReceivableAccoun).reduce(BigDecimal.ZERO, BigDecimal::add));
        }

        data.put("day", s);
        // 个人当月销售额
        List<SaleInfo> month = androidService.queryPersonageSaleVolume(list, "month");

        s = new SaleInfo();
        s.setReceivableAccoun(BigDecimal.ZERO);
        if (!CollectionUtils.isEmpty(month)){
            s.setReceivableAccoun(month.stream().map(SaleInfo::getReceivableAccoun).reduce(BigDecimal.ZERO, BigDecimal::add));
        }
        data.put("month", s);
        // 个人今年销售额
        List<SaleInfo> year = androidService.queryPersonageSaleVolume(list, "year");
        s = new SaleInfo();
        s.setReceivableAccoun(BigDecimal.ZERO);
        if (!CollectionUtils.isEmpty(year)){
            s.setReceivableAccoun(year.stream().map(SaleInfo::getReceivableAccoun).reduce(BigDecimal.ZERO, BigDecimal::add));
        }
        data.put("year", s);

        // 个人当月销售件数
        List<Integer> monthNum = androidService.queryPersonageNum(list, "month");
        s = new SaleInfo();
        s.setGoodsNum(0);
        if (!CollectionUtils.isEmpty(monthNum)){
            s.setGoodsNum(monthNum.stream().mapToInt(Integer::intValue).sum());
        }
        data.put("monthNum", s);

        // 个人当年销售件数
        List<Integer> yearNum = androidService.queryPersonageNum(list, "year");
        s = new SaleInfo();
        s.setGoodsNum(0);
        if (!CollectionUtils.isEmpty(yearNum)){
            s.setGoodsNum(yearNum.stream().mapToInt(Integer::intValue).sum());
        }
        data.put("yearNum", s);

        return data;
    }

    /**
     * 5002  app-yangshouhai
     * 006    yangshouhai
     * 010    jinyingluntan
     **/

    public Map<String, Object> queryCustomerCount(String userCode) {
        Map<String, Object> data = new HashMap<>();
        List<String> list = checkUserCode(userCode);

        // 今日新增客户数
        int newly = androidService.queryCustomerCount(list, "newly");
        data.put("newly", newly);
        // 客户总数
        int sum = androidService.queryCustomerCount(list, null);
        data.put("sum", sum);
        return data;
    }
}
