package com.basic.service;

import com.basic.dao.BaseDao;
import com.basic.cache.aspect.DeleteCache;
import com.basic.cache.aspect.GetFromCache;
import com.basic.cache.aspect.UpdateCache;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;

public class BaseService<T extends Serializable> {
    Logger log = Logger.getLogger(this.getClass());

    @Autowired
    protected BaseDao<T> baseDao;

    @UpdateCache
    public T add(T t) {
        try {
            return (T) baseDao.add(t);
        } catch (Exception e) {
            throw new RuntimeException("添加失败");
        }
    }

    @GetFromCache
    public T get(T t) {
        return (T) baseDao.get(t);
    }

    @DeleteCache
    public int delete(T t) {
        try {
            return baseDao.delete(t);
        } catch (Exception e) {
            throw new RuntimeException("删除失败");
        }
    }

    @UpdateCache
    public T update(T t) {
        try {
            int affectedRows = baseDao.update(t);
            return baseDao.get(t);
        } catch (Exception e) {
            throw new RuntimeException("更新失败");
        }
    }

    //TODO
    public List<T> list(T t) {
        return baseDao.list(t);
    }
}
