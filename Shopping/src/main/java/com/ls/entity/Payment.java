package com.ls.entity;

public class Payment {
    private Long Id;
    private Long shoppingCartId;
    private Double totalPrice;

    public Payment(Long id, Long shoppingCartId, Double totalPrice) {
        Id = id;
        this.shoppingCartId = shoppingCartId;
        this.totalPrice = totalPrice;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Long getShoppingCartId() {
        return shoppingCartId;
    }

    public void setShoppingCartId(Long shoppingCartId) {
        this.shoppingCartId = shoppingCartId;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
