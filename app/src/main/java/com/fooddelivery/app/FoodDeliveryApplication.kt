package com.fooddelivery.app

import android.app.Application
import com.fooddelivery.app.data.DataInitializer
import com.fooddelivery.app.data.local.AppDatabase
import com.fooddelivery.app.data.repository.AddressRepository
import com.fooddelivery.app.data.repository.CartRepository
import com.fooddelivery.app.data.repository.OrderRepository
import com.fooddelivery.app.data.repository.RestaurantRepository
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.first
import javax.inject.Inject

/**
 * 应用入口类 - 架构师配置
 */
@HiltAndroidApp
class FoodDeliveryApplication : Application() {
    
    @Inject
    lateinit var dataInitializer: DataInitializer
    
    @Inject
    lateinit var restaurantRepository: RestaurantRepository
    
    @Inject
    lateinit var addressRepository: AddressRepository
    
    @Inject
    lateinit var cartRepository: CartRepository
    
    @Inject
    lateinit var orderRepository: OrderRepository
    
    override fun onCreate() {
        super.onCreate()
        // 初始化示例数据
        dataInitializer.initializeData()
        
        // 额外调试：直接查询数据库
        CoroutineScope(Dispatchers.IO).launch {
            delay(2000) // 等待 2 秒让数据插入完成
            try {
                val allRestaurants = restaurantRepository.allRestaurants.first()
                println("DEBUG Application: 数据库中的餐厅列表:")
                allRestaurants.forEach { restaurant ->
                    println("  - 餐厅 ${restaurant.id}: ${restaurant.name}")
                }
                
                val mcdonaldsDishes = restaurantRepository.getDishesByRestaurant(1).first()
                println("DEBUG Application: 麦当劳 (ID=1) 的菜品:")
                mcdonaldsDishes.forEach { dish ->
                    println("  - 菜品 ${dish.id}: ${dish.name} (分类：${dish.categoryName})")
                }
            } catch (e: Exception) {
                println("ERROR Application: 查询数据库失败：${e.message}")
                e.printStackTrace()
            }
        }
    }
}
