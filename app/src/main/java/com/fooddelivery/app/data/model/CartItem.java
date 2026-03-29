package com.fooddelivery.app.data.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "cart_items")
public class CartItem {
    @PrimaryKey 
    private long id;
    private long dishId;
    private long restaurantId;
    private String name;
    private double price;
    private double packingFee;
    private String imageUrl;
    private int quantity;
    private String categoryName;

    public CartItem(long id, long dishId, long restaurantId, String name, double price,
                    double packingFee, String imageUrl, int quantity, String categoryName) {
        this.id = id;
        this.dishId = dishId;
        this.restaurantId = restaurantId;
        this.name = name;
        this.price = price;
        this.packingFee = packingFee;
        this.imageUrl = imageUrl;
        this.quantity = quantity;
        this.categoryName = categoryName;
    }
    
    // 无参构造函数
    public CartItem() {}
    
    // 辅助方法，用于从 Dish 创建
    public void setFromDish(Dish dish, int quantity) {
        this.dishId = dish.getId();
        this.restaurantId = dish.getRestaurantId();
        this.name = dish.getName();
        this.price = dish.getPrice();
        this.imageUrl = dish.getImageUrl();
        this.quantity = quantity;
        this.categoryName = dish.getCategoryName();
        this.packingFee = dish.getPackingFee();
    }

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }
    
    public long getDishId() { return dishId; }
    public void setDishId(long dishId) { this.dishId = dishId; }
    
    public long getRestaurantId() { return restaurantId; }
    public void setRestaurantId(long restaurantId) { this.restaurantId = restaurantId; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    
    public double getPackingFee() { return packingFee; }
    public void setPackingFee(double packingFee) { this.packingFee = packingFee; }
    
    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
    
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    
    public String getCategoryName() { return categoryName; }
    public void setCategoryName(String categoryName) { this.categoryName = categoryName; }
}
