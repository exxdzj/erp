package com.exx.dzj.result;

import lombok.Data;

/**
 * @Author
 * @Date 2019/1/7 0007 9:05
 * @Description
 */
@Data
public class Result {
    public static int SUCCESS_CODE = 200;
    public static int FAIL_CODE = 400;
    public static String SUCCESS_MSG="操作成功";
    public static String FAIL_MSG = "操作失败";

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
        result.setCode(SUCCESS_CODE);
        result.setMsg(SUCCESS_MSG);
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

    public static Result error(int code, String msg) {
        Result r = new Result();
        r.setCode(code);
        r.setMsg(msg);
        return r;
    }
}
