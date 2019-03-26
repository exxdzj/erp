package com.exx.dzj.util.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author yangyun
 * @create 2019-03-19-10:15
 */
public class ExcelListener extends AnalysisEventListener {

    private List<Object> datas = Collections.synchronizedList(new ArrayList<>());

    /**
     * @description
     * @author yangyun
     * @date 2019/3/19 0019
     * @param o 读取的每行数据
     * @param analysisContext
     * @return void
     */
    @Override
    public void invoke(Object o, AnalysisContext analysisContext) {
        datas.add(o);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
//        analysisContext.
    }

    public List<Object> getDatas() {
        return datas;
    }

    public void setDatas(List<Object> datas) {
        this.datas = datas;
    }
}
