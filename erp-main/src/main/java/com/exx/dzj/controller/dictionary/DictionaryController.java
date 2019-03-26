package com.exx.dzj.controller.dictionary;

import com.exx.dzj.entity.dictionary.DictionaryInfo;
import com.exx.dzj.facade.dictionary.DictionaryFacade;
import com.exx.dzj.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * @Author
 * @Date 2019/1/9 0009 17:54
 * @Description 字典表数据
 */
@RestController
@RequestMapping("dictionary/")
public class DictionaryController {

    @Autowired
    private DictionaryFacade dictionaryFacade;

    /**
     * 获取 字典表数据
     * @param request
     * @param response
     * @param dataType
     * @return
     */
    @GetMapping("queryDictionary")
    public List<DictionaryInfo> queryDictionary(HttpServletRequest request,
                                                HttpServletResponse response,
                                                String dataType){
        return dictionaryFacade.queryDictionary(dataType);
    }

    /**
     * @description
     * @author yangyun 根据类型和code查询名字
     * @date 2019/1/22 0022
     * @param request
     * @param response
     * @param dictionaryInfo
     * @return com.exx.dzj.result.Result
     */
    @GetMapping("queryDictName")
    public Result queryDictName(HttpServletRequest request, HttpServletResponse response, DictionaryInfo dictionaryInfo){
        Result result = Result.responseSuccess();
        String dictName = dictionaryFacade.queryDictName(dictionaryInfo.getDataType(), dictionaryInfo.getDictCode());
        result.setData(dictName);
        return result;
    }
}
