package com.exx.dzj.service.user.impl;

import com.exx.dzj.entity.user.UserTokenBean;
import com.exx.dzj.mapper.user.UserTokenMapper;
import com.exx.dzj.service.user.UserTokenService;
import com.exx.dzj.unique.DefaultIdGenerator;
import com.exx.dzj.unique.IdGenerator;
import com.exx.dzj.util.TokenBuilder;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author
 * @Date 2019/1/8 0008 16:03
 * @Description user token service
 */
@Service("tokenService")
public class UserTokenServiceImpl implements UserTokenService {

    private final static Logger LOGGER = LoggerFactory.getLogger(UserTokenServiceImpl.class);

    @Autowired
    private UserTokenMapper userTokenMapper;

    /**
     * 通过 user token 获取 user Code
     * @return
     */
    @Override
    public String queryUserCodeForToken(String userToken) {
        return userTokenMapper.queryUserCodeForToken(userToken);
    }

    /**
     * 获取 user token
     * @param userCode
     * @return
     */
    @Override
    public String getUserToken(String userCode) {
        try{
            IdGenerator idGenerator = new DefaultIdGenerator();

            Map<String, Object> param = new HashMap();
            param.put("userCode", userCode);
            //判断用户 token 是否过期
            UserTokenBean tokenBean = userTokenMapper.queryUserToken(param);
            if(null != tokenBean){
                return tokenBean.getUserToken();
            }
            tokenBean = new UserTokenBean();
            String userToken = getNextToken();
            tokenBean.setId(idGenerator.next());
            tokenBean.setUserCode(userCode);
            tokenBean.setUserToken(userToken);
            userTokenMapper.saveSelective(tokenBean);
            return userToken;
        }catch(Exception ex){
            LOGGER.error("异常方法:{}异常信息:{}", UserTokenServiceImpl.class.getName()+".getUserToken", ex.getMessage());
            return null;
        }
    }

    /**
     * 生成 user token
     * @return
     */
    private String getNextToken(){
        TokenBuilder.TokenCreator tokenCreator = TokenBuilder.uuidCreator;
        String token = tokenCreator.create();
        if(!StringUtils.isNotBlank(token) || token.length() < 28 || token.length() > 32){
            getNextToken();
        }
        return token;
    }
}
