package com.fooddelivery.app.data.model;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity(tableName = "orders")
public class Order implements Serializable {
    @PrimaryKey(autoGenerate = true) 
    private long id;
    private long userId;
    private String orderNo;
    private long restaurantId;
    private String restaurantName;
    private double totalAmount;
    private double deliveryFee;
    private double packingFee;
    private double discountAmount;
    private double actualAmount;
    private OrderStatus status;
    private Date createTime;
    private Date payTime;
    private Date deliveryTime;
    private Date completeTime;
    private long addressId;
    private String addressDetail;
    private String receiverName;
    private String receiverPhone;
    private List<OrderItem> items;
    private String riderName;
    private String riderPhone;
    private String estimatedDeliveryTime;
    private float rating;
    private String reviewComment;
    private Date reviewTime;
    
    // No-arg constructor for Room
    public Order() {}
    
    // Helper method for backward compatibility
    public Date getOrderTime() {
        return createTime;
    }
    
    public void setOrderTime(String orderTime) {
        // This method is kept for compatibility, not actually used
    }

    // Full constructor - ignored by Room
    @Ignore
    public Order(long id, String orderNo, long restaurantId, String restaurantName,
                 double totalAmount, double deliveryFee, double packingFee, double discountAmount,
                 double actualAmount, OrderStatus status, Date createTime, Date payTime,
                 Date deliveryTime, Date completeTime, long addressId, String addressDetail,
                 String receiverName, String receiverPhone, List<OrderItem> items,
                 String riderName, String riderPhone, String estimatedDeliveryTime) {
        this.id = id;
        this.orderNo = orderNo;
        this.restaurantId = restaurantId;
        this.restaurantName = restaurantName;
        this.totalAmount = totalAmount;
        this.deliveryFee = deliveryFee;
        this.packingFee = packingFee;
        this.discountAmount = discountAmount != 0.0 ? discountAmount : 0.0;
        this.actualAmount = actualAmount;
        this.status = status;
        this.createTime = createTime;
        this.payTime = payTime;
        this.deliveryTime = deliveryTime;
        this.completeTime = completeTime;
        this.addressId = addressId;
        this.addressDetail = addressDetail;
        this.receiverName = receiverName;
        this.receiverPhone = receiverPhone;
        this.items = items;
        this.riderName = riderName;
        this.riderPhone = riderPhone;
        this.estimatedDeliveryTime = estimatedDeliveryTime;
    }

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }
    
    public long getUserId() { return userId; }
    public void setUserId(long userId) { this.userId = userId; }
    
    public String getOrderNo() { return orderNo; }
    public void setOrderNo(String orderNo) { this.orderNo = orderNo; }
    
    public long getRestaurantId() { return restaurantId; }
    public void setRestaurantId(long restaurantId) { this.restaurantId = restaurantId; }
    
    public String getRestaurantName() { return restaurantName; }
    public void setRestaurantName(String restaurantName) { this.restaurantName = restaurantName; }
    
    public double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(double totalAmount) { this.totalAmount = totalAmount; }
    
    public double getDeliveryFee() { return deliveryFee; }
    public void setDeliveryFee(double deliveryFee) { this.deliveryFee = deliveryFee; }
    
    public double getPackingFee() { return packingFee; }
    public void setPackingFee(double packingFee) { this.packingFee = packingFee; }
    
    public double getDiscountAmount() { return discountAmount; }
    public void setDiscountAmount(double discountAmount) { this.discountAmount = discountAmount; }
    
    public double getActualAmount() { return actualAmount; }
    public void setActualAmount(double actualAmount) { this.actualAmount = actualAmount; }
    
    public OrderStatus getStatus() { return status; }
    public void setStatus(OrderStatus status) { this.status = status; }
    
    public Date getCreateTime() { return createTime; }
    public void setCreateTime(Date createTime) { this.createTime = createTime; }
    
    public Date getPayTime() { return payTime; }
    public void setPayTime(Date payTime) { this.payTime = payTime; }
    
    public Date getDeliveryTime() { return deliveryTime; }
    public void setDeliveryTime(Date deliveryTime) { this.deliveryTime = deliveryTime; }
    
    public Date getCompleteTime() { return completeTime; }
    public void setCompleteTime(Date completeTime) { this.completeTime = completeTime; }
    
    public long getAddressId() { return addressId; }
    public void setAddressId(long addressId) { this.addressId = addressId; }
    
    public String getAddressDetail() { return addressDetail; }
    public void setAddressDetail(String addressDetail) { this.addressDetail = addressDetail; }
    
    public String getReceiverName() { return receiverName; }
    public void setReceiverName(String receiverName) { this.receiverName = receiverName; }
    
    public String getReceiverPhone() { return receiverPhone; }
    public void setReceiverPhone(String receiverPhone) { this.receiverPhone = receiverPhone; }
    
    public List<OrderItem> getItems() { return items; }
    public void setItems(List<OrderItem> items) { this.items = items; }
    
    public String getRiderName() { return riderName; }
    public void setRiderName(String riderName) { this.riderName = riderName; }
    
    public String getRiderPhone() { return riderPhone; }
    public void setRiderPhone(String riderPhone) { this.riderPhone = riderPhone; }
    
    public String getEstimatedDeliveryTime() { return estimatedDeliveryTime; }
    public void setEstimatedDeliveryTime(String estimatedDeliveryTime) { this.estimatedDeliveryTime = estimatedDeliveryTime; }
    
    public float getRating() { return rating; }
    public void setRating(float rating) { this.rating = rating; }
    
    public String getReviewComment() { return reviewComment; }
    public void setReviewComment(String reviewComment) { this.reviewComment = reviewComment; }
    
    public Date getReviewTime() { return reviewTime; }
    public void setReviewTime(Date reviewTime) { this.reviewTime = reviewTime; }
}
