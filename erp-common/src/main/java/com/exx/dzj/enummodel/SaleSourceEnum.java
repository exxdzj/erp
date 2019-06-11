package com.exx.dzj.enummodel;


import com.sun.org.apache.bcel.internal.generic.RETURN;

/**
 * @author yangyun
 * @create 2019-06-11-10:50
 */
public enum SaleSourceEnum {
    WECHAT("201905301458531", "微信"),
    SSTORE("201905301459001","微店"),
    TAOBAO("201905301459211", "淘宝"),
    ALI("201905301459261", "阿里"),
    DZJAPP("201905301459401", "大专家APP");

    private final String key;
    private final String value;

    private SaleSourceEnum (String key, String value){
        this.key = key;
        this.value = value;
    }

    public SaleSourceEnum getSaleSourceEnum(String key){
        if (key == null){
            return null;
        }

        for (SaleSourceEnum temp : SaleSourceEnum.values()){
            if (temp.getKey().equals(key)){
                return temp;
            }
        }
        return null;
    }

    public String getKey (){
        return key;
    }

    public String getValue (){
        return value;
    }
}
