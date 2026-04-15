package com.fooddelivery.app.data.repository;

import android.content.Context;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.fooddelivery.app.data.local.AppDatabase;
import com.fooddelivery.app.data.local.CartDao;
import com.fooddelivery.app.data.model.CartItem;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 购物车数据仓库
 */
public class CartRepository {
    
    private static CartRepository instance;
    private final CartDao cartDao;
    private final ExecutorService executor;
    private final MutableLiveData<List<CartItem>> cartItems = new MutableLiveData<>();
    
    private CartRepository(Context context) {
        AppDatabase database = AppDatabase.getDatabase(context);
        this.cartDao = database.cartDao();
        this.executor = Executors.newSingleThreadExecutor();
    }
    
    public static synchronized CartRepository getInstance(Context context) {
        if (instance == null) {
            instance = new CartRepository(context.getApplicationContext());
        }
        return instance;
    }
    
    public LiveData<List<CartItem>> getCartItems() {
        loadCartItems();
        return cartItems;
    }
    
    private void loadCartItems() {
        executor.execute(() -> {
            List<CartItem> items = cartDao.getAllCartItems();
            cartItems.postValue(items);
        });
    }
    
    public void addToCart(CartItem item) {
        executor.execute(() -> {
            CartItem existingItem = cartDao.getItemByDishId(item.getDishId());
            if (existingItem != null) {
                // 已存在，累加数量
                existingItem.setQuantity(existingItem.getQuantity() + item.getQuantity());
                cartDao.update(existingItem);
            } else {
                // 不存在，设置唯一 ID 后插入
                item.setId(System.currentTimeMillis());
                cartDao.insert(item);
            }
            loadCartItems();
        });
    }
    
    public void updateQuantity(CartItem item, int quantity) {
        executor.execute(() -> {
            if (quantity <= 0) {
                cartDao.delete(item);
            } else {
                item.setQuantity(quantity);
                cartDao.update(item);
            }
            loadCartItems();
        });
    }
    
    public void removeFromCart(CartItem item) {
        executor.execute(() -> {
            cartDao.delete(item);
            loadCartItems();
        });
    }
    
    public void clearCart() {
        executor.execute(() -> {
            cartDao.clearCart();
            loadCartItems();
        });
    }
    
    public LiveData<Integer> getTotalCount() {
        MutableLiveData<Integer> totalCount = new MutableLiveData<>();
        executor.execute(() -> {
            List<CartItem> items = cartDao.getAllCartItems();
            int count = 0;
            for (CartItem item : items) {
                count += item.getQuantity();
            }
            totalCount.postValue(count);
        });
        return totalCount;
    }
}
