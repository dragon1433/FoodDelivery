package com.fooddelivery.app.ui.viewmodels;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.fooddelivery.app.data.model.Order;
import com.fooddelivery.app.data.model.OrderStatus;
import com.fooddelivery.app.data.repository.OrderRepository;
import java.util.List;

/**
 * 订单 ViewModel
 */
public class OrderViewModel extends AndroidViewModel {
    
    private final OrderRepository repository;
    private final LiveData<List<Order>> orders;
    
    public OrderViewModel(@NonNull Application application) {
        super(application);
        repository = new OrderRepository(application);
        orders = repository.getAllOrders();
    }
    
    public LiveData<List<Order>> getOrders() {
        return orders;
    }
    
    public void placeOrder(Order order) {
        repository.insertOrder(order);
    }
    
    public void addOrder(Order order) {
        repository.insertOrder(order);
    }
    
    public void updateStatus(long orderId, OrderStatus status) {
        repository.updateStatus(orderId, status);
    }
    
    public void deleteOrder(Order order) {
        repository.deleteOrder(order);
    }
    
    public void updateOrder(Order order) {
        repository.updateOrder(order);
    }
}
