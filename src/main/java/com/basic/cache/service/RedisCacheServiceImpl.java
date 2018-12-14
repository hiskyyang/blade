package com.basic.cache.service;

import com.code.vo.Code;
import com.user.vo.User;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONTokener;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

//@Component(value = "redisCacheService")
public class RedisCacheServiceImpl implements CacheService {
    private Jedis cache = new Jedis("localhost", 6379);

    public Object get(String key, Class t) {
        Object jsonString = cache.get(key);
        Object obj = new JSONTokener(jsonString.toString()).nextValue();

        if (obj instanceof JSONObject) {
            return JSONObject.toBean(JSONObject.fromObject(jsonString), t);
        }

        List list = new ArrayList();
        JSONArray jsonArray = (JSONArray) obj;
        for (int i = 0; i < jsonArray.size(); i++) {
            Object object = JSONObject.toBean(jsonArray.getJSONObject(i), t);
            list.add(object);
        }
        return list;
    }

    public void put(String key, Object value, int expiration) {
        String jsonString = null;
        if (value instanceof List) {
            jsonString = JSONArray.fromObject(value).toString();
        } else {
            jsonString = JSONObject.fromObject(value).toString();
        }
        cache.set(key, jsonString);
        cache.expire(key, expiration);
    }

    public void remove(String key) {
        cache.del(key);
    }

    public boolean contains(String key) {
        return cache.exists(key);
    }

    public void clearCodes() {
        Set<String> keys = cache.keys("code*");
        for (String key : keys) {
            cache.del(key);
        }
    }

    public static void main(String args[]) {
        RedisCacheServiceImpl cacheService = new RedisCacheServiceImpl();

        Code code = new Code("type", "abc");
        String key = "code_type_abc";
        cacheService.put(key, code, 10);
        Object object = cacheService.get(key, Code.class);

        List<Code> codes = new ArrayList<Code>();
        codes.add(code);
        codes.add(new Code("type", "cde"));
        key = "code_type";
        cacheService.put(key, codes, 10);
        object = cacheService.get(key, Code.class);
    }
}
