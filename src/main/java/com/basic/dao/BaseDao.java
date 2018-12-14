package com.basic.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;


/**
 * @author yangzhenglin
 * @time 2013-3-19 下午12:18:10
 */
public class BaseDao<T extends Serializable> {
    @Autowired
    protected SqlSession sqlSession;

    public T add(T t) {
        String sqlId = getNameSpace(t) + ".add";
        sqlSession.insert(sqlId, t);
        return (T) t;
    }

    public T get(T t) {
        String sqlId = getNameSpace(t) + ".get";
        Object obj = sqlSession.selectOne(sqlId, t);
        if (obj != null) {
            return (T) obj;
        }
        return null;
    }

    public int delete(T t) {
        String sqlId = getNameSpace(t) + ".delete";
        return sqlSession.delete(sqlId, t);
    }

    public int update(T t) {
        String sqlId = getNameSpace(t) + ".update";
        return sqlSession.update(sqlId, t);
    }

    public List<T> list(T t) {
        String sqlId = getNameSpace(t) + ".list";
        return sqlSession.selectList(sqlId, t);
    }

    protected String getNameSpace(T t) {
        String clazz = t.getClass().toString();
        return clazz.substring(clazz.lastIndexOf(".") + 1);
    }
}
