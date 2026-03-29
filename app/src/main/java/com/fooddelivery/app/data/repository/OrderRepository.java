package com.fooddelivery.app.data.repository;

import android.content.Context;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.fooddelivery.app.data.local.AppDatabase;
import com.fooddelivery.app.data.local.OrderDao;
import com.fooddelivery.app.data.model.Order;
import com.fooddelivery.app.data.model.OrderStatus;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 订单数据仓库
 */
public class OrderRepository {
    
    private final OrderDao orderDao;
    private final ExecutorService executor;
    private final MutableLiveData<List<Order>> orders = new MutableLiveData<>();
    
    public OrderRepository(Context context) {
        AppDatabase database = AppDatabase.getDatabase(context);
        this.orderDao = database.orderDao();
        this.executor = Executors.newSingleThreadExecutor();
    }
    
    public LiveData<List<Order>> getAllOrders() {
        loadOrders();
        return orders;
    }
    
    private void loadOrders() {
        executor.execute(() -> {
            List<Order> orderList = orderDao.getAllOrders();
            orders.postValue(orderList);
        });
    }
    
    public LiveData<Order> getOrderById(long orderId) {
        MutableLiveData<Order> order = new MutableLiveData<>();
        executor.execute(() -> {
            Order o = orderDao.getOrderById(orderId);
            order.postValue(o);
        });
        return order;
    }
    
    public void insertOrder(Order order) {
        executor.execute(() -> {
            orderDao.insert(order);
            loadOrders();
        });
    }
    
    public void updateOrder(Order order) {
        executor.execute(() -> {
            orderDao.update(order);
            loadOrders();
        });
    }
    
    public void updateStatus(long orderId, OrderStatus status) {
        executor.execute(() -> {
            Order order = orderDao.getOrderById(orderId);
            if (order != null) {
                order.setStatus(status);
                orderDao.update(order);
                loadOrders();
            }
        });
    }
}
