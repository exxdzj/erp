package com.exx.dzj.facade.sys;

import com.exx.dzj.busencode.BusEncodeGenerater;
import com.exx.dzj.entity.encode.BusEncodeRuleCacheData;
import com.exx.dzj.entity.encode.BusEncodeRuleInfo;
import com.exx.dzj.service.encode.BusEncodeService;
import com.exx.dzj.util.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author
 * @Date 2019/5/31 0031 14:46
 * @Description 业务数据编码规则
 */
@Component
public class BusEncodeFacade {

    @Autowired
    private BusEncodeService busEncodeService;

    /**
     * 获取 业务数据编码规则
     * @return
     */
    @Cacheable(value = "busEncode")
    public List<BusEncodeRuleCacheData> queryList() {
        return busEncodeService.queryList();
    }

    /**
     * 获取 业务编码前缀
     * @param busType
     * @return
     */
    public List<BusEncodeRuleInfo> queryBusEncode(String busType) {
        List<BusEncodeRuleInfo> list = new ArrayList<>();

        List<BusEncodeRuleCacheData> busEncodes = this.queryList();
        if(CollectionUtils.isEmpty(busEncodes)) {
            return null;
        }
        for(BusEncodeRuleCacheData busCodeData : busEncodes) {
            if(null != busCodeData
                    && ConvertUtils.isNotEmpty(busCodeData.getBusType())
                    && busCodeData.getBusType().equals(busType)) {
                list = busCodeData.getEncodeInfos();
            }
        }
        return list;
    }

    /**
     * 生成下一个业务编号(性能比较差，可以考虑使用分布式的中间件来实现)
     * @param busType
     * @param prefix
     * @return
     */
    public String nextBusCode(String busType, String prefix) {
        String busCode = "";
        List<BusEncodeRuleCacheData> busEncodes = this.queryList();
        if(CollectionUtils.isEmpty(busEncodes)) {
            return null;
        }

        for(BusEncodeRuleCacheData busCodeData : busEncodes) {
            if(null != busCodeData
                    && ConvertUtils.isNotEmpty(busCodeData.getBusType())
                    && busCodeData.getBusType().equals(busType)) {
                List<BusEncodeRuleInfo> encodeInfos = busCodeData.getEncodeInfos();
                if(!CollectionUtils.isEmpty(encodeInfos)) {
                    for(BusEncodeRuleInfo info : encodeInfos) {
                        if(null != info
                                && ConvertUtils.isNotEmpty(info.getPrefix())
                                && info.getPrefix().equals(prefix)) {
                            busCode = BusEncodeGenerater.nextBusCode(info.getPrefix(), info.getNextValue(), info.getSerialNumLength(), info.getSerialNumFormat());
                            Integer nextBusCode = BusEncodeGenerater.nextCode(info.getNextValue(), info.getStep());
                            //info.setNextValue(nextBusCode);
                            //BusEncodingRule busEncodingRule = new BusEncodingRule();
                            //busEncodingRule.setNextValue(nextBusCode);
                            //busEncodeService.update(busEncodingRule, new QueryWrapper<BusEncodingRule>().eq("prefix", info.getPrefix()).eq("bus_type", busType));

                            info.setNextValue(nextBusCode);
                            info.setBusType(busType);
                            busEncodeService.updateEncodeData(info);
                            break;
                        }
                    }
                }
            }
        }
        return busCode;
    }
}
