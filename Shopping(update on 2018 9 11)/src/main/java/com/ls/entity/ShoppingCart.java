package com.ls.entity;

public class ShoppingCart {
    private Long Id;
    private Long goodsId;
    private Long userId;
    //购物车商品数量
    private Integer number;
    //支付状态
    private Integer state;

    public ShoppingCart(Long Id, Long goodsId, Long userId, Integer number, Integer state) {
        this.Id = Id;
        this.goodsId = goodsId;
        this.userId = userId;
        this.number = number;
        this.state = state;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
