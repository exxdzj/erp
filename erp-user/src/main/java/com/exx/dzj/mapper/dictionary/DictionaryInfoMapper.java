package com.exx.dzj.mapper.dictionary;

import com.exx.dzj.entity.dictionary.DictionaryBean;
import com.exx.dzj.entity.dictionary.DictionaryInfo;
import com.exx.dzj.entity.dictionary.DictionaryInfoExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface DictionaryInfoMapper {
    long countByExample(DictionaryInfoExample example);

    int deleteByExample(DictionaryInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DictionaryInfo record);

    int insertSelective(DictionaryInfo record);

    List<DictionaryInfo> selectByExample(DictionaryInfoExample example);

    DictionaryInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DictionaryInfo record, @Param("example") DictionaryInfoExample example);

    int updateByExample(@Param("record") DictionaryInfo record, @Param("example") DictionaryInfoExample example);

    int updateByPrimaryKeySelective(DictionaryInfo record);

    int updateByPrimaryKey(DictionaryInfo record);

    /**
     * 查询 字典表数据
     * @param dataType
     * @return
     */
    List<DictionaryInfo> queryDictionary(@Param("dataType") String dataType);

    /**
     * 查询 字典表数据
     * @param dataType
     * @return
     */
    List<DictionaryInfo> queryDictionaryList(@Param("dataType") String dataType);

    String queryDictName(@Param("dictCode") String dictCode, @Param("dataType") String dataType);

    List<Map<String, String>> queryDictionaryCodeAndName();

    List<DictionaryBean> queryDictDataList(DictionaryInfo record);

    int queryMaxSeq();
}