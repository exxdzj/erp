package com.exx.dzj.shiro.excepte;

import lombok.Data;
import org.apache.shiro.authc.AuthenticationException;

/**
 * @Author
 * @Date 2019/5/8 0008 9:07
 * @Description
 */
@Data
public class AuthException extends AuthenticationException {
    /** 异常的状态码 **/
    private Integer code;

    /** 异常的状态描述 **/
    private String message;

    public AuthException(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
