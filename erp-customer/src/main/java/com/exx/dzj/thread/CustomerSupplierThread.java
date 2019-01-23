package com.exx.dzj.thread;

import com.exx.dzj.entity.customer.CustomerForExcelModel;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * @Author
 * @Date 2019/1/22 0022 17:12
 * @Description 导入线程
 */
public class CustomerSupplierThread implements Callable<Integer> {

    private static List<CustomerForExcelModel> list = new ArrayList<>();

    private static class Singleton {
        private static CustomerSupplierThread instance;

        static {
            instance = new CustomerSupplierThread();
        }

        public static CustomerSupplierThread newInstance() {
            return instance;
        }
    }

    /**
     * 静态内部类只会初始化一次
     * @return
     */
    public static CustomerSupplierThread getInstance(List<CustomerForExcelModel> customers) {
        list = customers;
        return Singleton.newInstance();
    }

    @Override
    public Integer call() throws Exception {
        try {
            if (CollectionUtils.isNotEmpty(list)) {
                //保存
            }
        } catch(Exception ex) {

        }
        return 0;
    }
}
