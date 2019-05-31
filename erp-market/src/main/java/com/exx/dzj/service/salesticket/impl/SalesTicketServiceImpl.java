package com.exx.dzj.service.salesticket.impl;

import com.exx.dzj.entity.market.SaleInfo;
import com.exx.dzj.entity.market.SaleReceiptsDetails;
import com.exx.dzj.mapper.market.SaleInfoMapper;
import com.exx.dzj.mapper.market.SaleReceiptsDetailsMapper;
import com.exx.dzj.service.salesticket.SalesTicketService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @author yangyun
 * @create 2019-01-08-10:56
 */
@Component
public class SalesTicketServiceImpl implements SalesTicketService {

    @Autowired
    private SaleInfoMapper saleInfoMapper;

    @Autowired
    private SaleReceiptsDetailsMapper saleReceiptsDetailsMapper;

    /**
     * @description 新增销售收款记录
     * @author yangyun
     * @date 2019/1/8 0008
     * @param saleReceiptsDetails
     * @return void
     */
    @Override
    public void saveSaleReceiptsDetails(SaleReceiptsDetails saleReceiptsDetails) {
        saleReceiptsDetailsMapper.insertSelective(saleReceiptsDetails);
    }

    /**
     * @description 新增销售表
     * @author yangyun
     * @date 2019/1/8 0008
     * @param saleInfo
     * @return void
     */
    @Override
    public void saveSaleInfo(SaleInfo saleInfo) {
        saleInfoMapper.insertSelective(saleInfo);
    }

    /**
     * @description 销售单列表查询
     * @author yangyun
     * @date 2019/1/9 0009
     * @param saleInfo
     * @return java.util.List<com.exx.dzj.entity.market.SaleInfo>
     */
    @Override
    public List<SaleInfo> querySalesTicketList(SaleInfo saleInfo) {
        return saleInfoMapper.querySalesTicketList(saleInfo);
    }

    /**
     * @description 销售单详情
     * @author yangyun
     * @date 2019/1/10 0010
     * @param id
     * @return com.exx.dzj.entity.market.SaleInfo
     */
    @Override
    public SaleInfo querySalesTicketById(Integer id) {
        return saleInfoMapper.selectByPrimaryKey(id);
    }

    /**
     * @description 销售单更新
     * @author yangyun
     * @date 2019/1/11 0011
     * @param saleInfo
     * @return void
     */
    @Override
    public void updateSalesTicketById(SaleInfo saleInfo) {
        saleInfoMapper.updateByPrimaryKeySelective(saleInfo);
    }

    @Override
    public void deleteSaleinfo(Integer id) {
        saleInfoMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void logisticsInfoDel(Integer id) {
        saleInfoMapper.logisticsInfoDel(id);
    }

    @Override
    public BigDecimal querySumSalesOnDay() {
        return saleInfoMapper.querySumSalesOnDay();
    }

    @Override
    public BigDecimal querySumSalesOnMonth() {
        return saleInfoMapper.querySumSalesOnMonth();
    }

    @Override
    public BigDecimal queryAdditionalSumSalesOnDay() {
        return saleInfoMapper.queryAdditionalSumSalesOnDay();
    }

    @Override
    public BigDecimal queryAdditionalSumSalesOnMonth() {
        return saleInfoMapper.queryAdditionalSumSalesOnMonth();
    }

    @Override
    public BigDecimal querySumSalesOnYear() {
        return saleInfoMapper.querySumSalesOnYear();
    }

    @Override
    public List<SaleInfo> querySalesTop() {
        return saleInfoMapper.querySalesTop();
    }
    @Override
    public List<SaleInfo> salesUncollectedTop() {
        return saleInfoMapper.salesUncollectedTop();
    }

    @Override
    public List<SaleInfo> querySalesTicketTop() {
        return saleInfoMapper.querySalesTicketTop();
    }
}
