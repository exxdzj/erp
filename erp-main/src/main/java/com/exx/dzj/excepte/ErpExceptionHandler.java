package com.exx.dzj.excepte;

import com.exx.dzj.result.Result;
import com.exx.dzj.shiro.excepte.AuthException;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * @Author
 * @Date 2019/5/8 0008 14:46
 * @Description 自定义异常处理
 */
@Slf4j
@RestControllerAdvice
public class ErpExceptionHandler {

    @ExceptionHandler(NoHandlerFoundException.class)
    public Result handlerNoFoundException(Exception e) {
        log.error(e.getMessage(), e);
        return Result.error(404, "路径不存在，请检查路径是否正确");
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public Result handleDuplicateKeyException(DuplicateKeyException e){
        log.error(e.getMessage(), e);
        return Result.error(500, "数据库中已存在该记录");
    }

    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e){
        log.error(e.getMessage(), e);
        return Result.error(500, e.getMessage());
    }

    @ExceptionHandler(AuthorizationException.class)
    public Result handleAuthorizationException(AuthorizationException e){
        log.error(e.getMessage(), e);
        return Result.error(1001,"没有权限，请联系管理员授权");
    }

    @ExceptionHandler(AuthException.class)
    public Result handleAuthException(AuthException e){
        log.error(e.getMessage(), e);
        return Result.error(e.getCode(),e.getMessage());
    }
}
