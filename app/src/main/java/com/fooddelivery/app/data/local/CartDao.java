package com.fooddelivery.app.data.local;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import com.fooddelivery.app.data.model.CartItem;

import java.util.List;

@Dao
public interface CartDao {
    @Query("SELECT * FROM cart_items ORDER BY id")
    List<CartItem> getAllCartItems();
    
    @Query("SELECT * FROM cart_items WHERE restaurantId = :restaurantId")
    List<CartItem> getCartItemsByRestaurant(long restaurantId);
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(CartItem item);
    
    @Update
    void update(CartItem item);
    
    @Delete
    void delete(CartItem item);
    
    @Query("DELETE FROM cart_items")
    void clearCart();
    
    @Query("SELECT * FROM cart_items WHERE dishId = :dishId LIMIT 1")
    CartItem getItemByDishId(long dishId);
}
