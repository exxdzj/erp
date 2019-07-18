package com.exx.dzj.service.dictionary.impl;

import com.exx.dzj.entity.dictionary.DictionaryInfo;
import com.exx.dzj.mapper.dictionary.DictionaryInfoMapper;
import com.exx.dzj.service.dictionary.DictionaryService;
import com.exx.dzj.util.ConvertUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Author
 * @Date 2019/1/7 0007 14:35
 * @Description
 */
@Service("dictService")
public class DictionaryServiceImpl implements DictionaryService {

    private final static Logger LOGGER = LoggerFactory.getLogger(DictionaryServiceImpl.class);

    @Autowired
    private DictionaryInfoMapper dictionaryMapper;

    /**
     * 查询 字典表信息
     * @param dataType
     * @return
     */
    @Override
    public List<DictionaryInfo> queryDictionary(String dataType) {
        if(!ConvertUtils.isEmpty(dataType) && dataType.equals("erp_region")) {
            return dictionaryMapper.queryDictionaryList(dataType);
        }
        return dictionaryMapper.queryDictionary(dataType);
    }

    @Override
    public void saveDictionary(DictionaryInfo info) {

    }

    @Override
    public String queryDictName(String dictCode, String dataType) {
        return dictionaryMapper.queryDictName(dictCode,dataType);
    }

    @Override
    @Cacheable(value = {"dictionary"}, keyGenerator = "myKeyGenerator")
    public List<Map<String, String>> queryDictionaryCodeAndName() {
        return dictionaryMapper.queryDictionaryCodeAndName();
    }
}
