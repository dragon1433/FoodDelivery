package com.fooddelivery.app.data.model

data class Category(
    val id: Long,
    val name: String,
    val iconUrl: String,
    val type: CategoryType = CategoryType.FOOD
)

enum class CategoryType {
    FOOD,
    DESSERT,
    DRINK,
    SNACK,
    FRUIT,
    SUPERMARKET
}

data class Banner(
    val id: Long,
    val imageUrl: String,
    val title: String,
    val linkType: BannerLinkType,
    val linkId: Long? = null
)

enum class BannerLinkType {
    RESTAURANT,
    CATEGORY,
    ACTIVITY
}
