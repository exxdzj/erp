package com.exx.dzj.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @author yangyun
 * @create 2019-04-17-10:53
 */
public class DateUtil {
    private static Log log = LogFactory.getLog(DateUtil.class);
    private static final String TIME_PATTERN = "HH:mm";

    /**
     * 日期格式 yyyy-MM-dd
     */
    public static final String DATE_PATTERN = "yyyy-MM-dd";
    /**
     * 日期格式 yyyy-MM-dd HH:mm
     */
    public static final String DATE_PATTERN_MM = "yyyy-MM-dd HH:mm";

    /**
     * 日期+时间格式 yyyy-MM-dd HH:mm:ss
     */
    public static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    /**
     * Checkstyle rule: utility classes should not have public constructor
     */
    private DateUtil() {
    }

    /**
     * Return default datePattern (yyyy-MM-dd)
     *
     * @return a string representing the date pattern on the UI
     */
    public static String getDatePattern() {

        return DATE_PATTERN;
    }

    /**
     * Return default datePattern (yyyy-MM-dd HH:mm:ss)
     *
     * @return a string representing the date pattern on the UI
     */
    public static String getDateTimePattern() {

        return DATE_TIME_PATTERN;
    }

    /**
     * Return default datePattern (yyyy-MM-dd HH:mm)
     *
     * @return a string representing the date pattern on the UI
     */
    public static String getDateMMPattern() {

        return DATE_PATTERN_MM;
    }

    /**
     * This method attempts to convert an Oracle-formatted date in the form
     * dd-MMM-yyyy toyyyy-MM-dd.
     *
     * @param aDate date from database as a string
     * @return formatted string for the ui
     */
    public static String getDate(Date aDate) {
        SimpleDateFormat df;
        String returnValue = "";

        if (aDate != null) {
            df = new SimpleDateFormat(getDatePattern());
            returnValue = df.format(aDate);
        }

        return (returnValue);
    }

    public static Date getCurrentDate() {
        return new Date();
    }

    /**
     * This method generates a string representation of a date/time in the format
     * you specify on input
     *
     * @param aMask   the date pattern the string is in
     * @param strDate a string representation of a date
     * @return a converted Date object
     * @throws ParseException when String doesn't match the expected format
     * @see java.text.SimpleDateFormat
     */
    public static Date convertStringToDate(String aMask, String strDate) throws ParseException {
        SimpleDateFormat df;
        Date date;
        df = new SimpleDateFormat(aMask);

        if (log.isDebugEnabled()) {
            log.debug("converting '" + strDate + "' to date with mask '" + aMask + "'");
        }

        try {
            date = df.parse(strDate);
        } catch (ParseException pe) {
            // log.error("ParseException: " + pe);
            throw new ParseException(pe.getMessage(), pe.getErrorOffset());
        }

        return (date);
    }

    /**
     * This method returns the current date time in the format:yyyy-MM-dd HH:MM a
     *
     * @param theTime the current time
     * @return the current date/time
     */
    public static String getTimeNow(Date theTime) {
        return getDateTime(TIME_PATTERN, theTime);
    }

    public static String getToDay(Date theTime) {
        return getDateTime(getDatePattern(), theTime);
    }

    /**
     * This method returns the current date in the format:yyyy-MM-dd
     *
     * @return the current date
     * @throws ParseException when String doesn't match the expected format
     */
    public static Calendar getToday() throws ParseException {
        Date today = new Date();
        SimpleDateFormat df = new SimpleDateFormat(getDatePattern());

        // This seems like quite a hack (date -> string -> date),
        // but it works ;-)
        String todayAsString = df.format(today);
        Calendar cal = new GregorianCalendar();
        cal.setTime(convertStringToDatePattern(todayAsString, getDatePattern()));

        return cal;
    }

    // 把String类型的日期格式转化成对应格式的Date类型
    public static Date convertStringToDatePattern(String sdate, String pattern) throws ParseException {
        SimpleDateFormat df;
        Date date;
        df = new SimpleDateFormat(pattern);
        date = df.parse(sdate);
        return date;
    }

