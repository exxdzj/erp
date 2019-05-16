package com.exx.dzj.service.login.impl;

import com.exx.dzj.annotation.SysLog;
import com.exx.dzj.constant.LogLevel;
import com.exx.dzj.constant.LogType;
import com.exx.dzj.entity.login.LoginInfo;
import com.exx.dzj.entity.user.UserInfo;
import com.exx.dzj.entity.user.UserVo;
import com.exx.dzj.mapper.user.UserInfoMapper;
import com.exx.dzj.result.Result;
import com.exx.dzj.service.login.LoginService;
import com.exx.dzj.service.user.UserTokenService;
import com.exx.dzj.util.ConvertUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author
 * @Date 2019/1/8 0008 15:31
 * @Description
 */
@Service("logonService")
public class LoginServiceImpl implements LoginService {

    private final static Logger LOGGER = LoggerFactory.getLogger(LoginServiceImpl.class);

    @Autowired
    private UserInfoMapper userMapper;

    @Autowired
    private UserTokenService tokenService;

    /**
     * 用户登录
     * @param loginInfo
     * @return 返回 userToken, 前端获取之后,将 userToken 放到 header 域中
     */
    @Override
    @SysLog(operate = "登录", logType = LogType.LOG_TYPE_LOGIN, logLevel = LogLevel.LOG_LEVEL_INFO)
    public Result signIn(LoginInfo loginInfo){
        Result result = Result.responseSuccess();

        //首先判断用户是否存在，用户名和密码是否错误
        UserInfo userInfo = new UserInfo();
        userInfo.setUserName(loginInfo.getUsername());
        userInfo.setPassWord(loginInfo.getPassword());
        UserVo userVo = userMapper.queryUserInfo(userInfo);
        if(null == userVo){
            result.setCode(400);
            result.setMsg("用户或密码错误!");
            return result;
        }

        tokenService.saveUserToken(userVo.getUserCode(), loginInfo.getUsertoken());

        //用户存在, 则生成 userToken, 并保存到数据库
        /*String userToken = tokenService.getUserToken(userVo.getUserCode());
        if(StringUtils.isNotBlank(userToken)){
            //loginInfo.setUsertoken(userToken);
            userVo.setUserToken(userToken);
        }*/
        userVo.setUserToken(loginInfo.getUsertoken());
        if(ConvertUtils.isEmpty(userVo.getHeadImg())) {
            // 如果头像为空，则设置一个默认头像(此处暂时写死)
            userVo.setHeadImg("https://exx-erp.oss-cn-shenzhen.aliyuncs.com/employee-images/prod/erpdefualtheadimg.png");
        }
        result.setData(userVo);
        return result;
    }

    /**
     * 用户退出登录
     * @return
     */
    @Override
    public Result loginOut(String userToken){
        Result result = Result.responseSuccess();
        Map<String, Object> param = new HashMap<>();
        param.put("userToken", userToken);
        tokenService.loginOut(param);
        return result;
    }
}
