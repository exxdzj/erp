package com.exx.dzj.mapper.user;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.exx.dzj.entity.user.*;
import org.apache.ibatis.annotations.Param;
import org.apache.poi.ss.formula.functions.T;

import java.util.List;

public interface UserInfoMapper extends BaseMapper<UserInfo> {
    long countByExample(UserInfoExample example);

    int insertSelective(UserInfo record);

    List<UserInfo> selectByExample(UserInfoExample example);

    UserVo queryUserInfo(UserInfo record);

    UserInfo selectByPrimaryKey(String userCode);

    int updateByExampleSelective(@Param("record") UserInfo record, @Param("example") UserInfoExample example);

    int updateByPrimaryKeySelective(UserInfo record);

    List<UserInfo> querySalesman();

    List<UserModel> queryUserList(UserQuery query);

    List<UserModel> queryList(@Param("ew") Wrapper<T> queryWrapper);

    UserVo queryUserBean(UserInfo info);

    List<String> queryUserRoleList(@Param("userCode") String userCode);

    List<String> queryRoleNames(@Param("userCode") String userCode);

    List<UserModel> selectionUserInfo();

    List<UserInfo> querySalesmanList(@Param("type") Integer type);

    String querySalesmanDeptCode(@Param("salesmanCode") String salesmanCode);

    public List<String> querySalesmanDeptCode2(String salesmanCode);
}