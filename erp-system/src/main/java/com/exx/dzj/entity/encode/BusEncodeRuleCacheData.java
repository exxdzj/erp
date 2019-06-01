package com.exx.dzj.entity.encode;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Author
 * @Date 2019/5/31 0031 11:36
 * @Description  业务编码规则缓存数据
 */
@Data
public class BusEncodeRuleCacheData implements Serializable {

    /**
     * 业务类型(业务编码)
     */
    private String busType;

    private List<BusEncodeRuleInfo> encodeInfos;
}
