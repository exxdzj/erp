package com.exx.dzj.entity.role;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * @描述 角色菜单关联实体类
 */
@Data
@ToString
@TableName("tab_role_menu")
public class RoleMenuBean implements Serializable {
    private Integer id;

    private String roleCode;

    private String menuCode;

    private Integer isHalf;

    private String dataRuleIds;

    private Date createTime;

    private String createUser;

    private Date updateTime;

    private String updateUser;
}