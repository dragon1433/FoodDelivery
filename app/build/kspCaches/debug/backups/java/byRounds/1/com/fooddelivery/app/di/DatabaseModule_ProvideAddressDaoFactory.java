package com.fooddelivery.app.di;

import com.fooddelivery.app.data.local.AddressDao;
import com.fooddelivery.app.data.local.AppDatabase;
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
public final class DatabaseModule_ProvideAddressDaoFactory implements Factory<AddressDao> {
  private final Provider<AppDatabase> databaseProvider;

  public DatabaseModule_ProvideAddressDaoFactory(Provider<AppDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public AddressDao get() {
    return provideAddressDao(databaseProvider.get());
  }

  public static DatabaseModule_ProvideAddressDaoFactory create(
      Provider<AppDatabase> databaseProvider) {
    return new DatabaseModule_ProvideAddressDaoFactory(databaseProvider);
  }

  public static AddressDao provideAddressDao(AppDatabase database) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideAddressDao(database));
  }
}
