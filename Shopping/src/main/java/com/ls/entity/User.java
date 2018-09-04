package com.ls.entity;

import java.util.Date;

public class User {
    private Long Id;
    private String md5Id;

    private String userName;
    private String password;
    //头像
    private String portrait;
    private Long phone;
    private Date birth;
    private Integer type;

    public User() {
    }

    public User(Long id, String md5Id, String username, String password, String portrait, Long phone, Date birth, Integer type) {
        this.Id = id;
        this.md5Id = md5Id;
        this.userName = username;
        this.password = password;
        this.portrait = portrait;
        this.phone = phone;
        this.birth = birth;
        this.type = type;
    }

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public User(String username, String password, String portrait, Long phone, Date birth) {
        this.userName = username;
        this.password = password;
        this.portrait = portrait;
        this.phone = phone;
        this.birth = birth;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getMd5Id() {
        return md5Id;
    }

    public void setMd5Id(String md5Id) {
        this.md5Id = md5Id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
