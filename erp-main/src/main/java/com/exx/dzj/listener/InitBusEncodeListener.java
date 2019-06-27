package com.exx.dzj.listener;

import com.exx.dzj.entity.encode.BusEncodeRuleCacheData;
import com.exx.dzj.facade.sys.BusEncodeFacade;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author
 * @Date 2019/5/31 0031 15:08
 * @Description 编码规则监听器
 */
@Slf4j
@Component
public class InitBusEncodeListener implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private BusEncodeFacade busEncodeFacade;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        //log.info("================================系统启动================================");
        try {
            List<BusEncodeRuleCacheData> list = busEncodeFacade.queryList();
            //log.info(list.toString());
            //BusEncodeThreadPool.init();
        } catch(Exception ex) {
            log.error("执行方法:{},异常信息:{}", InitBusEncodeListener.class.getName()+".onApplicationEvent", ex.getMessage());
        }
    }
}
