package com.exx.dzj.facade.log;

import com.exx.dzj.entity.syslog.LogBean;
import com.exx.dzj.service.syslog.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author
 * @Date 2019/5/15 0015 16:21
 * @Description 系统日志
 */
@Component
public class SysLogFacade {

    @Autowired
    private SysLogService sysLogService;

    /**
     * @功能: 保存系统日志信息
     * @param logBean
     */
    public void saveSysLog(LogBean logBean) {
        sysLogService.saveSysLog(logBean);
    }
}
