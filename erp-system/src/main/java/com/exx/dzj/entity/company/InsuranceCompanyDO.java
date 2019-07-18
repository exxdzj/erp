package com.exx.dzj.entity.company;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author
 * @Date 2019/7/17 0017 17:09
 * @Description 保险公司 Model
 */
@Data
@TableName("tab_insurance_company")
public class InsuranceCompanyDO implements Serializable {

    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    private String companyCode;
    private String companyName;
    private Integer seq;
    private Integer isEnable;
    private Date createTime;

}
