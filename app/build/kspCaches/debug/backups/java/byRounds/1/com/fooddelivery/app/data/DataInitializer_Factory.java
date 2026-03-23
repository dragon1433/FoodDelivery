package com.fooddelivery.app.data;

import com.fooddelivery.app.data.repository.RestaurantRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
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
public final class DataInitializer_Factory implements Factory<DataInitializer> {
  private final Provider<RestaurantRepository> restaurantRepositoryProvider;

  public DataInitializer_Factory(Provider<RestaurantRepository> restaurantRepositoryProvider) {
    this.restaurantRepositoryProvider = restaurantRepositoryProvider;
  }

  @Override
  public DataInitializer get() {
    return newInstance(restaurantRepositoryProvider.get());
  }

  public static DataInitializer_Factory create(
      Provider<RestaurantRepository> restaurantRepositoryProvider) {
    return new DataInitializer_Factory(restaurantRepositoryProvider);
  }

  public static DataInitializer newInstance(RestaurantRepository restaurantRepository) {
    return new DataInitializer(restaurantRepository);
  }
}
