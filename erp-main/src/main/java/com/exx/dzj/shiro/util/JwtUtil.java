package com.exx.dzj.shiro.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.exx.dzj.constant.DataBaseConstant;
import com.exx.dzj.entity.user.UserInfo;
import com.exx.dzj.excepte.ErpException;
import com.exx.dzj.util.ConvertUtils;
import com.exx.dzj.util.DataAutorUtils;
import com.exx.dzj.util.SpringContextUtils;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * @Author
 * @Date 2019/5/7 0007 10:07
 * @Description JWT工具类
 */
@Slf4j
public class JwtUtil {
    /**
     * 过期时间24小时
     */
    public static final long EXPIRE_TIME = 12 * 60 * 60 * 1000;

    /**
     * 校验token是否正确
     * @param token  密钥
     * @param secret 用户的密码
     * @return 是否正确
     */
    public static boolean verify(String token, String username, String secret) {
        try {
            // 根据密码生成JWT效验器
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm).withClaim("userName", username).build();
            // 效验TOKEN
            DecodedJWT jwt = verifier.verify(token);
            return true;
        } catch (Exception ex) {
            log.error("调用方法{}错误信息{}", JwtUtil.class.getName()+".verify", ex.getMessage());
            return false;
        }
    }

    /**
     * 获得token中的信息无需secret解密也能获得
     * @return token中包含的用户名
     */
    public static String getUserName(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("userName").asString();
        } catch (JWTDecodeException e) {
            log.error("调用方法{}错误信息{}", JwtUtil.class.getName()+".getUserName", e.getMessage());
            return null;
        }
    }

    /**
     * 生成签名, 24小时后过期
     * @param username 用户名
     * @param secret   用户的密码
     * @return 加密的token
     */
    public static String sign(String username, String secret) {
        Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        Algorithm algorithm = Algorithm.HMAC256(secret);
        // 附带username信息
        return JWT.create().withClaim("userName", username).withExpiresAt(date).sign(algorithm);

    }

    /**
     * 根据request中的token获取用户账号
     * @param request
     * @return
     * @throws ErpException
     */
    public static String getUserNameByToken(HttpServletRequest request) throws ErpException {
        String accessToken = request.getHeader("user-token");
        String userName = getUserName(accessToken);
        if (ConvertUtils.isEmpty(userName)) {
            throw new ErpException(400, "未获取到用户");
        }
        return userName;
    }

    /**
     * 从 session 中获取变量
     * @param key
     * @return
     */
    public static String getSessionData(String key) {
        //${myVar}%
        //得到${} 后面的值
        String moshi = "";
        if(key.indexOf("}")!=-1){
            moshi = key.substring(key.indexOf("}")+1);
        }
        String returnValue = null;
        if (key.contains("#{")) {
            key = key.substring(2,key.indexOf("}"));
        }
        if (ConvertUtils.isNotEmpty(key)) {
            HttpSession session = SpringContextUtils.getHttpServletRequest().getSession();
            returnValue = (String) session.getAttribute(key);
        }
        //结果加上${} 后面的值
        if(returnValue!=null){returnValue = returnValue + moshi;}
        return returnValue;
    }

    /**
     * 从当前用户中获取变量
     * @param key
     * @param user
     * @return
     */
    public static String getUserSystemData(String key, UserInfo user) {
        if(user==null) {
            user = DataAutorUtils.loadUserInfo();
        }
        //#{user_code}%
        String moshi = "";
        if(key.indexOf("}")!=-1){
            moshi = key.substring(key.indexOf("}")+1);
        }
        String returnValue = null;
        //针对特殊标示处理#{deptCode}，判断替换
        if (key.contains("#{")) {
            key = key.substring(2,key.indexOf("}"));
        } else {
            key = key;
        }
        // 登录用户帐号
        if (key.equals(DataBaseConstant.USER_CODE)|| key.equals(DataBaseConstant.USER_CODE_TABLE)) {
            returnValue = user.getUserCode();
        }
        // 用户登录所使用的机构编码
        if (key.equals(DataBaseConstant.DEPT_CODE)|| key.equals(DataBaseConstant.DEPT_CODE_TABLE)) {
            returnValue = user.getDeptCode();
        }
        // 用户所属结构编码
        if (key.equals(DataBaseConstant.ORG_CODE)|| key.equals(DataBaseConstant.ORG_CODE_TABLE)) {
            returnValue = user.getOrgCode();
        }

        if(returnValue!=null){
            returnValue = returnValue + moshi;
        }
        return returnValue;
    }
}
