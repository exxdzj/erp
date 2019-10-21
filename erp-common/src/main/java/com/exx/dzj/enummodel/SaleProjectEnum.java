package com.exx.dzj.enummodel;

import org.apache.commons.lang3.StringUtils;

/**
 * @author yangyun
 * @create 2019-10-21-11:46
 */
public enum SaleProjectEnum {
    ESZ("A001", "E行销深圳"),
    EJY("A002", "E行销精英论坛"),
    EZB("A003", "E行销正版书"),
    EZZ("A004", "E行销杂志"),
    EAXY("A005", "E行销爱西柚"),
    EBJ("A006", "E行销北京"),
    EWZ("A007", "E行销温州"),
    EAPP("A008", "E行销APP"),
    EYZK("A1", "样品/赠品/客户服务"),
    ESTB("A2", "S损失单/退单/补货单"),
    ECB("CB", "【成本中心】"),
    ECB1("CB1", "【成本中心】1"),
    EGZLS("A009", "E行销广州零售店"),
    EXALS("A010", "E行销西安零售店");

    private String key;
    private String name;

    private SaleProjectEnum(String key, String name){
        this.key = key;
        this.name = name;
    }

    public String getkey(){
        return key;
    }

    public String getName(){
        return name;
    }

    public static SaleProjectEnum getSaleProjectEnum(String key){
        if (StringUtils.isBlank(key)){
            return null;
        }

        for (SaleProjectEnum temp : SaleProjectEnum.values()){
            if (StringUtils.equals(temp.getkey(), key)){
                return temp;
            }
        }

        return null;
    }
}
