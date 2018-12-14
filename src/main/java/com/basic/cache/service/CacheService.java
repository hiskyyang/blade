package com.basic.cache.service;

public interface CacheService {
    Object get(String key, Class t);

    void put(String key, Object value, int expiration);

    void remove(String key);

    boolean contains(String key);

    void clearCodes();
}
