package com.fooddelivery.app.ui.screens.order

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.fooddelivery.app.data.model.Order
import com.fooddelivery.app.data.model.OrderStatus
import com.fooddelivery.app.ui.theme.*
import java.text.SimpleDateFormat
import java.util.*

/**
 * 历史订单页面
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistoryOrderScreen(
    onBackClick: () -> Unit,
    onOrderClick: (Long) -> Unit,
    viewModel: HistoryOrderViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("历史订单") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "返回"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        if (uiState.orders.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "📦",
                        style = MaterialTheme.typography.displayLarge
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "暂无订单",
                        style = MaterialTheme.typography.titleMedium,
                        color = GrayText
                    )
                }
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentPadding = PaddingValues(16.dp)
            ) {
                items(uiState.orders) { order ->
                    OrderCard(
                        order = order,
                        onClick = { onOrderClick(order.id) },
                        onCancelOrderClick = { viewModel.cancelOrder(order) }
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                }
            }
        }
    }
}

@Composable
private fun OrderCard(
    order: Order,
    onClick: () -> Unit,
    onCancelOrderClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        elevation = CardDefaults.cardElevation(2.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // 订单头部
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "订单号：${order.orderNo}",
                    style = MaterialTheme.typography.bodySmall,
                    color = GrayText
                )
                
                Text(
                    text = getOrderStatusText(order.status),
                    style = MaterialTheme.typography.bodySmall,
                    color = when (order.status) {
                        OrderStatus.PENDING_PAYMENT -> OrangePrimary
                        OrderStatus.PAID -> OrangePrimary
                        OrderStatus.PREPARING -> BlueAccent
                        OrderStatus.DELIVERING -> BlueAccent
                        OrderStatus.DELIVERED -> GreenSuccess
                        OrderStatus.COMPLETED -> GreenSuccess
                        OrderStatus.CANCELLED -> RedError
                    }
                )
            }
            
            Spacer(modifier = Modifier.height(12.dp))
            
            // 商品信息
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "共${order.items.size}件商品",
                    style = MaterialTheme.typography.bodyMedium
                )
                
                Spacer(modifier = Modifier.width(8.dp))
                
                Text(
                    text = "·",
                    style = MaterialTheme.typography.bodyMedium,
                    color = GrayText
                )
                
                Spacer(modifier = Modifier.width(8.dp))
                
                Text(
                    text = formatTimestamp(order.createTime.time),
                    style = MaterialTheme.typography.bodySmall,
                    color = GrayText
                )
            }
            
            Spacer(modifier = Modifier.height(12.dp))
            HorizontalDivider(color = GrayDivider)
            Spacer(modifier = Modifier.height(12.dp))
            
            // 订单金额
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "实付款",
                    style = MaterialTheme.typography.bodyMedium,
                    color = GrayText
                )
                
                Text(
                    text = "¥${String.format("%.2f", order.totalAmount + order.deliveryFee + order.packingFee)}",
                    style = MaterialTheme.typography.titleLarge,
                    color = OrangePrimary,
                    fontWeight = FontWeight.Bold
                )
            }
            
            // 订单操作按钮
            if (order.status == OrderStatus.PENDING_PAYMENT || order.status == OrderStatus.PAID) {
                Spacer(modifier = Modifier.height(12.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    TextButton(onClick = onCancelOrderClick) {
                        Text("取消订单")
                    }
                    
                    Spacer(modifier = Modifier.width(8.dp))
                    
                    if (order.status == OrderStatus.PAID) {
                        Button(
                            onClick = { /* 确认收货 */ },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = OrangePrimary
                            )
                        ) {
                            Text("确认收货")
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun getOrderStatusText(status: OrderStatus): String {
    return when (status) {
        OrderStatus.PENDING_PAYMENT -> "待支付"
        OrderStatus.PAID -> "已支付"
        OrderStatus.PREPARING -> "制作中"
        OrderStatus.DELIVERING -> "配送中"
        OrderStatus.DELIVERED -> "已送达"
        OrderStatus.COMPLETED -> "已完成"
        OrderStatus.CANCELLED -> "已取消"
    }
}

@Composable
private fun formatTimestamp(timestamp: Long): String {
    val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())
    return sdf.format(Date(timestamp))
}
