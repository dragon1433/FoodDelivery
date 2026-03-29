package com.fooddelivery.app.data.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "dishes")
public class Dish {
    @PrimaryKey 
    private long id;
    private long restaurantId;
    private String name;
    private String description;
    private String imageUrl;
    private double price;
    private Double originalPrice;
    private String categoryName;
    private int monthSales;
    private int stock;
    private boolean isRecommended;
    private double packingFee;

    public Dish(long id, long restaurantId, String name, String description, String imageUrl,
                double price, Double originalPrice, String categoryName, int monthSales,
                int stock, boolean isRecommended, double packingFee) {
        this.id = id;
        this.restaurantId = restaurantId;
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.price = price;
        this.originalPrice = originalPrice;
        this.categoryName = categoryName;
        this.monthSales = monthSales;
        this.stock = stock;
        this.isRecommended = isRecommended;
        this.packingFee = packingFee;
    }
    
    // 简化构造函数，用于 Mock 数据
    public Dish(long id, long restaurantId, String name, String description, 
                double price, String imageUrl, boolean isRecommended) {
        this.id = id;
        this.restaurantId = restaurantId;
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.price = price;
        this.originalPrice = price;
        this.categoryName = "";
        this.monthSales = 0;
        this.stock = 100;
        this.isRecommended = isRecommended;
        this.packingFee = 0;
    }
    
    // 无参构造函数
    public Dish() {}

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }
    
    public long getRestaurantId() { return restaurantId; }
    public void setRestaurantId(long restaurantId) { this.restaurantId = restaurantId; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
    
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    
    public Double getOriginalPrice() { return originalPrice; }
    public void setOriginalPrice(Double originalPrice) { this.originalPrice = originalPrice; }
    
    public String getCategoryName() { return categoryName; }
    public void setCategoryName(String categoryName) { this.categoryName = categoryName; }
    
    public int getMonthSales() { return monthSales; }
    public void setMonthSales(int monthSales) { this.monthSales = monthSales; }
    
    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }
    
    public boolean isRecommended() { return isRecommended; }
    public void setRecommended(boolean recommended) { isRecommended = recommended; }
    
    public double getPackingFee() { return packingFee; }
    public void setPackingFee(double packingFee) { this.packingFee = packingFee; }
}
