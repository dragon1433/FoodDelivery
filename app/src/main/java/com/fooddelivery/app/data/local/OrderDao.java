package com.fooddelivery.app.data.local;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import com.fooddelivery.app.data.model.Order;
import com.fooddelivery.app.data.model.OrderStatus;

import java.util.List;

@Dao
public interface OrderDao {
    @Query("SELECT * FROM orders ORDER BY createTime DESC")
    List<Order> getAllOrders();
    
    @Query("SELECT * FROM orders WHERE id = :orderId")
    Order getOrderById(long orderId);
    
    @Query("SELECT * FROM orders WHERE status = :status ORDER BY createTime DESC")
    List<Order> getOrdersByStatus(OrderStatus status);
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Order order);
    
    @Update
    void update(Order order);
    
    @Delete
    void delete(Order order);
}
