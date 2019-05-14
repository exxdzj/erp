package com.exx.dzj.entity.role;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * @描述 角色菜单关联实体类
 */
@Data
@ToString
public class RoleMenuBean implements Serializable {
    private Integer id;

    private String roleCode;

    private String menuCode;

    private Integer isHalf;

    private Date createTime;

    private String createUser;

    private Date updateTime;

    private String updateUser;
}