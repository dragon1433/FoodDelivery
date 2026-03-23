package com.fooddelivery.app.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "addresses")
data class Address(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    val phone: String,
    val detailAddress: String,
    val buildingInfo: String? = null,
    val floorInfo: String? = null,
    val doorplateInfo: String? = null,
    val addressTag: String? = null,
    val isDefault: Boolean = false,
    val latitude: Double? = null,
    val longitude: Double? = null
)
