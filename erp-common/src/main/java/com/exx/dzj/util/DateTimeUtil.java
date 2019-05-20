package com.exx.dzj.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Date;

/**
 * @Author
 * @Date 2019/5/20 0020 14:30
 * @Description 线程安全的日期时间和字符串互转工具类
 */
@Slf4j
public class DateTimeUtil {

    public static final String STANDARD_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * 将日期字符串转为 date 类型
     * @param dateTimeStr
     * @param formatStr
     * @return
     */
    public static Date strToDate(String dateTimeStr,String formatStr){
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern(formatStr);
        DateTime dateTime = dateTimeFormatter.parseDateTime(dateTimeStr);
        return dateTime.toDate();
    }

    /**
     * 将日期字符串转为 date 类型
     * @param dateTimeStr
     * @return
     */
    public static Date strToDate(String dateTimeStr){
        try {
            DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern(STANDARD_FORMAT);
            DateTime dateTime = dateTimeFormatter.parseDateTime(dateTimeStr);
            return dateTime.toDate();
        } catch(Exception ex) {
            log.error("日期转换失败!执行方法{},异常信息{}", DateTimeUtil.class.getName()+".strToDate", ex.getMessage());
            return null;
        }
    }

    public static String dateToStr(Date date,String formatStr){
        if(date == null){
            return StringUtils.EMPTY;
        }
        DateTime dateTime = new DateTime(date);
        return dateTime.toString(formatStr);
    }

    public static String dateToStr(Date date){
        if(date == null){
            return StringUtils.EMPTY;
        }
        DateTime dateTime = new DateTime(date);
        return dateTime.toString(STANDARD_FORMAT);
    }
}
