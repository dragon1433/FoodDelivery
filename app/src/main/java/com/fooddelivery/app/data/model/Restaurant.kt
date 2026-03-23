package com.fooddelivery.app.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "restaurants")
data class Restaurant(
    @PrimaryKey val id: Long,
    val name: String,
    val imageUrl: String,
    val rating: Float,
    val monthlySales: Int,
    val deliveryTime: String,
    val deliveryFee: Double,
    val minPrice: Double,
    val distance: Double,
    val categories: List<String>,
    val description: String,
    val isFavorite: Boolean = false
)
