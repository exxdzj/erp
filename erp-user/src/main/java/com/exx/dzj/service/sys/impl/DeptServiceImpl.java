package com.exx.dzj.service.sys.impl;

import com.exx.dzj.constant.CommonConstant;
import com.exx.dzj.entity.dept.DeptInfoBean;
import com.exx.dzj.entity.dept.DeptInfoBeanExample;
import com.exx.dzj.mapper.dept.DeptInfoBeanMapper;
import com.exx.dzj.page.ERPage;
import com.exx.dzj.service.sys.DeptService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author
 * @Date 2019/4/2 0002 15:06
 * @Description  部门
 */
@Component
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptInfoBeanMapper deptMapper;

    /**
     * 查询 部门列表数据
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public ERPage<DeptInfoBean> queryList(int pageNum, int pageSize) {
        DeptInfoBeanExample example = new DeptInfoBeanExample();
        DeptInfoBeanExample.Criteria criteria = example.createCriteria();
        criteria.andIsEnableEqualTo(CommonConstant.DEFAULT_VALUE_ONE);
        PageHelper.startPage(pageNum, pageSize);
        List<DeptInfoBean> depts = deptMapper.selectByExample(example);
        ERPage<DeptInfoBean> pages = new ERPage<>(depts);
        return pages;
    }

    /**
     * 查询 部门下拉框数据
     * @return
     */
    @Override
    public List<DeptInfoBean> queryDeptList() {
        return deptMapper.queryDeptList();
    }
}
