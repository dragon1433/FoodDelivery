package com.fooddelivery.app.ui.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
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
import com.fooddelivery.app.data.model.Banner
import com.fooddelivery.app.data.model.Category
import com.fooddelivery.app.data.model.Restaurant
import com.fooddelivery.app.ui.theme.*

/**
 * 首页 - 展示轮播图、分类、推荐餐厅
 */
@Composable
fun HomeScreen(
    onRestaurantClick: (Long) -> Unit,
    onCategoryClick: (Long) -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(bottom = 80.dp)
    ) {
        // 搜索栏
        item {
            SearchBar()
        }
        
        // 轮播图
        item {
            BannerSection(banners = uiState.banners)
        }
        
        // 分类
        item {
            CategorySection(
                categories = uiState.categories,
                onCategoryClick = onCategoryClick
            )
        }
        
        // 推荐餐厅
        item {
            Text(
                text = "推荐餐厅",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(16.dp)
            )
        }
        
        // 餐厅列表
        items(uiState.restaurants) { restaurant ->
            RestaurantItem(
                restaurant = restaurant,
                onClick = { onRestaurantClick(restaurant.id) }
            )
            HorizontalDivider(
                modifier = Modifier.padding(horizontal = 16.dp),
                color = GrayDivider
            )
        }
    }
}

@Composable
private fun SearchBar() {
    OutlinedTextField(
        value = "",
        onValueChange = { },
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        placeholder = { Text("搜索餐厅、美食") },
        leadingIcon = {
            Icon(
                imageVector = androidx.compose.material.icons.Icons.Default.Search,
                contentDescription = "搜索"
            )
        },
        shape = RoundedCornerShape(24.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = OrangePrimary,
            unfocusedBorderColor = GrayDivider
        )
    )
}

@Composable
private fun BannerSection(banners: List<Banner>) {
    if (banners.isEmpty()) return
    
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(banners) { banner ->
            val painter = rememberAsyncImagePainter(
                ImageRequest.Builder(LocalContext.current)
                    .data(banner.imageUrl)
                    .crossfade(true)
                    .build()
            )
            Image(
                painter = painter,
                contentDescription = banner.title,
                modifier = Modifier
                    .width(300.dp)
                    .fillMaxHeight()
                    .clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop
            )
        }
    }
}

@Composable
private fun CategorySection(
    categories: List<Category>,
    onCategoryClick: (Long) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = "美食分类",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )
        
        Spacer(modifier = Modifier.height(12.dp))
        
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            categories.take(6).forEach { category ->
                CategoryItem(
                    category = category,
                    onClick = { onCategoryClick(category.id) }
                )
            }
        }
    }
}

@Composable
private fun CategoryItem(
    category: Category,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .width(80.dp)
            .clickable(onClick = onClick)
    ) {
        val painter = rememberAsyncImagePainter(
            ImageRequest.Builder(LocalContext.current)
                .data(category.iconUrl)
                .crossfade(true)
                .build()
        )
        Image(
            painter = painter,
            contentDescription = category.name,
            modifier = Modifier
                .size(60.dp)
                .clip(RoundedCornerShape(12.dp)),
            contentScale = ContentScale.Crop
        )
        
        Spacer(modifier = Modifier.height(4.dp))
        
        Text(
            text = category.name,
            style = MaterialTheme.typography.bodySmall,
            color = GrayText
        )
    }
}

@Composable
private fun RestaurantItem(
    restaurant: Restaurant,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
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
                .size(80.dp)
                .clip(RoundedCornerShape(8.dp)),
            contentScale = ContentScale.Crop
        )
        
        Spacer(modifier = Modifier.width(12.dp))
        
        // 餐厅信息
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = restaurant.name,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            
            Spacer(modifier = Modifier.height(4.dp))
            
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "${restaurant.rating}",
                    style = MaterialTheme.typography.bodySmall,
                    color = OrangePrimary
                )
                
                Spacer(modifier = Modifier.width(4.dp))
                
                Text(
                    text = "月售${restaurant.monthlySales}",
                    style = MaterialTheme.typography.bodySmall,
                    color = GrayText
                )
                
                Spacer(modifier = Modifier.width(4.dp))
                
                Text(
                    text = restaurant.deliveryTime,
                    style = MaterialTheme.typography.bodySmall,
                    color = GrayText
                )
            }
            
            Spacer(modifier = Modifier.height(4.dp))
            
            Text(
                text = "${restaurant.distance}km | 配送费¥${restaurant.deliveryFee}",
                style = MaterialTheme.typography.bodySmall,
                color = GrayText
            )
        }
    }
}
