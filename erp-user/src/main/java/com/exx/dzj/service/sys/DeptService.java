package com.exx.dzj.service.sys;

import com.exx.dzj.entity.dept.DeptInfoBean;
import com.exx.dzj.page.ERPage;

import java.util.List;

/**
 * @Author
 * @Date 2019/4/2 0002 15:06
 * @Description
 */
public interface DeptService {

    /**
     * 查询 部门列表数据
     * @param pageNum
     * @param pageSize
     * @return
     */
    ERPage<DeptInfoBean> queryList(int pageNum, int pageSize);

    /**
     * 查询 部门下拉框数据
     * @return
     */
    List<DeptInfoBean> queryDeptList();
}
