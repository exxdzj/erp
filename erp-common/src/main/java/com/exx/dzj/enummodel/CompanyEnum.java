package com.exx.dzj.enummodel;

/**
 * @author yangyun
 * @create 2019-06-04-11:35
 */
public enum CompanyEnum {
    ESZ("201905081454131", "E行销深圳"),
    EPX("201905081720301", "E行销培训"),
    EWZ("201905081454261", "E行销温州"),
    EXA("201905081454341", "E行销西安"),
//    EBJ("e-bj", "E行销北京"),
    ESCIENCE("201905081720451", "E行销技术");

    private String code;
    private String value;

    private CompanyEnum (String code, String value){
        this.code = code;
        this.value = value;
    }

    public String getCode (){
        return code;
    }

    public String getValue (){
        return value;
    }

    public static CompanyEnum getCompanyEnum (String code){
        if (code == null){
            return null;
        }

        for (CompanyEnum temp : CompanyEnum.values()){
            if (temp.getCode().equals(code)){
                return temp;
            }
        }
        return null;
    }
}
