package com.exx.dzj.entity.bean;

import com.exx.dzj.entity.user.UserInfo;
import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * @author yangyun
 * @create 2019-04-19-15:47
 */
@Data
@ToString(callSuper = true)
public class UserInfoQuery extends UserInfo {

    private static final long serialVersionUID = -1249941446641828856L;
    private String startDate;
    private String endDate;
    private Integer businessType; // 业务类型 1 销货 2 退货
//    private Integer gross; // 是否计算毛利 1 是 0否

    private List<String> userCodeList;
}
