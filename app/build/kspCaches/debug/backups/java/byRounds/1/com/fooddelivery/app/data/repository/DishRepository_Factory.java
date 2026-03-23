package com.fooddelivery.app.data.repository;

import com.fooddelivery.app.data.local.DishDao;
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
public final class DishRepository_Factory implements Factory<DishRepository> {
  private final Provider<DishDao> dishDaoProvider;

  public DishRepository_Factory(Provider<DishDao> dishDaoProvider) {
    this.dishDaoProvider = dishDaoProvider;
  }

  @Override
  public DishRepository get() {
    return newInstance(dishDaoProvider.get());
  }

  public static DishRepository_Factory create(Provider<DishDao> dishDaoProvider) {
    return new DishRepository_Factory(dishDaoProvider);
  }

  public static DishRepository newInstance(DishDao dishDao) {
    return new DishRepository(dishDao);
  }
}
