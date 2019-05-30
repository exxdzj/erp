package com.exx.dzj.service.sys.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.exx.dzj.annotation.SysLog;
import com.exx.dzj.constant.CommonConstant;
import com.exx.dzj.entity.datarules.DataPermissionRules;
import com.exx.dzj.entity.datarules.DataRulesParam;
import com.exx.dzj.mapper.sys.DataPermissionRulesMapper;
import com.exx.dzj.mapper.sys.DataRulesMapper;
import com.exx.dzj.result.Result;
import com.exx.dzj.service.sys.DataPermissionRulesService;
import com.exx.dzj.unique.DefaultIdGenerator;
import com.exx.dzj.unique.IdGenerator;
import com.exx.dzj.util.ConvertUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Author
 * @Date 2019/5/27 0027 11:45
 * @Description 数据权限 service
 */
@Slf4j
@Component
public class DataPermissionRulesServiceImpl implements DataPermissionRulesService {

    @Autowired
    private DataPermissionRulesMapper dataPermissionRulesMapper;

    @Autowired
    private DataRulesMapper dataRulesMapper;

    /**
     * 查询  数据权限列表
     * @param params
     * @return
     */
    @Override
    public List<DataPermissionRules> queryDataPermissionRules(DataRulesParam params) {
        try {
            return dataPermissionRulesMapper.queryDataPermissionRules(params);
        } catch(Exception ex) {
            log.error("执行方法:{}异常信息:{}", DataPermissionRulesServiceImpl.class.getName()+".queryDataPermissionRules", ex.getMessage());
            return new ArrayList<>();
        }
    }

    /**
     * 获取 数据权限详细信息
     * @param params
     * @return
     */
    @Override
    public DataPermissionRules queryDataPermissionRulesInfo(DataRulesParam params) {
        try {
            return dataPermissionRulesMapper.queryDataPermissionRulesInfo(params);
        } catch(Exception ex) {
            log.error("执行方法:{}异常信息:{}", DataPermissionRulesServiceImpl.class.getName()+".queryDataPermissionRulesInfo", ex.getMessage());
            return new DataPermissionRules();
        }
    }

    /**
     * 更新  数据权限
     * @param info
     * @return
     */
    @Override
    @SysLog(operate="更新数据权限")
    public Result saveDataPermissionRules(DataPermissionRules info) {
        Result result = Result.responseSuccess();
        try {
            if (null != info && ConvertUtils.isNotEmpty(info.getRuleCode())) {
                dataPermissionRulesMapper.modifyDataPermissionRules(info);
            } else {
                IdGenerator idGenerator = new DefaultIdGenerator("");
                info.setRuleCode(idGenerator.next());
                dataPermissionRulesMapper.saveDataPermissionRules(info);
            }
        } catch(Exception ex) {
            log.error("执行方法:{}异常信息:{}", DataPermissionRulesServiceImpl.class.getName()+".saveDataPermissionRules", ex.getMessage());
            result.setCode(400);
            result.setMsg("更新数据失败!");
        }
        return result;
    }

    /**
     * 通过用户编码和菜单编码  查询对应的数据权限
     * @param userCode
     * @param menuCode
     * @return
     */
    @Override
    public List<DataPermissionRules> queryDataPermissionRules(String userCode, String menuCode) {
        DataRulesParam params = new DataRulesParam();
        params.setMenuCode(menuCode);
        params.setUserCode(userCode);

        List<String> dataRules = dataRulesMapper.queryDataRules(params);
        if(null == dataRules || dataRules.size() == 0) {
            return null;
        }

        Set<String> rules = new HashSet<>();
        for (String dataRule : dataRules) {
            if (ConvertUtils.isEmpty(dataRule)) {
                continue;
            }
            String[] ruleArr = dataRule.split(",");
            if(null != ruleArr && ruleArr.length > 0) {
                for(String rule : ruleArr) {
                    rules.add(rule);
                }
            }
        }

        if(null == rules || rules.isEmpty()) {
            return null;
        }

        /**
         * 通过条件查询，满足条件的数据
         */
        return dataPermissionRulesMapper.selectList(new QueryWrapper<DataPermissionRules>().in("rule_code", rules).eq("is_enable", CommonConstant.DEFAULT_VALUE_ONE));
    }
}
