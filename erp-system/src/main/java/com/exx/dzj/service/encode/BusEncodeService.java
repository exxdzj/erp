package com.exx.dzj.service.encode;

import com.baomidou.mybatisplus.extension.service.IService;
import com.exx.dzj.entity.encode.BusEncodeRuleCacheData;
import com.exx.dzj.entity.encode.BusEncodingRule;

import java.util.List;

/**
 * @Author
 * @Date 2019/5/31 0031 11:35
 * @Description 业务编码 service
 */
public interface BusEncodeService extends IService<BusEncodingRule> {

    List<BusEncodeRuleCacheData> queryList();
}
