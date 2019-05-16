package com.exx.dzj.enummodel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author
 * @Date 2019/5/15 0015 14:54
 * @Description 日志级别
 */
public enum LogLevelEnum {

    ERROR("error", "错误"),
    WARN("warn", "警告"),
    INFO("info", "信息"),
    DEBUG("debug", "调试");

    private final String levelCode;
    private final String levelInfo;

    private LogLevelEnum(String levelCode, String levelInfo) {
        this.levelCode = levelCode;
        this.levelInfo = levelInfo;
    }

    /**
     * @功能: 获取枚举类
     * @param levelCode
     * @return
     */
    public static LogLevelEnum getEnum(String levelCode) {
        LogLevelEnum[] arry = LogLevelEnum.values();
        for (int i = 0; i < arry.length; i++) {
            if (arry[i].name().equalsIgnoreCase(levelCode)) {
                return arry[i];
            }
        }
        return null;
    }

    /**
     * @功能: 将枚举类型转为 map
     * @return
     */
    public static Map<String, Map<String, Object>> toMap() {
        LogLevelEnum[] ary = LogLevelEnum.values();
        Map<String, Map<String, Object>> enumMap = new HashMap<String, Map<String, Object>>();
        for (int num = 0; num < ary.length; num++) {
            Map<String, Object> map = new HashMap<String, Object>();
            String key = ary[num].name();
            map.put("desc", ary[num].getLevelInfo());
            enumMap.put(key, map);
        }
        return enumMap;
    }

    /**
     * @功能: 将枚举类型转为 list
     * @return
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static List toList() {
        LogLevelEnum[] ary = LogLevelEnum.values();
        List list = new ArrayList();
        for (int i = 0; i < ary.length; i++) {
            Map<String, String> map = new HashMap<String, String>();
            map.put("desc", ary[i].getLevelInfo());
            map.put("code", ary[i].name());
            list.add(map);
        }
        return list;
    }

    /**
     * @功能: 根据相关条件获取枚举类，并将枚举类转为 map 放入到 list 中
     * @param levelCode
     * @return
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static List getWayList(String levelCode) {
        LogLevelEnum[] ary = LogLevelEnum.values();
        List list = new ArrayList();
        for (int i = 0; i < ary.length; i++) {
            if(ary[i].levelCode.equals(levelCode)){
                Map<String, String> map = new HashMap<String, String>();
                map.put("desc", ary[i].getLevelInfo());
                map.put("code", ary[i].name());
                list.add(map);
            }
        }
        return list;
    }

    /**
     * @功能: 取枚举的json字符串
     * @return
     */
    public static String getJsonStr() {
        LogLevelEnum[] enums = LogLevelEnum.values();
        StringBuffer jsonStr = new StringBuffer("[");
        for (LogLevelEnum senum : enums) {
            if (!"[".equals(jsonStr.toString())) {
                jsonStr.append(",");
            }
            jsonStr.append("{code:'").append(senum).append("',desc:'").append(senum.getLevelInfo()).append("'}");
        }
        jsonStr.append("]");
        return jsonStr.toString();
    }

    public String getLevelCode() {
        return levelCode;
    }

    public String getLevelInfo() {
        return levelInfo;
    }
}
