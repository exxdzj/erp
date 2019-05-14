package com.exx.dzj.entity.role;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Author
 * @Date 2019/4/4 0004 16:07
 * @Description 给角色授权时的 model
 */
@Data
public class RoleMenuInfo implements Serializable {

    private String roleCode;
    /**
     * 菜单编号
     */
    private List<String> menuCodes;
    /**
     * 半选择的菜单
     */
    private List<String> halfNodes;
}
