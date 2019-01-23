package com.exx.dzj.listen;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.exx.dzj.constant.CommonConstant;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Author
 * @Date 2019/1/22 0022 15:46
 * @Description
 */
public class ExcelListener extends AnalysisEventListener {

    /**
     * 通过 Collections.synchronizedList 定义线程安全的 list
     */
    private List<Object> data = Collections.synchronizedList(new ArrayList<>());

    /**
     * 回调方法
     * @param object
     * @param context
     */
    @Override
    public void invoke(Object object, AnalysisContext context) {
        data.add(object);
        //每满1000条记录,saveCustomerSupplier(),但是 saveCustomerSupplier() 实际是由 doAfterAllAnalysed() 调用
        //在超过 1w 条记录时, 分批次执行 saveCustomerSupplier() 比一次执行 doSomething() 速度要快一些
        if(data.size() >= CommonConstant.DEFAULT_SIZE){
            saveCustomerSupplier();
            data = Collections.synchronizedList(new ArrayList<>());
        }
    }

    /**
     * 实际执行 saveCustomerSupplier() 的方法
     * @param context
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        saveCustomerSupplier();
    }

    /**
     * 保存 客户或供应商 数据
     */
    public void saveCustomerSupplier(){
        for (Object o : data) {
            System.out.println(o);
        }
    }
}
