package com.exx.dzj.entity.user;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.exx.dzj.annotation.LIKE;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Data
@ToString
@TableName("tab_user_info")
public class UserInfo implements Serializable {

    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    private String userCode;

    private String userName;

    private String passWord;

    private String realName;

    private String deptCode;

    private String orgCode;

    private String salesmanCode;
    private String salesmanCode2;

    private String headImg;

    private String phoneNum;

    private String email;

    private Date birthday;

    private Integer gender;

    private Integer isQuit;

    private Integer loginStatus;

    private Date lastSigninTime;

    private Date lastSignoutTime;

    private Integer aloneRole;

    @LIKE
    public String getUserName() {
        return userName;
    }

    @LIKE
    public String getRealName() {
        return realName;
    }
}