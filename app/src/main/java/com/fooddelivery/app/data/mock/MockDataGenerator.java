package com.fooddelivery.app.data.mock;

import android.content.Context;
import com.fooddelivery.app.data.local.AppDatabase;
import com.fooddelivery.app.data.model.Restaurant;
import com.fooddelivery.app.data.model.Dish;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Mock 数据生成器 - 用于初始化测试数据
 */
public class MockDataGenerator {
    
    public static void generateMockData(Context context) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            AppDatabase database = AppDatabase.getDatabase(context);
            
            // 清除旧数据，重新生成新数据
            database.clearAllTables();
            
            // 插入餐厅数据
            insertRestaurants(database);
            
            // 插入菜品数据
            insertDishes(database);
        });
    }
    
    private static void insertRestaurants(AppDatabase database) {
        Restaurant[] restaurants = {
            new Restaurant(1, "麦当劳", "McDonald's", "restaurant_storefront", 
                4.5f, 2000, "30 分钟", 5.0, 20.0, 1.2, "快餐，汉堡", 
                "全球连锁快餐品牌，提供汉堡、薯条等美食", false),
            
            new Restaurant(2, "肯德基", "KFC", "restaurant_storefront", 
                4.6f, 2500, "25 分钟", 6.0, 25.0, 1.5, "快餐，炸鸡", 
                "知名炸鸡连锁品牌", false),
            
            new Restaurant(3, "必胜客", "Pizza Hut", "restaurant_storefront", 
                4.7f, 1800, "40 分钟", 8.0, 50.0, 2.0, "披萨，意面", 
                "专业披萨制作", false),
            
            new Restaurant(4, "海底捞火锅", "Haidilao Hot Pot", "restaurant_storefront", 
                4.8f, 3000, "50 分钟", 10.0, 100.0, 3.0, "火锅，川菜", 
                "服务周到的火锅店", false),
            
            new Restaurant(5, "星巴克", "Starbucks", "restaurant_storefront", 
                4.9f, 1500, "20 分钟", 0.0, 30.0, 0.5, "咖啡，甜点", 
                "全球知名咖啡连锁", false),
        };
        
        for (Restaurant restaurant : restaurants) {
            database.restaurantDao().insert(restaurant);
        }
    }
    
    private static void insertDishes(AppDatabase database) {
        Dish[] dishes = {
            // 麦当劳菜品
            new Dish(1, 1L, "巨无霸", "经典双层牛肉汉堡", 
                25.0, "food_burger", true),
            new Dish(2, 1L, "麦辣鸡翅", "香辣酥脆鸡翅", 
                15.0, "food_chicken", true),
            new Dish(3, 1L, "薯条", "金黄色脆薯条", 
                10.0, "food_fries", true),
            
            // 肯德基菜品
            new Dish(4, 2L, "原味鸡", "经典吮指原味鸡", 
                18.0, "food_chicken", true),
            new Dish(5, 2L, "香辣鸡腿堡", "香辣鸡腿汉堡", 
                20.0, "food_burger", true),
            new Dish(6, 2L, "蛋挞", "香甜蛋挞", 
                8.0, "food_dessert", true),
            
            // 必胜客菜品
            new Dish(7, 3L, "超级至尊披萨", "豪华配料披萨", 
                88.0, "food_pizza", true),
            new Dish(8, 3L, "夏威夷披萨", "火腿菠萝披萨", 
                75.0, "food_pizza", true),
            new Dish(9, 3L, "意大利面", "经典肉酱意面", 
                35.0, "food_noodles", true),
            
            // 海底捞菜品
            new Dish(10, 4L, "肥牛卷", "优质肥牛", 
                58.0, "food_meat", true),
            new Dish(11, 4L, "虾滑", "新鲜虾仁制作", 
                48.0, "food_chicken", true),
            new Dish(12, 4L, "蔬菜拼盘", "时令蔬菜", 
                28.0, "food_vegetables", true),
            
            // 星巴克菜品
            new Dish(13, 5L, "拿铁", "经典意式拿铁", 
                30.0, "food_coffee", true),
            new Dish(14, 5L, "美式咖啡", "纯正美式", 
                25.0, "food_coffee", true),
            new Dish(15, 5L, "芝士蛋糕", "浓郁芝士蛋糕", 
                35.0, "food_dessert", true),
        };
        
        for (Dish dish : dishes) {
            database.dishDao().insert(dish);
        }
    }
}