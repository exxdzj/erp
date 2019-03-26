package com.exx.dzj.service.contactway;

import com.exx.dzj.entity.contactway.ContactWayBean;

import java.util.List;

/**
 * @Author 天刀
 * @Date 2019/1/5 0005 16:58
 * @Description 联系信息 service
 */
public interface ContactWayService {

    /**
     * 保存 联系信息
     * @param bean
     */
    void saveContactWay(ContactWayBean bean);

    /**
     * 修改 联系信息
     * @param bean
     */
    int modifyContactWay(ContactWayBean bean);


    void batchContactWay(List<ContactWayBean> contactWayList);
}
