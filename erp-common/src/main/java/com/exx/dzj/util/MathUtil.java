package com.exx.dzj.util;

import com.exx.dzj.constant.CommonConstant;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.regex.Pattern;

/**
 * @Author
 * @Date 2019/1/7 0007 16:23
 * @Description
 */
public class MathUtil {
    static String[] units = { "", "十", "百", "千", "万", "十万", "百万", "千万", "亿", "十亿", "百亿", "千亿", "万亿" };
    static char[] numArray = { '零', '一', '二', '三', '四', '五', '六', '七', '八', '九' };
    private static final BigDecimal BIG_DECIMAL_HUNDRED = new BigDecimal(100);
    private static final BigDecimal BIG_DECIMAL_ZERO = new BigDecimal(0);

    private MathUtil() {
    }



    public static String format(double d, String pattern)
    {
        /***/
        DecimalFormat df1  =  new  DecimalFormat("####.000");
        return df1.format(d);
    }

    /**
     * 格式化KM
     * @param d
     * @return
     */
    public static String formatKm(double d)
    {
        return  formatDouble(d, "#0.00");
    }


    /**
     * 格式化KM
     * @param d
     * @return
     */
    public static String formatPrice(double d)
    {
        return  formatDouble(d, "#0.##");
    }


    /**
     * 字符串过长会导致jvm进入死循环
     *
     * @param s
     * @return
     */
    public static String parseAvgFormat(String s) {
        if (s == null) {
            return s;
        }
        if (s.length() <= 30) {
            return s;
        }
        return s.substring(0, 31);
    }

    public static byte toByte(String s) {
        return toByte(s, 10, (byte) 0);
    }

    public static byte toByte(String s, byte defaultValue) {
        return toByte(s, 10, defaultValue);
    }

    public static byte toByte(String s, int radix, byte defaultValue) {
        byte result = defaultValue;
        s = parseAvgFormat(s);
        try {
            result = (byte) toInt(s, radix, result);
        } catch (Exception e) {
            // LOG.error(e.getMessage(), e);
        }
        return result;
    }

    public static int toInt(char s) {
        return toInt(s, 0);
    }

    public static int toInt(char s, int defaultValue) {
        int result = defaultValue;
        try {
            result = Integer.parseInt(String.valueOf(s));
        } catch (Exception e) {
            // LOG.error(e.getMessage(), e);
        }
        return result;
    }

    public static int toInt(String s, int radix, int defaultValue) {
        if (StringUtils.isBlank(s)) {
            return defaultValue;
        }
        int result = defaultValue;
        s = parseAvgFormat(s);
        try {
            result = Integer.parseInt(s.trim(), radix);
        } catch (Exception e) {
            // LOG.error(e.getMessage(), e);
        }
        return result;
    }

    public static boolean equals(Integer value, Integer value2){

        if (value == null || value2 == null) {
            return false;
        }
        return value.equals(value2);
    }

    public static Integer toInteger(String s, Integer defaultValue) {
        if (StringUtils.isBlank(s)) {
            return defaultValue;
        }
        Integer result = defaultValue;
        try {
            result = Integer.parseInt(s.trim());
        } catch (Exception e) {
            // LOG.error(e.getMessage(), e);
        }
        return result;
    }



    public static Integer toInteger(Integer s, Integer defaultValue) {
        if (s == null){
            return defaultValue;
        }
        else {
            return s;
        }
    }


    public static int toInt(String s, int defaultValue) {
        return toInt(s, 10, defaultValue);
    }

    public static int toInt(String s) {
        return toInt(s, 0);
    }

    public static int toInt(Integer integer, int defaultValue) {
        if (integer == null)
        {
            return defaultValue;
        }
        return integer.intValue();
    }


    public static int[] toIntArr(String s, int[] defaultValueArr) {
        if (StringUtils.isBlank(s)) {
            return defaultValueArr;
        }
        String[] sArr = s.split(",");
        int sArrLength = sArr != null ? sArr.length : 0;
        if (sArrLength < 1) {
            return defaultValueArr;
        }
        int[] resultArr = new int[sArrLength];
        for (int i = 0; i < sArrLength; i++) {
            try {
                resultArr[i] = Integer.parseInt(sArr[i]);
            } catch (Exception e) {
            }
        }
        return resultArr;
    }

