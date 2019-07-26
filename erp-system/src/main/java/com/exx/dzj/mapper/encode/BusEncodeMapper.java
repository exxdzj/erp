package com.exx.dzj.mapper.encode;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.exx.dzj.entity.encode.BusEncodeRuleCacheData;
import com.exx.dzj.entity.encode.BusEncodeRuleInfo;
import com.exx.dzj.entity.encode.BusEncodingRule;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author
 * @Date 2019/5/31 0031 11:49
 * @Description 业务规则编码 mapper
 */
public interface BusEncodeMapper extends BaseMapper<BusEncodingRule> {

    List<BusEncodeRuleCacheData> queryList();

    List<BusEncodeRuleInfo> queryBusEncodeRule(@Param("busType") String busType);

    int updateEncodeData(BusEncodeRuleInfo info);

    int scheduleUpdateEncode(BusEncodeRuleInfo info);
}
