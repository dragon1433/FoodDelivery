package com.fooddelivery.app.ui.screens.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.fooddelivery.app.ui.theme.*

/**
 * 个人中心页面
 */
@Composable
fun ProfileScreen(
    onHistoryOrderClick: () -> Unit,
    onAddressListClick: () -> Unit,
    onSettingsClick: () -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        // 用户信息头部
        item {
            UserProfileHeader()
        }
        
        // 我的订单
        item {
            MyOrdersSection(onHistoryOrderClick = onHistoryOrderClick)
        }
        
        // 其他功能
        item {
            OtherFunctionsSection(
                onAddressListClick = onAddressListClick,
                onSettingsClick = onSettingsClick
            )
        }
    }
}

@Composable
private fun UserProfileHeader() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(OrangePrimary)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // 用户头像
        Surface(
            modifier = Modifier.size(80.dp),
            shape = MaterialTheme.shapes.medium,
            color = White
        ) {
            Box(
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "用户",
                    modifier = Modifier.size(60.dp),
                    tint = OrangePrimary
                )
            }
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // 用户名
        Text(
            text = "外卖达人",
            style = MaterialTheme.typography.titleLarge,
            color = White,
            fontWeight = FontWeight.Bold
        )
        
        Spacer(modifier = Modifier.height(4.dp))
        
        // 手机号
        Text(
            text = "138****8888",
            style = MaterialTheme.typography.bodyMedium,
            color = White.copy(alpha = 0.8f)
        )
    }
}

@Composable
private fun MyOrdersSection(onHistoryOrderClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = "我的订单",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            OrderStatusItem(
                icon = Icons.Default.List,
                label = "全部订单",
                onClick = onHistoryOrderClick
            )
            OrderStatusItem(
                icon = Icons.Default.Add,
                label = "待使用",
                onClick = { }
            )
            OrderStatusItem(
                icon = Icons.Default.ShoppingCart,
                label = "配送中",
                onClick = { }
            )
            OrderStatusItem(
                icon = Icons.Default.Star,
                label = "待评价",
                onClick = { }
            )
        }
    }
}

@Composable
private fun OrderStatusItem(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    label: String,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clickable(onClick = onClick)
            .padding(8.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            modifier = Modifier.size(32.dp),
            tint = OrangePrimary
        )
        
        Spacer(modifier = Modifier.height(4.dp))
        
        Text(
            text = label,
            style = MaterialTheme.typography.bodySmall,
            color = GrayText
        )
    }
}

@Composable
private fun OtherFunctionsSection(
    onAddressListClick: () -> Unit,
    onSettingsClick: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        FunctionItem(
            icon = Icons.Default.LocationOn,
            label = "地址管理",
            onClick = onAddressListClick
        )
        
        HorizontalDivider(
            modifier = Modifier.padding(horizontal = 16.dp),
            color = GrayDivider
        )
        
        FunctionItem(
            icon = Icons.Default.Settings,
            label = "设置",
            onClick = onSettingsClick
        )
        
        HorizontalDivider(
            modifier = Modifier.padding(horizontal = 16.dp),
            color = GrayDivider
        )
        
        FunctionItem(
            icon = Icons.Default.Info,
            label = "关于我们",
            onClick = { }
        )
    }
}

@Composable
private fun FunctionItem(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    label: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = label,
                tint = OrangePrimary
            )
            
            Spacer(modifier = Modifier.width(16.dp))
            
            Text(
                text = label,
                style = MaterialTheme.typography.bodyLarge
            )
        }
        
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
            contentDescription = "进入"
        )
    }
}
