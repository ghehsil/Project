package com.ls.entity;

public class Goods {
    private Long Id;
    private String md5Id;
    private String name;
    private Double price;
    private Integer number;
    private String picture;
    private String detail;
    private Long goodsTypeId;
    private Integer enable;

    //插入
    public Goods(String name, Long goodsTypeId, Double price, Integer number, String picture, String detail) {
        this.name = name;
        this.goodsTypeId = goodsTypeId;
        this.price = price;
        this.number = number;
        this.picture = picture;
        this.detail = detail;
    }

    //有图更新
    public Goods(Long Id, String name, Long goodsTypeId, Double price, Integer number, String picture, String detail) {
        this.Id = Id;
        this.name = name;
        this.goodsTypeId = goodsTypeId;
        this.price = price;
        this.number = number;
        this.picture = picture;
        this.detail = detail;
    }

    //无图更新
    public Goods(Long Id, String name, Long goodsTypeId, Double price, Integer number, String detail) {
        this.Id = Id;
        this.name = name;
        this.goodsTypeId = goodsTypeId;
        this.price = price;
        this.number = number;
        this.detail = detail;
    }

    //总的构造器
    public Goods(Long Id, String md5Id, String name, Double price, Integer number, String picture, String detail, Long goodsTypeId, Integer enable) {
        this.Id = Id;
        this.md5Id = md5Id;
        this.name = name;
        this.price = price;
        this.number = number;
        this.picture = picture;
        this.detail = detail;
        this.goodsTypeId = goodsTypeId;
        this.enable = enable;
    }


    public String getMd5Id() {
        return md5Id;
    }

    public void setMd5Id(String md5Id) {
        this.md5Id = md5Id;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Long getGoodsTypeId() {
        return goodsTypeId;
    }

    public void setGoodsTypeId(Long goodsTypeId) {
        this.goodsTypeId = goodsTypeId;
    }
}
