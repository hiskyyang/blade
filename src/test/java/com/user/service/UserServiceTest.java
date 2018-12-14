package com.user.service;

import com.user.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

@ContextConfiguration("classpath:/spring/spring-test.xml")
public class UserServiceTest extends AbstractTestNGSpringContextTests {
    @Autowired
    UserService service;

    @Test
    public void test() throws Exception {
        User user = new User();
        user.setUserId(21);
        user.setName("CreateTest");
        user.setPassword("123");
        user.setLevel("1");

        user = service.add(user);

        user.setName("UpdateTest");
        service.update(user);

        user = service.get(user);

        int effectRows = service.delete(user);
        Assert.assertEquals(effectRows == 1, true);
    }
}