    /**
     * This method generates a string representation of a date's date/time in the
     * format you specify on input
     *
     * @param aMask the date pattern the string is in
     * @param aDate a date object
     * @return a formatted string representation of the date
     * @see java.text.SimpleDateFormat
     */
    public static String getDateTime(String aMask, Date aDate) {
        SimpleDateFormat df = null;
        String returnValue = "";
        if (aDate == null) {
            log.warn("aDate is null!");
        } else {
            df = new SimpleDateFormat(aMask);
            returnValue = df.format(aDate);
        }
        return (returnValue);
    }

    /**
     * This method generates a string representation of a date based on the System
     * Property 'dateFormat' in the format you specify on input
     *
     * @param aDate A date to convert
     * @return a string representation of the date
     */
    public static String convertDateToString(Date aDate) {
        return getDateTime(getDateTimePattern(), aDate);
    }

    /**
     * This method generates a string representation of a date based on the System
     * Property 'dateFormat' in the format you specify on input
     *
     * @param aDate A date to convert
     * @return a string representation of the date
     */
    public static String convertDateToString(Date aDate, String pattern) {
        return getDateTime(pattern, aDate);
    }

    /**
     * This method converts a String to a date using the datePattern
     *
     * @param strDate the date to convert (in format yyyy-MM-dd)
     * @return a date object
     * @throws ParseException when String doesn't match the expected format
     */
    public static Date convertStringToDate(final String strDate) throws ParseException {
        return convertStringToDate(getDateTimePattern(), strDate);
    }

    public static Timestamp getCurrentTimestamp() {
        return new Timestamp(System.currentTimeMillis());
    }

