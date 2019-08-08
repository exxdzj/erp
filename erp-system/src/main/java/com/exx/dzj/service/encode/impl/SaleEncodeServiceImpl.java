package com.exx.dzj.service.encode.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.exx.dzj.entity.encode.SaleEncodeBean;
import com.exx.dzj.mapper.encode.SaleEncodeMapper;
import com.exx.dzj.service.encode.SaleEncodeService;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Author
 * @Date 2019/8/7 0007 17:02
 * @Description
 */
@Component
public class SaleEncodeServiceImpl extends ServiceImpl<SaleEncodeMapper, SaleEncodeBean> implements SaleEncodeService {


    @Override
    public SaleEncodeBean getSaleEncodeBean(Date saleDate) {
        return this.getOne(new QueryWrapper<SaleEncodeBean>().eq("sale_date", saleDate));
    }

    @Override
    public void updateSaleEncodeBean(Integer nextValue, Date saleDate) {
        SaleEncodeBean seBean = new SaleEncodeBean();
        seBean.setNextValue(nextValue + 1);
        this.update(seBean, new QueryWrapper<SaleEncodeBean>().eq("sale_date", saleDate));
    }

    @Override
    public void saveSaleEncodeBean(Date saleDate) {
        SaleEncodeBean info = new SaleEncodeBean();
        info.setSaleDate(saleDate);
        info.setNextValue(2);
        this.save(info);
    }
}
