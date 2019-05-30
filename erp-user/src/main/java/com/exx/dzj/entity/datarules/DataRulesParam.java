package com.exx.dzj.entity.datarules;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author
 * @Date 2019/5/27 0027 15:40
 * @Description 数据权限查询条件实体类
 */
@Data
public class DataRulesParam implements Serializable {

    private String ruleCode;
    private String menuCode;
    private String userCode;
}
