package com.exx.dzj.controller.sys;

import com.exx.dzj.facade.sys.BusEncodeFacade;
import com.exx.dzj.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author
 * @Date 2019/6/1 0001 14:24
 * @Description 业务数据编码
 */
@RestController
@RequestMapping("busEncode/")
public class BusEncodeController {

    @Autowired
    private BusEncodeFacade busEncodeFacade;

    /**
     * 获取 业务编码前缀
     * @param busType
     * @return
     */
    @GetMapping("queryBusEncode")
    public Result queryBusEncode(String busType) {
        Result result = Result.responseSuccess();
        result.setData(busEncodeFacade.queryBusEncode(busType));
        return result;
    }
}
