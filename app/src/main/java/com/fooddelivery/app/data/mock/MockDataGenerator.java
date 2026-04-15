package com.fooddelivery.app.data.mock;

import android.content.Context;
import com.fooddelivery.app.data.local.AppDatabase;
import com.fooddelivery.app.data.model.Banner;
import com.fooddelivery.app.data.model.BannerLinkType;
import com.fooddelivery.app.data.model.Combo;
import com.fooddelivery.app.data.model.Restaurant;
import com.fooddelivery.app.data.model.Dish;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Mock Data Generator - Used to initialize test data
 */
public class MockDataGenerator {
    
    public static void generateMockData(Context context) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            AppDatabase database = AppDatabase.getDatabase(context);
            
            // Clear only restaurant and dish tables, keep user data
            database.restaurantDao().deleteAllRestaurants();
            database.dishDao().deleteAllDishes();
            
            // Insert restaurant data
            insertRestaurants(database);
            
            // Insert dish data
            insertDishes(database);
            
            // Create a default test user if no users exist
            createTestUser(database);
        });
    }
    
    private static void insertRestaurants(AppDatabase database) {
        Restaurant[] restaurants = {
            new Restaurant(1, "McDonald's", "McDonald's", 
                "https://images.unsplash.com/photo-1572802419224-296b0aeee0d9?w=400", 
                4.5f, 2000, "30 min", 5.0, 20.0, 1.2, "Fast Food, Burgers", 
                "Global fast food chain offering burgers, fries and more. Famous for Big Mac, French Fries, and Chicken McNuggets.", false),
            
            new Restaurant(2, "KFC", "KFC", 
                "https://images.unsplash.com/photo-1626082927389-6cd097cdc6ec?w=400", 
                4.6f, 2500, "25 min", 6.0, 25.0, 1.5, "Fast Food, Fried Chicken", 
                "Famous fried chicken chain brand. Known for Original Recipe Chicken, Zinger Burger, and Egg Tarts.", false),
            
            new Restaurant(3, "Pizza Hut", "Pizza Hut", 
                "https://images.unsplash.com/photo-1513104890138-7c749659a591?w=400", 
                4.7f, 1800, "40 min", 8.0, 50.0, 2.0, "Pizza, Pasta", 
                "Professional pizza maker with a wide variety of pizzas, pastas, and side dishes. Perfect for family meals.", false),
            
            new Restaurant(4, "Haidilao Hot Pot", "Haidilao Hot Pot", 
                "https://images.unsplash.com/photo-1563245372-f21724e3856d?w=400", 
                4.8f, 3000, "50 min", 10.0, 100.0, 3.0, "Hot Pot, Sichuan Cuisine", 
                "Hot pot restaurant with excellent service. Premium ingredients including beef, lamb, seafood, and fresh vegetables.", false),
            
            new Restaurant(5, "Starbucks", "Starbucks", 
                "https://images.unsplash.com/photo-1501339847302-ac426a4a7cbb?w=400", 
                4.9f, 1500, "20 min", 0.0, 30.0, 0.5, "Coffee, Desserts", 
                "World-famous coffee chain serving premium coffee, tea, pastries, and light meals.", false),
            
            new Restaurant(6, "Subway", "Subway", 
                "https://images.unsplash.com/photo-1552566626-52f8b828add9?w=400", 
                4.4f, 1200, "15 min", 3.0, 15.0, 0.8, "Sandwiches, Salads", 
                "Fresh made-to-order sandwiches and salads. Choose your bread, fillings, and sauces.", false),
            
            new Restaurant(7, "Sushi Master", "Sushi Master", 
                "https://images.unsplash.com/photo-1579871494447-9811cf80d66c?w=400", 
                4.8f, 3500, "45 min", 12.0, 80.0, 2.5, "Japanese, Sushi", 
                "Authentic Japanese sushi restaurant with fresh fish and traditional recipes.", false),
            
            new Restaurant(8, "Burger King", "Burger King", 
                "https://images.unsplash.com/photo-1561758033-d89a9ad46330?w=400", 
                4.3f, 1800, "25 min", 5.0, 18.0, 1.0, "Fast Food, Burgers", 
                "Home of the Whopper! Flame-grilled burgers, crispy fries, and refreshing drinks.", false),
            
            new Restaurant(9, "Thai Garden", "Thai Garden", 
                "https://images.unsplash.com/photo-1559314809-0d155014e29e?w=400", 
                4.7f, 2200, "35 min", 8.0, 40.0, 2.0, "Thai, Asian", 
                "Authentic Thai cuisine with bold flavors. Specializing in Pad Thai, Green Curry, and Tom Yum Soup.", false),
            
            new Restaurant(10, "Domino's Pizza", "Domino's Pizza", 
                "https://images.unsplash.com/photo-1604382354936-07c5d9983bd3?w=400", 
                4.5f, 1600, "30 min", 6.0, 35.0, 1.5, "Pizza, Fast Food", 
                "Fast delivery pizza chain with a variety of toppings and crust options.", false),
        };
        
        for (Restaurant restaurant : restaurants) {
            database.restaurantDao().insert(restaurant);
        }
    }
    
    private static void insertDishes(AppDatabase database) {
        Dish[] dishes = {
            // McDonald's Dishes
            new Dish(1, 1L, "Big Mac", "Classic double beef burger with special sauce", 
                25.0, "https://images.unsplash.com/photo-1568901346375-23c9450c58cd?w=400", true),
            new Dish(2, 1L, "Chicken McNuggets", "Crispy chicken nuggets (6 pieces)", 
                18.0, "https://images.unsplash.com/photo-1608039829572-78524f79c4c7?w=400", true),
            new Dish(3, 1L, "French Fries", "Golden crispy fries", 
                10.0, "https://images.unsplash.com/photo-1573080496219-bb080dd4f877?w=400", true),
            new Dish(4, 1L, "McFlurry", "Creamy ice cream with Oreo cookies", 
                15.0, "https://images.unsplash.com/photo-1572490122747-3968b75cc699?w=400", true),
            
            // KFC Dishes
            new Dish(5, 2L, "Original Recipe Chicken", "Classic finger-lickin' chicken (2 pieces)", 
                28.0, "https://images.unsplash.com/photo-1626645738196-c2a7c87a8f58?w=400", true),
            new Dish(6, 2L, "Zinger Burger", "Spicy chicken leg burger", 
                22.0, "https://images.unsplash.com/photo-1606755962773-d324e0a13086?w=400", true),
            new Dish(7, 2L, "Egg Tart", "Sweet and creamy egg tart", 
                8.0, "https://images.unsplash.com/photo-1519915028121-7d3463d20b13?w=400", true),
            new Dish(8, 2L, "Hot Wings", "Spicy buffalo wings (6 pieces)", 
                20.0, "https://images.unsplash.com/photo-1567620832903-9fc6debc209f?w=400", true),
            
            // Pizza Hut Dishes
            new Dish(9, 3L, "Super Supreme Pizza", "Deluxe toppings pizza (Large)", 
                88.0, "https://images.unsplash.com/photo-1565299624946-b28f40a0ae38?w=400", true),
            new Dish(10, 3L, "Hawaiian Pizza", "Ham and pineapple pizza (Large)", 
                75.0, "https://images.unsplash.com/photo-1565299507177-b0ac66763828?w=400", true),
            new Dish(11, 3L, "Meat Lovers Pizza", "Loaded with pepperoni, sausage, and bacon", 
                95.0, "https://images.unsplash.com/photo-1513104890138-7c749659a591?w=400", true),
            new Dish(12, 3L, "Garlic Bread", "Toasted bread with garlic butter", 
                18.0, "https://images.unsplash.com/photo-1573140401552-388e7e2f00b8?w=400", true),
            
            // Haidilao Hot Pot
            new Dish(13, 4L, "Premium Beef Slices", "High-quality beef slices for hot pot", 
                68.0, "https://images.unsplash.com/photo-1600891964092-4316c288032e?w=400", true),
            new Dish(14, 4L, "Shrimp Paste", "Made from fresh shrimp", 
                48.0, "https://images.unsplash.com/photo-1565680018434-b513d5e5fd47?w=400", true),
            new Dish(15, 4L, "Vegetable Platter", "Seasonal vegetables assortment", 
                35.0, "https://images.unsplash.com/photo-1540420773420-3366772f4999?w=400", true),
            new Dish(16, 4L, "Lamb Slices", "Tender lamb slices", 
                72.0, "https://images.unsplash.com/photo-1603360946369-dc9bb6258143?w=400", true),
            
            // Starbucks
            new Dish(17, 5L, "Caffe Latte", "Classic Italian latte", 
                32.0, "https://images.unsplash.com/photo-1541167760496-1628856ab772?w=400", true),
            new Dish(18, 5L, "Caffe Americano", "Pure Americano coffee", 
                28.0, "https://images.unsplash.com/photo-1551030173-122aabc4489c?w=400", true),
            new Dish(19, 5L, "Blueberry Cheesecake", "Rich cheesecake with blueberry topping", 
                38.0, "https://images.unsplash.com/photo-1524351199678-941a58a3df50?w=400", true),
            new Dish(20, 5L, "Croissant", "Buttery flaky croissant", 
                22.0, "https://images.unsplash.com/photo-1555507036-ab1f4038808a?w=400", true),
            
            // Subway
            new Dish(21, 6L, "Italian B.M.T.", "Salami, pepperoni, and ham sub", 
                35.0, "https://images.unsplash.com/photo-1552566626-52f8b828add9?w=400", true),
            new Dish(22, 6L, "Turkey Breast", "Oven-roasted turkey breast sub", 
                32.0, "https://images.unsplash.com/photo-1554433607-66b5efe9d304?w=400", true),
            new Dish(23, 6L, "Veggie Delite", "Fresh vegetable sub", 
                25.0, "https://images.unsplash.com/photo-1540713434306-58505cf1b6fc?w=400", true),
            
            // Sushi Master
            new Dish(24, 7L, "Salmon Sashimi", "Fresh salmon sashimi (8 pieces)", 
                88.0, "https://images.unsplash.com/photo-1579871494447-9811cf80d66c?w=400", true),
            new Dish(25, 7L, "California Roll", "Crab, avocado, and cucumber roll", 
                58.0, "https://images.unsplash.com/photo-1579584425555-c3ce17fd4351?w=400", true),
            new Dish(26, 7L, "Tuna Maki", "Fresh tuna maki roll (6 pieces)", 
                52.0, "https://images.unsplash.com/photo-1617196034796-73dfa7b1fd56?w=400", true),
            new Dish(27, 7L, "Dragon Roll", "Eel and avocado specialty roll", 
                78.0, "https://images.unsplash.com/photo-1611143669185-af224c5e3252?w=400", true),
            
            // Burger King
            new Dish(28, 8L, "Whopper", "Flame-grilled beef burger", 
                32.0, "https://images.unsplash.com/photo-1561758033-d89a9ad46330?w=400", true),
            new Dish(29, 8L, "Chicken Fries", "Crispy chicken strips", 
                22.0, "https://images.unsplash.com/photo-1562967914-608f82629710?w=400", true),
            new Dish(30, 8L, "Onion Rings", "Crispy golden onion rings", 
                15.0, "https://images.unsplash.com/photo-1639024471283-03518883512d?w=400", true),
            
            // Thai Garden
            new Dish(31, 9L, "Pad Thai", "Classic Thai stir-fried noodles", 
                45.0, "https://images.unsplash.com/photo-1559314809-0d155014e29e?w=400", true),
            new Dish(32, 9L, "Green Curry", "Spicy green curry with chicken", 
                52.0, "https://images.unsplash.com/photo-1455619452474-d2be8b1e70cd?w=400", true),
            new Dish(33, 9L, "Tom Yum Soup", "Hot and sour soup with shrimp", 
                42.0, "https://images.unsplash.com/photo-1548943487-a2e4e43b4853?w=400", true),
            new Dish(34, 9L, "Mango Sticky Rice", "Sweet mango with coconut sticky rice", 
                28.0, "https://images.unsplash.com/photo-1596040033229-a9821ebd058d?w=400", true),
            
            // Domino's Pizza
            new Dish(35, 10L, "Pepperoni Feast", "Loaded with pepperoni (Large)", 
                82.0, "https://images.unsplash.com/photo-1604382354936-07c5d9983bd3?w=400", true),
            new Dish(36, 10L, "BBQ Chicken Pizza", "Grilled chicken with BBQ sauce", 
                85.0, "https://images.unsplash.com/photo-1565299624946-b28f40a0ae38?w=400", true),
            new Dish(37, 10L, "Margherita Pizza", "Classic tomato and mozzarella", 
                68.0, "https://images.unsplash.com/photo-1574071318508-1cdbab80d002?w=400", true),
            new Dish(38, 10L, "Chicken Wings", "Buffalo style chicken wings", 
                35.0, "https://images.unsplash.com/photo-1567620832903-9fc6debc209f?w=400", true),
        };
        
        for (Dish dish : dishes) {
            database.dishDao().insert(dish);
        }
    }
    
    private static void createTestUser(AppDatabase database) {
        try {
            // Check if test user already exists
            com.fooddelivery.app.data.model.User existingUser = database.userDao().getUserByPhone("12345678901");
            if (existingUser == null) {
                // Create a default test user
                com.fooddelivery.app.data.model.User testUser = new com.fooddelivery.app.data.model.User(
                    "12345678901",  // Phone
                    "123456",       // Password
                    "Test User"     // Name
                );
                long userId = database.userDao().insert(testUser);
                android.util.Log.d("MockDataGenerator", "Test user created with ID: " + userId);
            } else {
                android.util.Log.d("MockDataGenerator", "Test user already exists");
            }
        } catch (Exception e) {
            android.util.Log.e("MockDataGenerator", "Error creating test user: " + e.getMessage(), e);
        }
    }
    
    /**
     * Generate restaurant data for display
     */
    public static List<Restaurant> generateRestaurants() {
        List<Restaurant> restaurants = new ArrayList<>();
        
        restaurants.add(new Restaurant(1, "McDonald's", "McDonald's", 
            "https://images.unsplash.com/photo-1572802419224-296b0aeee0d9?w=400", 
            4.5f, 2000, "30 min", 5.0, 20.0, 1.2, "Fast Food, Burgers", 
            "Global fast food chain offering burgers, fries and more.", false));
        
        restaurants.add(new Restaurant(2, "KFC", "KFC", 
            "https://images.unsplash.com/photo-1626082927389-6cd097cdc6ec?w=400", 
            4.6f, 2500, "25 min", 6.0, 25.0, 1.5, "Fast Food, Fried Chicken", 
            "Famous fried chicken chain brand.", false));
        
        restaurants.add(new Restaurant(3, "Pizza Hut", "Pizza Hut", 
            "https://images.unsplash.com/photo-1513104890138-7c749659a591?w=400", 
            4.7f, 1800, "40 min", 8.0, 50.0, 2.0, "Pizza, Pasta", 
            "Professional pizza maker.", false));
        
        restaurants.add(new Restaurant(4, "Haidilao Hot Pot", "Haidilao Hot Pot", 
            "https://images.unsplash.com/photo-1563245372-f21724e3856d?w=400", 
            4.8f, 3000, "50 min", 10.0, 100.0, 3.0, "Hot Pot, Sichuan Cuisine", 
            "Hot pot restaurant with excellent service.", false));
        
        restaurants.add(new Restaurant(5, "Starbucks", "Starbucks", 
            "https://images.unsplash.com/photo-1501339847302-ac426a4a7cbb?w=400", 
            4.9f, 1500, "20 min", 0.0, 30.0, 0.5, "Coffee, Desserts", 
            "World-famous coffee chain.", false));
        
        restaurants.add(new Restaurant(6, "Subway", "Subway", 
            "https://images.unsplash.com/photo-1552566626-52f8b828add9?w=400", 
            4.4f, 1200, "15 min", 3.0, 15.0, 0.8, "Sandwiches, Salads", 
            "Fresh made-to-order sandwiches.", false));
        
        return restaurants;
    }
    
    /**
     * Generate banner data for carousel
     */
    public static List<Banner> generateBanners() {
        List<Banner> banners = new ArrayList<>();
        
        banners.add(new Banner(1, 
            "https://images.unsplash.com/photo-1504674900247-0877df9cc836?w=800",
            "Delicious Food Delivered Fast",
            BannerLinkType.ACTIVITY,
            null));
        
        banners.add(new Banner(2,
            "https://images.unsplash.com/photo-1565299624946-b28f40a0ae38?w=800",
            "Pizza Party - 20% Off!",
            BannerLinkType.CATEGORY,
            3L));
        
        banners.add(new Banner(3,
            "https://images.unsplash.com/photo-1568901346375-23c9450c58cd?w=800",
            "Burger Festival",
            BannerLinkType.CATEGORY,
            1L));
        
        banners.add(new Banner(4,
            "https://images.unsplash.com/photo-1501339847302-ac426a4a7cbb?w=800",
            "Coffee Time - Buy 1 Get 1 Free",
            BannerLinkType.RESTAURANT,
            5L));
        
        banners.add(new Banner(5,
            "https://images.unsplash.com/photo-1563245372-f21724e3856d?w=800",
            "Hot Pot Season is Here!",
            BannerLinkType.RESTAURANT,
            4L));
        
        return banners;
    }
    
    /**
     * Generate combo/meal deal data
     */
    public static List<Combo> generateCombos() {
        List<Combo> combos = new ArrayList<>();
        
        // Burger Combos (categoryId = 1)
        combos.add(new Combo(1, "Classic Burger Meal", 
            "Big Mac + Fries + Coke",
            35.0, 
            "https://images.unsplash.com/photo-1568901346375-23c9450c58cd?w=400",
            1L));
        
        combos.add(new Combo(2, "Chicken Nugget Box",
            "20 Nuggets + 2 Fries + 2 Drinks",
            45.0,
            "https://images.unsplash.com/photo-1608039829572-78524f79c4c7?w=400",
            1L));
        
        // Pizza Combos (categoryId = 2)
        combos.add(new Combo(3, "Pizza Party Pack",
            "2 Large Pizzas + Garlic Bread + 2L Drink",
            128.0,
            "https://images.unsplash.com/photo-1565299624946-b28f40a0ae38?w=400",
            2L));
        
        combos.add(new Combo(4, "Family Feast",
            "3 Medium Pizzas + Wings + Pasta",
            168.0,
            "https://images.unsplash.com/photo-1513104890138-7c749659a591?w=400",
            2L));
        
        // Fried Chicken Combos (categoryId = 3)
        combos.add(new Combo(5, "Crispy Chicken Bucket",
            "8 Pieces Chicken + 2 Fries + Coleslaw",
            68.0,
            "https://images.unsplash.com/photo-1626645738196-c2a7c87a8f58?w=400",
            3L));
        
        combos.add(new Combo(6, "Wings & Rings Combo",
            "12 Wings + Onion Rings + 2 Drinks",
            52.0,
            "https://images.unsplash.com/photo-1567620832903-9fc6debc209f?w=400",
            3L));
        
        // Coffee Combos (categoryId = 4)
        combos.add(new Combo(7, "Morning Brew Set",
            "2 Lattes + 2 Croissants",
            58.0,
            "https://images.unsplash.com/photo-1541167760496-1628856ab772?w=400",
            4L));
        
        combos.add(new Combo(8, "Afternoon Tea",
            "2 Cappuccinos + Cheesecake + Cookie",
            72.0,
            "https://images.unsplash.com/photo-1524351199678-941a58a3df50?w=400",
            4L));
        
        // Noodles Combos (categoryId = 5)
        combos.add(new Combo(9, "Noodle Lover's Set",
            "2 Bowls Ramen + Gyoza + Edamame",
            65.0,
            "https://images.unsplash.com/photo-1612929633738-8fe44f7ec841?w=400",
            5L));
        
        // Dessert Combos (categoryId = 6)
        combos.add(new Combo(10, "Sweet Treats Box",
            "Ice Cream + Cake + Coffee",
            48.0,
            "https://images.unsplash.com/photo-1524351199678-941a58a3df50?w=400",
            6L));
        
        return combos;
    }
}
