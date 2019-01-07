package com.exx.dzj.result;

/**
 * @Author
 * @Date 2019/1/7 0007 9:07
 * @Description
 */
public class ConstantStatus {
    //执行成功
    public static final Integer SUCCESS_CODE = 200;

    /**
     * 缺少参数
     */
    public static final Integer PARAM_LOST = 101;
    public static final String MSG_PARAM_LOST = "参数缺失";

    /**
     * 无效参数
     */
    public static final Integer PARAM_INVALID = 102;
    public static final String MSG_PARAM_INVALID = "参数异常";

    /** 响应失败*/
    public static final Integer RESPONSE_ERROR = 1;
    public static final String MSG_RESPONSE_ERROR = "请求失败，请重试";
}
