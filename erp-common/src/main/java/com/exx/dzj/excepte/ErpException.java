package com.exx.dzj.excepte;

import com.exx.dzj.result.Result;

/**
 * @Author
 * @Date 2019/1/7 0007 11:53
 * @Description
 */
public class ErpException extends RuntimeException{
    /** 异常的状态码 **/
    private Integer code;

    /** 异常的状态描述 **/
    private String message;

    public ErpException(Integer code,String message){
        this.code=code;
        this.message = message;
    }

    public Result returnResult(){
        Result result = new Result();
        result.setCode(code);
        result.setMsg(message);
        return result;
    }
}
