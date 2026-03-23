package com.fooddelivery.app.data.mock

import com.fooddelivery.app.data.model.*

object MockDataGenerator {
    
    fun generateRestaurants(): List<Restaurant> {
        return listOf(
            Restaurant(
                id = 1,
                name = "麦当劳 (McDonald's)",
                imageUrl = "https://picsum.photos/seed/mcdonalds/400/300",
                rating = 4.8f,
                monthlySales = 5000,
                deliveryTime = "30-40 分钟",
                deliveryFee = 9.0,
                minPrice = 20.0,
                distance = 1.2,
                categories = listOf("快餐", "汉堡", "小吃"),
                description = "全球知名快餐连锁，提供美味汉堡和小食"
            ),
            Restaurant(
                id = 2,
                name = "肯德基 (KFC)",
                imageUrl = "https://picsum.photos/seed/kfc/400/300",
                rating = 4.7f,
                monthlySales = 4500,
                deliveryTime = "35-45 分钟",
                deliveryFee = 9.0,
                minPrice = 25.0,
                distance = 2.0,
                categories = listOf("快餐", "炸鸡", "小吃"),
                description = "知名西式快餐，主打炸鸡汉堡"
            ),
            Restaurant(
                id = 3,
                name = "海底捞火锅",
                imageUrl = "https://picsum.photos/seed/haidilao/400/300",
                rating = 4.9f,
                monthlySales = 3000,
                deliveryTime = "40-50 分钟",
                deliveryFee = 15.0,
                minPrice = 100.0,
                distance = 3.5,
                categories = listOf("火锅", "川菜", "聚餐"),
                description = "服务至上的火锅连锁品牌"
            ),
            Restaurant(
                id = 4,
                name = "星巴克 (Starbucks)",
                imageUrl = "https://picsum.photos/seed/starbucks/400/300",
                rating = 4.6f,
                monthlySales = 2000,
                deliveryTime = "25-35 分钟",
                deliveryFee = 7.0,
                minPrice = 30.0,
                distance = 0.8,
                categories = listOf("咖啡", "甜品", "饮品"),
                description = "全球知名咖啡连锁品牌"
            ),
            Restaurant(
                id = 5,
                name = "必胜客 (Pizza Hut)",
                imageUrl = "https://picsum.photos/seed/pizzahut/400/300",
                rating = 4.5f,
                monthlySales = 2500,
                deliveryTime = "40-50 分钟",
                deliveryFee = 12.0,
                minPrice = 50.0,
                distance = 2.5,
                categories = listOf("披萨", "意面", "西餐"),
                description = "专业披萨制作，美味分享"
            ),
            Restaurant(
                id = 6,
                name = "老乡鸡",
                imageUrl = "https://picsum.photos/seed/laoxiangji/400/300",
                rating = 4.7f,
                monthlySales = 3500,
                deliveryTime = "30-40 分钟",
                deliveryFee = 8.0,
                minPrice = 25.0,
                distance = 1.5,
                categories = listOf("中式快餐", "家常菜", "鸡汤"),
                description = "正宗安徽菜，招牌肥西老母鸡汤"
            )
        )
    }
    
