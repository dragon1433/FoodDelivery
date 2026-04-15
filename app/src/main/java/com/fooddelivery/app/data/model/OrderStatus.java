package com.fooddelivery.app.data.model;

public enum OrderStatus {
    PENDING("Pending Payment"),
    PAID("Paid"),
    PREPARING("Preparing"),
    DELIVERING("Delivering"),
    DELIVERED("Delivered"),
    COMPLETED("Completed"),
    CANCELLED("Cancelled");
    
    private final String displayName;
    
    OrderStatus(String displayName) {
        this.displayName = displayName;
    }
    
    public String getDisplayName() {
        return displayName;
    }
}
