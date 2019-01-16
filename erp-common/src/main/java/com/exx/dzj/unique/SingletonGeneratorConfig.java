package com.exx.dzj.unique;

import java.util.Optional;

/**
 * @author yangyun
 * @create 2019-01-16-17:37
 *
 */
public class SingletonGeneratorConfig {
    private static DefaultIdGenerator defaultIdGenerator = null;

    public static synchronized DefaultIdGenerator getSingleton(){
        if(!Optional.ofNullable(defaultIdGenerator).isPresent()){
            defaultIdGenerator = new DefaultIdGenerator();
        }
        return  defaultIdGenerator;
    }
}
