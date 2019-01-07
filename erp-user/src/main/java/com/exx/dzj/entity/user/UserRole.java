package com.exx.dzj.entity.user;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Data
@ToString
public class UserRole implements Serializable {
    private Integer id;

    private String userCode;

    private String roleCode;

    private Date createTime;

    private String createUser;

    private Date updateTime;

    private String updateUser;
}