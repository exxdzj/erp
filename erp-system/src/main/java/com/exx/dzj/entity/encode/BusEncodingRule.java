package com.exx.dzj.entity.encode;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author
 * @Date 2019/5/31 0031 11:28
 * @Description  业务数据编码规则
 */
@Data
@TableName("tab_bus_encoding_rule")
public class BusEncodingRule implements Serializable {

    private Integer id;
    private String encodeCode;
    private String encodeName;
    private String prefix;
    private Integer initialValue;
    private Integer serialNumLength;
    private Integer step;
    private Integer nextValue;
    private Integer codingMode;
    private String busType;
    private Integer isDefualt;
    private Integer isEnable;
    private Date createTime;
    private Integer yearDigit;
    private String example;
    private String serialNumFormat;

}
