package com.ls.service.Impl;

import com.ls.dao.UserDao;
import com.ls.dao.cache.UserRedisDao;
import com.ls.entity.Goods;
import com.ls.entity.User;
import com.ls.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private UserRedisDao userRedisDao;

    //混淆md5
    private final String slat = "da#d$23*6b%s^da5sjda57+a*sf-a85-f9";

    //md5加密
    private String getMd5(Long Id) {
        String base = Id + "/" + slat;
        String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
        return md5;
    }

    //注册
    //先插基本信息,再插md5加密的Id,事务不成功回滚。
    @Transactional
    public int userRegister(User user) {
        int temp = userDao.userRegister(user);
        if (temp < 1) {
            return 0;
        }
        User md5User = userDao.repeatRegister(user.getUserName()).get(0);
        md5User.setMd5Id(getMd5(md5User.getId()));
        userDao.addMd5Id(md5User);
        return temp;
    }

    public List<User> repeatRegister(String userName) {
        return userDao.repeatRegister(userName);
    }

    public User userLogin(User user) {
        //得到redis里的对象
        User redisUser = userRedisDao.getUser(user.getUserName());
        if (redisUser == null) {
            //获取数据库里的对象
            redisUser = userDao.userLogin(user);
            userRedisDao.putUser(redisUser);
            return redisUser;
        }
        return redisUser;
    }

    public List<User> getPageList(Integer showNum, Integer pageNum) {
        int begin = (showNum - 1) * pageNum;
        return userDao.getPageList(begin, pageNum);
    }

    public Integer getTotalNum() {
        return userDao.getTotalNum();
    }

    public int deleteUserById(String md5Id) {
        return userDao.deleteUserById(md5Id);
    }
}
