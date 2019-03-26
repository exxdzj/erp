package com.exx.dzj.util.enums;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Predicate;

/**
 * @description 根据枚举value获取 enum 对象
 * @author yangyun
 * @date 2019/3/25 0025
 * @param null
 * @return
 */
public class EnumsUtils {
    private static Map<Class, Object> map = new ConcurrentHashMap<>();

    public static <T> Optional<T> getEnumObject (Class<T> className, Predicate<T> predicate){
        if (!className.isEnum()){
            return null;
        }
        Object obj = map.get(className);

        T[] ts = null;
        if (obj == null){
            ts = className.getEnumConstants();
            map.put(className,ts);
        } else {
            ts = (T[])obj;
        }
        return Arrays.stream(ts).filter(predicate).findAny();
    }
}
