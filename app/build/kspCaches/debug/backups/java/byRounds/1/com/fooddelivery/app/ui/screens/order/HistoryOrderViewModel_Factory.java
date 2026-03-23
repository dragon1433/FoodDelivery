package com.fooddelivery.app.ui.screens.order;

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
public final class HistoryOrderViewModel_Factory implements Factory<HistoryOrderViewModel> {
  private final Provider<OrderRepository> orderRepositoryProvider;

  public HistoryOrderViewModel_Factory(Provider<OrderRepository> orderRepositoryProvider) {
    this.orderRepositoryProvider = orderRepositoryProvider;
  }

  @Override
  public HistoryOrderViewModel get() {
    return newInstance(orderRepositoryProvider.get());
  }

  public static HistoryOrderViewModel_Factory create(
      Provider<OrderRepository> orderRepositoryProvider) {
    return new HistoryOrderViewModel_Factory(orderRepositoryProvider);
  }

  public static HistoryOrderViewModel newInstance(OrderRepository orderRepository) {
    return new HistoryOrderViewModel(orderRepository);
  }
}
