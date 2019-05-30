package com.exx.dzj.mapper.sys;

import com.exx.dzj.entity.datarules.DataRulesParam;

import java.util.List;

/**
 * @Author
 * @Date 2019/5/28 0028 9:59
 * @Description  数据权限
 */
public interface DataRulesMapper {

    /**
     * 查询 数据权限
     * @param params
     * @return
     */
    List<String> queryDataRules(DataRulesParam params);
}
