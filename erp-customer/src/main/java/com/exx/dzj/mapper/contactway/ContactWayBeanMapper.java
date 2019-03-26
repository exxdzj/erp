package com.exx.dzj.mapper.contactway;

import com.exx.dzj.entity.contactway.ContactWayBean;
import com.exx.dzj.entity.contactway.ContactWayBeanExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ContactWayBeanMapper {
    long countByExample(ContactWayBeanExample example);

    int deleteByExample(ContactWayBeanExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ContactWayBean record);

    int insertSelective(ContactWayBean record);

    List<ContactWayBean> selectByExample(ContactWayBeanExample example);

    ContactWayBean selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ContactWayBean record, @Param("example") ContactWayBeanExample example);

    int updateByExample(@Param("record") ContactWayBean record, @Param("example") ContactWayBeanExample example);

    int updateByPrimaryKeySelective(ContactWayBean record);

    int updateByPrimaryKey(ContactWayBean record);

    void batchContactWay(List<ContactWayBean> contactWayList);
}