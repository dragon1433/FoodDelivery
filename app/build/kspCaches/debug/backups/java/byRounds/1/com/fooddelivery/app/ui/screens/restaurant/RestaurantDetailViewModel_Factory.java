package com.fooddelivery.app.ui.screens.restaurant;

import com.fooddelivery.app.data.repository.CartRepository;
import com.fooddelivery.app.data.repository.RestaurantRepository;
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
public final class RestaurantDetailViewModel_Factory implements Factory<RestaurantDetailViewModel> {
  private final Provider<RestaurantRepository> restaurantRepositoryProvider;

  private final Provider<CartRepository> cartRepositoryProvider;

  public RestaurantDetailViewModel_Factory(
      Provider<RestaurantRepository> restaurantRepositoryProvider,
      Provider<CartRepository> cartRepositoryProvider) {
    this.restaurantRepositoryProvider = restaurantRepositoryProvider;
    this.cartRepositoryProvider = cartRepositoryProvider;
  }

  @Override
  public RestaurantDetailViewModel get() {
    return newInstance(restaurantRepositoryProvider.get(), cartRepositoryProvider.get());
  }

  public static RestaurantDetailViewModel_Factory create(
      Provider<RestaurantRepository> restaurantRepositoryProvider,
      Provider<CartRepository> cartRepositoryProvider) {
    return new RestaurantDetailViewModel_Factory(restaurantRepositoryProvider, cartRepositoryProvider);
  }

  public static RestaurantDetailViewModel newInstance(RestaurantRepository restaurantRepository,
      CartRepository cartRepository) {
    return new RestaurantDetailViewModel(restaurantRepository, cartRepository);
  }
}
