package com.exx.dzj.constant;

/**
 * @Author
 * @Date 2019/5/15 0015 17:56
 * @Description
 */
public enum LogType {

    /*********************日志类型*********************/
    // 登录日志
    LOG_TYPE_LOGIN(1),
    // 操作日志
    LOG_TYPE_OPERATE(2),
    // 定时任务
    LOG_TYPE_CRON_JOB(3);

    private int value;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    LogType(int s) {
        this.value = s;
    }
}
