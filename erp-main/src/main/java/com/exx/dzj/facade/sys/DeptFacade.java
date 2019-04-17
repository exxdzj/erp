package com.exx.dzj.facade.sys;

import com.exx.dzj.entity.dept.DeptBean;
import com.exx.dzj.entity.dept.DeptInfoBean;
import com.exx.dzj.facade.user.UserTokenFacade;
import com.exx.dzj.result.Result;
import com.exx.dzj.service.sys.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author
 * @Date 2019/4/2 0002 15:24
 * @Description  部门
 */
@Component
public class DeptFacade {

    @Autowired
    private DeptService deptService;
    @Autowired
    private UserTokenFacade userTokenFacade;

    /**
     * 查询 部门列表数据
     * @param pageNum
     * @param pageSize
     * @return
     */
    public Result queryList(int pageNum, int pageSize){
        Result result = Result.responseSuccess();
        result.setData(deptService.queryList(pageNum, pageSize));
        return result;
    }

    /**
     * 查询 部门下拉框数据
     * @return
     */
    public Result queryDeptList(){
        Result result = Result.responseSuccess();
        result.setData(deptService.queryDeptList());
        return result;
    }

    /**
     * 查询 部门树形结构数据
     * @return
     */
    public Result queryDeptTree(String deptName) {
        Result result = Result.responseSuccess();
        result.setData(deptService.queryDeptTree(deptName));
        return result;
    }

    /**
     * 查询  部门详细信息
     * @param deptCode
     * @return
     */
    public DeptInfoBean queryDeptInfo(String deptCode) {
        return deptService.queryDeptInfo(deptCode);
    }

    /**
     * 保存 部门信息
     * @param dept
     * @return
     */
    public Result saveDeptInfo(DeptInfoBean dept) {
        String operator = userTokenFacade.queryUserCodeForToken(null);
        dept.setCreateUser(operator);
        dept.setUpdateUser(operator);
        return deptService.saveDeptInfo(dept);
    }

    /**
     * 删除 部门数据
     * @param dept
     * @return
     */
    public Result delDept(DeptBean dept) {
        return deptService.delDept(dept);
    }
}
