package com.fooddelivery.app.ui.screens.checkout

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.fooddelivery.app.data.model.Address
import com.fooddelivery.app.data.model.CartItem
import com.fooddelivery.app.data.model.Order
import com.fooddelivery.app.data.model.OrderItem
import com.fooddelivery.app.data.model.OrderStatus
import com.fooddelivery.app.data.repository.AddressRepository
import com.fooddelivery.app.data.repository.CartRepository
import com.fooddelivery.app.data.repository.OrderRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.Date

/**
 * 结算页 ViewModel
 */
class CheckoutViewModel(
    private val cartRepository: CartRepository,
    private val addressRepository: AddressRepository,
    private val orderRepository: OrderRepository
) : ViewModel() {
    
    private val _uiState = MutableStateFlow(CheckoutUiState())
    val uiState: StateFlow<CheckoutUiState> = _uiState.asStateFlow()
    
    init {
        loadCartItems()
        loadAddress()
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
    
    private fun loadAddress() {
        viewModelScope.launch {
            val address = addressRepository.getDefaultAddress()
            _uiState.value = _uiState.value.copy(selectedAddress = address)
        }
    }
    
    fun setSelectedAddress(address: Address) {
        viewModelScope.launch {
            println("DEBUG: 设置选中地址 - ${address.name}")
            _uiState.value = _uiState.value.copy(selectedAddress = address)
        }
    }
    
    fun createOrder() {
        viewModelScope.launch {
            val address = uiState.value.selectedAddress
            val cartItems = uiState.value.cartItems
            
            if (cartItems.isEmpty()) {
                println("ERROR: 购物车为空，无法创建订单")
                return@launch
            }
            
            if (address == null) {
                println("ERROR: 未选择配送地址，无法创建订单")
                return@launch
            }
            
            try {
                // 创建订单项
                val orderItems = cartItems.map { cartItem ->
                    OrderItem(
                        dishId = cartItem.dishId,
                        name = cartItem.name,
                        price = cartItem.price,
                        quantity = cartItem.quantity,
                        packingFee = cartItem.packingFee
                    )
                }
                
                val firstCartItem = cartItems.firstOrNull()
                if (firstCartItem == null) {
                    println("ERROR: 无法获取购物车商品")
                    return@launch
                }
                
                val order = Order(
                    id = System.currentTimeMillis(),
                    orderNo = generateOrderNo(),
                    restaurantId = firstCartItem.restaurantId,
                    restaurantName = firstCartItem.name,
                    totalAmount = uiState.value.totalAmount,
                    deliveryFee = uiState.value.deliveryFee,
                    packingFee = uiState.value.packingFee,
                    discountAmount = 0.0,
                    actualAmount = uiState.value.totalAmount + uiState.value.deliveryFee + uiState.value.packingFee,
                    status = OrderStatus.PENDING_PAYMENT,
                    createTime = Date(),
                    addressId = address.id,
                    addressDetail = address.detailAddress,
                    receiverName = address.name,
                    receiverPhone = address.phone,
                    items = orderItems
                )
                
                println("DEBUG: 创建订单 - ${order.orderNo}")
                orderRepository.createOrder(order)
                
                // 清空购物车
                cartItems.forEach { cartItem ->
                    cartRepository.removeFromCart(cartItem)
                }
                
                println("SUCCESS: 订单创建成功")
            } catch (e: Exception) {
                e.printStackTrace()
                println("ERROR: 创建订单失败 - ${e.message}")
            }
        }
    }
    
    private fun generateOrderNo(): String {
        return "ORD${System.currentTimeMillis()}${(1000..9999).random()}"
    }
    
    class Factory(
        private val cartRepository: CartRepository,
        private val addressRepository: AddressRepository,
        private val orderRepository: OrderRepository
    ) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(CheckoutViewModel::class.java)) {
                return CheckoutViewModel(cartRepository, addressRepository, orderRepository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}

/**
 * 结算页 UI 状态
 */
data class CheckoutUiState(
    val cartItems: List<CartItem> = emptyList(),
    val selectedAddress: Address? = null,
    val totalAmount: Double = 0.0,
    val deliveryFee: Double = 0.0,
    val packingFee: Double = 0.0
)
