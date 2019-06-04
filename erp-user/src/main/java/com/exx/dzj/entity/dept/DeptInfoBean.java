package com.exx.dzj.entity.dept;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Data
@ToString
@TableName("tab_dept_info")
public class DeptInfoBean implements Serializable {
    private Integer id;

    private String deptCode;

    private String deptName;

    private String parentCode;

    private String deptDescribe;

    private Integer seq;

    private Integer isEnable;

    private Date createTime;

    private String createUser;

    private Date updateTime;

    private String updateUser;

    private Integer isCompare;

    private String orgCode;
}