package com.exx.dzj.service.dictionary;

import com.exx.dzj.entity.dictionary.DictionaryInfo;

import java.util.List;

/**
 * @Author
 * @Date 2019/1/7 0007 14:32
 * @Description 字典表数据
 */
public interface DictionaryService {

    /**
     * 查询 字典表数据
     * @param dataType
     * @return
     */
    List<DictionaryInfo> queryDictionary(String dataType);

    /**
     * 保存 字典表数据
     * @param info
     */
    void saveDictionary(DictionaryInfo info);

    String queryDictName(String dictCode, String dataType);
}
