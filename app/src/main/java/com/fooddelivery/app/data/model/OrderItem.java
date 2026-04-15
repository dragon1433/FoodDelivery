package com.fooddelivery.app.data.model;

import java.io.Serializable;

public class OrderItem implements Serializable {
    private long dishId;
    private String name;
    private double price;
    private int quantity;
    private double packingFee;

    public OrderItem(long dishId, String name, double price, int quantity, double packingFee) {
        this.dishId = dishId;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.packingFee = packingFee;
    }
    
    // 无参构造函数
    public OrderItem() {}
    
    // 辅助方法，别名用于兼容
    public void setDishName(String name) {
        this.name = name;
    }
    
    public String getDishName() {
        return this.name;
    }
    
    public void setDishImage(String image) {
        // OrderItem 中没有图片字段，忽略
    }

    public long getDishId() { return dishId; }
    public void setDishId(long dishId) { this.dishId = dishId; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    
    public double getPackingFee() { return packingFee; }
    public void setPackingFee(double packingFee) { this.packingFee = packingFee; }
}
