package com.ls.entity;

public class goodsType {
    private Long Id;
    private String typeName;

    public goodsType(Long id, String typeName) {
        Id = id;
        this.typeName = typeName;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }
}
