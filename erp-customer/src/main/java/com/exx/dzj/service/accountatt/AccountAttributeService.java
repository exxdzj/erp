package com.exx.dzj.service.accountatt;

import com.exx.dzj.entity.accountatt.AccountAttributeBean;

import java.util.List;

/**
 * @Author 天刀
 * @Date 2019/1/5 0005 17:00
 * @Description 会计属性 service
 */
public interface AccountAttributeService {

    /**
     * 保存 会计属性信息
     * @param bean
     */
    void saveAccountAttribute(AccountAttributeBean bean);

    /**
     * 修改 会计数据信息
     * @param bean
     */
    int modifyAccountAttribute(AccountAttributeBean bean);

    void batchAccountAttribute (List<AccountAttributeBean> attributeList);
}
