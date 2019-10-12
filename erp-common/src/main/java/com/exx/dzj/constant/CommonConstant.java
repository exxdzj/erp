package com.exx.dzj.constant;

import java.math.BigDecimal;

/**
 * @Author
 * @Date 2019/1/7 0007 14:49
 * @Description 公共常量
 */
public class CommonConstant {

    public final static int DEFAULT_VALUE_LOSE_ONE = -1;

    public final static int DEFAULT_VALUE_ZERO = 0;

    public final static int DEFAULT_VALUE_ONE = 1;

    public final static int DEFAULT_VALUE_TWO = 2;

    public final static int DEFAULT_VALUE_THREE = 3;

    public final static int DEFAULT_VALUE_FOUR = 4;

    /**
     * Excel 文件后缀
     */
    public final static String EXCEL_XLS = ".xls";
    public final static String EXCEL_XLSX = ".xlsx";

    /**
     * 定义集合的默认大小
     */
    public final static int DEFAULT_SIZE = 1000;

    /**
     * 默认 页码
     */
    public final static int DEFAULT_PAGE_NUM = 1;
    /**
     * 默认 页面大小
     */
    public final static int DEFAULT_PAGE_SIZE = 20;

    /**
     * 采购单价类型
     */
    public final static String UNIT_PURCHASE_PRICE = "unit_purchase_price";
    /**
     * 采购付款条件
     */
    public final static String TERM_OF_PAYMENT	= "term_of_payment";
    /**
     * 销售收款条件
     */
    public final static String COLLECTION_TERMS = "collection_terms";
    /**
     * 收款付款方式
     */
    public final static String RECEIVABLES_PAYMENT_METHOD = "receivables_payment_method";
    /**
     * 付款账户(收款账户、退款帐号)
     */
    public final static String PAYMENT_RECEIPT_REFUND_ACCOUNT = "payment_receipt_refund_account";
    /**
     * 供应商类别
     */
    public final static String SUPPLIER_SATEGORY = "supplier_sategory";
    /**
     * 地区
     */
    public final static String ERP_REGION = "erp_region";
    /**
     * 客户编号
     */
    public final static String CUSTOMER_CODE = "customer_code";
    /**
     * 客户类别
     */
    public final static String CUSTOMER_CATEGORY = "customer_category";
    /**
     * 开票类型
     */
    public final static String BILLING_TYPE = "billing_type";
    /**
     * 收款状态
     */
    public final static String COLLECTION_STATUS = "collection_status";
    /**
     * 订单状态
     */
    public final static String ORDER_STATUS = "order_status";
    /**
     * 仓库入库业务类别
     */
    public final static String WAREHOUSE_INPUT_BUS_CATEGORY = "warehouse_input_bus_category";
    /**
     * 仓库部门
     */
    public final static String WAREHOUSE_DEPT = "warehouse_dept";
    /**
     * 仓库出库业务类别
     */
    public final static String WAREHOUSE_OUTGO_BUS_CATEGORY = "warehouse_outgo_bus_category";
    /**
     * 存货明细业务类型
     */
    public final static String INVENTORY_DETAILED_BUS_TYPE	= "inventory_detailed_bus_type";
    /**
     * 存货地址-发货地址
     */
    public final static String INVENTORY_SHIP_ADDRESS = "inventory_ship_address";

    /**
     * 存货类别
     */
    public final static String INVENTORY_TYPE = "inventory_type";

    public final static String HOME_MENU = "201901010201021";

    /**
     * @description 请求结果码

     */
    public  final static Integer FAIL_CODE = 400;
    public final static Integer SUCCESS_CODE = 200;

    /*********************客户供应商标识*********************/
    public static final Integer CUST_TYPE_OF_CUSTOMER = 1;
    public static final Integer CUST_TYPE_OF_SUPPLIER = 2;
    public static final Integer CUST_TYPE_OF_ALL = 3;

    public static final String BIGDECIMAL_ZERO_STR = "0.00";

    /*********************快递鸟物流状态(2-在途中,3-签收,4-问题件)*********************/
    public static final Integer KDNIAO_ON_WAY_STATE = 2;
    public static final Integer KDNIAO_SIGN_IN_STATE = 3;
    public static final Integer KDNIAO_PROBLEM_STATE = 4;

    public static final String DATE_YEAR_PATTERN = "yyyy";
    public static final int HOME_PAGE_DEFAULT_MONTH = 12;
    public static final int HOME_PAGE_DEFAULT_YEAR = 4;

    public static final int STOCK_INVENTORY_WARNING = 100;
    public static final Integer INTEGER_VALUE_ONE = 1;

    public static final int SALE_TIME_OUT = 120;

    public static final String ANDROID_TYPE = "android";

    public static final String INSERT = "insert";
    public static final String UPDATE = "update";
    public static final String DELETE = "delete";

    public static final String USE_NEW_CDOE ="useNewCode";
    public static final String USE_OLD_CDOE ="useOldCode";
}
