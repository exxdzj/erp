package com.exx.dzj.entity.datarules;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author
 * @Date 2019/5/27 0027 11:18
 * @Description 数据规则实体类
 */
@Data
@TableName("tab_data_permission_rules")
public class DataPermissionRules implements Serializable {

    /**
     * 主键
     */
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    /**
     * 规则编码
     */
    private String ruleCode;

    /**
     * 菜单编码
     */
    private String menuCode;

    /**
     * 规则名称
     */
    private String ruleName;

    /**
     * 规则字段(列， 可为空)
     */
    private String ruleColumn;

    /**
     * 规则连接条件(IN、LIKE、>=、= 等条件)
     */
    private String ruleConditions;

    /**
     * 规则条件(具体的条件)
     */
    private String ruleValue;

    /**
     * 前缀(表的别名)
     */
    private String prefix;

    /**
     * 是否有效
     */
    private Integer isEnable;

    /**
     * 创建人
     */
    private String createUser;

    /**
     * 创建时间
     */
    private Date createTime;

}
