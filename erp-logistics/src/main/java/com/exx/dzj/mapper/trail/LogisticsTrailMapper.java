package com.exx.dzj.mapper.trail;

import com.exx.dzj.entity.trail.LogisticsTrail;
import com.exx.dzj.entity.trail.LogisticsTrailParam;

import java.util.List;

/**
 * @Author
 * @Date 2019/5/18 0018 17:36
 * @Description 物流明细 mapper
 */
public interface LogisticsTrailMapper {

    /**
     * @功能: 保存 物流明细数据
     * @param trails
     * @return
     */
    int saveLogisticsTrail(List<LogisticsTrail> trails);

    /**
     * @功能: 查询 物流明细数据
     * @param param
     * @return
     */
    List<LogisticsTrail> queryLogisticsTrails(LogisticsTrailParam param);
}
