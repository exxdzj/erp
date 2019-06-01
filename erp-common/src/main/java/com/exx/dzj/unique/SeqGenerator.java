package com.exx.dzj.unique;

import com.exx.dzj.util.DateTimeUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @Author
 * @Date 2019/1/8 0008 10:58
 * @Description 唯一序列生成器
 */
public class SeqGenerator {
    /**
     * @return 形如 yyyyMMddHHmmssSSS-Z0000019558195832297 的(38位)保证唯一的递增的序列号字符串，
     * 主要用于数据库的主键，方便基于时间点的跨数据库的异步数据同步。
     * 前半部分是currentTimeMillis，后半部分是nanoTime（正数）补齐20位的字符串，
     * 如果通过System.nanoTime()获取的是负数，则通过nanoTime = nanoTime+Long.MAX_VALUE+1;
     * 转化为正数或零。
     */
    public static String getTimeMillisSequence(){
        long nanoTime = System.nanoTime();
        String preFix="";
        if (nanoTime<0){
            /**
             * 负数补位A保证负数排在正数Z前面,解决正负临界值(如A9223372036854775807至Z0000000000000000000)问题
             */
            preFix="A";
            nanoTime = nanoTime+Long.MAX_VALUE+1;
        }else{
            preFix="Z";
        }
        String nanoTimeStr = String.valueOf(nanoTime);

        int difBit=String.valueOf(Long.MAX_VALUE).length()-nanoTimeStr.length();
        for (int i=0;i<difBit;i++){
            preFix = preFix+"0";
        }
        nanoTimeStr = preFix+nanoTimeStr;
        /**
         * 24小时制
         */
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String timeMillisSequence=sdf.format(System.currentTimeMillis())+"-"+nanoTimeStr;

        return timeMillisSequence;
    }

    public static String nextCode() {
        String nanoTime = DateTimeUtil.dateToStr(new Date(), "yyMMdd");
        Random random = new Random();
        // 获取5位随机数
        int rannum = (int) (random.nextDouble() * (99 - 10 + 1)) + 100;
        return nanoTime + rannum;
    }

    public static void main(String[] agrs) {
        System.out.println(nextCode());
    }
}
