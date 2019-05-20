package com.exx.dzj.facade.logistics;

import com.alibaba.fastjson.JSON;
import com.exx.dzj.constant.CommonConstant;
import com.exx.dzj.entity.trail.LogisticsTrail;
import com.exx.dzj.entity.trail.LogisticsTrailParam;
import com.exx.dzj.entity.trail.TracesBean;
import com.exx.dzj.entity.trail.TrailJsonBean;
import com.exx.dzj.fms.KdniaoTrackQueryAPI;
import com.exx.dzj.result.Result;
import com.exx.dzj.service.trail.LogisticsTrailService;
import com.exx.dzj.util.ConvertUtils;
import com.exx.dzj.util.DateTimeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author
 * @Date 2019/5/20 0020 10:08
 * @Description 物流明细
 */
@Slf4j
@Component
public class LogisticsTrailFacade {

    @Autowired
    private LogisticsTrailService logisticsTrailService;

    /**
     * 查询 物流轨迹信息
     * @param param
     * @return
     */
    public Result queryLogisticsTrails(LogisticsTrailParam param) {
        Result result = Result.responseSuccess();

        try {
            //首先向数据库查询
            List<LogisticsTrail> trails = logisticsTrailService.queryLogisticsTrails(param);
            if(!CollectionUtils.isEmpty(trails)) {
                result.setData(trails);
                return result;
            }

            //否则向第三方查询
            KdniaoTrackQueryAPI api = new KdniaoTrackQueryAPI();
            String trail = api.getOrderTracesByJson(param.getLogisticCompanyCode(), param.getFreightCode());
            if(ConvertUtils.isEmpty(trail)) {
                result.setCode(400);
                result.setMsg("未查询到物流信息,请确认是否发货!");
                return result;
            }

            //解析物流信息
            TrailJsonBean bean = getLogisticsTrail(trail);
            if(null == bean || CollectionUtils.isEmpty(bean.getTraces())) {
                result.setCode(400);
                result.setMsg("未查询到物流信息,请确认是否发货!");
                return result;
            }

            if(null == bean.getState() && !CommonConstant.KDNIAO_SIGN_IN_STATE.equals(bean.getState())) {
                trails = paserTraces(bean, param);
            }

            //判断是否签收,如果签收则将数据写入到数据库中
            if(CommonConstant.KDNIAO_SIGN_IN_STATE.equals(bean.getState())) {
                logisticsTrailService.saveLogisticsTrail(trails);
            }

            result.setData(trails);
        } catch(Exception ex) {
            log.error("查询物流信息失败!执行方法:{}异常信息:{}", LogisticsTrailFacade.class.getName()+".queryLogisticsTrails", ex.getMessage());
            result.setCode(400);
            result.setMsg("查询物流信息失败!");
        }

        return result;
    }

    /**
     * 解析从第三方获取的物流信息
     * @pam trail
     * @return
     */
    private static TrailJsonBean getLogisticsTrail(String trail) {
        TrailJsonBean bean = JSON.parseObject(trail, TrailJsonBean.class);
        //System.out.println(bean.toString());
        return bean;
    }

    /**
     * 转换成 LogisticsTrail 对象
     * @param bean
     * @return
     */
    private static List<LogisticsTrail> paserTraces(TrailJsonBean bean, LogisticsTrailParam param) {
        List<LogisticsTrail> trails = new ArrayList<>();
        LogisticsTrail trail = null;
        for (TracesBean traces : bean.getTraces()) {
            trail = new LogisticsTrail();
            trail.setAcceptStation(traces.getAcceptStation());
            trail.setAcceptTime(traces.getAcceptTime());
            trail.setAcceptDate(DateTimeUtil.strToDate(traces.getAcceptTime()));
            trail.setDataSource(1);
            trail.setBusinessId("1298315");
            trail.setBusModule(param.getBusModule());
            trail.setFreightCode(param.getFreightCode());
            trail.setOrderNo(param.getOrderNo());
            trail.setLogisticCompanyCode(bean.getShipperCode());
            trails.add(trail);
        }

        if(!CollectionUtils.isEmpty(trails)) {
            trails.sort((t1, t2) -> t2.getAcceptDate().compareTo(t1.getAcceptDate()));
            //System.out.println("排序后的结果:" + trails);
        }

        return trails;
    }

    /**
     * 测试
     * @param args
     */
    public static void main(String[] args) {
        KdniaoTrackQueryAPI api = new KdniaoTrackQueryAPI();
        try {
            String result = api.getOrderTracesByJson("UC", "900091390935");
            System.out.println(result);
            TrailJsonBean bean = getLogisticsTrail(result);
            paserTraces(bean, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
