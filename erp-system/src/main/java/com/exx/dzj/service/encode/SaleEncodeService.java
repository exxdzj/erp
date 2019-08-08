package com.exx.dzj.service.encode;

import com.baomidou.mybatisplus.extension.service.IService;
import com.exx.dzj.entity.encode.SaleEncodeBean;

import java.util.Date;

/**
 * @Author
 * @Date 2019/8/7 0007 16:57
 * @Description 销售单编码 service
 */
public interface SaleEncodeService extends IService<SaleEncodeBean> {

    SaleEncodeBean getSaleEncodeBean(Date saleDate);

    void updateSaleEncodeBean(Integer nextValue, Date saleDate);

    void saveSaleEncodeBean(Date saleDate);
}
