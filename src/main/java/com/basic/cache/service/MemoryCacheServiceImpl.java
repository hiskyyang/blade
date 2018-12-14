package com.basic.cache.service;

import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Component(value = "memoryCacheService")
public class MemoryCacheServiceImpl implements CacheService {
    private ConcurrentHashMap cache = new ConcurrentHashMap();

    public Object get(String key, Class t){
        Object value = cache.get(key);
        return value;
    }

    public void put(String key, Object value, int expiration) {
        cache.put(key, value);
    }

    public void remove(String key) {
        cache.remove(key);
    }

    public boolean contains(String key) {
        return cache.containsKey(key);
    }

    public void clearCodes() {
        Set<String> keys = cache.keySet();
        for (String key : keys) {
            if (key.indexOf("code") > -1) {
                cache.remove(key);
            }
        }
    }
}
