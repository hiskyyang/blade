package com.basic.cache.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//@Component(value = "doubleCacheService")
public class DoubleCacheServiceImpl implements CacheService {
    private boolean enableRedisCache = false;
    private boolean enableMemoryCache = true;

    @Autowired
    private CacheService redisCacheService;
    @Autowired
    private CacheService memoryCacheService;

    public Object get(String key, Class t) {
        Object object = null;
        if (enableRedisCache) {
            object = redisCacheService.get(key, t);
        }
        if (object != null) {
            return object;
        }

        if (enableMemoryCache) {
            object = memoryCacheService.get(key, t);
        }
        return object;
    }

    public void put(String key, Object value, int expiration) {
        if (enableRedisCache) {
            redisCacheService.put(key, value, expiration);
        }
        if (enableMemoryCache) {
            memoryCacheService.put(key, value, expiration);
        }
    }

    public void remove(String key) {
        if (enableRedisCache) {
            redisCacheService.remove(key);
        }
        if (enableMemoryCache) {
            memoryCacheService.remove(key);
        }
    }

    public boolean contains(String key) {
        boolean contains = false;
        if (enableRedisCache) {
            contains = redisCacheService.contains(key);
        }
        if (true == contains) {
            return true;
        }
        if (enableMemoryCache) {
            contains = memoryCacheService.contains(key);
        }
        return contains;
    }

    public void clearCodes() {
        if (enableRedisCache) {
            redisCacheService.clearCodes();
        }
        if (enableMemoryCache) {
            memoryCacheService.clearCodes();
        }
    }

}
