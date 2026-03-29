package com.fooddelivery.app.data.model;

public enum OrderStatus {
    PENDING("待支付"),
    PAID("已支付"),
    PREPARING("制作中"),
    DELIVERING("配送中"),
    DELIVERED("已送达"),
    COMPLETED("已完成"),
    CANCELLED("已取消");
    
    private final String displayName;
    
    OrderStatus(String displayName) {
        this.displayName = displayName;
    }
    
    public String getDisplayName() {
        return displayName;
    }
}
