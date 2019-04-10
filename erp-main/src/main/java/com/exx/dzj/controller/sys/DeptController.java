package com.exx.dzj.controller.sys;

import com.exx.dzj.facade.sys.DeptFacade;
import com.exx.dzj.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
