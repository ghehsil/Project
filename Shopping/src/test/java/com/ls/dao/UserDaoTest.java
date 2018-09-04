package com.ls.dao;

import com.ls.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-dao.xml")
public class UserDaoTest {
    @Resource
    private UserDao userDao;

    @Test
    public void userRegister() throws Exception {
        /*Date birth=new Date();
        DateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        birth=format.parse("2000-02-02");
        User user=new User("Simon","123456","Simon",11111111111L, birth,10001,10002,10003);
        System.out.println(userDao.userRegister(user));*/
    }

    @Test
    public void userLogin() throws Exception {
        User user=new User("Simon","123456");
        System.out.println(userDao.userLogin(user).getBirth());
    }

}