package com.fooddelivery.app.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dishes")
data class Dish(
    @PrimaryKey val id: Long,
    val restaurantId: Long,
    val name: String,
    val description: String,
    val imageUrl: String,
    val price: Double,
    val originalPrice: Double? = null,
    val categoryName: String,
    val monthSales: Int = 0,
    val stock: Int = 999,
    val isRecommended: Boolean = false,
    val packingFee: Double = 1.0
)
