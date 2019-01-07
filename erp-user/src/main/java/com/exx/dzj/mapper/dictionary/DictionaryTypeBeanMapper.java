package com.exx.dzj.mapper.dictionary;

import com.exx.dzj.entity.dictionary.DictionaryTypeBean;
import com.exx.dzj.entity.dictionary.DictionaryTypeBeanExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DictionaryTypeBeanMapper {
    long countByExample(DictionaryTypeBeanExample example);

    int deleteByExample(DictionaryTypeBeanExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DictionaryTypeBean record);

    int insertSelective(DictionaryTypeBean record);

    List<DictionaryTypeBean> selectByExample(DictionaryTypeBeanExample example);

    DictionaryTypeBean selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DictionaryTypeBean record, @Param("example") DictionaryTypeBeanExample example);

    int updateByExample(@Param("record") DictionaryTypeBean record, @Param("example") DictionaryTypeBeanExample example);

    int updateByPrimaryKeySelective(DictionaryTypeBean record);

    int updateByPrimaryKey(DictionaryTypeBean record);
}