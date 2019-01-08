package com.exx.dzj.entity.user;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author
 * @Date 2019/1/8 0008 16:20
 * @Description user token model
 */
@Data
@ToString
public class UserTokenBean implements Serializable {

    /** 主键 */
    private String id;

    /** 用户code */
    private String userCode;

    /** user token */
    private String userToken;

    /** 生效时间 */
    private Date startDate;

    /** 失效时间 */
    private Date expiredDate;
}
