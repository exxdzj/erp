package com.exx.dzj.entity.icon;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Data
@ToString
public class IconBean implements Serializable {
    private String id;

    private String iconName;

    private String iconPath;

    private String iconSuffix;

    private String iconType;

    private Date createTime;

    private String createUser;

    private Date updateTime;

    private String updateUser;
}