    public static Integer[] toIntegerArr(String[] strArray) {
        if (null == strArray || strArray.length == 0)
        {
            return null;
        }
        Integer[] resultArr = new Integer[strArray.length];
        for (int i = 0; i < strArray.length; i++) {
            try {
                resultArr[i] = new Integer(strArray[i]);
            } catch (Exception e) {
            }
        }
        return resultArr;
    }

    public static Integer[] toIntegerArr(String s, String splitor) {
        if (StringUtils.isBlank(s)) {
            return null;
        }
        String[] sArr = s.split(",");
        int sArrLength = sArr != null ? sArr.length : 0;
        if (sArrLength < 1) {
            return null;
        }
        Integer[] resultArr = new Integer[sArrLength];
        for (int i = 0; i < sArrLength; i++) {
            try {
                resultArr[i] = Integer.parseInt(sArr[i]);
            } catch (Exception e) {
            }
        }
        return resultArr;
    }


    public static Integer[] toIntegerArr(String s, String splitor,Integer[] filter) {
        if (StringUtils.isBlank(s)) {
            return null;
        }
        String[] sArr = s.split(",");
        int sArrLength = sArr != null ? sArr.length : 0;
        if (sArrLength < 1) {
            return null;
        }
        Integer[] resultArr = new Integer[sArrLength];
        for (int i = 0; i < sArrLength; i++) {
            try {
                resultArr[i] = Integer.parseInt(sArr[i]);
            } catch (Exception e) {
            }
        }

        Integer[] filterValue = {-1,0};
        resultArr = (Integer[]) ArrayUtils.removeElement(resultArr, filterValue);
        return resultArr;
    }

    public static short toShort(String s, int radix, short defaultValue) {
        if (StringUtils.isBlank(s)) {
            return defaultValue;
        }
        short result = defaultValue;
        s = parseAvgFormat(s);
        try {
            result = Short.parseShort(s.trim(), radix);
        } catch (Exception e) {
            // LOG.error(e.getMessage(), e);
        }
        return result;
    }

    public static short toShort(String s, short defaultValue) {
        return toShort(s, 10, defaultValue);
    }

    public static short toShort(String s) {
        return toShort(s, 10, (short) 0);
    }

    public static String[] toStringArray(Integer[] values) {
        String[] strings = new String[values.length];
        for (int i = 0; i < values.length; i++) {
            strings[i] = String.valueOf(values[i]);
        }
        return strings;
    }

    public static long toLong(String s, long defaultValue) {
        return toLong(s, 10, defaultValue);
    }

    public static long toLong(String s, int radix, long defaultValue) {
        if (StringUtils.isBlank(s)) {
            return defaultValue;
        }
        long result = defaultValue;
        s = parseAvgFormat(s);
        try {
            result = Long.parseLong(s.trim(), radix);
        } catch (Exception e) {
            // LOG.error(e.getMessage(), e);
        }
        return result;
    }

    public static long toLong(String s) {
        return toLong(s, 10, 0);
    }

    public static float toFloat(String s, Float defaultValue) {
        if (StringUtils.isBlank(s)) {
            return defaultValue;
        }
        Float d = defaultValue;
        s = parseAvgFormat(s);
        try {
            d = Float.parseFloat(s.trim());
        } catch (Exception e) {
            // LOG.error(e.getMessage(), e);
        }
        return d;
    }

    public static float toFloat(String s) {
        return toFloat(s, 0.0f);
    }

    public static Double toDouble(String s, Double defaultValue) {
        if (StringUtils.isBlank(s)) {
            return defaultValue;
        }
        Double result = defaultValue;
        s = parseAvgFormat(s);
        try {
            result = Double.parseDouble(s);
        } catch (Exception e) {
            // LOG.error(e.getMessage(), e);
        }
        return result;
    }

    public static Double toDouble(String s,int scale, Double defaultValue) {
        if (StringUtils.isBlank(s)) {
            return defaultValue;
        }
        Double result = defaultValue;
        s = parseAvgFormat(s);
        try {
            result = Double.parseDouble(s);
            BigDecimal bg = new BigDecimal(result);
            result = bg.setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
        } catch (Exception e) {
            // LOG.error(e.getMessage(), e);
        }
        return result;
    }

    public static double toDouble(String s) {
        return toDouble(s, 0.0D);
    }

