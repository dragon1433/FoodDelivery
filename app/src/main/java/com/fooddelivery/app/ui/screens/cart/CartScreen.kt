package com.fooddelivery.app.ui.screens.cart

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.fooddelivery.app.data.model.CartItem
import com.fooddelivery.app.ui.theme.*

/**
 * 购物车页面
 */
@Composable
fun CartScreen(
    onCheckoutClick: () -> Unit,
    viewModel: CartViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    
    if (uiState.cartItems.isEmpty()) {
        // 空购物车
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "🛒",
                    style = MaterialTheme.typography.displayLarge
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "购物车是空的",
                    style = MaterialTheme.typography.titleMedium,
                    color = GrayText
                )
            }
        }
    } else {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // 购物车列表
            LazyColumn(
                modifier = Modifier.weight(1f),
                contentPadding = PaddingValues(16.dp)
            ) {
                items(uiState.cartItems) { cartItem ->
                    CartItemRow(
                        cartItem = cartItem,
                        onIncreaseQuantity = { viewModel.updateQuantity(cartItem, cartItem.quantity + 1) },
                        onDecreaseQuantity = { viewModel.updateQuantity(cartItem, cartItem.quantity - 1) },
                        onDeleteClick = { viewModel.removeFromCart(cartItem) }
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                }
            }
            
            // 底部结算栏
            CheckoutBar(
                totalAmount = uiState.totalAmount,
                deliveryFee = uiState.deliveryFee,
                packingFee = uiState.packingFee,
                onCheckoutClick = onCheckoutClick
            )
        }
    }
}

@Composable
private fun CartItemRow(
    cartItem: CartItem,
    onIncreaseQuantity: () -> Unit,
    onDecreaseQuantity: () -> Unit,
    onDeleteClick: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(2.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // 菜品图片
            val painter = rememberAsyncImagePainter(
                ImageRequest.Builder(LocalContext.current)
                    .data(cartItem.imageUrl)
                    .crossfade(true)
                    .build()
            )
            androidx.compose.foundation.Image(
                painter = painter,
                contentDescription = cartItem.name,
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = androidx.compose.ui.layout.ContentScale.Crop
            )
            
            Spacer(modifier = Modifier.width(12.dp))
            
            // 菜品信息
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = cartItem.name,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                
                Spacer(modifier = Modifier.height(4.dp))
                
                Text(
                    text = "¥${cartItem.price}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = OrangePrimary
                )
                
                Spacer(modifier = Modifier.height(8.dp))
                
                // 数量控制
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    IconButton(
                        onClick = onDecreaseQuantity,
                        modifier = Modifier.size(28.dp)
                    ) {
                        Text(
                            text = "−",
                            style = MaterialTheme.typography.titleLarge
                        )
                    }
                    
                    Text(
                        text = "${cartItem.quantity}",
                        style = MaterialTheme.typography.titleMedium
                    )
                    
                    IconButton(
                        onClick = onIncreaseQuantity,
                        modifier = Modifier.size(28.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "增加"
                        )
                    }
                }
            }
            
            // 删除按钮
            IconButton(
                onClick = onDeleteClick
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "删除",
                    tint = RedError
                )
            }
        }
    }
}

@Composable
private fun CheckoutBar(
    totalAmount: Double,
    deliveryFee: Double,
    packingFee: Double,
    onCheckoutClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(White)
            .padding(16.dp)
    ) {
        // 费用明细
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "配送费",
                style = MaterialTheme.typography.bodyMedium,
                color = GrayText
            )
            Text(
                text = "¥$deliveryFee",
                style = MaterialTheme.typography.bodyMedium
            )
        }
        
        Spacer(modifier = Modifier.height(4.dp))
        
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "包装费",
                style = MaterialTheme.typography.bodyMedium,
                color = GrayText
            )
            Text(
                text = "¥$packingFee",
                style = MaterialTheme.typography.bodyMedium
            )
        }
        
        Spacer(modifier = Modifier.height(8.dp))
        
        HorizontalDivider(color = GrayDivider)
        
        Spacer(modifier = Modifier.height(8.dp))
        
        // 总计
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "总计",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "¥${String.format("%.2f", totalAmount + deliveryFee + packingFee)}",
                style = MaterialTheme.typography.titleLarge,
                color = OrangePrimary,
                fontWeight = FontWeight.Bold
            )
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // 结算按钮
        Button(
            onClick = onCheckoutClick,
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            shape = RoundedCornerShape(24.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = OrangePrimary
            )
        ) {
            Text(
                text = "去结算",
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}
