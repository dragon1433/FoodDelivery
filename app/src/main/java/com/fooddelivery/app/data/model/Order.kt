package com.fooddelivery.app.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "orders")
data class Order(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val orderNo: String,
    val restaurantId: Long,
    val restaurantName: String,
    val totalAmount: Double,
    val deliveryFee: Double,
    val packingFee: Double,
    val discountAmount: Double = 0.0,
    val actualAmount: Double,
    var status: OrderStatus,
    val createTime: Date,
    val payTime: Date? = null,
    val deliveryTime: Date? = null,
    val completeTime: Date? = null,
    val addressId: Long,
    val addressDetail: String,
    val receiverName: String,
    val receiverPhone: String,
    val items: List<OrderItem>,
    val riderName: String? = null,
    val riderPhone: String? = null,
    val estimatedDeliveryTime: String? = null
)

enum class OrderStatus {
    PENDING_PAYMENT,
    PAID,
    PREPARING,
    DELIVERING,
    DELIVERED,
    COMPLETED,
    CANCELLED
}

data class OrderItem(
    val dishId: Long,
    val name: String,
    val price: Double,
    val quantity: Int,
    val packingFee: Double
)
