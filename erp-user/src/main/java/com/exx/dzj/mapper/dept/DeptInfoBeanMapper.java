package com.exx.dzj.mapper.dept;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.exx.dzj.entity.dept.DeptBean;
import com.exx.dzj.entity.dept.DeptInfoBean;
import com.exx.dzj.entity.dept.DeptInfoBeanExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DeptInfoBeanMapper extends BaseMapper<DeptInfoBean> {
    long countByExample(DeptInfoBeanExample example);

    int insertSelective(DeptInfoBean record);

    List<DeptInfoBean> selectByExample(DeptInfoBeanExample example);

    DeptInfoBean selectByPrimaryKey(String deptCode);

    int updateByExampleSelective(@Param("record") DeptInfoBean record, @Param("example") DeptInfoBeanExample example);

    int updateByPrimaryKeySelective(DeptInfoBean record);

    List<DeptInfoBean> queryDeptList();

    int queryMaxSeq();

    int delDept(DeptBean dept);

    DeptInfoBean queryDept(@Param("userCode") String userCode);
}