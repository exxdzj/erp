package com.exx.dzj.controller.newyeargoods;

import com.exx.dzj.entity.newyeargoods.NewGiftDeatilBean;
import com.exx.dzj.entity.newyeargoods.NewGiftMainBean;
import com.exx.dzj.facade.newyeargoods.NewYearGoodsFacade;
import com.exx.dzj.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @ClassName NewYearGoodsController
 * @Description:
 * @Author yangyun
 * @Date 2019/10/9 0009 10:13
 * @Version 1.0
 **/
@RestController
@RequestMapping("newyeargoods/")
public class NewYearGoodsController {

    @Autowired
    private NewYearGoodsFacade newYearGoodsFacade;

    /**
     * @description: 列表数据, 总数据
     * @author yangyun
     * @date 2019/10/9 0009
     * @param
     * @return com.exx.dzj.result.Result
     */
    @GetMapping("query2020newyearlist")
    public Result query2020NewYearList (){
        Result result = Result.responseSuccess();
        Map<String, Object> data = newYearGoodsFacade.query2020NewYearList();
        result.setData(data);
        return result;
    }
}
