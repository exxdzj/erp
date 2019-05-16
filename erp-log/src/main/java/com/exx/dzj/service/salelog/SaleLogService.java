package com.exx.dzj.service.salelog;

import com.exx.dzj.entity.salelog.SaleLog;

/**
 * @Author
 * @Date 2019/5/15 0015 14:35
 * @Description 销售单操作日志
 */
public interface SaleLogService {

    /**
     * @功能: 保存销售单的操作日志
     * @param bean
     */
    void saveSaleLog(SaleLog bean);
}
