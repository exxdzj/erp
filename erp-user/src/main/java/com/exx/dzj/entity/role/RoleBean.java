package com.exx.dzj.entity.role;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Data
@ToString
public class RoleBean implements Serializable {
    private Integer id;

    private String roleCode;

    private String roleName;

    private String roleDict;

    private Integer isEnable;

    private Integer seq;

    private Date createTime;

    private String createUser;

    private Date updateTime;

    private String updateUser;
}