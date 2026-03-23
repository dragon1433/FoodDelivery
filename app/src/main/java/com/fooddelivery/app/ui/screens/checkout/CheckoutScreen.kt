package com.fooddelivery.app.ui.screens.checkout

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.LocationOn
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
import com.fooddelivery.app.data.model.Address
import com.fooddelivery.app.data.model.CartItem
import com.fooddelivery.app.ui.theme.*
import kotlinx.coroutines.launch

/**
 * 订单确认和结算页面
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CheckoutScreen(
    onBackClick: () -> Unit,
    onOrderConfirmed: () -> Unit,
    onNavigateToAddressList: () -> Unit,
    viewModel: CheckoutViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("确认订单") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "返回"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentPadding = PaddingValues(16.dp)
        ) {
            // 配送地址
            item {
                DeliveryAddressSection(
                    address = uiState.selectedAddress,
                    onAddressClick = onNavigateToAddressList
                )
            }
            
            // 商品清单
            item {
                Text(
                    text = "商品清单",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(vertical = 16.dp)
                )
            }
            
            items(uiState.cartItems) { cartItem ->
                CheckoutCartItemRow(cartItem = cartItem)
                Spacer(modifier = Modifier.height(12.dp))
            }
            
            // 费用明细
            item {
                PriceDetailSection(
                    totalAmount = uiState.totalAmount,
                    deliveryFee = uiState.deliveryFee,
                    packingFee = uiState.packingFee
                )
            }
            
            // 备注
            item {
                NoteSection()
            }
            
            // 底部按钮
            item {
                Spacer(modifier = Modifier.height(16.dp))
                SubmitOrderButton(
                    totalAmount = uiState.totalAmount + uiState.deliveryFee + uiState.packingFee,
                    onOrderConfirmed = {
                        viewModel.createOrder()
                        // 订单创建成功后，延迟一下返回，让用户看到提交效果
                        kotlinx.coroutines.GlobalScope.launch(kotlinx.coroutines.Dispatchers.Main) {
                            kotlinx.coroutines.delay(500)
                            onOrderConfirmed()
                        }
                    }
                )
            }
        }
    }
}

@Composable
private fun DeliveryAddressSection(
    address: Address?,
    onAddressClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onAddressClick),
        elevation = CardDefaults.cardElevation(2.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier.weight(1f)
            ) {
                Icon(
                    imageVector = Icons.Default.LocationOn,
                    contentDescription = null,
                    tint = OrangePrimary
                )
                
                Spacer(modifier = Modifier.width(8.dp))
                
                Column {
                    if (address != null) {
                        Text(
                            text = address.name,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "${address.phone} | ${address.detailAddress}",
                            style = MaterialTheme.typography.bodyMedium,
                            color = GrayText
                        )
                    } else {
                        Text(
                            text = "请选择配送地址",
                            style = MaterialTheme.typography.titleMedium
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "暂无配送地址",
                            style = MaterialTheme.typography.bodyMedium,
                            color = GrayText
                        )
                    }
                }
            }
            
            Icon(
                imageVector = Icons.Default.ArrowForward,
                contentDescription = "选择地址"
            )
        }
    }
}

@Composable
private fun CheckoutCartItemRow(cartItem: CartItem) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        androidx.compose.foundation.Image(
            painter = rememberAsyncImagePainter(
                ImageRequest.Builder(LocalContext.current)
                    .data(cartItem.imageUrl)
                    .crossfade(true)
                    .build()
            ),
            contentDescription = cartItem.name,
            modifier = Modifier
                .size(60.dp)
                .clip(RoundedCornerShape(8.dp)),
            contentScale = androidx.compose.ui.layout.ContentScale.Crop
        )
        
        Spacer(modifier = Modifier.width(12.dp))
        
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = cartItem.name,
                style = MaterialTheme.typography.bodyLarge
            )
            
            Spacer(modifier = Modifier.height(4.dp))
            
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = "¥${cartItem.price}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = OrangePrimary
                )
                
                Text(
                    text = "x${cartItem.quantity}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = GrayText
                )
            }
        }
        
        Text(
            text = "¥${String.format("%.2f", cartItem.price * cartItem.quantity)}",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
private fun PriceDetailSection(
    totalAmount: Double,
    deliveryFee: Double,
    packingFee: Double
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(2.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = "费用明细",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            
            Spacer(modifier = Modifier.height(12.dp))
            
            PriceRow("商品总额", totalAmount)
            Spacer(modifier = Modifier.height(4.dp))
            PriceRow("配送费", deliveryFee)
            Spacer(modifier = Modifier.height(4.dp))
            PriceRow("包装费", packingFee)
            
            Spacer(modifier = Modifier.height(8.dp))
            HorizontalDivider(color = GrayDivider)
            Spacer(modifier = Modifier.height(8.dp))
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "实付款",
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
        }
    }
}

@Composable
private fun PriceRow(label: String, amount: Double) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            color = GrayText
        )
        Text(
            text = "¥${String.format("%.2f", amount)}",
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
private fun NoteSection() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(2.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        OutlinedTextField(
            value = "",
            onValueChange = { },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("订单备注（选填）") },
            placeholder = { Text("例如：不要辣、少放盐等") },
            maxLines = 3,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = OrangePrimary,
                unfocusedBorderColor = GrayDivider
            )
        )
    }
}

@Composable
private fun SubmitOrderButton(
    totalAmount: Double,
    onOrderConfirmed: () -> Unit
) {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "需支付",
                style = MaterialTheme.typography.bodyMedium,
                color = GrayText
            )
            Text(
                text = "¥${String.format("%.2f", totalAmount)}",
                style = MaterialTheme.typography.titleLarge,
                color = OrangePrimary,
                fontWeight = FontWeight.Bold
            )
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        Button(
            onClick = onOrderConfirmed,
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            shape = RoundedCornerShape(24.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = OrangePrimary
            )
        ) {
            Text(
                text = "提交订单",
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}
