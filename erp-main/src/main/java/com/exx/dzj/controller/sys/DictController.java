package com.exx.dzj.controller.sys;

import com.exx.dzj.entity.dictionary.DictionaryInfo;
import com.exx.dzj.entity.dictionary.DictionaryTypeBean;
import com.exx.dzj.facade.sys.DictFacade;
import com.exx.dzj.result.Result;
import com.exx.dzj.util.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author
 * @Date 2019/4/15 0015 15:47
 * @Description
 */
@RestController
@RequestMapping("sys/dict/")
public class DictController {

    @Autowired
    private DictFacade dictFacade;

    /**
     * 查询 字典数据类型 类表
     * @return
     */
    @GetMapping("queryList")
    public Result queryList() {
        return dictFacade.queryList();
    }

    /**
     * 查询 字典数据类型 是否存在
     * @return
     */
    @GetMapping("checkDictType")
    public Result checkDictType(String dataType) {
        return dictFacade.checkDictType(dataType);
    }

    /**
     * 保存 字典数据类型
     * @param bean
     * @return
     */
    @PostMapping("saveDictType")
    public Result saveDictType(@RequestBody DictionaryTypeBean bean) {
        return dictFacade.saveDictType(bean);
    }

    /**
     * 删除 字典数据类型
     * @param bean
     * @return
     */
    @PostMapping("delDictType")
    public Result delDictType(@RequestBody DictionaryTypeBean bean) {
        return dictFacade.delDictType(bean);
    }

    /**
     * 查询 字典数据列表
     * @param info
     * @return
     */
    @GetMapping("queryDictDataList")
    public Result queryDictDataList(DictionaryInfo info) {
        return dictFacade.queryDictDataList(info);
    }

    /**
     * 删除 字典数据
     * @param info
     * @return
     */
    @PostMapping("delDictData")
    public Result delDictData(@RequestBody DictionaryInfo info) {
        return dictFacade.delDictData(info);
    }

    /**
     * 保存 字典数据
     * @param info
     * @return
     */
    @PostMapping("saveDictData")
    public Result saveDictData(@RequestBody DictionaryInfo info) {
        Result result = Result.responseSuccess();
        if(null == info) {
            result.setCode(400);
            result.setMsg("数据不可为空!");
            return result;
        }
        if(ConvertUtils.isEmpty(info.getDataType()) || ConvertUtils.isEmpty(info.getDictName())) {
            result.setCode(400);
            result.setMsg("字典数据名称或字典数据类型不可为空!");
            return result;
        }
        return dictFacade.saveDictData(info);
    }
}
