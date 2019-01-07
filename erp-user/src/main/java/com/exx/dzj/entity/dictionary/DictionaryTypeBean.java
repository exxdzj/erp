package com.exx.dzj.entity.dictionary;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Data
@ToString
public class DictionaryTypeBean implements Serializable {
    private Integer id;

    private String dataType;

    private String describe;

    private Integer isEnable;

    private Date createTime;

    private String createUser;

    private Date updateTime;

    private String updateUser;
}