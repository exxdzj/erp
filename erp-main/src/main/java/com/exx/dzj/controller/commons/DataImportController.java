package com.exx.dzj.controller.commons;

import com.exx.dzj.constant.CommonConstant;
import com.exx.dzj.controller.commons.dataproccess.ProccessImportDataUtil;
import com.exx.dzj.entity.accountatt.AccountAttributeBean;
import com.exx.dzj.entity.contactway.ContactWayBean;
import com.exx.dzj.entity.customer.CustomerSupplierBean;
import com.exx.dzj.entity.dept.DeptInfoBean;
import com.exx.dzj.entity.market.SaleInfo;
import com.exx.dzj.entity.purchase.PurchaseInfo;
import com.exx.dzj.entity.user.UserInfo;
import com.exx.dzj.facade.customer.CustomerSupplierFacade;
import com.exx.dzj.facade.dictionary.DictionaryFacade;
import com.exx.dzj.facade.market.SalesTicketFacade;
import com.exx.dzj.facade.purchase.PurchaseTicketFacade;
import com.exx.dzj.facade.stock.StockFacade;
import com.exx.dzj.facade.user.UserFacade;
import com.exx.dzj.model.CustomerModel;
import com.exx.dzj.model.PurchaseModel;
import com.exx.dzj.model.SaleModel;
import com.exx.dzj.model.StockModel;
import com.exx.dzj.result.Result;
import com.exx.dzj.service.sys.DeptService;
import com.exx.dzj.util.excel.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author yangyun
 * @create 2019-03-18-16:10
 */
@RestController
@RequestMapping("import/")
public class DataImportController {

    @Autowired
    private DictionaryFacade dictionaryFacade;

    @Autowired
    private UserFacade userFacade;

    @Autowired
    private CustomerSupplierFacade customerSupplierFacade;

    @Autowired
    private SalesTicketFacade salesTicketFacade;

    @Autowired
    private StockFacade stockFacade;

    @Autowired
    private PurchaseTicketFacade purchaseTicketFacade;

    @PostMapping("dataimport")
    public Result dataImport(@RequestParam("excelFile") MultipartFile excelFile, @RequestParam("type") Integer type){
        Result result = Result.responseSuccess();
        try {
            // 查询用户销售员信息
            Map<String, UserInfo> userInfoMap = userFacade.querySaleManCodeName();

            // 字典信息
            Map<String, String> stringMap = dictionaryFacade.queryDictionaryCodeAndName();

            // 客户供应商信息信息
            Map<String, CustomerSupplierBean> customerSupplierBeanMap = customerSupplierFacade.queryCustomerSupplierBeanList();

             switch (type){
                case 0:// 客户信息
                    List<Object> customerList = ExcelUtil.readExcel(excelFile, new CustomerModel(), CommonConstant.DEFAULT_VALUE_ONE);
                    // 查询客户等级
                    Map<String, List> map = ProccessImportDataUtil.proccessCustomerData(customerList, stringMap, userInfoMap);

                    customerSupplierFacade.batchInsertCustomerSupplier(map);
                    break;
                case 1:// 销售单
                    List<Object> saleList = ExcelUtil.readExcel(excelFile, new SaleModel(), CommonConstant.DEFAULT_VALUE_ONE);

//                    List<SaleInfo> saleInfoList = ProccessImportDataUtil.proccessSaleInfo(saleList, userInfoMap, stringMap, customerSupplierBeanMap);
                    List<SaleInfo> saleInfoList = ProccessImportDataUtil.proccessSaleInfo2(saleList, userInfoMap, stringMap, customerSupplierBeanMap);

                    salesTicketFacade.importData(saleInfoList);
                    break;
                case 2: // 采购单
                    List<Object> purchaseList = ExcelUtil.readExcel(excelFile, new PurchaseModel(), CommonConstant.DEFAULT_VALUE_ONE);

                    List<PurchaseInfo> purchaseInfoList = ProccessImportDataUtil.proccessPurchaseInfo(purchaseList, userInfoMap, stringMap, customerSupplierBeanMap);
                    purchaseTicketFacade.importData(purchaseInfoList);
                    break;
                case 3: // 存货
                    List<Object> stockList = ExcelUtil.readExcel(excelFile, new StockModel(), CommonConstant.DEFAULT_VALUE_ONE);
                    Map<String, List> listMap = ProccessImportDataUtil.proccessStockInfo(stockList, stringMap);

                    stockFacade.batchInventoryDataProccess(listMap);
                    break;
                case 4:
                    break;
            }

        } catch (IOException e) {
            result.setCode(400);
            result.setMsg("import fail");
            e.printStackTrace();
        }
        return result;
    }

    @GetMapping("test")
    public Result test(){
        Result result = Result.responseSuccess();
        userFacade.querySaleManCodeName();
        return result;
    }

}
