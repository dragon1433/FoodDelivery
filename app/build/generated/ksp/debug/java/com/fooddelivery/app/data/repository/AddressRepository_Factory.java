package com.fooddelivery.app.data.repository;

import com.fooddelivery.app.data.local.AddressDao;
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
public final class AddressRepository_Factory implements Factory<AddressRepository> {
  private final Provider<AddressDao> addressDaoProvider;

  public AddressRepository_Factory(Provider<AddressDao> addressDaoProvider) {
    this.addressDaoProvider = addressDaoProvider;
  }

  @Override
  public AddressRepository get() {
    return newInstance(addressDaoProvider.get());
  }

  public static AddressRepository_Factory create(Provider<AddressDao> addressDaoProvider) {
    return new AddressRepository_Factory(addressDaoProvider);
  }

  public static AddressRepository newInstance(AddressDao addressDao) {
    return new AddressRepository(addressDao);
  }
}
