package com.exx.dzj.util;

import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;

/**
 * @Author 天刀
 * @Date 2019/1/8 0008 10:27
 * @Description 判断实体类中的所有属性是否为空，为空则返回 true, 否则 false
 */
public class EntityJudgeUtil {

    /**
     * 判断该对象是否: 返回ture表示所有属性为null  返回false表示不是所有属性都是null
     * @param object
     * @return
     */
    public static boolean checkObjAllFieldsIsNull(Object object) {
        if (null == object) {
            return true;
        }
        try {
            /**
             * 得到类的 class 对象
             */
            Class stuCla = (Class) object.getClass();
            /**
             * 得到类的属性集合
             */
            Field[] fs = stuCla.getDeclaredFields();
            for (Field f : fs) {
                f.setAccessible(true);

                //System.out.print(f.getName() + ":");
                //System.out.println(f.get(object));

                if (f.get(object) != null && StringUtils.isNotBlank(f.get(object).toString())) {
                    return false;
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
}
