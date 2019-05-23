package com.exx.dzj.entity.menu;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class MenuInfo {
    private Integer id;

    private String menuCode;

    private String parentCode;

    private String menuName;

    private String menuUrl;

    private String component;

    private String redirect;

    private Integer menuType;

    private String menuTypeName;

    private String perms;

    private Integer permsType;

    private String icon;

    private String deskIconid;

    private boolean alwaysShow;

    private Integer isLeaf;

    private boolean hidden;

    private String menuDict;

    private Integer seq;

    private Integer menuLevel;

    private Integer isEnable;

}