package com.exx.dzj.controller.user;

import com.exx.dzj.constant.CommonConstant;
import com.exx.dzj.entity.user.UserBean;
import com.exx.dzj.entity.user.UserInfo;
import com.exx.dzj.entity.user.UserQuery;
import com.exx.dzj.entity.user.UserVo;
import com.exx.dzj.facade.user.UserFacade;
import com.exx.dzj.result.Result;
import com.exx.dzj.util.ConvertUtils;
import com.exx.dzj.util.JsonUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @Author
 * @Date 2019/1/8 0008 17:18
 * @Description 获取用户信息
 */
@RestController
@RequestMapping("user/")
public class UserController {

    @Autowired
    private UserFacade userFacade;

    /**
     * 当用户登录成功后,进入主页通过 token 获取用户信息和用户角色
     * @return
     */
    @GetMapping("getUserRoles")
    public Result getUserInfo(HttpServletRequest request, @RequestParam(value="token") String token){
        Result result = Result.responseSuccess();
        result.setData(userFacade.getUserRoles(token));
        return result;
    }

    /**
     * 查询业务员列表数据
     * @return
     */
    @GetMapping("querySalesman")
    public Result querySalesman() {
        Result result = Result.responseSuccess();
        result.setData(userFacade.querySalesman());
        return result;
    }

    /**
     * 查询用户(公司员工)列表数据
     * @param request
     * @param response
     * @param query
     * @return
     */
    @GetMapping("list")
    public Result list(HttpServletRequest request, HttpServletResponse response, String query) {
        Result result = Result.responseSuccess();
        UserQuery queryParam = JsonUtils.jsonToPojo(query, UserQuery.class);
        int pageNum = queryParam != null ? queryParam.getPage() : CommonConstant.DEFAULT_PAGE_NUM;
        int pageSize = queryParam != null ? queryParam.getLimit() : CommonConstant.DEFAULT_PAGE_SIZE;
        result = userFacade.list(pageNum, pageSize, queryParam);
        return result;
    }

    /**
     * 保存 用户信息
     * @param request
     * @param response
     * @param bean
     * @return
     */
    @PostMapping("saveUserInfo")
    public Result saveUserInfo(HttpServletRequest request, HttpServletResponse response, @RequestBody UserVo bean) {
        Result result = Result.responseSuccess();
        if(null == bean) {
            result.setCode(400);
            result.setMsg("请填写用户数据!");
            return result;
        }
        if(StringUtils.isBlank(bean.getUserName())){
            result.setCode(400);
            result.setMsg("请填写用户账号!");
            return result;
        }
        try {
            userFacade.saveUserInfo(bean);
        } catch (Exception ex) {
            result.setCode(400);
            result.setMsg("保存用户信息失败!");
        }
        return result;
    }

    /**
     * 查询 用户信息
     * @param userCode
     * @return
     */
    @GetMapping("queryUserInfo")
    public Result queryUserInfo(@RequestParam(value = "userCode") String userCode) {
        Result result = Result.responseSuccess();
        result.setData(userFacade.queryUserBean(userCode));
        return  result;
    }

    /**
     * 检查 用户的账号是否被注册
     * @param userName
     * @return
     */
    @GetMapping("checkUserName")
    public Result checkUserName(String userName) {
        return userFacade.checkUserName(userName);
    }

    /**
     * 判断当前的业务编码是否有人使用
     * @param salesmanCode
     * @return
     */
    @GetMapping("checkSalesmanCode")
    public Result checkSalesmanCode(String salesmanCode) {
        Result result = Result.responseSuccess();
        if(ConvertUtils.isEmpty(salesmanCode)) {
            return result;
        }
        result = userFacade.checkSalesmanCode(salesmanCode);
        return result;
    }

    /**
     * 离职操作
     * @param userCode
     * @return
     */
    @PostMapping("quitUser")
    public Result quitUser(@RequestParam String userCode) {
        return userFacade.quitUser(userCode);
    }

    /**
     * @param request
     * @param response
     * @return 获取用户信息(登录后)
     */
    @GetMapping("getUserInfo")
    public Result getUserInfo(HttpServletRequest request, HttpServletResponse response) {
        Result result = Result.responseSuccess();
        result.setData(userFacade.getUserInfo(null));
        return result;
    }
}
