package com.exx.dzj.controller.company;

import com.exx.dzj.facade.sys.InsuranceCompanyFacade;
import com.exx.dzj.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author
 * @Date 2019/7/17 0017 17:43
 * @Description  保险公司
 */
@RestController
@RequestMapping("sys/insurance/company/")
public class InsuranceCompanyController {

    @Autowired
    private InsuranceCompanyFacade companyFacade;

    /**
     * 查询  保险公司下拉框数据
     * @return
     */
    @GetMapping("queryList")
    public Result queryList(@RequestParam(value = "companyName", required = false) String companyName) {
        Result result = Result.responseSuccess();
        result.setData(companyFacade.queryList(companyName));
        return result;
    }
}
