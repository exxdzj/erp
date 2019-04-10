package com.exx.dzj.controller.sys;

import com.exx.dzj.facade.sys.SysPermissionFacade;
import com.exx.dzj.mapper.sys.SysPermissionMapper;
import com.exx.dzj.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author
 * @Date 2019/3/22 0022 14:35
 * @Description 系统权限
 */
@RestController
@RequestMapping("sys/Permission/")
public class SysPermissionController {

    @Autowired
    private SysPermissionFacade sysPermissionFacade;

    /**
     * 根据用户角色获取用户权限
     * @param request
     * @param response
     * @return
     */
    @GetMapping("queryPermissionsByUser")
    public Result queryPermissionsByUser(HttpServletRequest request, HttpServletResponse response, @RequestParam(value="token") String token) {
        Result result = Result.responseSuccess();
        result.setData(sysPermissionFacade.queryPermissionsByUser(token));
        return result;
    }
}
