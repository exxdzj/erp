package com.exx.dzj.service.sys;

import com.exx.dzj.entity.datarules.DataPermissionRules;
import com.exx.dzj.entity.datarules.DataRulesParam;
import com.exx.dzj.result.Result;

import java.util.List;

/**
 * @Author
 * @Date 2019/5/27 0027 11:43
 * @Description  数据权限 service
 */
public interface DataPermissionRulesService {

    /**
     * 查询  数据权限列表
     * @param params
     * @return
     */
    List<DataPermissionRules> queryDataPermissionRules(DataRulesParam params);

    /**
     * 获取 数据权限信息
     * @param params
     * @return
     */
    DataPermissionRules queryDataPermissionRulesInfo(DataRulesParam params);

    /**
     * 更新 数据权限
     * @param info
     * @return
     */
    Result saveDataPermissionRules(DataPermissionRules info);

    /**
     * 通过用户编码和菜单编码  查询对应的数据权限
     * @param userCode
     * @param menuCode
     * @return
     */
    List<DataPermissionRules> queryDataPermissionRules(String userCode, String menuCode);
}
