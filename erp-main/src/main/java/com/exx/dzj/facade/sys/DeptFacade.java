package com.exx.dzj.facade.sys;

import com.exx.dzj.result.Result;
import com.exx.dzj.service.sys.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author
 * @Date 2019/4/2 0002 15:24
 * @Description  部门
 */
@Component
public class DeptFacade {

    @Autowired
    private DeptService deptService;

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
}
