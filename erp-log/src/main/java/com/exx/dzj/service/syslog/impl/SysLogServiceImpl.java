package com.exx.dzj.service.syslog.impl;

import com.exx.dzj.entity.syslog.LogBean;
import com.exx.dzj.mapper.syslog.LogBeanMapper;
import com.exx.dzj.service.syslog.SysLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author 天刀
 * @Date 2019/5/15 0015 14:28
 * @Description 记录系统的操作日志 service
 */
@Slf4j
@Component
public class SysLogServiceImpl implements SysLogService {

    @Autowired
    private LogBeanMapper sysLogMapper;

    /**
     * @功能: 保存系统级操作日志信息
     * @param bean
     */
    @Override
    public void saveSysLog(LogBean bean) {
        try {
            sysLogMapper.insertSelective(bean);
        } catch (Exception ex) {
            log.error("执行方法{}错误信息{}", SysLogServiceImpl.class.getName()+".saveSysLog", ex.getMessage());
        }
    }
}
