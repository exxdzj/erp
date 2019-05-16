package com.exx.dzj.service.user;

import com.exx.dzj.entity.user.*;
import com.exx.dzj.result.Result;

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
     * @param bean
     */
    String saveSalesman(UserVo bean);

    /**
     * 查询 用户详细信息
     * @param info
     * @return
     */
    UserVo queryUserInfo(UserInfo info);

    /**
     * 查询 用户信息(包括角色和部门信息)
     * @param userCode
     * @return
     */
    UserVo queryUserBean(String userCode);

    /**
     * 查询用户(公司员工)列表数据
     * @param pageNum
     * @param pageSize
     * @param query
     * @return
     */
    Result list(int pageNum, int pageSize, UserQuery query);

    /**
     * 检查 用户账号是否被注册
     * @param userName
     * @return
     */
    Result checkUserName(String userName);

    /**
     * 判断当前的业务编码是否有人使用
     * @param salesmanCode
     * @return
     */
    Result checkSalesmanCode(String salesmanCode);

    /**
     * 用户 离职操作
     * @param record
     * @return
     */
    Result quitUser(UserInfo record);

    public List<UserModel> selectionUserInfo ();

    List<UserInfo> querySalesmanList();
}
