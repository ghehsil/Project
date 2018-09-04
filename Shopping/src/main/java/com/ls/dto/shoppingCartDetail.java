package com.ls.dto;

public class shoppingCartDetail {
    private Long goodsId;
    private String name;
    private Double price;
    private Integer number;
    private String picture;
    private String detail;

    public shoppingCartDetail(Long goodsId, String name, Double price, Integer number, String picture, String detail) {
        this.goodsId = goodsId;
        this.name = name;
        this.price = price;
        this.number = number;
        this.picture = picture;
        this.detail = detail;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
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
}
