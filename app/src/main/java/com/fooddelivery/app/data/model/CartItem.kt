package com.fooddelivery.app.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart_items")
data class CartItem(
    @PrimaryKey val id: Long,
    val dishId: Long,
    val restaurantId: Long,
    val name: String,
    val price: Double,
    val packingFee: Double,
    val imageUrl: String,
    var quantity: Int = 1,
    val categoryName: String
)
