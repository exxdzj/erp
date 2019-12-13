package com.exx.dzj.mapper.company;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.exx.dzj.entity.company.InsuranceCompanyDO;
import org.apache.ibatis.annotations.Param;

/**
 * @Author
 * @Date 2019/7/17 0017 17:52
 * @Description
 */
public interface InsuranceCompanyMapper extends BaseMapper<InsuranceCompanyDO> {

    InsuranceCompanyDO queryByName(@Param("name") String name);
}
