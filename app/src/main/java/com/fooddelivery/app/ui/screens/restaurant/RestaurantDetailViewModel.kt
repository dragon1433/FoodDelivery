package com.fooddelivery.app.ui.screens.restaurant

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fooddelivery.app.data.model.CartItem
import com.fooddelivery.app.data.model.Dish
import com.fooddelivery.app.data.model.Restaurant
import com.fooddelivery.app.data.repository.CartRepository
import com.fooddelivery.app.data.repository.RestaurantRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * 餐厅详情页 ViewModel
 */
@HiltViewModel
class RestaurantDetailViewModel @Inject constructor(
    private val restaurantRepository: RestaurantRepository,
    private val cartRepository: CartRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(RestaurantDetailUiState())
    val uiState: StateFlow<RestaurantDetailUiState> = _uiState.asStateFlow()

    fun loadRestaurant(restaurantId: Long) {
        viewModelScope.launch {
            try {
                println("DEBUG ViewModel: 开始加载餐厅 $restaurantId")
                val restaurant = restaurantRepository.getRestaurantById(restaurantId)
                println("DEBUG ViewModel: 餐厅信息：${restaurant?.name}")
                _uiState.value = _uiState.value.copy(restaurant = restaurant)
            } catch (e: Exception) {
                e.printStackTrace()
                println("ERROR: 加载餐厅失败：${e.message}")
                _uiState.value = _uiState.value.copy(restaurant = null)
            }
        }
    }

    fun loadDishes(restaurantId: Long) {
        viewModelScope.launch {
            try {
                println("DEBUG ViewModel: 开始加载餐厅 $restaurantId 的菜品")
                restaurantRepository.getDishesByRestaurant(restaurantId).collect { dishes ->
                    println("DEBUG ViewModel: 收到菜品数量：${dishes.size}")
                    dishes.forEach { dish ->
                        println("DEBUG ViewModel: 菜品 - ${dish.name}")
                    }
                    _uiState.value = _uiState.value.copy(dishes = dishes)
                }
            } catch (e: Exception) {
                e.printStackTrace()
                println("ERROR: 加载菜品失败：${e.message}")
                _uiState.value = _uiState.value.copy(dishes = emptyList())
            }
        }
    }

    fun loadCartState() {
        viewModelScope.launch {
            cartRepository.allCartItems.collect { cartItems ->
                val itemCount = cartItems.sumOf { it.quantity }
                val totalAmount = cartItems.sumOf { it.price * it.quantity + it.packingFee * it.quantity }
                _uiState.value = _uiState.value.copy(
                    cartItemCount = itemCount,
                    cartTotalAmount = totalAmount
                )
            }
        }
    }

    fun addToCart(dish: Dish, restaurantId: Long) {
        viewModelScope.launch {
            val existingItems = cartRepository.allCartItems.firstOrNull() ?: emptyList()
            val existingItem = existingItems.find { it.dishId == dish.id }

            if (existingItem != null) {
                cartRepository.updateQuantity(existingItem, existingItem.quantity + 1)
            } else {
                val cartItem = CartItem(
                    id = System.currentTimeMillis(),
                    dishId = dish.id,
                    restaurantId = restaurantId,
                    name = dish.name,
                    price = dish.price,
                    packingFee = dish.packingFee,
                    imageUrl = dish.imageUrl,
                    quantity = 1,
                    categoryName = dish.categoryName
                )
                cartRepository.addToCart(cartItem)
            }
        }
    }
}

/**
 * 餐厅详情页 UI 状态
 */
data class RestaurantDetailUiState(
    val restaurant: Restaurant? = null,
    val dishes: List<Dish> = emptyList(),
    val cartItemCount: Int = 0,
    val cartTotalAmount: Double = 0.0
)
