package com.fooddelivery.app.data

import com.fooddelivery.app.data.mock.MockDataGenerator
import com.fooddelivery.app.data.repository.RestaurantRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

/**
 * 数据初始化类 - 用于初始化 Mock 数据到数据库
 */
@Singleton
class DataInitializer @Inject constructor(
    private val restaurantRepository: RestaurantRepository
) {
    
    /**
     * 初始化示例数据
     */
    fun initializeData() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val mockData = MockDataGenerator
                val restaurants = mockData.generateRestaurants()
                val dishes = mockData.generateDishes()
                
                println("=== 数据初始化开始 ===")
                println("DEBUG: 准备插入餐厅数量：${restaurants.size}")
                println("DEBUG: 准备插入菜品数量：${dishes.size}")
                
                // 直接插入数据，不检查是否已存在
                restaurantRepository.insertRestaurants(restaurants)
                println("DEBUG: 餐厅插入完成")
                
                restaurantRepository.insertDishes(dishes)
                println("DEBUG: 菜品插入完成")
                
                // 等待一下确保数据已写入
                kotlinx.coroutines.delay(500)
                
                // 验证数据是否插入成功
                val allRestaurants = restaurantRepository.allRestaurants.first()
                println("DEBUG: 数据库中实际餐厅数量：${allRestaurants.size}")
                allRestaurants.forEach { r ->
                    println("  餐厅 ID=${r.id}, 名称=${r.name}")
                }
                
                val allDishes = restaurantRepository.getDishesByRestaurant(1).first()
                println("DEBUG: 麦当劳 (ID=1) 的菜品数量：${allDishes.size}")
                allDishes.forEach { d ->
                    println("  菜品 ID=${d.id}, 名称=${d.name}, 分类=${d.categoryName}")
                }
                println("=== 数据初始化结束 ===")
            } catch (e: Exception) {
                e.printStackTrace()
                println("ERROR: 初始化数据失败：${e.message}")
            }
        }
    }
}
