package com.fooddelivery.app.data.local

import androidx.room.*
import com.fooddelivery.app.data.model.Address
import com.fooddelivery.app.data.model.CartItem
import com.fooddelivery.app.data.model.Dish
import com.fooddelivery.app.data.model.Order
import com.fooddelivery.app.data.model.Restaurant
import kotlinx.coroutines.flow.Flow

@Dao
interface RestaurantDao {
    @Query("SELECT * FROM restaurants ORDER BY id")
    fun getAllRestaurants(): Flow<List<Restaurant>>
    
    @Query("SELECT * FROM restaurants WHERE id = :restaurantId")
    suspend fun getRestaurantById(restaurantId: Long): Restaurant?
    
    @Query("SELECT * FROM restaurants WHERE name LIKE '%' || :keyword || '%' OR categories LIKE '%' || :keyword || '%'")
    fun searchRestaurants(keyword: String): Flow<List<Restaurant>>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRestaurants(restaurants: List<Restaurant>)
    
    @Update
    suspend fun updateRestaurant(restaurant: Restaurant)
}

@Dao
interface DishDao {
    @Query("SELECT * FROM dishes WHERE restaurantId = :restaurantId ORDER BY categoryName, id")
    fun getDishesByRestaurant(restaurantId: Long): Flow<List<Dish>>
    
    @Query("SELECT * FROM dishes WHERE id = :dishId")
    suspend fun getDishById(dishId: Long): Dish?
    
    @Query("SELECT DISTINCT categoryName FROM dishes WHERE restaurantId = :restaurantId")
    suspend fun getCategoriesByRestaurant(restaurantId: Long): List<String>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDishes(dishes: List<Dish>)
    
    @Update
    suspend fun updateDish(dish: Dish)
}

@Dao
interface CartDao {
    @Query("SELECT * FROM cart_items ORDER BY id")
    fun getAllCartItems(): Flow<List<CartItem>>
    
    @Query("SELECT * FROM cart_items WHERE restaurantId = :restaurantId")
    fun getCartItemsByRestaurant(restaurantId: Long): Flow<List<CartItem>>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCartItem(cartItem: CartItem)
    
    @Update
    suspend fun updateCartItem(cartItem: CartItem)
    
    @Delete
    suspend fun deleteCartItem(cartItem: CartItem)
    
    @Query("DELETE FROM cart_items WHERE restaurantId = :restaurantId")
    suspend fun clearCart(restaurantId: Long)
    
    @Query("SELECT SUM(quantity * price + quantity * packingFee) FROM cart_items")
    suspend fun getTotalAmount(): Double?
}

@Dao
interface AddressDao {
    @Query("SELECT * FROM addresses ORDER BY isDefault DESC, id")
    fun getAllAddresses(): Flow<List<Address>>
    
    @Query("SELECT * FROM addresses WHERE id = :addressId")
    suspend fun getAddressById(addressId: Long): Address?
    
    @Query("SELECT * FROM addresses WHERE isDefault = 1 LIMIT 1")
    suspend fun getDefaultAddress(): Address?
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAddress(address: Address): Long
    
    @Update
    suspend fun updateAddress(address: Address)
    
    @Delete
    suspend fun deleteAddress(address: Address)
    
    @Query("UPDATE addresses SET isDefault = 0")
    suspend fun clearDefaultAddress()
}

@Dao
interface OrderDao {
    @Query("SELECT * FROM orders ORDER BY createTime DESC")
    fun getAllOrders(): Flow<List<Order>>
    
    @Query("SELECT * FROM orders WHERE id = :orderId")
    suspend fun getOrderById(orderId: Long): Order?
    
    @Query("SELECT * FROM orders WHERE status = :status ORDER BY createTime DESC")
    fun getOrdersByStatus(status: com.fooddelivery.app.data.model.OrderStatus): Flow<List<Order>>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrder(order: Order): Long
    
    @Update
    suspend fun updateOrder(order: Order)
    
    @Delete
    suspend fun deleteOrder(order: Order)
}
