package com.fooddelivery.app.data.repository

import com.fooddelivery.app.data.local.*
import com.fooddelivery.app.data.model.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import javax.inject.Inject
import javax.inject.Singleton

/**
 * 餐厅仓库 - 成员 B 使用
 */
class RestaurantRepository @Inject constructor(
    private val restaurantDao: RestaurantDao,
    private val dishDao: DishDao
) {
    val allRestaurants: Flow<List<Restaurant>> = restaurantDao.getAllRestaurants()
    
    fun searchRestaurants(keyword: String): Flow<List<Restaurant>> {
        return restaurantDao.searchRestaurants(keyword)
    }
    
    suspend fun getRestaurantById(restaurantId: Long): Restaurant? {
        return restaurantDao.getRestaurantById(restaurantId)
    }
    
    suspend fun insertRestaurants(restaurants: List<Restaurant>) {
        restaurantDao.insertRestaurants(restaurants)
    }
    
    suspend fun getDishesByRestaurant(restaurantId: Long): Flow<List<Dish>> {
        return dishDao.getDishesByRestaurant(restaurantId)
    }
    
    suspend fun insertDishes(dishes: List<Dish>) {
        dishDao.insertDishes(dishes)
    }
    
    /**
     * 初始化示例数据
     */
    suspend fun initializeMockData(restaurants: List<Restaurant>, dishes: List<Dish>) {
        if (restaurantDao.getAllRestaurants().firstOrNull().isNullOrEmpty()) {
            insertRestaurants(restaurants)
            insertDishes(dishes)
        }
    }
}
class DishRepository @Inject constructor(
    private val dishDao: DishDao
) {
    val allDishes: Flow<List<Dish>> = dishDao.getDishesByRestaurant(0)
    
    fun getDishesByRestaurant(restaurantId: Long): Flow<List<Dish>> {
        return dishDao.getDishesByRestaurant(restaurantId)
    }
    
    suspend fun getDishById(dishId: Long): Dish? {
        return dishDao.getDishById(dishId)
    }
    
    suspend fun insertDishes(dishes: List<Dish>) {
        dishDao.insertDishes(dishes)
    }
}
class CartRepository @Inject constructor(
    private val cartDao: CartDao
) {
    val allCartItems: Flow<List<CartItem>> = cartDao.getAllCartItems()
    
    fun getCartItems(): Flow<List<CartItem>> {
        return allCartItems
    }
    
    fun getCartItemsByRestaurant(restaurantId: Long): Flow<List<CartItem>> {
        return cartDao.getCartItemsByRestaurant(restaurantId)
    }
    
    suspend fun addToCart(cartItem: CartItem) {
        val allItems = cartDao.getAllCartItems().firstOrNull() ?: emptyList()
        val existingItem = allItems.firstOrNull { item -> item.dishId == cartItem.dishId }
        
        if (existingItem != null) {
            val updatedItem = existingItem.copy(quantity = existingItem.quantity + cartItem.quantity)
            cartDao.updateCartItem(updatedItem)
        } else {
            cartDao.insertCartItem(cartItem)
        }
    }
    
    suspend fun updateQuantity(cartItem: CartItem, newQuantity: Int) {
        if (newQuantity <= 0) {
            cartDao.deleteCartItem(cartItem)
        } else {
            val updatedItem = cartItem.copy(quantity = newQuantity)
            cartDao.updateCartItem(updatedItem)
        }
    }
    
    suspend fun removeFromCart(cartItem: CartItem) {
        cartDao.deleteCartItem(cartItem)
    }
    
    suspend fun clearCart(restaurantId: Long) {
        cartDao.clearCart(restaurantId)
    }
}
class AddressRepository @Inject constructor(
    private val addressDao: AddressDao
) {
    val allAddresses: Flow<List<Address>> = addressDao.getAllAddresses()
    
    suspend fun getAddressById(addressId: Long): Address? {
        return addressDao.getAddressById(addressId)
    }
    
    suspend fun getDefaultAddress(): Address? {
        return addressDao.getDefaultAddress()
    }
    
    suspend fun addAddress(address: Address): Long {
        if (address.isDefault) {
            addressDao.clearDefaultAddress()
        }
        return addressDao.insertAddress(address)
    }
    
    suspend fun updateAddress(address: Address) {
        if (address.isDefault) {
            addressDao.clearDefaultAddress()
        }
        addressDao.updateAddress(address)
    }
    
    suspend fun deleteAddress(address: Address) {
        addressDao.deleteAddress(address)
    }
}
class OrderRepository @Inject constructor(
    private val orderDao: OrderDao
) {
    val allOrders: Flow<List<Order>> = orderDao.getAllOrders()
    
    fun getOrdersByStatus(status: OrderStatus): Flow<List<Order>> {
        return orderDao.getOrdersByStatus(status)
    }
    
    suspend fun getOrderById(orderId: Long): Order? {
        return orderDao.getOrderById(orderId)
    }
    
    suspend fun createOrder(order: Order): Long {
        return orderDao.insertOrder(order)
    }
    
    suspend fun updateOrder(order: Order) {
        orderDao.updateOrder(order)
    }
    
    suspend fun cancelOrder(order: Order) {
        val cancelledOrder = order.copy(status = OrderStatus.CANCELLED)
        orderDao.updateOrder(cancelledOrder)
    }
}
