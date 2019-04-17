package com.exx.dzj.service.sys;

import com.exx.dzj.entity.dictionary.DictionaryBean;
import com.exx.dzj.entity.dictionary.DictionaryInfo;
import com.exx.dzj.entity.dictionary.DictionaryTypeBean;
import com.exx.dzj.result.Result;

import java.util.List;

/**
 * @Author
 * @Date 2019/4/15 0015 15:49
 * @Description 字典数据 service
 */
public interface DictService {

    /**
     * 查询  字典数据类型 列表
     * @return
     */
    List<DictionaryTypeBean> queryList();

    /**
     * 查询  字典数据  列表
     * @return
     */
    List<DictionaryBean> queryDictDataList(DictionaryInfo dict);

    /**
     * 查找 字典数据类型是否存在
     * @param dataType
     * @return
     */
    long checkDictType(String dataType);

    /**
     * 保存 字典数据类型
     * @param bean
     * @return
     */
    Result saveDictType(DictionaryTypeBean bean);

    /**
     * 删除 字典数据类型
     * @param bean
     * @return
     */
    Result delDictType(DictionaryTypeBean bean);

    /**
     * 删除 字典数据
     * @param info
     * @return
     */
    Result delDictData(DictionaryInfo info);

    /**
     * 保存 字典数据
     * @param info
     * @return
     */
    Result saveDictData(DictionaryInfo info);
}
