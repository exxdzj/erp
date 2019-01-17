package com.exx.dzj.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * @ClassName:  JsonUtils
 * @Description:自定义响应结构
 * @date:   2019年1月09日
 */
public class JsonUtils {

    // 定义jackson对象
    private static final ObjectMapper MAPPER = new ObjectMapper();

    /**
     * @Title: objectToJson
     * @Description: 将对象转换成json字符串
     * @author: 天刀-盛泽荣
     * @date: 2018年6月10日 下午9:45:48
     * @param: @param data
     * @param: @return
     * @return: String
     * @throws
     */
    public static String objectToJson(Object data) {
        try {
            if(null == data){
                return null;
            }
            String string = MAPPER.writeValueAsString(data);
            return string;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @Title: jsonToPojo
     * @Description: 将json结果集转化为对象
     * @author: 天刀-盛泽荣
     * @date: 2018年6月10日 下午9:45:35
     * @param: @param jsonData
     * @param: @param beanType
     * @param: @return
     * @return: T
     * @throws
     */
    public static <T> T jsonToPojo(String jsonData, Class<T> beanType) {
        try {
            if(!StringUtils.isNotBlank(jsonData)){
                return null;
            }
            T t = MAPPER.readValue(jsonData, beanType);
            return t;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @Title: jsonToList
     * @Description: 将json数据转换成pojo对象list
     * @author: 天刀-盛泽荣
     * @date: 2018年6月10日 下午9:45:24
     * @param: @param jsonData
     * @param: @param beanType
     * @param: @return
     * @return: List<T>
     * @throws
     */
    public static <T>List<T> jsonToList(String jsonData, Class<T> beanType) {
        JavaType javaType = MAPPER.getTypeFactory().constructParametricType(List.class, beanType);
        try {
            if(!StringUtils.isNotBlank(jsonData)){
                return null;
            }
            List<T> list = MAPPER.readValue(jsonData, javaType);
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
