package com.exx.dzj.util;

import java.text.*;
import java.util.Calendar;

/**
 * @Author
 * @Date 2019/4/3 0003 10:45
 * @Description  主要是用于生成 userCode 编码(短编码)
 */
public class GenerateSequenceUtil {
    private static final FieldPosition HELPER_POSITION = new FieldPosition(0);

    /** 时间：精确到秒 */
    private final static Format dateFormat = new SimpleDateFormat("HHmmss");

    private final static NumberFormat numberFormat = new DecimalFormat("00000");

    private static int seq = 0;

    /**
     * 最大值
     */
    private static final int MAX = 99999;

    public static synchronized String generateSequenceNo() {

        Calendar rightNow = Calendar.getInstance();
        StringBuffer sb = new StringBuffer();
        dateFormat.format(rightNow.getTime(), sb, HELPER_POSITION);
        //numberFormat.format(seq, sb, HELPER_POSITION);

        /*if (seq == MAX) {
            seq = 0;
        } else {
            seq++;
        }*/

        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(generateSequenceNo());
    }
}
