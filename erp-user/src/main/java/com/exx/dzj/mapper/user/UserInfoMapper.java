package com.exx.dzj.mapper.user;

import com.exx.dzj.entity.user.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserInfoMapper {
    long countByExample(UserInfoExample example);

    int deleteByExample(UserInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UserInfo record);

    int insertSelective(UserInfo record);

    List<UserInfo> selectByExample(UserInfoExample example);

    UserVo queryUserInfo(UserInfo record);

    UserInfo selectByPrimaryKey(String userCode);

    int updateByExampleSelective(@Param("record") UserInfo record, @Param("example") UserInfoExample example);

    int updateByExample(@Param("record") UserInfo record, @Param("example") UserInfoExample example);

    int updateByPrimaryKeySelective(UserInfo record);

    int updateByPrimaryKey(UserInfo record);

    List<UserInfo> querySalesman();

    List<UserModel> queryUserList(UserQuery query);

    UserVo queryUserBean(UserInfo info);

    List<String> queryUserRoleList(@Param("userCode") String userCode);

    public List<UserModel> selectionUserInfo();
}