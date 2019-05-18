package com.exx.dzj.service.contactway.impl;

import com.exx.dzj.annotation.SysLog;
import com.exx.dzj.constant.LogLevel;
import com.exx.dzj.constant.LogType;
import com.exx.dzj.entity.contactway.ContactWayBean;
import com.exx.dzj.excepte.ErpException;
import com.exx.dzj.mapper.contactway.ContactWayBeanMapper;
import com.exx.dzj.service.contactway.ContactWayService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author
 * @Date 2019/1/7 0007 13:53
 * @Description 会计属性 service
 */
@Service("contactwayService")
public class ContactWayServiceImpl implements ContactWayService {

    private final static Logger LOGGER = LoggerFactory.getLogger(ContactWayServiceImpl.class);

    @Autowired
    private ContactWayBeanMapper contactWayMapper;

    /**
     * 保存 联系信息
     * @param bean
     */
    @Override
    @SysLog(operate = "保存联系信息", logType = LogType.LOG_TYPE_OPERATE, logLevel = LogLevel.LOG_LEVEL_INFO)
    public void saveContactWay(ContactWayBean bean) {
        try{
            contactWayMapper.insertSelective(bean);
        }catch(Exception ex){
            LOGGER.error("异常方法:{}异常信息:{}", ContactWayServiceImpl.class.getName()+".saveContactWay", ex.getMessage());
            throw ex;
        }
    }

    /**
     * 修改 联系信息
     * @param bean
     */
    @Override
    @SysLog(operate = "修改联系信息", logType = LogType.LOG_TYPE_OPERATE, logLevel = LogLevel.LOG_LEVEL_INFO)
    public int modifyContactWay(ContactWayBean bean) {
        try{
            return contactWayMapper.updateByPrimaryKeySelective(bean);
        }catch(Exception ex){
            LOGGER.error("异常方法:{}异常信息:{}", ContactWayServiceImpl.class.getName()+".modifyContactWay", ex.getMessage());
            throw ex;
        }
    }

    @Override
    public void batchContactWay(List<ContactWayBean> contactWayList) {
        contactWayMapper.batchContactWay(contactWayList);
    }
}
