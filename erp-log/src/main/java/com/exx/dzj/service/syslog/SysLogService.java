package com.exx.dzj.service.syslog;

import com.exx.dzj.entity.syslog.LogBean;

/**
 * @Author
 * @Date 2019/5/15 0015 14:19
 * @Description 系统日志 service
 */
public interface SysLogService {

    /**
     * @功能: 记录系统的操作日志
     * @param log
     * @return
     */
    void saveSysLog(LogBean log);
}
