package com.user.dao;

import com.user.vo.User;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

@ContextConfiguration("classpath:/spring/spring-test.xml")
public class UserDaoTest extends AbstractTestNGSpringContextTests {
    @Autowired
    UserDao dao;

    @Test
    public void test() throws Exception {
        User user = new User();
        user.setName("Tester");
        user.setPassword("123");
        user.setLevel("1");
        user = dao.add(user);

        user.setName("Updated");
        dao.update(user);

        user = dao.get(user);
        System.out.println(user);

        int effectRows = dao.delete(user);
        Assert.assertEquals(effectRows == 1, true);
    }
}
