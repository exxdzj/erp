package com.exx.dzj.mapper.syslog;

import com.exx.dzj.entity.syslog.LogBean;
import com.exx.dzj.entity.syslog.LogBeanExample;

import java.util.List;

public interface LogBeanMapper {

    int insertSelective(LogBean record);

    List<LogBean> selectByExampleWithBLOBs(LogBeanExample example);

    List<LogBean> selectByExample(LogBeanExample example);

    LogBean selectByPrimaryKey(Integer id);
}