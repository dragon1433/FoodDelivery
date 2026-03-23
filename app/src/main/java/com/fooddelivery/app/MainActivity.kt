package com.fooddelivery.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.fooddelivery.app.navigation.Screen
import com.fooddelivery.app.ui.screens.cart.CartScreen
import com.fooddelivery.app.ui.screens.home.MainScreenWithBottomNav
import com.fooddelivery.app.ui.screens.checkout.CheckoutScreen
import com.fooddelivery.app.ui.screens.order.HistoryOrderScreen
import com.fooddelivery.app.ui.screens.restaurant.RestaurantDetailScreen
import com.fooddelivery.app.ui.screens.address.AddressListScreen
import com.fooddelivery.app.ui.screens.address.AddressListViewModel
import com.fooddelivery.app.ui.screens.checkout.CheckoutViewModel
import com.fooddelivery.app.ui.screens.order.HistoryOrderViewModel
import com.fooddelivery.app.ui.theme.FoodDeliveryTheme
import dagger.hilt.android.AndroidEntryPoint

/**
 * 主 Activity - 架构师 (成员 A) 配置
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FoodDeliveryTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    AppNavigation(navController)
                }
            }
        }
    }
}

/**
 * 应用主导航 - 架构师 (成员 A) 负责
 */
@androidx.compose.runtime.Composable
fun AppNavigation(navController: androidx.navigation.NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "main"
    ) {
        composable("main") {
            MainScreenWithBottomNav(navController)
        }
        // 购物车结算页
        composable("checkout") { backStackEntry ->
            val app = LocalContext.current.applicationContext as FoodDeliveryApplication
            val cartRepository = app.cartRepository
            val addressRepository = app.addressRepository
            val orderRepository = app.orderRepository
            val viewModel: CheckoutViewModel = androidx.lifecycle.viewmodel.compose.viewModel(
                factory = CheckoutViewModel.Factory(cartRepository, addressRepository, orderRepository)
            )
            CheckoutScreen(
                onBackClick = { navController.popBackStack() },
                onOrderConfirmed = {
                    navController.popBackStack()
                },
                onNavigateToAddressList = {
                    navController.navigate("address_list")
                },
                viewModel = viewModel
            )
        }
        // 购物车页面
        composable("shopping_cart") {
            CartScreen(
                onCheckoutClick = {
                    navController.navigate("checkout")
                }
            )
        }
        // 地址列表页面
        composable("address_list") {
            val app = LocalContext.current.applicationContext as FoodDeliveryApplication
            val addressRepository = app.addressRepository
            val viewModel: AddressListViewModel = androidx.lifecycle.viewmodel.compose.viewModel(
                factory = AddressListViewModel.Factory(addressRepository)
            )
            AddressListScreen(
                onBackClick = { navController.popBackStack() },
                onAddressSelected = { address ->
                    // 直接通过 ViewModel 传递地址
                    println("DEBUG: 选中地址 ${address.name}，返回结算页")
                    navController.popBackStack()
                },
                viewModel = viewModel
            )
        }
        // 历史订单页
        composable("history_order") {
            val app = LocalContext.current.applicationContext as FoodDeliveryApplication
            val orderRepository = app.orderRepository
            val viewModel: HistoryOrderViewModel = androidx.lifecycle.viewmodel.compose.viewModel(
                factory = HistoryOrderViewModel.Factory(orderRepository)
            )
            HistoryOrderScreen(
                onBackClick = { navController.popBackStack() },
                onOrderClick = { orderId ->
                    // TODO: 跳转到订单详情
                },
                viewModel = viewModel
            )
        }
        // 餐厅详情页 - 成员 C 使用
        composable(
            route = "restaurant_detail/{restaurantId}",
            arguments = listOf(
                androidx.navigation.navArgument("restaurantId") {
                    type = androidx.navigation.NavType.LongType
                }
            )
        ) { backStackEntry ->
            val restaurantId = backStackEntry.arguments?.getLong("restaurantId") ?: 0L
            println("DEBUG Navigation: 接收到的餐厅 ID = $restaurantId")
            RestaurantDetailScreen(
                restaurantId = restaurantId,
                onBackClick = { navController.popBackStack() },
                onCartClick = { navController.navigate("shopping_cart") }
            )
        }
        // 订单状态页 - 成员 E 使用
        composable(
            route = Screen.OrderStatus.route
        ) { backStackEntry ->
            val orderId = backStackEntry.arguments?.getLong("orderId")
            // TODO: 成员 E 实现订单状态页
            // OrderStatusScreen(orderId!!) { navController.popBackStack() }
        }
        // 地址编辑页 - 成员 D 使用
        composable(route = Screen.AddressEdit.route) { backStackEntry ->
            val addressId = backStackEntry.arguments?.getLong("addressId")
            // TODO: 成员 D 实现地址编辑页
            // AddressEditScreen(addressId, onBackClick = { navController.popBackStack() })
        }
    }
}
