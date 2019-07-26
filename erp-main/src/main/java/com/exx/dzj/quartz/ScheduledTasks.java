package com.exx.dzj.quartz;

import com.exx.dzj.entity.encode.BusEncodeRuleInfo;
import com.exx.dzj.facade.sys.BusEncodeFacade;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @Author
 * @Date 2019/7/25 0025 11:09
 * @Description 定时任务，通过定时任务修改编码的初始值
 */
@Component
@Slf4j
public class ScheduledTasks {

    @Autowired
    private BusEncodeFacade busEncodeFacade;

    /**
     * 通过cron来设置定时规则，每隔10秒
     * 对应含义为：
     *   字段         允许值         允许的特殊字符
     秒          0-59 ,          - * /
     分          0-59 ,          - * /
     小时         0-23 ,          - * /
     日期         1-31 ,          - * ? / L W C
     月份         1-12 或者 JAN-DEC , - * /
     星期         1-7 或者 SUN-SAT , - * ? / L C #
     年（可选） 留空, 1970-2099 , - * /

     0 0 3 * * ?     每天 3 点执行
     0 5 3 * * ?     每天 3 点 5 分执行
     0 5 3 ? * *     每天 3 点 5 分执行，与上面作用相同
     0 5/10 3 * * ?  每天 3 点的 5 分、15 分、25 分、35 分、45 分、55 分这几个时间点执行
     0 10 3 ? * 1    每周星期天，3点10分 执行，注：1 表示星期天
     0 10 3 ? * 1#3  每个月的第 三 个星期，星期天执行，# 号只能出现在星期的位置
     */
    @Scheduled(cron = "0 0 0 * * ?")
    public void executeByTenSecond() {
        BusEncodeRuleInfo info1 = new BusEncodeRuleInfo();
        info1.setBusType("sale_ticket");
        info1.setPrefix("Z");

        busEncodeFacade.scheduleUpdateEncode(info1);

        BusEncodeRuleInfo info2 = new BusEncodeRuleInfo();
        info2.setBusType("purchase_ticket");
        info2.setPrefix("ZC");

        busEncodeFacade.scheduleUpdateEncode(info2);

        log.info("使用定时任务修改编码！！！");
    }
}
