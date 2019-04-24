package com.exx.dzj.util.enums;
/**
 * @author yangyun
 * @create 2019-04-16-16:58
 */
public enum ExportFileNameEnum {

    CUSTOMER_SALE_NAME(4, "销售分析-依客户"),
    SALEMAN_SALE_NAME(3, "销售分析-依销售员"),
    INVENTORY_SALE_NAME(2, "销售分析-依存货"),
    BUSINESS_SALES(1, "销货"),
    BACK_SALES(0, "退货");

    private final Integer key;
    private final String value;

    private ExportFileNameEnum (Integer key, String value){
        this.key = key;
        this.value = value;
    }

    public static ExportFileNameEnum getExportFileNameEnum (Integer key){
        if (key == null){
            return null;
        }

        for (ExportFileNameEnum temp : ExportFileNameEnum.values()){
            if (temp.getKey().equals(key)){
                return temp;
            }
        }
        return null;
    }

    public Integer getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

}
