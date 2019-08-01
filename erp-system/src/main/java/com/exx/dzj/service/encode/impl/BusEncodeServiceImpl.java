package com.exx.dzj.service.encode.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.exx.dzj.annotation.SysLog;
import com.exx.dzj.constant.LogType;
import com.exx.dzj.entity.encode.BusEncodeRuleCacheData;
import com.exx.dzj.entity.encode.BusEncodeRuleInfo;
import com.exx.dzj.entity.encode.BusEncodingRule;
import com.exx.dzj.mapper.encode.BusEncodeMapper;
import com.exx.dzj.service.encode.BusEncodeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author
 * @Date 2019/5/31 0031 11:52
 * @Description 业务编码 service
 */
@Component
@Slf4j
public class BusEncodeServiceImpl extends ServiceImpl<BusEncodeMapper, BusEncodingRule> implements BusEncodeService {

    @Autowired
    private BusEncodeMapper busEncodeMapper;

    /**
     * 查询 业务编码规则
     * @return
     */
    @Override
    public List<BusEncodeRuleCacheData> queryList() {
        return busEncodeMapper.queryList();
    }

    /**
     * 修改  数据
     * @param info
     */
    @Override
    public void updateEncodeData(BusEncodeRuleInfo info) {
        try {
            busEncodeMapper.updateEncodeData(info);
        } catch(Exception ex) {
            log.error("修改下一个编码失败,方法{},原因{}", BusEncodeServiceImpl.class.getName()+".updateEncodeData", ex.getMessage());
        }
    }

    /**
     * 定时修改起始编码
     * @param info
     */
    @Override
    @SysLog(operate = "定时任务修改编码", logType = LogType.LOG_TYPE_CRON_JOB)
    public void scheduleUpdateEncode(BusEncodeRuleInfo info) {
        try {
            busEncodeMapper.scheduleUpdateEncode(info);
        } catch(Exception ex) {
            log.error("定时修改编码失败,方法{},原因{}", BusEncodeServiceImpl.class.getName()+".scheduleUpdateEncode", ex.getMessage());
        }
    }
}
