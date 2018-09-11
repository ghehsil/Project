package com.ls.dao;

import com.ls.entity.Goods;
import com.ls.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDao {
    //注册
    int userRegister(@Param("user") User user);

    int addMd5Id(@Param("user") User user);

    //根据用户名、密码查询User
    User userLogin(@Param("user") User user);

    //重复注册
    List<User> repeatRegister(@Param("userName") String userName);

    List<User> getPageList(@Param("begin") int begin, @Param("pageNum") int pageNum);

    Integer getTotalNum();

    int deleteUserById(@Param("md5Id") String md5Id);
}
