package com.exx.dzj.mapper.sys;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.exx.dzj.entity.datarules.DataPermissionRules;
import com.exx.dzj.entity.datarules.DataRulesParam;

import java.util.List;

/**
 * @Author
 * @Date 2019/5/27 0027 15:45
 * @Description  数据权限 mapper
 */
public interface DataPermissionRulesMapper extends BaseMapper<DataPermissionRules> {

    /**
     * 查询  数据权限列表数据
     * @param params
     * @return
     */
    List<DataPermissionRules> queryDataPermissionRules(DataRulesParam params);

    /**
     * 查询  数据权限详细信息
     * @param params
     * @return
     */
    DataPermissionRules queryDataPermissionRulesInfo(DataRulesParam params);

    /**
     * 保存  数据权限信息
     * @param info
     * @return
     */
    int saveDataPermissionRules(DataPermissionRules info);

    /**
     * 修改  数据权限信息
     * @param info
     * @return
     */
    int modifyDataPermissionRules(DataPermissionRules info);
}
