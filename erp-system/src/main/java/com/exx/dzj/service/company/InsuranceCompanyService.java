package com.exx.dzj.service.company;

import com.baomidou.mybatisplus.extension.service.IService;
import com.exx.dzj.entity.company.InsuranceCompanyDO;

import java.util.List;

/**
 * @Author
 * @Date 2019/7/17 0017 17:18
 * @Description  保险公司
 */
public interface InsuranceCompanyService extends IService<InsuranceCompanyDO> {

    List<InsuranceCompanyDO> list(String companyName);

    InsuranceCompanyDO queryByName(String name);
}