package com.fooddelivery.app.ui.screens.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fooddelivery.app.data.model.CartItem
import com.fooddelivery.app.data.repository.CartRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * 购物车 ViewModel
 */
@HiltViewModel
class CartViewModel @Inject constructor(
    private val cartRepository: CartRepository
) : ViewModel() {
    
    private val _uiState = MutableStateFlow(CartUiState())
    val uiState: StateFlow<CartUiState> = _uiState.asStateFlow()
    
    init {
        loadCartItems()
    }
    
    private fun loadCartItems() {
        viewModelScope.launch {
            cartRepository.allCartItems.collect { items ->
                val totalAmount = items.sumOf { it.price * it.quantity }
                val deliveryFee = 9.0 // TODO: 根据餐厅计算
                val packingFee = items.sumOf { it.packingFee * it.quantity }
                
                _uiState.value = _uiState.value.copy(
                    cartItems = items,
                    totalAmount = totalAmount,
                    deliveryFee = deliveryFee,
                    packingFee = packingFee
                )
            }
        }
    }
    
    fun updateQuantity(cartItem: CartItem, newQuantity: Int) {
        viewModelScope.launch {
            cartRepository.updateQuantity(cartItem, newQuantity)
        }
    }
    
    fun removeFromCart(cartItem: CartItem) {
        viewModelScope.launch {
            cartRepository.removeFromCart(cartItem)
        }
    }
}

/**
 * 购物车 UI 状态
 */
data class CartUiState(
    val cartItems: List<CartItem> = emptyList(),
    val totalAmount: Double = 0.0,
    val deliveryFee: Double = 0.0,
    val packingFee: Double = 0.0
)
