package com.code.service;

import com.basic.service.BaseService;
import com.code.dao.CodeDao;
import com.code.vo.Code;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CodeService extends BaseService<Code> {
    Logger log = Logger.getLogger(this.getClass());

    public List<String> types() {
        return ((CodeDao) baseDao).types();
    }

}
