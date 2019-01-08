package com.exx.dzj.unique;

/**
 * @Author
 * @Date 2019/1/8 0008 11:06
 * @Description
 */
public class DefaultIdGeneratorConfig implements IdGeneratorConfig{
    @Override
    public String getSplitString() {
        return "";
    }

    @Override
    public int getInitial() {
        return 1;
    }

    @Override
    public String getPrefix() {
        return "";
    }

    @Override
    public int getRollingInterval() {
        return 1;
    }
}