    /**
     * 返回某月的第一天
     *
     * @param date
     * @return
     * @since DBS V100
     */
    public static Date getBeginDateOfCurrentMonth(java.util.Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.DAY_OF_MONTH, 1);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);

        return new Date(c.getTime().getTime());
    }

    /**
     * 返回某天的开始时刻
     *
     * @param date
     * @return
     */
    public static Date getBeginDateOfCurrentDay(java.util.Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        return new Date(c.getTime().getTime());
    }

    /**
     * 返回某天的最后时刻
     *
     * @param date
     * @return
     */
    public static Date getEndDateOfCurrentDay(java.util.Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);
        return new Date(c.getTime().getTime());
    }

    public static Date getYesterday() throws ParseException {
        SimpleDateFormat dft = new SimpleDateFormat(DATE_PATTERN);
        Date beginDate = getCurrentDate();
        Calendar date = Calendar.getInstance();
        date.setTime(beginDate);
        date.set(Calendar.DATE, date.get(Calendar.DATE) - 1);
        Date endDate = dft.parse(dft.format(date.getTime()));
        return endDate;
    }

    /**
     * 日期增加或者减少秒，分钟，天，月，年
     *
     * @param srcDate
     * @param type    类型 Y M D HH MM SS 年月日时分秒
     * @param offset  （整数）
     * @return 增加或者减少之后的日期
     */
    public static java.util.Date addDate(java.util.Date srcDate, String type, int offset) {
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(srcDate);
        if (type.equals("SS")) {
            gc.add(GregorianCalendar.SECOND, offset);
        } else if (type.equals("MM")) {
            gc.add(GregorianCalendar.MINUTE, offset);
        } else if (type.equals("HH")) {
            gc.add(GregorianCalendar.HOUR, offset);
        } else if (type.equals("D")) {
            gc.add(GregorianCalendar.DATE, offset);
        } else if (type.equals("M")) {
            gc.add(GregorianCalendar.MONTH, offset);
        } else if (type.equals("Y")) {
            gc.add(GregorianCalendar.YEAR, offset);
        }
        return gc.getTime();
    }

    /**
     * 得到距离当前时间的时间间距 1分钟以内： 刚刚 1分钟-2分钟以内： 1分钟前 2分钟-3分钟以内： 2分钟前 …... …... 以此类推 59分钟前
     * 1小时-2小时以内: 1小时前 …… …... 以此类推 23小时前 24小时-48小时以内： 昨天 大于48小时的显示具体日期
     * （例如：2015-09-01）
     *
     * @param srcDate
     * @return 格式化后的时间
     */
    public static String getTimeInterval(Date srcDate) {
        SimpleDateFormat df;
        String returnValue = "";
        if (srcDate != null) {
            Date nowDate = getCurrentDate();
            long nowTime = nowDate.getTime();
            long srcTime = srcDate.getTime();

            long step = nowTime - srcTime;
            long per = 1 * 60 * 1000; // 一分钟毫秒数
            if (step >= 0) {
                if (step < per) {
                    returnValue = "刚刚";
                } else if (step < 59 * per) {
                    int p = (int) (step / per);
                    returnValue = p + "分钟前";
                } else if (step < 23 * 60 * per) {
                    int p = (int) (step / (1 * 60 * per));
                    returnValue = p + "小时前";
                } else if (step < 48 * 60 * per) {
                    returnValue = "昨天";
                } else {
                    df = new SimpleDateFormat(DATE_PATTERN);
                    returnValue = df.format(srcDate);
                }
            }
        }

        return returnValue;
    }

    /**
     * 获取过期时间
     *
     * @description: TODO
     * @param created
     * @return
     */
    public static Integer getExpireSecond(Integer created) {
        Date nowDate = getCurrentDate();
        Date srcdate = getUnixDate(created);
        long nowTime = nowDate.getTime();
        long srcTime = srcdate.getTime();
        long second = (nowTime - srcTime) / 1000;
        int expireSecond = new Long(second).intValue();
        return expireSecond;
    }

    public static Integer getExpireSecond(Integer created, Integer exprieSecond) {
        long nowDate = getCurrentUnixTimestamp();
        long endTime = created + exprieSecond;
        long second = endTime - nowDate;
        int expireSecond = new Long(second).intValue();
        return expireSecond;
    }

    public static long getCurrentTime() {
        return System.currentTimeMillis();
    }

    /**
     * 获取当前时间unix时间戳
     *
     * @return
     */
    public static Integer getCurrentUnixTimestamp() {
        return (int) (System.currentTimeMillis() / 1000);
    }

    public static Integer convertDateToUnixTime(Date date) {
        return (int) (date.getTime() / 1000);
    }

    public static Date getUnixDate(Integer unixTime) {
        Date date = new Date(unixTime * 1000L);
        return date;
    }

    public static String convertUnixTimeToString(Integer unixTime, String pattern) {
        Date date = new Date(unixTime * 1000L);
        return convertDateToString(date, pattern);
    }

    /**
     * 根据用户生日计算年龄
     */
    public static int getAgeByBirthday(Date birthday) {
        Calendar cal = Calendar.getInstance();

        if (cal.before(birthday)) {
            throw new IllegalArgumentException("The birthDay is before Now.It's unbelievable!");
        }

        int yearNow = cal.get(Calendar.YEAR);
        int monthNow = cal.get(Calendar.MONTH) + 1;
        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);

        cal.setTime(birthday);
        int yearBirth = cal.get(Calendar.YEAR);
        int monthBirth = cal.get(Calendar.MONTH) + 1;
        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);

        int age = yearNow - yearBirth;

        if (monthNow <= monthBirth) {
            if (monthNow == monthBirth) {
                // monthNow==monthBirth
                if (dayOfMonthNow < dayOfMonthBirth) {
                    age--;
                }
            } else {
                // monthNow>monthBirth
                age--;
            }
        }
        return age;
    }

    /**
     * 验证指定时间是否在区间内
     *
     * @param date 指定时间
     * @param num  小时
     * @return
     */
    public static boolean passTime(Date date, int num) {
        // 指定时间毫秒数
        long createTime = date.getTime();
        // 当前时间毫秒数
        Date today = new Date();
        long toDayTime = today.getTime();
        // 间隔
        long time = 3600000 * num; // 一小时=3600000毫秒，区间num小时
        if ((toDayTime - createTime) <= time) {
            return true;// 区间内
        } else {
            return false;// 超出指定区间
        }
    }
}
