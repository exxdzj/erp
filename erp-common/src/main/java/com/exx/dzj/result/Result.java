package com.exx.dzj.result;

import lombok.Data;

/**
 * @Author
 * @Date 2019/1/7 0007 9:05
 * @Description
 */
@Data
public class Result {
    public static int success_code = 200;
    public static String success_msg="操作成功";

    /** 客户端提示状态 */
    private int code;
    /** 是否对结果进行了加密(0未加密,1已加密) */
    private int is_encrypt;

    /** 客户端提示信息 */
    private String msg;
    /** 客户端返回的数据 */
    private Object data;

    /**
     * 响应成功
     * @return
     */
    public static Result responseSuccess(){
        Result result = new Result();
        result.setCode(success_code);
        result.setMsg(success_msg);
        return result;
    }

    /**
     * 缺少参数
     * @return
     */
    public static Result paramLost(){
        Result result = new Result();
        result.setCode(ConstantStatus.PARAM_LOST);
        result.setMsg(ConstantStatus.MSG_PARAM_LOST);
        return result;
    }

    /**
     * 缺少参数
     * @return
     */
    public static Result paramLost(String msg){
        Result result = new Result();
        result.setCode(ConstantStatus.PARAM_LOST);
        result.setMsg(msg);
        return result;
    }

    /**
     * 新建
     * @return
     */
    public static Result create(){

        return new Result();
    }

    public static Result paramInvalid(){
        Result result = new Result();
        result.setCode(ConstantStatus.PARAM_INVALID);
        result.setMsg(ConstantStatus.MSG_PARAM_INVALID);
        return result;
    }

    public static Result paramInvalid(String msg){
        Result result = new Result();
        result.setCode(ConstantStatus.PARAM_INVALID);
        result.setMsg(msg);
        return result;
    }

    /**
     * 响应错误
     * @return
     */
    public static Result responseError(){
        Result result = new Result();
        result.setCode(ConstantStatus.RESPONSE_ERROR);
        result.setMsg(ConstantStatus.MSG_RESPONSE_ERROR);
        return result;
    }

    public static Result responseError(String msg){
        Result result = new Result();
        result.setCode(ConstantStatus.RESPONSE_ERROR);
        result.setMsg(msg);
        return result;
    }
}
