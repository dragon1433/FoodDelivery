package com.fooddelivery.app.data.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "restaurants")
public class Restaurant {
    @PrimaryKey 
    private long id;
    private String name;
    private String englishName;
    private String imageUrl;
    private float rating;
    private int monthlySales;
    private String deliveryTime;
    private double deliveryFee;
    private double minPrice;
    private double distance;
    private String categories;
    private String description;
    private boolean isFavorite;

    public Restaurant(long id, String name, String englishName, String imageUrl, float rating, int monthlySales,
                     String deliveryTime, double deliveryFee, double minPrice, double distance,
                     String categories, String description, boolean isFavorite) {
        this.id = id;
        this.name = name;
        this.englishName = englishName;
        this.imageUrl = imageUrl;
        this.rating = rating;
        this.monthlySales = monthlySales;
        this.deliveryTime = deliveryTime;
        this.deliveryFee = deliveryFee;
        this.minPrice = minPrice;
        this.distance = distance;
        this.categories = categories;
        this.description = description;
        this.isFavorite = isFavorite;
    }

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getEnglishName() { return englishName; }
    public void setEnglishName(String englishName) { this.englishName = englishName; }
    
    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
    
    public float getRating() { return rating; }
    public void setRating(float rating) { this.rating = rating; }
    
    public int getMonthlySales() { return monthlySales; }
    public void setMonthlySales(int monthlySales) { this.monthlySales = monthlySales; }
    
    public String getDeliveryTime() { return deliveryTime; }
    public void setDeliveryTime(String deliveryTime) { this.deliveryTime = deliveryTime; }
    
    public double getDeliveryFee() { return deliveryFee; }
    public void setDeliveryFee(double deliveryFee) { this.deliveryFee = deliveryFee; }
    
    public double getMinPrice() { return minPrice; }
    public void setMinPrice(double minPrice) { this.minPrice = minPrice; }
    
    public double getDistance() { return distance; }
    public void setDistance(double distance) { this.distance = distance; }
    
    public String getCategories() { return categories; }
    public void setCategories(String categories) { this.categories = categories; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public boolean isFavorite() { return isFavorite; }
    public void setFavorite(boolean favorite) { isFavorite = favorite; }
}
