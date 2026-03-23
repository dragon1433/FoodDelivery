package com.fooddelivery.app;

import com.fooddelivery.app.data.DataInitializer;
import com.fooddelivery.app.data.repository.AddressRepository;
import com.fooddelivery.app.data.repository.CartRepository;
import com.fooddelivery.app.data.repository.OrderRepository;
import com.fooddelivery.app.data.repository.RestaurantRepository;
import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

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
public final class FoodDeliveryApplication_MembersInjector implements MembersInjector<FoodDeliveryApplication> {
  private final Provider<DataInitializer> dataInitializerProvider;

  private final Provider<RestaurantRepository> restaurantRepositoryProvider;

  private final Provider<AddressRepository> addressRepositoryProvider;

  private final Provider<CartRepository> cartRepositoryProvider;

  private final Provider<OrderRepository> orderRepositoryProvider;

  public FoodDeliveryApplication_MembersInjector(Provider<DataInitializer> dataInitializerProvider,
      Provider<RestaurantRepository> restaurantRepositoryProvider,
      Provider<AddressRepository> addressRepositoryProvider,
      Provider<CartRepository> cartRepositoryProvider,
      Provider<OrderRepository> orderRepositoryProvider) {
    this.dataInitializerProvider = dataInitializerProvider;
    this.restaurantRepositoryProvider = restaurantRepositoryProvider;
    this.addressRepositoryProvider = addressRepositoryProvider;
    this.cartRepositoryProvider = cartRepositoryProvider;
    this.orderRepositoryProvider = orderRepositoryProvider;
  }

  public static MembersInjector<FoodDeliveryApplication> create(
      Provider<DataInitializer> dataInitializerProvider,
      Provider<RestaurantRepository> restaurantRepositoryProvider,
      Provider<AddressRepository> addressRepositoryProvider,
      Provider<CartRepository> cartRepositoryProvider,
      Provider<OrderRepository> orderRepositoryProvider) {
    return new FoodDeliveryApplication_MembersInjector(dataInitializerProvider, restaurantRepositoryProvider, addressRepositoryProvider, cartRepositoryProvider, orderRepositoryProvider);
  }

  @Override
  public void injectMembers(FoodDeliveryApplication instance) {
    injectDataInitializer(instance, dataInitializerProvider.get());
    injectRestaurantRepository(instance, restaurantRepositoryProvider.get());
    injectAddressRepository(instance, addressRepositoryProvider.get());
    injectCartRepository(instance, cartRepositoryProvider.get());
    injectOrderRepository(instance, orderRepositoryProvider.get());
  }

  @InjectedFieldSignature("com.fooddelivery.app.FoodDeliveryApplication.dataInitializer")
  public static void injectDataInitializer(FoodDeliveryApplication instance,
      DataInitializer dataInitializer) {
    instance.dataInitializer = dataInitializer;
  }

  @InjectedFieldSignature("com.fooddelivery.app.FoodDeliveryApplication.restaurantRepository")
  public static void injectRestaurantRepository(FoodDeliveryApplication instance,
      RestaurantRepository restaurantRepository) {
    instance.restaurantRepository = restaurantRepository;
  }

  @InjectedFieldSignature("com.fooddelivery.app.FoodDeliveryApplication.addressRepository")
  public static void injectAddressRepository(FoodDeliveryApplication instance,
      AddressRepository addressRepository) {
    instance.addressRepository = addressRepository;
  }

  @InjectedFieldSignature("com.fooddelivery.app.FoodDeliveryApplication.cartRepository")
  public static void injectCartRepository(FoodDeliveryApplication instance,
      CartRepository cartRepository) {
    instance.cartRepository = cartRepository;
  }

  @InjectedFieldSignature("com.fooddelivery.app.FoodDeliveryApplication.orderRepository")
  public static void injectOrderRepository(FoodDeliveryApplication instance,
      OrderRepository orderRepository) {
    instance.orderRepository = orderRepository;
  }
}
