package com.exx.dzj.entity.encode;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author
 * @Date 2019/5/31 0031 11:39
 * @Description 业务编码规则信息
 */
@Data
public class BusEncodeRuleInfo implements Serializable {

    // 前缀
    private String prefix;

    private String encodeName;

    /**
     * 编码方式(1-流水号  2-年编号(yy) 3-月编号(yyMM)  4-日编号(yyMMdd))
     */
    private Integer codingMode;
    // 初始值
    private Integer initialValue;
    // 长度
    private Integer serialNumLength;
    // 步长(增长量)
    private Integer step;
    // 下一个 code
    private Integer nextValue;
    // 格式
    private String serialNumFormat;

    private String busType;
}
