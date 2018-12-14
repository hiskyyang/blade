package com.basic.cache.service;

import com.code.service.CodeService;
import com.code.vo.Code;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class CacheManager {
    Logger log = Logger.getLogger(this.getClass());
    private int codeExpiration = Integer.MAX_VALUE;
    @Autowired
    @Qualifier("memoryCacheService")
    private CacheService cacheService;
    @Autowired
    CodeService codeService;

    public Object get(String key, Class t) {
        Object object = cacheService.get(key, t);
        return object;
    }

    public void put(String key, Object value, int expiration) {
        cacheService.put(key, value, expiration);
    }

    public void remove(String key) {
        cacheService.remove(key);
    }

    public List<Code> getCodes(String type) {
        String key = constructCodesKey(type);

        if (cacheService.contains(key)) {
            log.info(String.format("Find the codes from cache, key=%s", key));
            return (List<Code>) cacheService.get(key, Code.class);
        }

        List<Code> codes = codeService.list(new Code(type));
        cacheService.put(key, codes, codeExpiration);
        return codes;
    }

    public Map<String, String> getCodeMap(String type) {
        List<Code> list = getCodes(type);
        return list.stream().collect(Collectors.toMap(Code::getCode, Code::getValue));
    }

    private String constructCodesKey(String type) {
        return new StringBuilder().append("code_").append(type).toString();
    }

    private String constructCodesKey(String type, String code) {
        return new StringBuilder().append("code_").append(type).append("_").append(code).toString();
    }

    public List<Code> getCodes(String type, String code) {
        String key = constructCodesKey(type, code);

        if (cacheService.contains(key)) {
            log.info(String.format("Find the codes from cache, key=%s", key));
            return (List<Code>) cacheService.get(key, Code.class);
        }

        List<Code> codes = codeService.list(new Code(type, code));
        cacheService.put(key, codes, codeExpiration);
        return codes;
    }

    public void clearCodes() {
        log.info("Clear the codes from cache");
        cacheService.clearCodes();
    }
}
