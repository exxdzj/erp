package com.exx.dzj.service.salesticket.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.exx.dzj.entity.market.*;
import com.exx.dzj.mapper.market.SaleInfoMapper;
import com.exx.dzj.mapper.market.SaleReceiptsDetailsMapper;
import com.exx.dzj.service.salesticket.SalesTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author yangyun
 * @create 2019-01-08-10:56
 */
@Component
public class SalesTicketServiceImpl extends ServiceImpl<SaleInfoMapper, SaleInfo> implements SalesTicketService {

    @Autowired
    private SaleInfoMapper saleInfoMapper;

    @Autowired
    private SaleReceiptsDetailsMapper saleReceiptsDetailsMapper;

    @Override
    public long countByExample(SaleInfoExample example) {
        return saleInfoMapper.countByExample(example);
    }

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
     * @description 销售单列表查询(未根据权限查询销售单)
     * @author yangyun
     * @date 2019/1/9 0009
     * @param query
     * @return java.util.List<com.exx.dzj.entity.market.SaleInfo>
     */
    @Override
    public List<SaleInfo> querySalesTicketList(SaleInfoQuery query) {
        return saleInfoMapper.querySalesTicketList(query);
    }

    /**
     * 根据数据权限查询销售单
     * @param query
     * @param queryWrapper
     * @return
     */
    @Override
    public List<SaleInfo> getSalesTicketList(SaleInfoQuery query, QueryWrapper queryWrapper) {
        return saleInfoMapper.getSalesTicketList(query, queryWrapper);
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
     * @description 打印销售单
     * @author yangyun
     * @date 2019/1/10 0010
     * @param saleCode
     * @return com.exx.dzj.entity.market.SaleInfo
     */
    @Override
    public SaleInfo printSalesTicket(String saleCode) {
        return saleInfoMapper.printSalesTicket(saleCode);
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
    public List<SaleInfo> querySumSalesOnDay() {
        return saleInfoMapper.querySumSalesOnDay();
    }

    @Override
    public List<SaleInfo> querySumSalesOnMonth() {
        return saleInfoMapper.querySumSalesOnMonth();
    }

    @Override
    public List<SaleInfo> queryAdditionalSumSalesOnDay() {
        return saleInfoMapper.queryAdditionalSumSalesOnDay();
    }

    @Override
    public List<SaleInfo> queryAdditionalSumSalesOnMonth() {
        return saleInfoMapper.queryAdditionalSumSalesOnMonth();
    }

    @Override
    public List<SaleInfo> querySumSalesOnYear() {
        return saleInfoMapper.querySumSalesOnYear();
    }

    @Override
    public List<SaleInfo> querySalesTop(String data) {
        return saleInfoMapper.querySalesTop(data);
    }
    @Override
    public List<SaleInfo> salesUncollectedTop() {
        return saleInfoMapper.salesUncollectedTop();
    }

    @Override
    public List<SaleInfo> querySalesTicketTop() {
        return saleInfoMapper.querySalesTicketTop();
    }

    @Override
    public List<SaleInfo> queryCustomerSalesToday(SaleInfo saleInfo) {
        return saleInfoMapper.queryCustomerSalesToday(saleInfo);
    }

    @Override
    public List<SaleGoodsTop> querySaleGoodsTop(String type) {
        return saleInfoMapper.querySaleGoodsTop(type);
    }

    @Override
    public List<SaleInfo> querySubordinateCompanySelect() {
        return saleInfoMapper.querySubordinateCompanySelect();
    }

    @Override
    public int querySaleBySaleCode(String saleCode) {
        return saleInfoMapper.querySaleBySaleCode(saleCode);
    }

    @Override
    public void updateSalesmanSubordinateCompany(SaleInfo saleInfo) {
        saleInfoMapper.updateSalesmanSubordinateCompany(saleInfo);
    }

    @Override
    public List<SaleListInfo> querySalesListForIds(SaleInfoQuery query) {
        return saleInfoMapper.querySalesListForIds(query);
    }

    @Override
    public List<SaleListInfo> exportSaleList(SaleInfoQuery query) {
        return saleInfoMapper.exportSaleList(query);
    }
}
