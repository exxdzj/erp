package com.exx.dzj.service.dictionary.impl;

import com.exx.dzj.entity.dictionary.DictionaryInfo;
import com.exx.dzj.mapper.dictionary.DictionaryInfoMapper;
import com.exx.dzj.service.dictionary.DictionaryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return dictionaryMapper.queryDictionary(dataType);
    }

    @Override
    public void saveDictionary(DictionaryInfo info) {

    }
}
