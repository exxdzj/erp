package com.exx.dzj.service.sys;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.extension.service.IService;
import com.exx.dzj.entity.dept.DeptBean;
import com.exx.dzj.entity.dept.DeptInfoBean;
import com.exx.dzj.page.ERPage;
import com.exx.dzj.result.Result;

import java.util.List;

/**
 * @Author
 * @Date 2019/4/2 0002 15:06
 * @Description
 */
public interface DeptService extends IService<DeptInfoBean> {

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

    /**
     * 查询 部门树形结构数据
     * @return
     */
    JSONArray queryDeptTree(String deptName);

    /**
     * 获取 部门详细数据
     * @param deptCode
     * @return
     */
    DeptInfoBean queryDeptInfo(String deptCode);

    /**
     * 保存 部门信息
     * @param dept
     * @return
     */
    Result saveDeptInfo(DeptInfoBean dept);

    /**
     * 删除 部门数据
     * @param dept
     * @return
     */
    Result delDept(DeptBean dept);

    DeptInfoBean queryDept(String userCode);
}
