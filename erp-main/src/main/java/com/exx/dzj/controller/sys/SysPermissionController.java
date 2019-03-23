package com.exx.dzj.controller.sys;

import com.exx.dzj.result.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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

    /**
     * 根据用户角色获取用户权限
     * @param request
     * @param response
     * @return
     */
    @GetMapping("queryPermissionsByUser")
    public Result queryPermissionsByUser(HttpServletRequest request, HttpServletResponse response) {
        Result result = Result.responseSuccess();

        return result;
    }
}
