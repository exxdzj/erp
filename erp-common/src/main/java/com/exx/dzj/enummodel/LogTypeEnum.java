package com.exx.dzj.enummodel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author
 * @Date 2019/5/15 0015 15:19
 * @Description 系统日志类型
 */
public enum LogTypeEnum {

    LOG_TYPE_LOGIN("1", "登录日志"),
    LOG_TYPE_OPERATE("2", "操作日志"),
    LOG_TYPE_CRON_JOB("3", "定时任务");

    private final String key;
    private final String value;

    private LogTypeEnum(String key, String value) {
        this.key = key;
        this.value = value;
    }

    /**
     * @功能: 获取枚举类
     * @param key
     * @return
     */
    public static LogTypeEnum getEnum(String key) {
        LogTypeEnum[] arry = LogTypeEnum.values();
        for (int i = 0; i < arry.length; i++) {
            if (arry[i].name().equalsIgnoreCase(key)) {
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
        LogTypeEnum[] ary = LogTypeEnum.values();
        Map<String, Map<String, Object>> enumMap = new HashMap<String, Map<String, Object>>();
        for (int num = 0; num < ary.length; num++) {
            Map<String, Object> map = new HashMap<String, Object>();
            String key = ary[num].name();
            map.put("desc", ary[num].getValue());
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
        LogTypeEnum[] ary = LogTypeEnum.values();
        List list = new ArrayList();
        for (int i = 0; i < ary.length; i++) {
            Map<String, String> map = new HashMap<String, String>();
            map.put("desc", ary[i].getValue());
            map.put("code", ary[i].name());
            list.add(map);
        }
        return list;
    }

    /**
     * @功能: 根据相关条件获取枚举类，并将枚举类转为 map 放入到 list 中
     * @param key
     * @return
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static List getWayList(String key) {
        LogTypeEnum[] ary = LogTypeEnum.values();
        List list = new ArrayList();
        for (int i = 0; i < ary.length; i++) {
            if(ary[i].key.equals(key)){
                Map<String, String> map = new HashMap<String, String>();
                map.put("desc", ary[i].getValue());
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
        LogTypeEnum[] enums = LogTypeEnum.values();
        StringBuffer jsonStr = new StringBuffer("[");
        for (LogTypeEnum senum : enums) {
            if (!"[".equals(jsonStr.toString())) {
                jsonStr.append(",");
            }
            jsonStr.append("{code:'").append(senum).append("',desc:'").append(senum.getValue()).append("'}");
        }
        jsonStr.append("]");
        return jsonStr.toString();
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}
