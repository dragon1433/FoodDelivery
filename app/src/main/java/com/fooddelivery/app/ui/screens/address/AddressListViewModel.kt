package com.fooddelivery.app.ui.screens.address

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.fooddelivery.app.data.model.Address
import com.fooddelivery.app.data.repository.AddressRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * 地址管理 ViewModel
 */
class AddressListViewModel(
    private val addressRepository: AddressRepository
) : ViewModel() {
    
    private val _uiState = MutableStateFlow(AddressListUiState())
    val uiState: StateFlow<AddressListUiState> = _uiState.asStateFlow()
    
    var showAddAddressDialog by mutableStateOf(false)
    
    init {
        loadAddresses()
    }
    
    private fun loadAddresses() {
        viewModelScope.launch {
            addressRepository.allAddresses.collect { addresses ->
                println("DEBUG: 地址列表更新，数量 = ${addresses.size}")
                _uiState.value = _uiState.value.copy(addresses = addresses)
            }
        }
    }
    
    fun addAddress(name: String, phone: String, detailAddress: String) {
        viewModelScope.launch {
            val address = Address(
                id = System.currentTimeMillis(),
                name = name,
                phone = phone,
                detailAddress = detailAddress,
                isDefault = _uiState.value.addresses.isEmpty()
            )
            println("DEBUG: 添加地址 - ${address.name}")
            addressRepository.addAddress(address)
        }
    }
    
    fun deleteAddress(address: Address) {
        viewModelScope.launch {
            println("DEBUG: 删除地址 - ${address.name}")
            addressRepository.deleteAddress(address)
        }
    }
    
    class Factory(
        private val addressRepository: AddressRepository
    ) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(AddressListViewModel::class.java)) {
                return AddressListViewModel(addressRepository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}

/**
 * 地址列表 UI 状态
 */
data class AddressListUiState(
    val addresses: List<Address> = emptyList()
)
