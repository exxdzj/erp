package com.exx.dzj.entity.log;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Data
@ToString
public class LogBean implements Serializable {
    private Integer id;

    private String operate;

    private String method;

    private String params;

    private String node;

    private String logLevel;

    private Date createTime;

    private String createUser;

    private String logContent;
}