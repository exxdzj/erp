package com.exx.dzj.unique;

/**
 * @Author
 * @Date 2019/1/8 0008 11:03
 * @Description  ID生成器接口, 用于生成全局唯一的ID流水号
 */
public interface IdGenerator {

    /**
     * 生成下一个不重复的流水号
     * @return
     */
    String next();
}
