package com.exx.dzj.entity.menu;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@TableName("tab_menu_info")
public class MenuInfo {

    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    private String menuCode;

    private String parentCode;

    private String menuName;

    private String menuUrl;

    private String component;

    private String redirect;

    private Integer menuType;

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