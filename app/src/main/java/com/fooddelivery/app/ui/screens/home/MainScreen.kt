package com.fooddelivery.app.ui.screens.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.fooddelivery.app.ui.screens.cart.CartScreen
import com.fooddelivery.app.ui.screens.profile.ProfileScreen
import com.fooddelivery.app.ui.screens.search.SearchScreen
import com.fooddelivery.app.ui.theme.*

/**
 * 底部导航项 - 架构师 (成员 A) 配置
 */
sealed class BottomNavItem(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object Home : BottomNavItem("home", "首页", Icons.Default.Home)
    object Search : BottomNavItem("search", "搜索", Icons.Default.Search)
    object Cart : BottomNavItem("cart", "购物车", Icons.Default.ShoppingCart)
    object Profile : BottomNavItem("profile", "我的", Icons.Default.Person)
}

@Composable
fun MainScreenWithBottomNav(
    navController: NavHostController
) {
    var selectedTab by remember { mutableStateOf(0) }
    val bottomNavItems = listOf(
        BottomNavItem.Home,
        BottomNavItem.Search,
        BottomNavItem.Cart,
        BottomNavItem.Profile
    )

    Scaffold(
        bottomBar = {
            NavigationBar(
                containerColor = White,
                tonalElevation = 8.dp
            ) {
                bottomNavItems.forEachIndexed { index, item ->
                    NavigationBarItem(
                        icon = {
                            Icon(
                                imageVector = item.icon,
                                contentDescription = item.title
                            )
                        },
                        label = {
                            Text(
                                text = item.title,
                                style = MaterialTheme.typography.labelSmall
                            )
                        },
                        selected = selectedTab == index,
                        onClick = {
                            selectedTab = index
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = OrangePrimary,
                            selectedTextColor = OrangePrimary,
                            unselectedIconColor = GrayText,
                            unselectedTextColor = GrayText,
                            indicatorColor = OrangeLight
                        )
                    )
                }
            }
        }
    ) { paddingValues ->
        // 内容区域
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            when (selectedTab) {
                0 -> HomeScreen(
                    onRestaurantClick = { restaurantId ->
                        navController.navigate("restaurant_detail/$restaurantId")
                    },
                    onCategoryClick = { categoryId ->
                        // TODO: 实现分类筛选
                    }
                )
                1 -> SearchScreen(
                    onSearchClick = { _ -> }
                )
                2 -> CartScreen(
                    onCheckoutClick = {
                        navController.navigate("checkout")
                    }
                )
                3 -> ProfileScreen(
                    onHistoryOrderClick = {
                        println("DEBUG: 点击全部订单")
                        navController.navigate("history_order")
                    },
                    onAddressListClick = {
                        println("DEBUG: 点击地址管理")
                        navController.navigate("address_list")
                    },
                    onSettingsClick = {
                        println("DEBUG: 点击设置")
                        // TODO: 跳转到设置页面
                    }
                )
            }
        }
    }
}
