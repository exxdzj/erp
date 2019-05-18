package com.exx.dzj.service.accountatt.impl;

import com.exx.dzj.annotation.SysLog;
import com.exx.dzj.constant.LogLevel;
import com.exx.dzj.constant.LogType;
import com.exx.dzj.entity.accountatt.AccountAttributeBean;
import com.exx.dzj.excepte.ErpException;
import com.exx.dzj.mapper.accountatt.AccountAttributeBeanMapper;
import com.exx.dzj.service.accountatt.AccountAttributeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author
 * @Date 2019/1/7 0007 13:54
 * @Description 会计属性 service
 */
@Service("accountAttService")
public class AccountAttributeServiceImpl implements AccountAttributeService {

    private final static Logger LOGGER = LoggerFactory.getLogger(AccountAttributeServiceImpl.class);

    @Autowired
    private AccountAttributeBeanMapper accountAttributeMapper;

    /**
     * 保存 会计属性信息
     * @param bean
     */
    @Override
    @SysLog(operate = "保存会计属性信息", logType = LogType.LOG_TYPE_OPERATE, logLevel = LogLevel.LOG_LEVEL_INFO)
    public void saveAccountAttribute(AccountAttributeBean bean) {
        try{
            accountAttributeMapper.insertSelective(bean);
        }catch(Exception ex){
            LOGGER.error("异常方法:{}异常信息:{}", AccountAttributeServiceImpl.class.getName()+".saveAccountAttribute", ex.getMessage());
            throw ex;
        }
    }

    /**
     * 修改 会计数据信息
     * @param bean
     */
    @Override
    @SysLog(operate = "修改会计属性信息", logType = LogType.LOG_TYPE_OPERATE, logLevel = LogLevel.LOG_LEVEL_INFO)
    public int modifyAccountAttribute(AccountAttributeBean bean) {
        try{
            return accountAttributeMapper.updateByPrimaryKeySelective(bean);
        }catch(Exception ex){
            LOGGER.error("异常方法:{}异常信息:{}", AccountAttributeServiceImpl.class.getName()+".modifyAccountAttribute", ex.getMessage());
            throw ex;
        }
    }

    @Override
    public void batchAccountAttribute(List<AccountAttributeBean> attributeList) {
        accountAttributeMapper.batchAccountAttribute(attributeList);
    }
}
