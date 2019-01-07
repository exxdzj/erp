package com.exx.dzj.mapper.log;

import com.exx.dzj.entity.log.LogBean;
import com.exx.dzj.entity.log.LogBeanExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LogBeanMapper {
    long countByExample(LogBeanExample example);

    int deleteByExample(LogBeanExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(LogBean record);

    int insertSelective(LogBean record);

    List<LogBean> selectByExampleWithBLOBs(LogBeanExample example);

    List<LogBean> selectByExample(LogBeanExample example);

    LogBean selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") LogBean record, @Param("example") LogBeanExample example);

    int updateByExampleWithBLOBs(@Param("record") LogBean record, @Param("example") LogBeanExample example);

    int updateByExample(@Param("record") LogBean record, @Param("example") LogBeanExample example);

    int updateByPrimaryKeySelective(LogBean record);

    int updateByPrimaryKeyWithBLOBs(LogBean record);

    int updateByPrimaryKey(LogBean record);
}