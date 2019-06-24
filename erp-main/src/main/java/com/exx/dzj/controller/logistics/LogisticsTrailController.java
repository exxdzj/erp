package com.exx.dzj.controller.logistics;

import com.exx.dzj.entity.trail.LogisticsTrailParam;
import com.exx.dzj.facade.logistics.LogisticsTrailFacade;
import com.exx.dzj.result.Result;
import com.exx.dzj.util.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author
 * @Date 2019/5/20 0020 11:19
 * @Description 物流信息
 */
@RestController
@RequestMapping("logistics/trail/")
public class LogisticsTrailController {

    @Autowired
    private LogisticsTrailFacade logisticsTrailFacade;

    /**
     * 获取 物流信息queryTrails
     * busModule : 1-销售单 、2-采购单
     * @return
     */
    @PostMapping("queryTrails/{busModule}")
    public Result queryTrails(LogisticsTrailParam param, @PathVariable("busModule") Integer busModule) {
        Result result = Result.responseSuccess();
        boolean b = null == param || ConvertUtils.isEmpty(param.getFreightCode()) || ConvertUtils.isEmpty(param.getLogisticCompanyCode());
        if(b) {
            result.setCode(400);
            result.setMsg("请选择要查询物流信息的数据!");
            return result;
        }
        param.setBusModule(busModule);
        result = logisticsTrailFacade.queryLogisticsTrails(param);
        return result;
    }
}
