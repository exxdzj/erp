package com.exx.dzj.service.user;

/**
 * @Author
 * @Date 2019/1/8 0008 15:54
 * @Description user token service
 */
public interface UserTokenService {

    /**
     * 保存 user token
     * @param userCode
     * @param userToken
     */
    void saveUserToken(String userCode, String userToken);

    /**
     * 根据 user token 获取 user code
     * @return
     */
    String queryUserCodeForToken(String userToken);

    /**
     * 生成 user token
     * @param userCode
     * @return
     */
    String getUserToken(String userCode);
}
