package com.fooddelivery.app.data.repository;

import android.content.Context;
import android.content.SharedPreferences;
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
    private final SharedPreferences sharedPreferences;
    private static final String PREFS_NAME = "user_session";
    private static final String KEY_CURRENT_USER_ID = "current_user_id";
    
    public OrderRepository(Context context) {
        AppDatabase database = AppDatabase.getDatabase(context);
        this.orderDao = database.orderDao();
        this.executor = Executors.newSingleThreadExecutor();
        this.sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }
    
    // 获取当前用户ID
    private long getCurrentUserId() {
        return sharedPreferences.getLong(KEY_CURRENT_USER_ID, -1);
    }
    
    public LiveData<List<Order>> getAllOrders() {
        loadOrders();
        return orders;
    }
    
    private void loadOrders() {
        executor.execute(() -> {
            long userId = getCurrentUserId();
            List<Order> orderList;
            if (userId > 0) {
                // 只查询当前用户的订单
                orderList = orderDao.getOrdersByUserId(userId);
            } else {
                // 如果没有登录，返回空列表
                orderList = new java.util.ArrayList<>();
            }
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
    
    public void deleteOrder(Order order) {
        executor.execute(() -> {
            orderDao.delete(order);
            loadOrders();
        });
    }
}
