package com.exx.dzj.constant;

/**
 * @Author
 * @Date 2019/5/15 0015 17:56
 * @Description
 */
public enum LogLevel {

    /*********************日志等级*********************/
    // 错误
    LOG_LEVEL_ERROR("error"),
    // 警告
    LOG_LEVEL_WARN("warn"),
    // 信息
    LOG_LEVEL_INFO("info"),
    // 调试
    LOG_LEVEL_DEBUG("debug");

    String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    LogLevel(String value) {
        this.value = value;
    }
}
