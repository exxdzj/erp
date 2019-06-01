package com.exx.dzj.service.encode.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.exx.dzj.entity.encode.BusEncodeRuleCacheData;
import com.exx.dzj.entity.encode.BusEncodingRule;
import com.exx.dzj.mapper.encode.BusEncodeMapper;
import com.exx.dzj.service.encode.BusEncodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author
 * @Date 2019/5/31 0031 11:52
 * @Description 业务编码 service
 */
@Component
public class BusEncodeServiceImpl extends ServiceImpl<BusEncodeMapper, BusEncodingRule> implements BusEncodeService {

    @Autowired
    private BusEncodeMapper busEncodeMapper;

    /**
     * 查询 业务编码规则
     * @return
     */
    @Override
    public List<BusEncodeRuleCacheData> queryList() {
        return busEncodeMapper.queryList();
    }
}
