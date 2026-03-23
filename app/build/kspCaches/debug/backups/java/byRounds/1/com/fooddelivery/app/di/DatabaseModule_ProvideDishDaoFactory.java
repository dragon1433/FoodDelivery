package com.fooddelivery.app.di;

import com.fooddelivery.app.data.local.AppDatabase;
import com.fooddelivery.app.data.local.DishDao;
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
public final class DatabaseModule_ProvideDishDaoFactory implements Factory<DishDao> {
  private final Provider<AppDatabase> databaseProvider;

  public DatabaseModule_ProvideDishDaoFactory(Provider<AppDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public DishDao get() {
    return provideDishDao(databaseProvider.get());
  }

  public static DatabaseModule_ProvideDishDaoFactory create(
      Provider<AppDatabase> databaseProvider) {
    return new DatabaseModule_ProvideDishDaoFactory(databaseProvider);
  }

  public static DishDao provideDishDao(AppDatabase database) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideDishDao(database));
  }
}
