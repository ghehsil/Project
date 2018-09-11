package com.ls.service;

import com.ls.entity.User;

import java.util.List;

public interface UserService {
    int userRegister(User user);

    //根据用户名、密码查询User
    User userLogin(User user);

    List<User> getPageList(Integer showNum, Integer pageNum);

    Integer getTotalNum();

    List<User> repeatRegister(String userName);

    int deleteUserById(String md5Id);
}
