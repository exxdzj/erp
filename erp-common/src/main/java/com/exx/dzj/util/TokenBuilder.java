package com.exx.dzj.util;

import org.apache.commons.lang3.StringUtils;

import java.util.UUID;

/**
 * @Author
 * @Date 2019/1/8 0008 15:48
 * @Description 非线程安全单例
 */
public class TokenBuilder {
    public static UUIDCreator uuidCreator = TokenBuilder.UUIDCreator.getInstance();

    public static interface TokenCreator{
        public String create();
    }


    public static class UUIDCreator implements TokenCreator{

        private static UUIDCreator ins = new UUIDCreator();

        public 	static UUIDCreator  getInstance(){
            return ins;
        }

        @Override
        public String create() {
            return UUID.randomUUID().toString().replace("-", "");
        }

    }
}
