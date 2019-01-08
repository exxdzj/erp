package com.exx.dzj.mapper.user;

import com.exx.dzj.entity.user.UserTokenBean;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * @Author
 * @Date 2019/1/8 0008 16:05
 * @Description
 */
public interface UserTokenMapper {

    int saveSelective(UserTokenBean bean);

    String queryUserCodeForToken(@Param("userToken") String userToken);

    UserTokenBean queryUserToken(Map<String, Object> param);
}
