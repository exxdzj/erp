package com.exx.dzj.entity.syslog;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * 系统日志实体类
 */
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

    private Integer logType;

    private String userName;

    private Long costTime;
}