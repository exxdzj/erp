package com.exx.dzj.entity.role;

import lombok.Data;

/**
 * @Author
 * @Date 2019/4/4 0004 14:00
 * @Description 角色列表查询条件 model
 */
@Data
public class RoleQuery extends RoleBean {
    private int page;

    private int limit;
}
