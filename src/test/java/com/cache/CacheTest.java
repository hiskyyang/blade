package com.cache;

import com.user.service.UserService;
import com.user.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

@ContextConfiguration("classpath:/spring/spring-test.xml")
public class CacheTest extends AbstractTestNGSpringContextTests {
    @Autowired
    UserService service;

    @Test
    public void test() throws Exception {
        User user = new User();
        user.setName("CreateTest");
        user.setPassword("123");
        user.setLevel("1");

        user = service.add(user);

        User user1 = service.get(user);
        User user2 = service.get(user);

        Assert.assertEquals(user1 == user2, true);

        int effectRows = service.delete(user);
        Assert.assertEquals(effectRows == 1, true);
    }
}
