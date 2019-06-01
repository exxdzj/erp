package com.exx.dzj.busencode;

import com.exx.dzj.constant.CommonConstant;
import com.exx.dzj.util.ConvertUtils;
import com.exx.dzj.util.DateTimeUtil;

import java.util.Date;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author
 * @Date 2019/5/31 0031 16:29
 * @Description 业务编码生成器
 */
public class BusEncodeGenerater {

    /**
     * 生成业务编码的方法
     * @param prefix
     * @param codingMode
     * @param nowValue
     * @param serialNumLength
     * @param step
     * @param serialNumFormat
     */
    public static void generateBusEncode(String prefix, Integer codingMode,
                                  Integer nowValue, Integer serialNumLength,
                                  Integer step, String serialNumFormat) {
        String format = "";
        if(null != codingMode && !codingMode.equals(CommonConstant.DEFAULT_VALUE_ONE)) {
            format = DateTimeUtil.dateToStr(new Date(), serialNumFormat);
        }

        serialNumber(prefix, codingMode, nowValue, serialNumLength, format);
    }

    /**
     * 生成序列号 (格式化后的编号)
     * @param prefix
     * @param nowValue
     * @param serialNumLength
     * @param serialNumFormat
     */
    private static String serialNumber(String prefix, Integer codingMode, Integer nowValue, Integer serialNumLength, String serialNumFormat) {
        String format = "";
        if(null != codingMode && !codingMode.equals(CommonConstant.DEFAULT_VALUE_ONE)) {
            format = DateTimeUtil.dateToStr(new Date(), serialNumFormat);
        }

        String seq = "";
        AtomicInteger tempSeq = new AtomicInteger(nowValue);
        //tempSeq.addAndGet(step);
        seq = lpad(tempSeq.get(), serialNumLength);
        seq = prefix + format+seq;
        return seq;
    }

    /**
     * 获取下一编号
     * @param nowValue
     * @param step
     * @return
     */
    public static Integer nextCode(Integer nowValue, Integer step) {
        AtomicInteger tempSeq = new AtomicInteger(nowValue);
        tempSeq.addAndGet(step);
        return tempSeq.get();
    }

    public static String nextBusCode(String prefix, Integer code, Integer serialNumLength, String serialNumFormat) {
        String format = "";
        if(ConvertUtils.isNotEmpty(serialNumFormat)) {
            format = DateTimeUtil.dateToStr(new Date(), serialNumFormat);
        }
        String seq = seq = lpad(code, serialNumLength);
        if(ConvertUtils.isEmpty(prefix)) {
            prefix = "";
        }
        seq = prefix + format + seq;
        return seq;
    }

    private static String lpad(int number, int length) {
        String f = "%0" + length + "d";
        return String.format(f, number);
    }

    public static void main(String[] args) {
        int size = 10;
        ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<>(size);

        try {
            AtomicInteger seq = new AtomicInteger(1);
            seq.addAndGet(100);
            //seq.addAndGet(100);

            System.out.println(seq.get());

            System.out.println(lpad(3, 1));

            generateBusEncode("Z", 4,
                    1, 3,
                    1, "yyMMdd");

            System.out.println("==================================");
            System.out.println(queue);


            System.out.println(lpad(25685, 3));
        } catch(Exception ex) {

        }
    }
}
