package com.fooddelivery.app.ui.screens.home;

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
public final class HomeViewModel_Factory implements Factory<HomeViewModel> {
  private final Provider<RestaurantRepository> restaurantRepositoryProvider;

  public HomeViewModel_Factory(Provider<RestaurantRepository> restaurantRepositoryProvider) {
    this.restaurantRepositoryProvider = restaurantRepositoryProvider;
  }

  @Override
  public HomeViewModel get() {
    return newInstance(restaurantRepositoryProvider.get());
  }

  public static HomeViewModel_Factory create(
      Provider<RestaurantRepository> restaurantRepositoryProvider) {
    return new HomeViewModel_Factory(restaurantRepositoryProvider);
  }

  public static HomeViewModel newInstance(RestaurantRepository restaurantRepository) {
    return new HomeViewModel(restaurantRepository);
  }
}
