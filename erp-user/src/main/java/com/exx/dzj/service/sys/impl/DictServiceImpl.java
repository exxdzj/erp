package com.exx.dzj.service.sys.impl;

import com.exx.dzj.constant.CommonConstant;
import com.exx.dzj.entity.dictionary.*;
import com.exx.dzj.mapper.dictionary.DictionaryInfoMapper;
import com.exx.dzj.mapper.dictionary.DictionaryTypeBeanMapper;
import com.exx.dzj.result.Result;
import com.exx.dzj.service.sys.DictService;
import com.exx.dzj.unique.DefaultIdGenerator;
import com.exx.dzj.util.ConvertUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.logging.Logger;

/**
 * @Author
 * @Date 2019/4/15 0015 15:54
 * @Description 字典数据 service
 */
@Component
@Slf4j
public class DictServiceImpl implements DictService {

    @Autowired
    private DictionaryTypeBeanMapper dictTypeMapper;

    @Autowired
    private DictionaryInfoMapper dictMapper;

    /**
     * 查询  字典数据类型 列表
     * @return
     */
    @Override
    public List<DictionaryTypeBean> queryList() {
        DictionaryTypeBeanExample example = new DictionaryTypeBeanExample();
        example.createCriteria().andIsEnableEqualTo(CommonConstant.DEFAULT_VALUE_ONE);
        return dictTypeMapper.selectByExample(example);
    }

    /**
     * 查询  字典数据  列表
     * @param dict
     * @return
     */
    @Override
    public List<DictionaryBean> queryDictDataList(DictionaryInfo dict) {
        dict.setIsEnable(CommonConstant.DEFAULT_VALUE_ONE);
        return dictMapper.queryDictDataList(dict);
    }

    /**
     * 查找 字典数据类型是否存在
     * @param dataType
     * @return
     */
    @Override
    public long checkDictType(String dataType) {
        DictionaryTypeBeanExample example = new DictionaryTypeBeanExample();
        example.createCriteria().andDataTypeEqualTo(dataType);
        return dictTypeMapper.countByExample(example);
    }

    /**
     * 保存 字典数据类型
     * @param bean
     * @return
     */
    @Override
    public Result saveDictType(DictionaryTypeBean bean) {
        Result result = Result.responseSuccess();
        try {
            if(checkDictType(bean.getDataType()) > 0) {
                dictTypeMapper.updateByPrimaryKeySelective(bean);
            }else{
                dictTypeMapper.insertSelective(bean);
            }
        } catch(Exception ex) {
            result.setCode(400);
            result.setMsg("保存字典数据类型失败!");
            log.error("异常方法{}异常信息{}", DictServiceImpl.class.getName()+"saveDictType", ex.getMessage());
        }
        return result;
    }

    /**
     * 获取
     * @param bean
     * @return
     */
    @Override
    public Result delDictType(DictionaryTypeBean bean) {
        Result result = Result.responseSuccess();
        try {
            bean.setIsEnable(CommonConstant.DEFAULT_VALUE_ZERO);
            dictTypeMapper.updateByPrimaryKeySelective(bean);
        } catch(Exception ex) {
            result.setCode(400);
            result.setMsg("删除字典数据类型失败!");
            log.error("异常方法{}异常信息{}", DictServiceImpl.class.getName()+"delDictType", ex.getMessage());
        }
        return result;
    }

    /**
     * 删除 字典数据
     * @param info
     * @return
     */
    @Override
    public Result delDictData(DictionaryInfo info) {
        Result result = Result.responseSuccess();
        try {
            info.setIsEnable(CommonConstant.DEFAULT_VALUE_ZERO);
            dictMapper.updateByPrimaryKeySelective(info);
        } catch(Exception ex) {
            result.setCode(400);
            result.setMsg("删除字典数据失败!");
            log.error("异常方法{}异常信息{}", DictServiceImpl.class.getName()+"delDictType", ex.getMessage());
        }
        return result;
    }

    /**
     * 保存 字典数据
     * @param info
     * @return
     */
    @Override
    public Result saveDictData(DictionaryInfo info) {
        Result result = Result.responseSuccess();
        try {
            if(ConvertUtils.isNotEmpty(info.getDictCode())) {
                dictMapper.updateByPrimaryKeySelective(info);
            } else {
                // 生成编码
                DefaultIdGenerator generator = new DefaultIdGenerator();
                info.setDictCode(generator.next());
                // 获取最大排序值
                int seq = dictMapper.queryMaxSeq();
                info.setSeq(seq + 1);
                dictMapper.insertSelective(info);
            }
        } catch(Exception ex) {
            result.setCode(400);
            result.setMsg("保存字典数据失败!");
            log.error("异常方法{}异常信息{}", DictServiceImpl.class.getName()+"saveDictData", ex.getMessage());
        }
        return result;
    }
}
