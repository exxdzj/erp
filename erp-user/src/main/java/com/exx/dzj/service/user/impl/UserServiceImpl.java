package com.exx.dzj.service.user.impl;

import com.exx.dzj.entity.user.UserInfo;
import com.exx.dzj.excepte.ErpException;
import com.exx.dzj.mapper.user.UserInfoMapper;
import com.exx.dzj.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author
 * @Date 2019/1/7 0007 15:13
 * @Description 用户(业务员) service
 */
@Service("salesmanService")
public class UserServiceImpl implements UserService {

    private final static Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserInfoMapper userMapper;

    /**
     * 查询 业务员列表
     * @return
     */
    @Override
    public List<UserInfo> querySalesman() {
        return userMapper.querySalesman();
    }

    /**
     * 保存 业务员信息
     * @param info
     */
    @Override
    public void saveSalesman(UserInfo info) throws ErpException{
        try{
            info.setPassWord("exx88dzj");
            userMapper.insertSelective(info);
        }catch(Exception ex){
            LOGGER.error("异常方法:{}异常信息:{}", UserServiceImpl.class.getName()+".saveSalesman", ex.getMessage());
            throw new ErpException(400, "保存业务员信息失败!");
        }
    }

    /**
     * 查询 用户详细信息
     * @param userCode
     * @return
     */
    @Override
    public UserInfo queryUserInfo(String userCode){
        return userMapper.selectByPrimaryKey(userCode);
    }
}
