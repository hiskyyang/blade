package com.basic.cache.aspect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义注解
 * <p>
 * 在Service方法上加此注解，则可在根据主键更新单条数据库记录后，自动更新该主键对应的缓存数据
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface UpdateCache {
    /**
     * 缓存失效时间，默认为60秒
     *
     * @return
     */
    int expiration() default 60;
}
