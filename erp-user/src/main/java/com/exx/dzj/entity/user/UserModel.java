package com.exx.dzj.entity.user;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author
 * @Date 2019/4/1 0001 14:38
 * @Description
 */
@Data
public class UserModel implements Serializable {

    private String userCode;
    private String userName;
    private String realName;
    private String salesmanCode;
    private String headImg;
    private String phoneNum;
    private String deptName;
    private String email;
    private String genderName;
    private Integer isQuit;
    private String quitDesc;
}
