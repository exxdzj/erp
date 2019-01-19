package com.exx.dzj.service.stock.impl;

import com.exx.dzj.constant.CommonConstant;
import com.exx.dzj.entity.stock.*;
import com.exx.dzj.excepte.ErpException;
import com.exx.dzj.mapper.stock.StockInfoMapper;
import com.exx.dzj.mapper.stock.StockNumPriceMapper;
import com.exx.dzj.page.ERPage;
import com.exx.dzj.result.Result;
import com.exx.dzj.service.stock.StockService;
import com.exx.dzj.unique.DefaultIdGenerator;
import com.exx.dzj.unique.IdGenerator;
import com.exx.dzj.util.EntityJudgeUtil;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author
 * @Date 2019/1/14 0014 11:25
 * @Description 存货
 */
@Service("stockInfoService")
public class StockServiceImpl implements StockService {

    private final static Logger LOGGER = LoggerFactory.getLogger(StockServiceImpl.class);

    @Autowired
    private StockInfoMapper stockMapper;

    @Autowired
    private StockNumPriceMapper priceMapper;

    /**
     * 获取存货列表数据
     * @param pageNum
     * @param pageSize
     * @param query
     * @return
     */
    @Override
    public Result queryStockList(int pageNum, int pageSize, StockQuery query) {
        Result result = Result.responseSuccess();
        PageHelper.startPage(pageNum, pageSize);
        List<StockModel> list = stockMapper.queryStockList(query);
        ERPage<StockModel> page = new ERPage<>(list);
        result.setData(page);
        return result;
    }

    /**
     * 获取 存货信息
     * @param stockCode
     * @return
     */
    @Override
    public StockBean queryStockInfo(String stockCode) {
        return stockMapper.queryStockInfo(stockCode);
    }

    /**
     * 更新 存货信息
     * @return
     */
    @Override
    @Transactional(rollbackFor = ErpException.class)
    public Result saveStockInfo(StockBean bean) {
        Result result = Result.responseSuccess();
        try{
            if(StringUtils.isNotBlank(bean.getPictures()) && bean.getPictures().endsWith(",")){
                bean.setPictures(bean.getPictures().substring(0, bean.getPictures().length() - 1));
            }
            StockInfo stockInfo = new StockInfo();
            StockNumPrice stockNumPrice = new StockNumPrice();
            BeanUtils.copyProperties(bean, stockInfo);
            BeanUtils.copyProperties(bean, stockNumPrice);

            if(StringUtils.isNotBlank(stockInfo.getStockCode())){
                stockMapper.updateByPrimaryKeySelective(stockInfo);

                stockNumPrice.setStockCode(null);
                if(!EntityJudgeUtil.checkObjAllFieldsIsNull(stockNumPrice)){
                    stockNumPrice.setStockCode(stockInfo.getStockCode());
                    int count = priceMapper.updateByPrimaryKeySelective(stockNumPrice);
                    if(count == 0){
                        priceMapper.insertSelective(stockNumPrice);
                    }
                }
            }else{
                IdGenerator idGenerator = new DefaultIdGenerator();
                String stockCode = idGenerator.next();

                stockInfo.setStockCode(stockCode);
                stockMapper.insertSelective(stockInfo);
                if(!EntityJudgeUtil.checkObjAllFieldsIsNull(stockNumPrice)){
                    stockNumPrice.setStockCode(stockCode);
                    priceMapper.insertSelective(stockNumPrice);
                }
            }
        } catch(Exception ex) {
            LOGGER.error("异常方法:{}异常信息:{}", StockServiceImpl.class.getName()+".saveStockInfo", ex.getMessage());
            throw new ErpException(400, "保存数据失败!");
        }
        return result;
    }

    /**
     * 删除 存货
     * @param stockCodes
     * @return
     */
    @Override
    public void delStockInfo(String stockCodes, int isEnable) {
        try{
            String comma = ",";
            if(stockCodes.contains(comma)){
                List<String> list = new ArrayList<String>(stockCodes.split(",").length);
                stockMapper.shelvesStock(null, isEnable, list);
            }
        }catch(Exception ex){
            LOGGER.error("异常方法:{}异常信息:{}", StockServiceImpl.class.getName()+".delStockInfo", ex.getMessage());
            throw new ErpException(400, "删除存货数据失败!");
        }


    }

    /**
     * 上架-下架
     * @param isShelves
     * @param stockCodes
     * @return
     */
    @Override
    @Transactional(rollbackFor = ErpException.class)
    public void shelvesStock(String isShelves, String stockCodes){
        try{
            String comma = ",";
            List<String> list = new ArrayList<String>();
            if(stockCodes.contains(comma)){
                list = new ArrayList<String>(stockCodes.split(",").length);
            } else {
                list.add(stockCodes);
            }
            stockMapper.shelvesStock(isShelves, null, list);
        }catch(Exception ex){
            LOGGER.error("异常方法:{}异常信息:{}", StockServiceImpl.class.getName()+".shelvesStock", ex.getMessage());
            throw new ErpException(400, "删除存货数据失败!");
        }
    }
}
