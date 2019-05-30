package com.exx.dzj.entity.menu;

import com.exx.dzj.constant.CommonConstant;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author
 * @Date 2019/4/11 0011 14:56
 * @Description  菜单  树形结构 model
 */
@Data
public class MenuTreeBean implements Serializable {

    private String menuCode;
    private String menuName;
    private Integer menuType;
    private String menuTypeName;
    private String menuUrl;
    private String component;
    private String icon;
    private Integer isLeaf;
    private List<MenuTreeBean> children;

    public MenuTreeBean(MenuBean menu) {
        this.menuCode = menu.getMenuCode();
        this.menuName = menu.getMenuName();
        this.menuType = menu.getMenuType();
        this.menuTypeName = menu.getMenuTypeName();
        this.menuUrl = menu.getMenuUrl();
        this.component = menu.getComponent();
        this.icon = menu.getIcon();
        this.isLeaf = menu.getIsLeaf();
        if(menu.getIsLeaf() == CommonConstant.DEFAULT_VALUE_ZERO) {
            this.children = new ArrayList<>();
        }
    }
}
