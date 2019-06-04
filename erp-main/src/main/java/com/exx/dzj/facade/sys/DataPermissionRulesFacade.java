package com.exx.dzj.facade.sys;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.exx.dzj.constant.CommonConstant;
import com.exx.dzj.entity.datarules.DataPermissionRules;
import com.exx.dzj.entity.datarules.DataRulesParam;
import com.exx.dzj.facade.user.UserTokenFacade;
import com.exx.dzj.result.Result;
import com.exx.dzj.service.sys.DataPermissionRulesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author
 * @Date 2019/5/27 0027 15:36
 * @Description  数据权限
 */
@Component
public class DataPermissionRulesFacade {

    @Autowired
    public DataPermissionRulesService dataPermissionRulesService;

    @Autowired
    private UserTokenFacade userTokenFacade;

    /**
     * 查询 菜单所对应的数据权限
     * @param menuCode
     * @return
     */
    public List<DataPermissionRules> list(String menuCode) {
        LambdaQueryWrapper<DataPermissionRules> query = new LambdaQueryWrapper<>();
        query.eq(DataPermissionRules::getMenuCode, menuCode);
        query.eq(DataPermissionRules::getIsEnable, CommonConstant.DEFAULT_VALUE_ONE);
        return dataPermissionRulesService.list(query);
    }

    /**
     * 查询  数据权限列表数据
     * @param params
     * @return
     */
    public Result queryDataRulesList(DataRulesParam params) {
        Result result = Result.responseSuccess();
        try {
            result.setData(dataPermissionRulesService.queryDataPermissionRules(params));
        } catch(Exception ex) {
            result.setCode(400);
            result.setMsg("没有数据!");
        }
        return result;
    }

    /**
     * 获取 数据
     * @param params
     * @return
     */
    public Result queryDataPermissionRulesInfo(DataRulesParam params) {
        Result result = Result.responseSuccess();
        result.setData(dataPermissionRulesService.queryDataPermissionRulesInfo(params));
        return result;
    }

    /**
     * 更新  数据权限数据
     * @param info
     * @return
     */
    public Result saveDataRules(DataPermissionRules info) {
        String userCode = userTokenFacade.queryUserCodeForToken(null);
        info.setCreateUser(userCode);
        return dataPermissionRulesService.saveDataPermissionRules(info);
    }
}
