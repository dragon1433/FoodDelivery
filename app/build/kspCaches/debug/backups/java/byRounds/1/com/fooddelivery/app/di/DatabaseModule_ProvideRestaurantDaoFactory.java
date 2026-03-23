package com.fooddelivery.app.di;

import com.fooddelivery.app.data.local.AppDatabase;
import com.fooddelivery.app.data.local.RestaurantDao;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
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
public final class DatabaseModule_ProvideRestaurantDaoFactory implements Factory<RestaurantDao> {
  private final Provider<AppDatabase> databaseProvider;

  public DatabaseModule_ProvideRestaurantDaoFactory(Provider<AppDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public RestaurantDao get() {
    return provideRestaurantDao(databaseProvider.get());
  }

  public static DatabaseModule_ProvideRestaurantDaoFactory create(
      Provider<AppDatabase> databaseProvider) {
    return new DatabaseModule_ProvideRestaurantDaoFactory(databaseProvider);
  }

  public static RestaurantDao provideRestaurantDao(AppDatabase database) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideRestaurantDao(database));
  }
}
