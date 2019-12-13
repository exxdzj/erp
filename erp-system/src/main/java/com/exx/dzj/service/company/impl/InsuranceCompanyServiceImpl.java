package com.exx.dzj.service.company.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.enums.SqlLike;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.exx.dzj.entity.company.InsuranceCompanyDO;
import com.exx.dzj.mapper.company.InsuranceCompanyMapper;
import com.exx.dzj.service.company.InsuranceCompanyService;
import com.exx.dzj.util.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author
 * @Date 2019/7/17 0017 17:20
 * @Description
 */
@Component
public class InsuranceCompanyServiceImpl
        extends ServiceImpl<InsuranceCompanyMapper, InsuranceCompanyDO>
        implements InsuranceCompanyService {

    @Autowired
    private InsuranceCompanyMapper companyMapper;

    /**
     * 获取保险公司数据
     * @return
     */
    @Override
    public List<InsuranceCompanyDO> list(String companyName) {
        QueryWrapper<InsuranceCompanyDO> queryWrapper = new QueryWrapper<>();
        // 指定要查询的列
        queryWrapper.select("company_code","company_name")
                .eq("is_enable", 1)
                .orderByDesc("seq");
        if(ConvertUtils.isNotEmpty(companyName)) {
            queryWrapper.like("company_name", companyName);
        }

        return companyMapper.selectList(queryWrapper);
    }

    @Override
    public InsuranceCompanyDO queryByName(String name) {
        return companyMapper.queryByName(name);
    }
}
