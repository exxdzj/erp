package com.exx.dzj.service.contactway.impl;

import com.exx.dzj.entity.contactway.ContactWayBean;
import com.exx.dzj.excepte.ErpException;
import com.exx.dzj.mapper.contactway.ContactWayBeanMapper;
import com.exx.dzj.service.contactway.ContactWayService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author
 * @Date 2019/1/7 0007 13:53
 * @Description 会计属性 service
 */
@Service
public class ContactWayServiceImpl implements ContactWayService {

    private final static Logger LOGGER = LoggerFactory.getLogger(ContactWayServiceImpl.class);

    @Autowired
    private ContactWayBeanMapper contactWayMapper;

    /**
     * 保存 联系信息
     * @param bean
     */
    @Override
    public void saveContactWay(ContactWayBean bean) {
        try{
            contactWayMapper.insertSelective(bean);
        }catch(Exception ex){
            LOGGER.error("异常方法:{}异常信息:{}", ContactWayServiceImpl.class.getName()+".saveContactWay", ex.getMessage());
            throw new ErpException(400, "保存客户或供应商联系信息失败!");
        }
    }

    /**
     * 修改 联系信息
     * @param bean
     */
    @Override
    public void modifyContactWay(ContactWayBean bean) {
        try{
            contactWayMapper.updateByPrimaryKeySelective(bean);
        }catch(Exception ex){
            LOGGER.error("异常方法:{}异常信息:{}", ContactWayServiceImpl.class.getName()+".modifyContactWay", ex.getMessage());
            throw new ErpException(400, "修改客户或供应商联系信息失败!");
        }
    }
}
