package com.exx.dzj;

import com.exx.dzj.facade.customer.CustomerSupplierFacade;
import com.exx.dzj.facade.dictionary.DictionaryFacade;
import com.exx.dzj.facade.market.SalesTicketFacade;
import com.exx.dzj.facade.stock.StockFacade;
import com.exx.dzj.facade.user.UserFacade;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.entity.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author
 * @Date 2019/7/29 0029 15:02
 * @Description 销售单导入
 */
@Slf4j
public class SalesSlipTests extends AppTests {

    @Autowired
    private UserFacade userFacade;

    @Autowired
    private DictionaryFacade dictionaryFacade;

    @Autowired
    private CustomerSupplierFacade customerSupplierFacade;

    @Autowired
    private SalesTicketFacade salesTicketFacade;

    @Autowired
    private StockFacade stockFacade;

    /**
     * 销售单数据导入
     * 获取文件，并且将文件导入
     */
    /*@Test
    public void salesSlipImport() {

        // 查询用户销售员信息
        Map<String, UserInfo> userInfoMap = userFacade.querySaleManCodeName();

        // 字典信息
        Map<String, String> stringMap = dictionaryFacade.queryDictionaryCodeAndName();

        // 客户供应商信息信息
        Map<String, CustomerSupplierBean> customerSupplierBeanMap = customerSupplierFacade.queryCustomerSupplierBeanList();

        String path = "F:\\oldimportdate\\2019\\exportsale"; //"F:\\oldimportdate\\exportsale";
        List<MultipartFile> files = getFileList(path);
        if(!files.isEmpty()) {
            try {
                for(MultipartFile file : files) {
                    List<Object> saleList = ExcelUtil.readExcel(file, new SaleModel(), CommonConstant.DEFAULT_VALUE_ONE);

                    List<SaleInfo> saleInfoList = ProccessImportDataUtil.proccessSaleInfo(saleList, userInfoMap, stringMap, customerSupplierBeanMap);
                    salesTicketFacade.importData(saleInfoList);
                }
            } catch(Exception ex) {
                log.error("导入数据错误,原因:{}", ex.getMessage());
            }
        }
    }*/

    /**
     * 销售单商品数据导入
     * 获取文件，并且将文件导入
     */
    /*@Test
    public void goodsImport() {
        // 字典信息
        Map<String, String> stringMap = dictionaryFacade.queryDictionaryCodeAndName();


        List<StockInfo> stockInfos = stockFacade.queryStockGoodsInfoForImportData();

        String path = "F:\\oldimportdate\\2019\\goods"; //"F:\\oldimportdate\\goods";
        List<MultipartFile> files = getFileList(path);
        if(!files.isEmpty()) {
            try {
                for(MultipartFile file : files) {
                    List<Object> objects = ExcelUtil.readExcel(file, new SaleGoodsModel(), CommonConstant.DEFAULT_VALUE_ONE);
                    SaleGoodsModel o = (SaleGoodsModel)objects.get(0);
                    //log.info("商品信息:{}", o.toString());
                    List<SaleGoodsDetailBean> saleGoodsBeans = ProccessImportDataUtil.processSaleGoodsImportData(objects, stringMap, stockInfos);
                    Map<String, List<SaleGoodsDetailBean>> collect = saleGoodsBeans.stream().collect(Collectors.groupingBy(SaleGoodsDetailBean::getSaleCode));
                    salesTicketFacade.insertImportGoodsData(saleGoodsBeans, DateUtil.convertDateToString(o.getSaleDate(), "yyyy-MM"));
                }
            } catch(Exception ex) {
                log.error("导入数据错误,原因:{}", ex.getMessage());
            }
        }
    }*/

    /**
     * 销售单收款信息数据导入
     * 获取文件，并且将文件导入
     */
    /*@Test
    public void receiptImport() {
        // 字典信息
        Map<String, String> stringMap = dictionaryFacade.queryDictionaryCodeAndName();


        List<StockInfo> stockInfos = stockFacade.queryStockGoodsInfoForImportData();

        String path = "F:\\oldimportdate\\2019\\receipt"; // "F:\\oldimportdate\\receipt";
        List<MultipartFile> files = getFileList(path);
        if(!files.isEmpty()) {
            try {
                for(MultipartFile file : files) {
                    List<Object> receipts = ExcelUtil.readExcel(file, new SaleReceiptModel(), CommonConstant.DEFAULT_VALUE_ONE);

                    if (CollectionUtils.isEmpty(receipts)){
                        break;
                    }
                    salesTicketFacade.exportReceiptData(receipts);
                }
            } catch(Exception ex) {
                log.error("导入数据错误,原因:{}", ex.getMessage());
            }
        }
    }

    /**
     * 公共方法，读取文件夹中的所有文件，并且将文件转为 MultipartFile
     * @param strPath
     * @return
     */
    private List<MultipartFile> getFileList(String strPath) {
        List<MultipartFile> mfiles = new ArrayList<>();
        List<File> filelist = new ArrayList<>();
        File dir = new File(strPath);
        File[] files = dir.listFiles(); // 该文件目录下文件全部放入数组
        if (files != null) {
            for (int i = 0; i < files.length; i++) {
                String fileName = files[i].getName();
                if (files[i].isDirectory()) { // 判断是文件还是文件夹
                    getFileList(files[i].getAbsolutePath()); // 获取文件绝对路径
                } else if (fileName.endsWith("xlsm") || fileName.endsWith("xlsx") || fileName.endsWith("xls")) { // 判断文件名是否以excel后缀结尾
                    String strFileName = files[i].getAbsolutePath();
                    System.out.println("---" + strFileName);
                    filelist.add(files[i]);

                    try {
                        FileInputStream fiStream = new FileInputStream(files[i]);

                        // MockMultipartFile(String name, @Nullable String originalFilename, @Nullable String contentType, InputStream contentStream)
                        // 其中originalFilename,String contentType 旧名字，类型  可为空
                        // ContentType.APPLICATION_OCTET_STREAM.toString() 需要使用HttpClient的包
                        MultipartFile multipartFile = new MockMultipartFile(files[i].getName(),files[i].getName(),ContentType.APPLICATION_OCTET_STREAM.toString(),fiStream);
                        mfiles.add(multipartFile);
                        System.out.println(multipartFile.getName()); // 输出文件名称
                    } catch (FileNotFoundException e) {
                        log.error("文件为找到,原因：{}", e.getMessage());
                    } catch (IOException ex) {
                        log.error("IO错误,原因：{}", ex.getMessage());
                    }
                } else {
                    continue;
                }
            }

        }
        return mfiles;
    }
}
