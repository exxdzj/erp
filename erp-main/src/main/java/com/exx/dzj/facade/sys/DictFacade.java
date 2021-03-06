package com.exx.dzj.facade.sys;

import com.exx.dzj.entity.dictionary.DictionaryInfo;
import com.exx.dzj.entity.dictionary.DictionaryTypeBean;
import com.exx.dzj.facade.user.UserTokenFacade;
import com.exx.dzj.result.Result;
import com.exx.dzj.service.sys.DictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @Author
 * @Date 2019/4/15 0015 16:07
 * @Description 数据字典
 */
@Component
public class DictFacade {

    @Autowired
    private DictService dictService;
    @Autowired
    private UserTokenFacade userTokenFacade;

    /**
     * 查询  字典数据类型 列表
     * @return
     */
    public Result queryList() {
        Result result = Result.responseSuccess();
        result.setData(dictService.queryList());
        return result;
    }

    /**
     * 判断 字典数据类型 是否存在
     * @param dataType
     * @return
     */
    public Result checkDictType(String dataType) {
        Result result = Result.responseSuccess();
        long count = dictService.checkDictType(dataType);
        if(count > 0) {
            result.setCode(400);
            result.setMsg("字典数据类型已存在!");
            return result;
        }
        return result;
    }

    /**
     * 保存 字典数据类型
     * @param bean
     * @return
     */
    public Result saveDictType(DictionaryTypeBean bean) {
        Result result = Result.responseSuccess();
        String operator = userTokenFacade.queryUserCodeForToken(null);
        bean.setCreateUser(operator);
        bean.setUpdateUser(operator);
        try {
            result = dictService.saveDictType(bean);
        } catch(Exception ex) {
            result.setCode(400);
            result.setMsg("保存字典数据类型失败!");
        }
        return result;
    }

    /**
     * 删除 字典类型数据
     * @param bean
     * @return
     */
    public Result delDictType(DictionaryTypeBean bean) {
        Result result = Result.responseSuccess();
        String operator = userTokenFacade.queryUserCodeForToken(null);
        bean.setUpdateUser(operator);
        try {
            result = dictService.delDictType(bean);
        } catch(Exception ex) {
            result.setCode(400);
            result.setMsg("删除字典数据类型失败!");
        }
        return result;
    }

    /**
     * 查询 字典数据列表
     * @return
     */
    public Result queryDictDataList(DictionaryInfo info) {
        Result result = Result.responseSuccess();
        result.setData(dictService.queryDictDataList(info));
        return result;
    }

    /**
     * 删除 字典数据
     * @param info
     * @return
     */
    public Result delDictData(DictionaryInfo info) {
        Result result = Result.responseSuccess();
        String operator = userTokenFacade.queryUserCodeForToken(null);
        info.setUpdateUser(operator);
        try {
            result = dictService.delDictData(info);
        } catch(Exception ex) {
            result.setCode(400);
            result.setMsg("删除字典数据失败!");
        }
        return result;
    }

    /**
     * 保存 字典数据
     * @param info
     * @return
     */
    public Result saveDictData(DictionaryInfo info) {
        Result result = Result.responseSuccess();
        String operator = userTokenFacade.queryUserCodeForToken(null);
        info.setCreateUser(operator);
        info.setUpdateUser(operator);
        try {
            result = dictService.saveDictData(info);
        } catch(Exception ex) {
            result.setCode(400);
            result.setMsg("保存字典数据失败!");
        }
        return result;
    }
}
