package com.exx.dzj.facade.sys;

import com.exx.dzj.entity.company.InsuranceCompanyDO;
import com.exx.dzj.service.company.InsuranceCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author
 * @Date 2019/7/17 0017 17:26
 * @Description 保险公司
 */
@Component
public class InsuranceCompanyFacade {

    @Autowired
    private InsuranceCompanyService companyService;

    /**
     * 保险公司
     * @return
     */
    public List<InsuranceCompanyDO> queryList(String companyName) {
        return companyService.list(companyName);
    }

}
