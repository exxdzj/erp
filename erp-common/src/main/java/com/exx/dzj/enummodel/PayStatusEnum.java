package com.exx.dzj.enummodel;

/**
 * @author yangyun
 * @create 2019-03-25-17:42
 */
public enum PayStatusEnum {
    NONPAYMENT("cs01", "未收款"),
    PARTPAYMENT("cs02", "部分收款"),
    ALLPAYMENT("cs03", "全部已收款");

    private final String key;
    private final String value;

    private PayStatusEnum(String key, String value){
        this.key = key;
        this.value = value;
    }

    /**
     * @description 根据 key 获取 enum 对象
     * @author yangyun
     * @date 2019/3/25 0025
     * @param key
     * @return com.exx.dzj.enummodel.PayStatusEnum
     */
    public static PayStatusEnum getPayStatusEnumByKey (String key){
        if (key == null){
            return null;
        }

        for (PayStatusEnum temp : PayStatusEnum.values()){
            if (temp.getKey().equals(key)){
                return temp;
            }
        }

        return null;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}