    public static BigDecimal toBigDecimal(String s) {
        BigDecimal bigDecimal = new BigDecimal("0.0");
        if (StringUtils.isBlank(s)) {
            return bigDecimal;
        }
        try {
            bigDecimal = new BigDecimal(s);
        } catch (Exception e) {
            // LOG.error(e.getMessage(), e);
        }
        return bigDecimal;
    }

    public static BigDecimal nullToBigDecimal(BigDecimal bigDecimal) {
        if (bigDecimal == null) {
            return new BigDecimal("0.0");
        }
        return bigDecimal;
    }

    /**
     * @Title : getBigDecimal
     * @功能描述: 将 Object 转为 BigDecimal
     * @设定文件：@param value
     * @设定文件：@return
     * @返回类型：BigDecimal
     * @throws ：
     */
    public static BigDecimal getBigDecimal(Object value) {
        BigDecimal ret = null;
        if( value != null ) {
            if( value instanceof BigDecimal ) {
                ret = (BigDecimal) value;
            } else if( value instanceof String ) {
                ret = new BigDecimal( (String) value );
            } else if( value instanceof BigInteger) {
                ret = new BigDecimal( (BigInteger) value );
            } else if( value instanceof Number ) {
                ret = new BigDecimal( ((Number)value).doubleValue() );
            } else {
                throw new ClassCastException("Not possible to coerce ["+value+"] from class "+value.getClass()+" into a BigDecimal.");
            }
        }
        return ret;
    }

    public static boolean isDouble(String s) {
        s = parseAvgFormat(s);
        try {
            Double.parseDouble(s);
            return true;
        } catch (Exception e) {
            // LOG.error(e.getMessage(), e);
        }
        return false;
    }

    /**
     * 功能：格式化双精度数,四舍五入 <br>
     *
     * @param d
     * @param pattern
     *            ########0<br>
     *            ########0.0<br>
     *            ########0.00<br>
     *            ########0.000<br>
     *            ########0.0000<br>
     *            ########0.00000<br>
     *            ##.######最高6位精度123.456789 等
     * @return
     */
    public static String formatDouble(double d, String pattern) {
        return formatDouble(d, pattern, RoundingMode.HALF_UP);// 四舍五入
    }

    /**
     * 功能：格式化双精度数,四舍五入 <br>
     *
     * @param d
     * @param pattern
     *            ########0<br>
     *            ########0.0<br>
     *            ########0.00<br>
     *            ########0.000<br>
     *            ########0.0000<br>
     *            ########0.00000<br>
     *            ##.######最高6位精度123.456789 等
     * @param roundingMode
     *            {@link java.math.RoundingMode#HALF_UP}
     * @return
     */
    public static String formatDouble(double d, String pattern, RoundingMode roundingMode) {
        String result = "Format Error";
        if (pattern == null || "".equals(pattern)) {
            pattern = "########0.00";
        }
        try {
            DecimalFormat decimalFormat = new DecimalFormat(pattern);
            decimalFormat.setRoundingMode(roundingMode);
            result = decimalFormat.format(d);
        } catch (Exception e) {
            // LOG.error(e.getMessage(), e);
        }
        return result;
    }

    public static String formatDouble(double d) {
        return formatDouble(d, "########0.00");
    }

    /**
     * 功能：格式化浮点数
     *
     * @param f
     * @param pattern
     * @return
     */
    public static String formatFloat(float f, String pattern) {
        String result = "Format Error";
        double d = 0.00d;
        try {
            d = Double.parseDouble(parseAvgFormat(Float.toString(f)));
            result = formatDouble(d, pattern);
        } catch (Exception e) {
            // LOG.error(e.getMessage(), e);
        }
        return result;
    }

    public static String formatFloat(float f) {
        return formatFloat(f, "########0.00");
    }

    /**
     *
     * @param number
     *            数据值
     * @param scale
     *            保留小时点位数
     * @return 返回经过四舍五入的小数
     */
    public static Object round(Object number, int scale) {
        if (number instanceof Float) {
            BigDecimal bg = new BigDecimal((Float) number);
            number = bg.setScale(scale, BigDecimal.ROUND_HALF_UP).floatValue();
        } else if (number instanceof Double) {
            BigDecimal bg = new BigDecimal((Double) number);
            number = bg.setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
        }
        return number;
    }

