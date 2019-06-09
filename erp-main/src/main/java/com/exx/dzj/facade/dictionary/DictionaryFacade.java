package com.exx.dzj.facade.dictionary;

import com.exx.dzj.entity.dictionary.DictionaryInfo;
import com.exx.dzj.service.dictionary.DictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @Author
 * @Date 2019/1/9 0009 17:55
 * @Description 字典数据
 */
@Component
public class DictionaryFacade {

    @Autowired
    private DictionaryService dictService;

    /**
     * 获取 字典表数据
     * @param dataType
     * @return
     */
    public List<DictionaryInfo> queryDictionary(String dataType){
        return dictService.queryDictionary(dataType);
    }

    public String queryDictName(String dateType, String dictCode){
        return dictService.queryDictName(dictCode, dateType);
    }

    public Map<String, String> queryDictionaryCodeAndName (){
        Map<String, String> map = new HashMap<>();
        List<Map<String, String>> mapList = dictService.queryDictionaryCodeAndName();
        for (Map<String, String> mapi : mapList){
//            Iterator<String> iterator = mapi.keySet().iterator();
//            while (iterator.hasNext()){
//                String next = iterator.next();
//                System.out.println(next);
//                String s = mapi.get(next);
//
//                System.out.println(s);
//            }
            map.put(mapi.get("dictName"), mapi.get("dictCode"));
        }
        return map;
    }
}
