package com.exx.dzj.service.trail.impl;

import com.exx.dzj.annotation.SysLog;
import com.exx.dzj.constant.LogLevel;
import com.exx.dzj.constant.LogType;
import com.exx.dzj.entity.trail.LogisticsTrail;
import com.exx.dzj.entity.trail.LogisticsTrailParam;
import com.exx.dzj.excepte.ErpException;
import com.exx.dzj.mapper.trail.LogisticsTrailMapper;
import com.exx.dzj.service.trail.LogisticsTrailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author
 * @Date 2019/5/18 0018 17:33
 * @Description 物流明细 service
 */
@Slf4j
@Component
public class LogisticsTrailServiceImpl implements LogisticsTrailService {

    @Autowired
    private LogisticsTrailMapper trailMapper;

    /**
     * @功能: 保存物流明细数据
     * @param trails
     */
    @Override
    @Transactional(rollbackFor = ErpException.class)
    @SysLog(operate = "保存物流明细", logType = LogType.LOG_TYPE_OPERATE, logLevel = LogLevel.LOG_LEVEL_INFO)
    public void saveLogisticsTrail(List<LogisticsTrail> trails) {
        try {
            trailMapper.saveLogisticsTrail(trails);
        } catch(Exception ex) {
            log.error("保存物流信息失败!执行方法:{}异常信息:{}", LogisticsTrailServiceImpl.class.getName()+".saveLogisticsTrail", ex.getMessage());
            throw new ErpException(400, "保存物流信息失败!");
        }
    }

    /**
     * @功能: 根据条件查询物流明细数据
     * @param param
     * @return
     */
    @Override
    public List<LogisticsTrail> queryLogisticsTrails(LogisticsTrailParam param) {
        try {
            return trailMapper.queryLogisticsTrails(param);
        } catch(Exception ex) {
            log.error("查询物流信息失败!执行方法:{}异常信息:{}", LogisticsTrailServiceImpl.class.getName()+".saveLogisticsTrail", ex.getMessage());
            return null;
        }
    }
}
