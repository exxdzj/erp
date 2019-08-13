package com.exx.dzj.enummodel;

/**
 * @author yangyun
 * @create 2019-05-28-16:05
 */
public enum InsuranceCustomerLevelEnum {
    GOVERNOR("zj01", "总监"),
    MANAGER("jl02", "经理"),
    DIRECTOR("zr03", "主管"),
    SALE("ywy04", "业务员"),
    NQLDA("nqlda06", "内勤领导/A类"),
    NQJLB("nqjlb07", "内勤经理/B类"),
    NQZRC("nqzrc08", "内勤主任/C类"),
    NQKHD("nqkhd09", "内勤客户/D类");
//    OTHER("qt05", "其他");

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
