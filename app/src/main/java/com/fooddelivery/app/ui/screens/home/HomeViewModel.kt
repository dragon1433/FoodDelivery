package com.fooddelivery.app.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fooddelivery.app.data.mock.MockDataGenerator
import com.fooddelivery.app.data.model.Banner
import com.fooddelivery.app.data.model.Category
import com.fooddelivery.app.data.model.Restaurant
import com.fooddelivery.app.data.repository.RestaurantRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * 首页 ViewModel
 */
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val restaurantRepository: RestaurantRepository
) : ViewModel() {
    
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()
    
    init {
        loadRestaurants()
        loadMockData()
    }
    
    private fun loadRestaurants() {
        viewModelScope.launch {
            restaurantRepository.allRestaurants.collect { restaurants ->
                _uiState.value = _uiState.value.copy(
                    restaurants = restaurants
                )
            }
        }
    }
    
    private fun loadMockData() {
        // 加载 Mock 数据（分类和轮播图）
        val mockData = MockDataGenerator
        _uiState.value = _uiState.value.copy(
            categories = mockData.generateCategories(),
            banners = mockData.generateBanners()
        )
    }
}

/**
 * 首页 UI 状态
 */
data class HomeUiState(
    val restaurants: List<Restaurant> = emptyList(),
    val categories: List<Category> = emptyList(),
    val banners: List<Banner> = emptyList()
)
