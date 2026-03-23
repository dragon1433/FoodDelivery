package com.fooddelivery.app.data.local

import androidx.room.TypeConverter
import com.fooddelivery.app.data.model.OrderItem
import java.util.Date

/**
 * 类型转换器 - 用于 Room 数据库处理复杂类型
 */
class Converters {
    
    @TypeConverter
    fun fromStringList(value: List<String>?): String? {
        return value?.joinToString(separator = "|")
    }
    
    @TypeConverter
    fun toStringList(value: String?): List<String>? {
        return value?.split("|")
            ?.map { it.trim() }
            ?.filter { it.isNotEmpty() && it != "null" }
    }
    
    @TypeConverter
    fun fromDoubleList(value: List<Double>?): String? {
        return value?.joinToString(separator = "|")
    }
    
    @TypeConverter
    fun toDoubleList(value: String?): List<Double>? {
        return value?.split("|")
            ?.map { it.trim() }
            ?.filter { it.isNotEmpty() && it != "null" }
            ?.map { it.toDouble() }
    }
    
    @TypeConverter
    fun fromDate(value: Date?): Long? {
        return value?.time
    }
    
    @TypeConverter
    fun toDate(value: Long?): Date? {
        return value?.let { Date(it) }
    }
    
    @TypeConverter
    fun fromOrderItemList(value: List<OrderItem>?): String? {
        return value?.joinToString(separator = "@@") { item ->
            "${item.dishId}|${item.name}|${item.price}|${item.quantity}|${item.packingFee}"
        }
    }
    
    @TypeConverter
    fun toOrderItemList(value: String?): List<OrderItem>? {
        return value?.split("@@")
            ?.filter { it.isNotEmpty() }
            ?.map { parts ->
                val data = parts.split("|")
                OrderItem(
                    dishId = data[0].toLong(),
                    name = data[1],
                    price = data[2].toDouble(),
                    quantity = data[3].toInt(),
                    packingFee = data[4].toDouble()
                )
            }
    }
}
