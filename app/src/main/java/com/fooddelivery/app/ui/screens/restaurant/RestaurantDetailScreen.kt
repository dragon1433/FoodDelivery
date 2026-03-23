package com.fooddelivery.app.ui.screens.restaurant

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.fooddelivery.app.data.model.Dish
import com.fooddelivery.app.data.model.Restaurant
import com.fooddelivery.app.ui.theme.*

/**
 * 餐厅详情页 - 展示餐厅信息和菜品列表
 */
@Composable
fun RestaurantDetailScreen(
    restaurantId: Long,
    onBackClick: () -> Unit,
    onCartClick: () -> Unit,
    viewModel: RestaurantDetailViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    
    LaunchedEffect(restaurantId) {
        // 先加载餐厅信息
        viewModel.loadRestaurant(restaurantId)
        // 再加载菜品
        viewModel.loadDishes(restaurantId)
        // 最后加载购物车状态
        viewModel.loadCartState()
    }
    
    // 显示调试信息
    println("DEBUG UI: 餐厅 = ${uiState.restaurant?.name}, 菜品数量 = ${uiState.dishes.size}")
    
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // 顶部栏 - 即使餐厅为 null 也显示一个占位
            if (uiState.restaurant != null) {
                RestaurantHeader(
                    restaurant = uiState.restaurant,
                    onBackClick = onBackClick
                )
            } else {
                // 餐厅加载中或加载失败的占位
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
            
            // 菜品列表
            DishList(
                dishes = uiState.dishes,
                onAddToCart = { dish ->
                    viewModel.addToCart(dish, restaurantId)
                }
            )
        }
        
        // 购物车按钮
        if (uiState.cartItemCount > 0) {
            FloatingActionButton(
                onClick = onCartClick,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(16.dp),
                containerColor = OrangePrimary
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.ShoppingCart,
                        contentDescription = "购物车"
                    )
                    Text("¥${String.format("%.2f", uiState.cartTotalAmount)}")
                }
            }
        }
    }
}

@Composable
private fun RestaurantHeader(
    restaurant: Restaurant?,
    onBackClick: () -> Unit
) {
    if (restaurant == null) return
    
    Box(modifier = Modifier.fillMaxWidth()) {
        // 餐厅图片
        val painter = rememberAsyncImagePainter(
            ImageRequest.Builder(LocalContext.current)
                .data(restaurant.imageUrl)
                .crossfade(true)
                .build()
        )
        Image(
            painter = painter,
            contentDescription = restaurant.name,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            contentScale = ContentScale.Crop
        )
        
        // 返回按钮
        IconButton(
            onClick = onBackClick,
            modifier = Modifier
                .padding(8.dp)
                .align(Alignment.TopStart)
                .background(White, shape = RoundedCornerShape(50))
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "返回",
                tint = Black
            )
        }
        
        // 餐厅信息卡片
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(16.dp),
            elevation = CardDefaults.cardElevation(4.dp),
            shape = RoundedCornerShape(12.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = restaurant.name,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
                
                Spacer(modifier = Modifier.height(8.dp))
                
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    RatingInfo(restaurant)
                    DeliveryInfo(restaurant)
                }
                
                Spacer(modifier = Modifier.height(8.dp))
                
                Text(
                    text = restaurant.description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = GrayText
                )
            }
        }
    }
}

@Composable
private fun RatingInfo(restaurant: Restaurant) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = "${restaurant.rating}",
            style = MaterialTheme.typography.titleMedium,
            color = OrangePrimary,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.width(4.dp))
        Column {
            Text(
                text = "评分",
                style = MaterialTheme.typography.bodySmall,
                color = GrayText
            )
        }
    }
}

@Composable
private fun DeliveryInfo(restaurant: Restaurant) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = restaurant.deliveryTime,
            style = MaterialTheme.typography.bodyMedium,
            color = GrayText
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = "·",
            style = MaterialTheme.typography.bodyMedium,
            color = GrayText
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = "${restaurant.distance}km",
            style = MaterialTheme.typography.bodyMedium,
            color = GrayText
        )
    }
}

@Composable
private fun DishList(
    dishes: List<Dish>,
    onAddToCart: (Dish) -> Unit
) {
    // 按分类分组
    val groupedDishes = dishes.groupBy { it.categoryName }
    
    if (dishes.isEmpty()) {
        // 显示空状态提示
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "暂无菜品",
                    style = MaterialTheme.typography.bodyLarge,
                    color = GrayText
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "餐厅还未上架菜品",
                    style = MaterialTheme.typography.bodySmall,
                    color = GrayText
                )
            }
        }
        return
    }
    
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(top = 180.dp, bottom = 80.dp)
    ) {
        groupedDishes.forEach { (category, categoryDishes) ->
            item {
                Text(
                    text = category,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(16.dp)
                )
            }
            
            items(categoryDishes) { dish ->
                DishItem(
                    dish = dish,
                    onAddToCart = { onAddToCart(dish) }
                )
                HorizontalDivider(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    color = GrayDivider
                )
            }
        }
    }
}

@Composable
private fun DishItem(
    dish: Dish,
    onAddToCart: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // 菜品图片
        val painter = rememberAsyncImagePainter(
            ImageRequest.Builder(LocalContext.current)
                .data(dish.imageUrl)
                .crossfade(true)
                .build()
        )
        Image(
            painter = painter,
            contentDescription = dish.name,
            modifier = Modifier
                .size(80.dp)
                .clip(RoundedCornerShape(8.dp)),
            contentScale = ContentScale.Crop
        )
        
        Spacer(modifier = Modifier.width(12.dp))
        
        // 菜品信息
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = dish.name,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            
            Spacer(modifier = Modifier.height(4.dp))
            
            Text(
                text = dish.description,
                style = MaterialTheme.typography.bodySmall,
                color = GrayText,
                maxLines = 2
            )
            
            Spacer(modifier = Modifier.height(4.dp))
            
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "¥${dish.price}",
                    style = MaterialTheme.typography.titleMedium,
                    color = OrangePrimary,
                    fontWeight = FontWeight.Bold
                )
                
                Spacer(modifier = Modifier.width(8.dp))
                
                Text(
                    text = "月售${dish.monthSales}",
                    style = MaterialTheme.typography.bodySmall,
                    color = GrayText
                )
            }
        }
        
        // 添加按钮
        IconButton(
            onClick = onAddToCart,
            modifier = Modifier
                .size(32.dp)
                .background(OrangePrimary, shape = RoundedCornerShape(50))
        ) {
            Text(
                text = "+",
                color = White,
                style = MaterialTheme.typography.titleLarge
            )
        }
    }
}
