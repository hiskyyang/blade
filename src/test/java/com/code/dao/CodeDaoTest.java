package com.code.dao;

import com.code.vo.Code;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

@ContextConfiguration("classpath:/spring/spring-test.xml")
public class CodeDaoTest extends AbstractTestNGSpringContextTests {
    @Autowired
    CodeDao dao;

    @Test
    public void test() throws Exception {
        Code code = new Code();
        code.setType("Province");
        code.setCode("Hunan");
        code.setValue("湖南");
        code.setSequence(0);
        code = dao.add(code);

        code.setValue("湖南省");
        dao.update(code);

        code = dao.get(code);
        System.out.println(code);

        int effectRows = dao.delete(code);
        Assert.assertEquals(effectRows == 1, true);
    }
}
