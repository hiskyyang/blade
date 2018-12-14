package com.basic.cache.aspect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义注解
 * <p>
 * 在Service方法上加此注解，则可在根据主键查询单条数据库记录前，优先从缓存查询主键对应的数据。
 * 若缓存没有命中，则继续调用Service的方法查询数据库，若数据库返回不为空，则自动将返回结果加载的缓存中。
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface GetFromCache {
    /**
     * 缓存失效时间，默认为60秒
     *
     * @return
     */
    int expiration() default 60;
}
