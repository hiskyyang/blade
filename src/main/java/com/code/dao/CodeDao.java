package com.code.dao;

import com.basic.dao.BaseDao;
import com.code.vo.Code;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CodeDao extends BaseDao<Code> {
    public List<String> types(){
        return sqlSession.selectList("Code.types");
    }

    public List<Code> parents(Code code){
        return sqlSession.selectList("Code.parents", code);
    }
}
