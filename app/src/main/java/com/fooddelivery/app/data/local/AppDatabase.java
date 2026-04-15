package com.fooddelivery.app.data.local;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import com.fooddelivery.app.data.model.Address;
import com.fooddelivery.app.data.model.CartItem;
import com.fooddelivery.app.data.model.Dish;
import com.fooddelivery.app.data.model.Order;
import com.fooddelivery.app.data.model.Restaurant;
import com.fooddelivery.app.data.model.User;

@Database(
    entities = {
        User.class,
        Restaurant.class,
        Dish.class,
        CartItem.class,
        Address.class,
        Order.class
    },
    version = 8,  // Upgrade to 8, added userId to Order
    exportSchema = false
)
@TypeConverters(Converters.class)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
    public abstract RestaurantDao restaurantDao();
    public abstract DishDao dishDao();
    public abstract CartDao cartDao();
    public abstract AddressDao addressDao();
    public abstract OrderDao orderDao();
    
    private static volatile AppDatabase INSTANCE;
    
    public static AppDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                        context.getApplicationContext(),
                        AppDatabase.class,
                        "food_delivery_db"
                    )
                    .fallbackToDestructiveMigration()
                    .build();
                }
            }
        }
        return INSTANCE;
    }
    
    // 清除数据库 (用于测试)
    public static void clearDatabase(Context context) {
        context.deleteDatabase("food_delivery_db");
        INSTANCE = null;
    }
}