    fun generateDishes(): List<Dish> {
        return listOf(
            // 麦当劳菜品
            Dish(
                id = 1,
                restaurantId = 1,
                name = "巨无霸汉堡",
                description = "经典双层牛肉堡，配新鲜生菜和特制酱料",
                imageUrl = "https://picsum.photos/seed/bigmac/300/200",
                price = 25.0,
                originalPrice = 28.0,
                categoryName = "主食汉堡",
                monthSales = 1000,
                packingFee = 1.0,
                isRecommended = true
            ),
            Dish(
                id = 2,
                restaurantId = 1,
                name = "麦辣鸡翅 (2 块)",
                description = "外酥里嫩，香辣可口",
                imageUrl = "https://picsum.photos/seed/wings/300/200",
                price = 15.0,
                categoryName = "小食",
                monthSales = 800,
                packingFee = 1.0
            ),
            Dish(
                id = 3,
                restaurantId = 1,
                name = "中份薯条",
                description = "金黄酥脆，现炸现卖",
                imageUrl = "https://picsum.photos/seed/fries/300/200",
                price = 12.0,
                categoryName = "小食",
                monthSales = 1200,
                packingFee = 0.5
            ),
            Dish(
                id = 4,
                restaurantId = 1,
                name = "可口可乐 (中)",
                description = "冰爽可乐，解渴必备",
                imageUrl = "https://picsum.photos/seed/coke/300/200",
                price = 8.0,
                categoryName = "饮料",
                monthSales = 1500,
                packingFee = 0.5
            ),
            Dish(
                id = 5,
                restaurantId = 2,
                name = "香辣鸡腿堡",
                description = "经典香辣鸡腿，口感丰富",
                imageUrl = "https://picsum.photos/seed/spicyburger/300/200",
                price = 22.0,
                categoryName = "主食汉堡",
                monthSales = 900,
                packingFee = 1.0,
                isRecommended = true
            ),
            Dish(
                id = 6,
                restaurantId = 2,
                name = "原味鸡 (1 块)",
                description = "经典原味，鲜嫩多汁",
                imageUrl = "https://picsum.photos/seed/chicken/300/200",
                price = 18.0,
                categoryName = "炸鸡",
                monthSales = 1100,
                packingFee = 1.0
            ),
            Dish(
                id = 7,
                restaurantId = 3,
                name = "鸳鸯锅底",
                description = "麻辣 + 番茄双拼锅",
                imageUrl = "https://picsum.photos/seed/hotpotsoup/300/200",
                price = 68.0,
                categoryName = "锅底",
                monthSales = 500,
                packingFee = 5.0,
                isRecommended = true
            ),
            Dish(
                id = 8,
                restaurantId = 3,
                name = "精品肥牛",
                description = "优质肥牛卷，涮煮佳品",
                imageUrl = "https://picsum.photos/seed/beef/300/200",
                price = 58.0,
                categoryName = "肉类",
                monthSales = 600,
                packingFee = 2.0
            ),
            Dish(
                id = 9,
                restaurantId = 4,
                name = "拿铁咖啡 (大杯)",
                description = "经典意式拿铁，香浓顺滑",
                imageUrl = "https://picsum.photos/seed/latte/300/200",
                price = 32.0,
                categoryName = "咖啡",
                monthSales = 700,
                packingFee = 1.0,
                isRecommended = true
            ),
            Dish(
                id = 10,
                restaurantId = 4,
                name = "提拉米苏",
                description = "经典意式甜品",
                imageUrl = "https://picsum.photos/seed/tiramisu/300/200",
                price = 35.0,
                categoryName = "甜品",
                monthSales = 400,
                packingFee = 2.0
            ),
            Dish(
                id = 11,
                restaurantId = 6,
                name = "肥西老母鸡汤",
                description = "招牌菜品，汤鲜味美",
                imageUrl = "https://picsum.photos/seed/chickensoup/300/200",
                price = 48.0,
                categoryName = "招牌菜",
                monthSales = 800,
                packingFee = 3.0,
                isRecommended = true
            ),
            Dish(
                id = 12,
                restaurantId = 6,
                name = "农家小炒肉",
                description = "地道湖南味，下饭神器",
                imageUrl = "https://picsum.photos/seed/stirpork/300/200",
                price = 32.0,
                categoryName = "热菜",
                monthSales = 600,
                packingFee = 2.0
            )
        )
    }
    
    fun generateCategories(): List<Category> {
        return listOf(
            Category(1, "美食", "https://picsum.photos/seed/food/100/100", CategoryType.FOOD),
            Category(2, "甜品", "https://picsum.photos/seed/dessert/100/100", CategoryType.DESSERT),
            Category(3, "饮料", "https://picsum.photos/seed/drink/100/100", CategoryType.DRINK),
            Category(4, "小吃", "https://picsum.photos/seed/snack/100/100", CategoryType.SNACK),
            Category(5, "水果", "https://picsum.photos/seed/fruit/100/100", CategoryType.FRUIT),
            Category(6, "超市", "https://picsum.photos/seed/market/100/100", CategoryType.SUPERMARKET)
        )
    }
    
    fun generateBanners(): List<Banner> {
        return listOf(
            Banner(1, "https://picsum.photos/seed/banner1/800/400", "新人专享红包", BannerLinkType.ACTIVITY),
            Banner(2, "https://picsum.photos/seed/banner2/800/400", "满减优惠进行中", BannerLinkType.CATEGORY, 1),
            Banner(3, "https://picsum.photos/seed/banner3/800/400", "品质外卖，极速送达", BannerLinkType.RESTAURANT, 1)
        )
    }
}
