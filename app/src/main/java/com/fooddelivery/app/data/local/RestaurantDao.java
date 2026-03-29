package com.fooddelivery.app.data.local;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import com.fooddelivery.app.data.model.Address;
import com.fooddelivery.app.data.model.CartItem;
import com.fooddelivery.app.data.model.Dish;
import com.fooddelivery.app.data.model.Order;
import com.fooddelivery.app.data.model.Restaurant;

import java.util.List;

@Dao
public interface RestaurantDao {
    @Query("SELECT * FROM restaurants ORDER BY id")
    List<Restaurant> getAllRestaurants();
    
    @Query("SELECT * FROM restaurants WHERE id = :restaurantId")
    Restaurant getRestaurantById(long restaurantId);
    
    @Query("SELECT * FROM restaurants WHERE name LIKE '%' || :keyword || '%' OR categories LIKE '%' || :keyword || '%'")
    List<Restaurant> searchRestaurants(String keyword);
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Restaurant restaurant);
    
    @Update
    void update(Restaurant restaurant);
    
    @Delete
    void delete(Restaurant restaurant);
}
