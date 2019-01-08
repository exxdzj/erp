package com.exx.dzj.service.user;

import com.exx.dzj.entity.user.UserInfo;

import java.util.List;

/**
 * @Author
 * @Date 2019/1/5 0005 9:39
 * @Description 用户(公司业务员) service
 */
public interface UserService {

    /**
     * 查询 业务员列表
     * @return
     */
    List<UserInfo> querySalesman();

    /**
     * 保存 业务员信息
     * @param info
     */
    void saveSalesman(UserInfo info);

    /**
     * 查询 用户详细信息
     * @param userCode
     * @return
     */
    UserInfo queryUserInfo(String userCode);
}
