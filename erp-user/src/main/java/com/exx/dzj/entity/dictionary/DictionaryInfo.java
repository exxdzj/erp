package com.exx.dzj.entity.dictionary;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Data
@ToString
public class DictionaryInfo implements Serializable {
    private Integer id;

    private String dictCode;

    private String dictName;

    private Integer seq;

    private String img;

    private String dataType;

    private String remarks;

    private Integer isEnable;

    private Date createTime;

    private String createUser;

    private Date updateTime;

    private String updateUser;
}