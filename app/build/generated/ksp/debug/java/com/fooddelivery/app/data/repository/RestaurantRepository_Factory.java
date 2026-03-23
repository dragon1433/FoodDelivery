package com.fooddelivery.app.data.repository;

import com.fooddelivery.app.data.local.DishDao;
import com.fooddelivery.app.data.local.RestaurantDao;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava",
    "cast"
})
public final class RestaurantRepository_Factory implements Factory<RestaurantRepository> {
  private final Provider<RestaurantDao> restaurantDaoProvider;

  private final Provider<DishDao> dishDaoProvider;

  public RestaurantRepository_Factory(Provider<RestaurantDao> restaurantDaoProvider,
      Provider<DishDao> dishDaoProvider) {
    this.restaurantDaoProvider = restaurantDaoProvider;
    this.dishDaoProvider = dishDaoProvider;
  }

  @Override
  public RestaurantRepository get() {
    return newInstance(restaurantDaoProvider.get(), dishDaoProvider.get());
  }

  public static RestaurantRepository_Factory create(Provider<RestaurantDao> restaurantDaoProvider,
      Provider<DishDao> dishDaoProvider) {
    return new RestaurantRepository_Factory(restaurantDaoProvider, dishDaoProvider);
  }

  public static RestaurantRepository newInstance(RestaurantDao restaurantDao, DishDao dishDao) {
    return new RestaurantRepository(restaurantDao, dishDao);
  }
}
