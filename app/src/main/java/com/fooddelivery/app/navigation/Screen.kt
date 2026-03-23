package com.fooddelivery.app.navigation

import androidx.navigation.NavType
import androidx.navigation.navArgument

/**
 * 导航路由定义 - 架构师 (成员 A) 负责维护
 */
sealed class Screen(val route: String, val navArgs: List<NavArgument> = emptyList()) {
    data class NavArgument(
        val name: String,
        val type: NavType<*>,
        val nullable: Boolean = false,
        val defaultValue: Any? = null
    )
    
    object Home : Screen("home")
    object Search : Screen("search")
    object RestaurantDetail : Screen(
        route = "restaurant_detail/{restaurantId}",
        navArgs = listOf(
            NavArgument("restaurantId", NavType.LongType)
        )
    ) {
        fun createRoute(restaurantId: Long) = "restaurant_detail/$restaurantId"
    }
    object ShoppingCart : Screen("shopping_cart")
    object Checkout : Screen("checkout")
    object OrderStatus : Screen(
        route = "order_status/{orderId}",
        navArgs = listOf(
            NavArgument("orderId", NavType.LongType)
        )
    ) {
        fun createRoute(orderId: Long) = "order_status/$orderId"
    }
    object AddressList : Screen("address_list")
    object AddressEdit : Screen(
        route = "address_edit/{addressId}",
        navArgs = listOf(
            NavArgument("addressId", NavType.LongType, nullable = true, defaultValue = null)
        )
    ) {
        fun createRoute(addressId: Long? = null) = 
            if (addressId == null) "address_edit" else "address_edit/$addressId"
    }
    object Profile : Screen("profile")
    object HistoryOrder : Screen("history_order")
}
