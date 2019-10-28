package com.exx.dzj.share;

import com.exx.dzj.constant.CommonConstant;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @ClassName TokenCache
 * @Description: 缓存用户登录 token
 * @Author yangyun
 * @Date 2019/10/28 0028 15:23
 * @Version 1.0
 **/
public class TokenCache {

    private volatile static TokenCache instance = null;

    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    private volatile Map<String, Object> android = new HashMap<>();
    private volatile Map<String, Object> pc = new HashMap<>();

    public static TokenCache getTokenCache (){
        if (instance == null){
            synchronized (TokenCache.class) {
                if (instance == null){
                    instance = new TokenCache();
                }
            }
        }
        return instance;
    }

    public Map<String, Object> getCache(String type){
        return StringUtils.equalsIgnoreCase(type, CommonConstant.ANDROID_TYPE) ? android : pc;
    }

    public void put (String key, String value, Map<String, Object> cache){
        try {
            lock.writeLock().lock();
            cache.put(key, value);
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            lock.writeLock().unlock();
        }
    }

    public Object get(String key, Map<String, Object> cache){
        try {
            lock.readLock().lock();
            return cache.get(key);
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            lock.readLock().unlock();
        }
        return null;
    }
}
