package com.ls.dto;

public class paymentDetail {
    private String name;
    private Double price;
    private Integer number;
    private Double totalPrice;
    private String picture;
    private String detail;

    public paymentDetail(String name, Double price, Integer number, Double totalPrice, String picture, String detail) {
        this.name = name;
        this.price = price;
        this.number = number;
        this.totalPrice = totalPrice;
        this.picture = picture;
        this.detail = detail;
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

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
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