    public static String toUnsignBinaryString(byte number) {
        if (number < 0) {
            int max = 256;
            number = (byte) (max + number);
        }
        return toUnsignBinaryString(number, 8);
    }

    public static String toUnsignBinaryString(short number) {
        if (number < 0) {
            int max = 65536;
            number = (short) (max + number);
        }
        return toUnsignBinaryString(number, 16);
    }

    public static String toUnsignBinaryString(int number) {
        return toUnsignBinaryString(number, 32);
    }

    /**
     *
     * @param number
     * @param bitLength
     *            8||16||32
     * @return
     */
    public static String toUnsignBinaryString(int number, int bitLength) {
        if (bitLength != 8 && bitLength != 16 && bitLength != 32) {
            throw new IllegalArgumentException("bitLength != 8 && bitLength != 16 && bitLength != 32 bitLength=" + bitLength);
        }
        if (number < 0) {
            long max = (bitLength == 8 ? 256 : (bitLength == 16 ? 65536 : 4294967296L));
            number = (int) (max + number);
        }
        String result = Integer.toBinaryString(number);
        for (int i = result.length(); i < bitLength; i++) {
            result = "0" + result;
        }
        if (result.length() > bitLength) {
            result = result.substring(result.length() - bitLength, result.length());
        }
        return result;
    }

    /**
     *
     * @param number
     * @param bitLength
     *            8||16||32||64
     * @return
     */
    public static String toBinaryString(long number, int bitLength) {
        if (bitLength != 8 && bitLength != 16 && bitLength != 32 && bitLength != 64) {
            throw new IllegalArgumentException("bitLength != 8 && bitLength != 16 && bitLength != 32 bitLength=" + bitLength);
        }
        String result = Long.toBinaryString(number);
        for (int i = result.length(); i < bitLength; i++) {
            result = "0" + result;
        }
        if (result.length() > bitLength) {
            result = result.substring(result.length() - bitLength, result.length());
        }
        return result;
    }

    /**
     * 获取非空数字
     * @param value
     * @param defaultValue
     * @return
     */
    public static Integer getNotNullInteger(Integer value,Integer defaultValue){
        if (value == null) {
            return defaultValue;
        }
        return value;
    }

    public static void main(String[] args) {
        System.out.println(toDouble("113.3670537517419", 6, null));
    }

    public static String foematInteger(int num) {
        char[] val = String.valueOf(num).toCharArray();
        int len = val.length;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            String m = val[i] + "";
            int n = Integer.valueOf(m);
            boolean isZero = n == 0;
            String unit = units[(len - 1) - i];
            if (isZero) {
                if ('0' == val[i - 1]) {
                    // not need process if the last digital bits is 0
                    continue;
                } else {
                    // no unit for 0
                    sb.append(numArray[n]);
                }
            } else {
                sb.append(numArray[n]);
                sb.append(unit);
            }
        }
        return sb.toString();
    }

    /**
     * @Title : isInteger
     * @功能描述: 判断字符串全是整型数字
     * @设定文件：@param str
     * @设定文件：@return
     * @返回类型：boolean
     * @创建人 ：何文亮
     * @throws ：
     */
    public static boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }

    /**
     * @Title : isNumber
     * @功能描述: 判断字符串全是数字(整型、浮点型都可以)
     * @设定文件：@param str
     * @设定文件：@return
     * @返回类型：boolean
     * @创建人 ：何文亮
     * @throws ：
     */
    public static boolean isNumber(String str){
        String reg = "^[0-9]+(.[0-9]+)?$";
        return str.matches(reg);
    }

    /**
     * @description 保留 Bigdecimal 精度
     * @author yangyun
     * @date 2019/4/16 0016
     * @param divisor 除数
     * @param dividend 被除数
     * @param scale 精度位
     * @return java.math.BigDecimal
     */
    public static BigDecimal keepTwoBigdecimal (BigDecimal divisor, BigDecimal dividend, int scale){
        boolean flag = CommonConstant.BIGDECIMAL_ZERO_STR.equals(dividend.toString()) || BIG_DECIMAL_ZERO.equals(dividend) || (dividend.intValue() == CommonConstant.DEFAULT_VALUE_ZERO);

        return flag ? divisor : divisor.divide(dividend, scale, BigDecimal.ROUND_HALF_UP).multiply(BIG_DECIMAL_HUNDRED);
    }
}
