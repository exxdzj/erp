package com.exx.dzj.facade.log;

import com.exx.dzj.entity.salelog.SaleLogBean;
import com.exx.dzj.service.salelog.SaleLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author
 * @Date 2019/5/16 0016 14:24
 * @Description 销售单日志
 */
@Component
public class SaleLogFacade {

    @Autowired
    private SaleLogService saleLogService;

    /**
     * @功能: 保存销售单日志
     * @param bean
     */
    public void saveSaleLog(SaleLogBean bean) {
        saleLogService.saveSaleLog(bean);
    }
}
