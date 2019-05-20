package com.exx.dzj.service.trail;

import com.exx.dzj.entity.trail.LogisticsTrail;
import com.exx.dzj.entity.trail.LogisticsTrailParam;

import java.util.List;

/**
 * @Author
 * @Date 2019/5/18 0018 17:22
 * @Description 物流明细 service
 */
public interface LogisticsTrailService {

    /**
     * @功能: 保存物流明细数据
     * @param trails
     */
    void saveLogisticsTrail(List<LogisticsTrail> trails);

    /**
     * @功能: 根据条件查询物流明细数据
     * @param param
     * @return
     */
    List<LogisticsTrail> queryLogisticsTrails(LogisticsTrailParam param);
}
