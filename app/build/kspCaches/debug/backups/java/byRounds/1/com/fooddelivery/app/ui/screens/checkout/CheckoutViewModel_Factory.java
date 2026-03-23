package com.fooddelivery.app.ui.screens.checkout;

import com.fooddelivery.app.data.repository.AddressRepository;
import com.fooddelivery.app.data.repository.CartRepository;
import com.fooddelivery.app.data.repository.OrderRepository;
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
public final class CheckoutViewModel_Factory implements Factory<CheckoutViewModel> {
  private final Provider<CartRepository> cartRepositoryProvider;

  private final Provider<AddressRepository> addressRepositoryProvider;

  private final Provider<OrderRepository> orderRepositoryProvider;

  public CheckoutViewModel_Factory(Provider<CartRepository> cartRepositoryProvider,
      Provider<AddressRepository> addressRepositoryProvider,
      Provider<OrderRepository> orderRepositoryProvider) {
    this.cartRepositoryProvider = cartRepositoryProvider;
    this.addressRepositoryProvider = addressRepositoryProvider;
    this.orderRepositoryProvider = orderRepositoryProvider;
  }

  @Override
  public CheckoutViewModel get() {
    return newInstance(cartRepositoryProvider.get(), addressRepositoryProvider.get(), orderRepositoryProvider.get());
  }

  public static CheckoutViewModel_Factory create(Provider<CartRepository> cartRepositoryProvider,
      Provider<AddressRepository> addressRepositoryProvider,
      Provider<OrderRepository> orderRepositoryProvider) {
    return new CheckoutViewModel_Factory(cartRepositoryProvider, addressRepositoryProvider, orderRepositoryProvider);
  }

  public static CheckoutViewModel newInstance(CartRepository cartRepository,
      AddressRepository addressRepository, OrderRepository orderRepository) {
    return new CheckoutViewModel(cartRepository, addressRepository, orderRepository);
  }
}
