package com.exx.dzj.controller.dictionary;

import com.exx.dzj.entity.dictionary.DictionaryInfo;
import com.exx.dzj.facade.dictionary.DictionaryFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

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
}
