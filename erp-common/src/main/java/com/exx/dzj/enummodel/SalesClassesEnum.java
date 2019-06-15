package com.exx.dzj.enummodel;

/**
 * @author yangyun
 * @create 2019-06-14-16:26
 */
public enum SalesClassesEnum {
    UNRECEIPTED(0, "未签收"),
    RECEIPTED(1, "已签收"),
    OTHER(2, "其他");

    private Integer key;
    private String value;

    private SalesClassesEnum (Integer key, String value){
        this.key = key;
        this.value = value;
    }

    public static SalesClassesEnum getSalesClassesEnum (Integer key){
        if (key == null){
            return null;
        }
        for (SalesClassesEnum temp : SalesClassesEnum.values()){
            if (key.equals(temp.getKey())){
                return temp;
            }
        }
        return null;
    }

    public Integer getKey (){
        return key;
    }

    public String getValue (){
        return value;
    }
}
