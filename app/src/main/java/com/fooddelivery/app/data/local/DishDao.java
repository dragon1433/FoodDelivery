package com.fooddelivery.app.data.local;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import com.fooddelivery.app.data.model.Dish;

import java.util.List;

@Dao
public interface DishDao {
    @Query("SELECT * FROM dishes WHERE restaurantId = :restaurantId ORDER BY categoryName, id")
    List<Dish> getDishesByRestaurant(long restaurantId);
    
    @Query("SELECT * FROM dishes WHERE id = :dishId")
    Dish getDishById(long dishId);
    
    @Query("SELECT DISTINCT categoryName FROM dishes WHERE restaurantId = :restaurantId")
    List<String> getCategoriesByRestaurant(long restaurantId);
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Dish dish);
    
    @Update
    void update(Dish dish);
    
    @Delete
    void delete(Dish dish);
}
