package com.exx.dzj.controller.sys;

import com.exx.dzj.entity.dept.DeptBean;
import com.exx.dzj.entity.dept.DeptInfoBean;
import com.exx.dzj.facade.sys.DeptFacade;
import com.exx.dzj.result.Result;
import com.exx.dzj.util.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author
 * @Date 2019/4/2 0002 15:39
 * @Description 部门
 */
@RestController
@RequestMapping("sys/dept/")
public class DeptController {

    @Autowired
    private DeptFacade deptFacade;

    /**
     * 查询 部门下拉框数据
     * @return
     */
    @GetMapping("queryDeptList")
    public Result queryDeptList(){
        return deptFacade.queryDeptList();
    }

    /**
     * 查询 部门的树形结构数据
     * @return
     */
    @GetMapping("queryDeptTree")
    public Result queryDeptTree(String deptName) {
        return deptFacade.queryDeptTree(deptName);
    }

    /**
     * 获取 部门详细信息
     * @param deptCode
     * @return
     */
    @GetMapping("queryDeptInfo")
    public Result queryDeptInfo(String deptCode) {
        Result result = Result.responseSuccess();
        if(ConvertUtils.isEmpty(deptCode)){
            result.setCode(400);
            result.setMsg("请选择部门!");
            return result;
        }

        result.setData(deptFacade.queryDeptInfo(deptCode));
        return result;
    }

    /**
     * 保存 部门数据
     * @param dept
     * @return
     */
    @PostMapping("saveDeptInfo")
    public Result saveDeptInfo(@RequestBody DeptInfoBean dept) {
        Result result = Result.responseSuccess();
        if(null == dept) {
            result.setCode(400);
            result.setMsg("数据不可为空,请填写数据!");
            return result;
        }
        return deptFacade.saveDeptInfo(dept);
    }

    /**
     * 删除 部门数据
     * @param dept
     * @return
     */
    @PostMapping("delDept")
    public Result delDept(@RequestBody DeptBean dept) {
        Result result = Result.responseSuccess();
        if(null == dept || CollectionUtils.isEmpty(dept.getDepts())) {
            result.setCode(400);
            result.setMsg("请选择要删除的数据!");
            return result;
        }
        result = deptFacade.delDept(dept);
        return result;
    }
}
