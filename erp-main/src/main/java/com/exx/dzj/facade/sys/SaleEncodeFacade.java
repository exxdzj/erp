package com.exx.dzj.facade.sys;

import com.exx.dzj.entity.encode.SaleEncodeBean;
import com.exx.dzj.service.encode.SaleEncodeService;
import com.exx.dzj.util.DateTimeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Author
 * @Date 2019/8/7 0007 16:59
 * @Description
 */
@Slf4j
@Component
public class SaleEncodeFacade {

    @Autowired
    private SaleEncodeService saleEncodeService;

    /**
     * 通过销售日期查询销售单下一编码
     * 使用 synchronized 关键字，确保并发情况下生成的编码是唯一的
     * @param saleDate
     * @return
     */
    public Integer nextBusCode(Date saleDate) {
        try {
            saleDate = DateTimeUtil.dateFormat(saleDate, "yyyy-MM-dd");
            // 首先根据日期获取当前销售日期的下一个编码
            SaleEncodeBean bean = saleEncodeService.getSaleEncodeBean(saleDate);
            if(null != bean && bean.getNextValue() > 0) {
                // 修改，编码递增
                saleEncodeService.updateSaleEncodeBean(bean.getNextValue(), saleDate);
                return bean.getNextValue();
            }

            // 如果不存在，则保存
            saleEncodeService.saveSaleEncodeBean(saleDate);
        } catch(Exception ex) {
            log.error("获取销售单编码失败,方法：{}, 原因:{}", SaleEncodeFacade.class.getName()+".nextBusCode()", ex.getMessage());
        }
        return 1;
    }
}
