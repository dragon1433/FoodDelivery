package com.fooddelivery.app.ui.screens.order

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.fooddelivery.app.data.model.Order
import com.fooddelivery.app.data.repository.OrderRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * 历史订单 ViewModel
 */
class HistoryOrderViewModel(
    private val orderRepository: OrderRepository
) : ViewModel() {
    
    private val _uiState = MutableStateFlow(HistoryOrderUiState())
    val uiState: StateFlow<HistoryOrderUiState> = _uiState.asStateFlow()
    
    init {
        loadOrders()
    }
    
    private fun loadOrders() {
        viewModelScope.launch {
            orderRepository.allOrders.collect { orders ->
                _uiState.value = _uiState.value.copy(orders = orders)
            }
        }
    }
    
    fun cancelOrder(order: Order) {
        viewModelScope.launch {
            println("DEBUG: 取消订单 - ${order.orderNo}")
            orderRepository.cancelOrder(order)
            println("SUCCESS: 订单已取消")
        }
    }
    
    class Factory(
        private val orderRepository: OrderRepository
    ) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(HistoryOrderViewModel::class.java)) {
                return HistoryOrderViewModel(orderRepository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}

/**
 * 历史订单 UI 状态
 */
data class HistoryOrderUiState(
    val orders: List<Order> = emptyList()
)
