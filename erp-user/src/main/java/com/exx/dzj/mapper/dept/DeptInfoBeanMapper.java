package com.exx.dzj.mapper.dept;

import com.exx.dzj.entity.dept.DeptInfoBean;
import com.exx.dzj.entity.dept.DeptInfoBeanExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DeptInfoBeanMapper {
    long countByExample(DeptInfoBeanExample example);

    int deleteByExample(DeptInfoBeanExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DeptInfoBean record);

    int insertSelective(DeptInfoBean record);

    List<DeptInfoBean> selectByExample(DeptInfoBeanExample example);

    DeptInfoBean selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DeptInfoBean record, @Param("example") DeptInfoBeanExample example);

    int updateByExample(@Param("record") DeptInfoBean record, @Param("example") DeptInfoBeanExample example);

    int updateByPrimaryKeySelective(DeptInfoBean record);

    int updateByPrimaryKey(DeptInfoBean record);

    List<DeptInfoBean> queryDeptList();
}