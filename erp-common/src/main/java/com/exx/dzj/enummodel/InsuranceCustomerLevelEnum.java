package com.exx.dzj.enummodel;

/**
 * @author yangyun
 * @create 2019-05-28-16:05
 */
public enum InsuranceCustomerLevelEnum {
    GOVERNOR("zj01", "总监"),
    MANAGER("jl02", "经理"),
    DIRECTOR("zr03", "主任"),
    SALE("ywy04", "业务员"),
    OTHER("qt05", "其他");

    private final String code;
    private final String value;

    private InsuranceCustomerLevelEnum(String code, String value){
        this.code = code;
        this.value = value;
    }

    public String getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }

    public static InsuranceCustomerLevelEnum getInsuranceCustomerLevelEnum (String code){
        if (code == null){
            return null;
        }

        for (InsuranceCustomerLevelEnum temp : InsuranceCustomerLevelEnum.values()){
            if (temp.getCode().equals(code)){
                return temp;
            }
        }
        return null;
    }
}
