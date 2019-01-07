package com.exx.dzj.entity.menu;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class MenuInfo implements Serializable {
    private Integer id;

    private String menuCode;

    private String menuName;

    private String menuUrl;

    private String menuDict;

    private String parentCode;

    private Integer isEnable;

    private String iconid;

    private String deskIconid;

    private Integer seq;

    private Integer menuLevel;
}