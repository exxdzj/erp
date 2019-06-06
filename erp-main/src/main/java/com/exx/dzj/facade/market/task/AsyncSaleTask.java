package com.exx.dzj.facade.market.task;

import com.exx.dzj.constant.CommonConstant;
import com.exx.dzj.entity.dept.DeptInfoBean;
import com.exx.dzj.entity.market.SaleInfo;
import com.exx.dzj.excepte.ErpException;
import com.exx.dzj.service.salesticket.SalesTicketService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

/**
 * @description 销售单 异步任务处理器
 * @author yangyun
 * @date 2019/6/5 0005
 * @return
 */
public class AsyncSaleTask implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(AsyncSaleTask.class);

    private SaleInfo saleInfo;

    private List<DeptInfoBean> list;

    private String deptCode;

    private SalesTicketService salesTicketService;

    public AsyncSaleTask (SaleInfo saleInfo, List<DeptInfoBean> list, String deptCode, SalesTicketService salesTicketService){
        this.saleInfo = saleInfo;
        this.list = list;
        this.deptCode = deptCode;
        this.salesTicketService = salesTicketService;
    }

    @Override
    public void run() {
        logger.info("开始执行更新销售单销售员所属分公司信息, 销售单: {}", saleInfo.getSaleCode());
        try {
            DeptInfoBean deptInfoBean = new DeptInfoBean();
            deptInfoBean.setDeptCode(deptCode);

            DeptInfoBean deptInfo = gainSubordinateCompanyInfo(list, deptInfoBean);
            logger.info("销售员: {}, 所属部门: {}" ,saleInfo.getSalesmanCode(), deptInfo.getDeptName());

            String saleCode = saleInfo.getSaleCode();

            saleInfo.setSubordinateCompanyCode(deptInfo.getDeptCode());
            saleInfo.setSubordinateCompanyName(deptInfo.getDeptName());

            int count = 0;
            int times = 0;

            // 查询销售单是否已经新增
            while (count == CommonConstant.DEFAULT_VALUE_ZERO) {
                Thread.sleep(1000);
                count = salesTicketService.querySaleBySaleCode(saleCode);
                times ++;
                if (times == CommonConstant.SALE_TIME_OUT){
                    logger.error("更新销售单销售员所属分公司信息失败, 原因为新增销售单失败!");

                    break;
                }
            }

            // 更新分公司信息
            salesTicketService.updateSalesmanSubordinateCompany(saleInfo);

            if (times != CommonConstant.SALE_TIME_OUT){

                logger.info("更新销售单销售员所属分公司信息完成, 销售单: {}, 所属分公司: {}", saleInfo.getSaleCode(), deptInfo.getDeptName());
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
            logger.error("更新销售单销售员所属分公司信息失败, 销售单: {}, 原因{}", saleInfo.getSaleCode(), e.getMessage());
        }

    }

    /**
     * @description 获取分公司信息
     * @author yangyun
     * @date 2019/6/6 0006
     * @param list
     * @param deptInfoBean
     * @return com.exx.dzj.entity.dept.DeptInfoBean
     */
    private DeptInfoBean gainSubordinateCompanyInfo (List<DeptInfoBean> list, DeptInfoBean deptInfoBean){

        for (DeptInfoBean db : list){
            if (StringUtils.equals(db.getDeptCode(), deptInfoBean.getDeptCode())){
                deptInfoBean.setDeptCode(db.getParentCode());
                if (db.getIsCompare().equals(CommonConstant.DEFAULT_VALUE_ONE)){
                    return db;
                }
            }
        }

        return gainSubordinateCompanyInfo(list, deptInfoBean);
    }

}
