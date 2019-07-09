package com.exx.dzj.controller.commons;

import java.util.regex.Pattern;

public class DataImportUtil {

    public static void main(String[] args) {
//        System.out.println(isHasChart(""));
    }

    // 数字
    static final Pattern NUM = Pattern.compile("[0-9]");


    /**
     * @description 验证字符串是否包含数字
     * @author yangyun
     * @date 2019/7/5 0005
     * @param str
     * @return boolean
     */
    public static boolean strIshasNum (String str){
        return NUM.matcher(str).find();
    }
}
