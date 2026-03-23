package com.fooddelivery.app.ui.screens.address

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fooddelivery.app.FoodDeliveryApplication
import com.fooddelivery.app.data.model.Address
import com.fooddelivery.app.data.repository.AddressRepository
import com.fooddelivery.app.ui.theme.GrayText
import com.fooddelivery.app.ui.theme.OrangePrimary

/**
 * 地址管理页面
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddressListScreen(
    onBackClick: () -> Unit,
    onAddressSelected: (Address) -> Unit,
    viewModel: AddressListViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    
    LaunchedEffect(Unit) {
        println("DEBUG: AddressListScreen 已加载，地址数量：${uiState.addresses.size}")
    }
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("选择配送地址") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "返回"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { 
                        viewModel.showAddAddressDialog = true
                    }) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "添加地址"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        if (uiState.addresses.isEmpty()) {
            // 空状态
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "暂无配送地址",
                        style = MaterialTheme.typography.bodyLarge,
                        color = GrayText
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "点击右上角 + 添加地址",
                        style = MaterialTheme.typography.bodySmall,
                        color = GrayText
                    )
                }
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(uiState.addresses) { address ->
                    AddressItem(
                        address = address,
                        isDefault = address.isDefault,
                        onClick = { 
                            println("DEBUG: 选择地址 - ${address.name}")
                            onAddressSelected(address) 
                        },
                        onDeleteClick = { 
                            println("DEBUG: 删除地址 - ${address.name}")
                            viewModel.deleteAddress(address) 
                        }
                    )
                }
            }
        }
        
        // 添加地址对话框
        if (viewModel.showAddAddressDialog) {
            AddAddressDialog(
                onDismiss = { viewModel.showAddAddressDialog = false },
                onConfirm = { name, phone, detail ->
                    viewModel.addAddress(name, phone, detail)
                    viewModel.showAddAddressDialog = false
                }
            )
        }
    }
}

@Composable
private fun AddressItem(
    address: Address,
    isDefault: Boolean,
    onClick: () -> Unit,
    onDeleteClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        elevation = CardDefaults.cardElevation(2.dp),
        colors = if (isDefault) {
            CardDefaults.cardColors(containerColor = OrangePrimary.copy(alpha = 0.1f))
        } else {
            CardDefaults.cardColors()
        }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = address.name,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    if (isDefault) {
                        Spacer(modifier = Modifier.width(8.dp))
                        AssistChip(
                            onClick = { },
                            label = { Text("默认", style = MaterialTheme.typography.labelSmall) },
                            colors = AssistChipDefaults.assistChipColors(
                                containerColor = OrangePrimary
                            )
                        )
                    }
                }
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = address.phone,
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = address.detailAddress,
                    style = MaterialTheme.typography.bodySmall,
                    color = GrayText
                )
            }
            
            IconButton(onClick = onDeleteClick) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "删除",
                    tint = GrayText
                )
            }
        }
    }
}

@Composable
private fun AddAddressDialog(
    onDismiss: () -> Unit,
    onConfirm: (String, String, String) -> Unit
) {
    var name by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var detailAddress by remember { mutableStateOf("") }
    
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("添加配送地址") },
        text = {
            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("收货人姓名") },
                    singleLine = true
                )
                OutlinedTextField(
                    value = phone,
                    onValueChange = { phone = it },
                    label = { Text("手机号码") },
                    singleLine = true
                )
                OutlinedTextField(
                    value = detailAddress,
                    onValueChange = { detailAddress = it },
                    label = { Text("详细地址") },
                    placeholder = { Text("街道、楼牌号等信息") },
                    maxLines = 3
                )
            }
        },
        confirmButton = {
            Button(
                onClick = { onConfirm(name, phone, detailAddress) },
                enabled = name.isNotBlank() && phone.isNotBlank() && detailAddress.isNotBlank()
            ) {
                Text("确定")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("取消")
            }
        }
    )
}
