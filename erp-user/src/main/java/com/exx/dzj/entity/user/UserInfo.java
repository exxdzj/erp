package com.exx.dzj.entity.user;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Data
@ToString
public class UserInfo implements Serializable {
    private Integer id;

    private String userCode;

    private String userName;

    private String passWord;

    private String realName;

    private String deptCode;

    private String salesmanCode;

    private String headImg;

    private String phoneNum;

    private String email;

    private Date birthday;

    private Integer gender;

    private Integer isQuit;

    private Integer loginStatus;

    private Date lastSigninTime;

    private Date lastSignoutTime;
}