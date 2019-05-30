package com.exx.dzj.controller.sys;

import com.exx.dzj.constant.CommonConstant;
import com.exx.dzj.entity.datarules.DataPermissionRules;
import com.exx.dzj.entity.datarules.DataRulesParam;
import com.exx.dzj.facade.sys.DataPermissionRulesFacade;
import com.exx.dzj.result.Result;
import com.exx.dzj.util.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author
 * @Date 2019/5/27 0027 15:33
 * @Description  数据权限 controller
 */
@RestController
@RequestMapping("sys/data/rules/")
public class DataPermissionRulesController {

    @Autowired
    private DataPermissionRulesFacade dataPermissionRulesFacade;

    /**
     * 查询 数据权限列表
     * @param request
     * @return
     */
    @GetMapping("queryDataRulesList")
    public Result queryDataRulesList(HttpServletRequest request, DataRulesParam params) {
        return dataPermissionRulesFacade.queryDataRulesList(params);
    }

    /**
     * 查询 数据权限信息
     * @paramequest
     * @param params
     * @return
     */
    @GetMapping("queryDataRulesInfo")
    public Result queryDataRulesInfo(HttpServletRequest request, DataRulesParam params) {
        return dataPermissionRulesFacade.queryDataPermissionRulesInfo(params);
    }

    /**
     * 更新 数据权限
     * @param request
     * @param info
     * @return
     */
    @PostMapping("saveDataRules")
    public Result saveDataRules(HttpServletRequest request, @RequestBody DataPermissionRules info) {
        Result result = Result.responseSuccess();
        if(null == info
                || ConvertUtils.isEmpty(info.getRuleConditions())
                || ConvertUtils.isEmpty(info.getRuleName())
                || ConvertUtils.isEmpty(info.getRuleValue())
                || ConvertUtils.isEmpty(info.getMenuCode())) {
            result.setCode(400);
            result.setMsg("数据不可为空!");
            return  result;
        }
        return dataPermissionRulesFacade.saveDataRules(info);
    }

    /**
     * 删除 数据权限
     * @param request
     * @param info
     * @return
     */
    @PostMapping("delDateRules")
    public Result delDateRules(HttpServletRequest request, @RequestBody DataPermissionRules info) {
        Result result = Result.responseSuccess();
        if(null == info
                || ConvertUtils.isEmpty(info.getRuleCode())) {
            result.setCode(400);
            result.setMsg("数据不可为空!");
            return  result;
        }
        info.setIsEnable(CommonConstant.DEFAULT_VALUE_ZERO);
        return dataPermissionRulesFacade.saveDataRules(info);
    }
}
