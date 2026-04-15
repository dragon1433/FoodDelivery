package com.fooddelivery.app.ui.viewmodels;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.fooddelivery.app.data.model.CartItem;
import com.fooddelivery.app.data.repository.CartRepository;
import java.util.List;

/**
 * 购物车 ViewModel
 */
public class CartViewModel extends AndroidViewModel {
    
    private final CartRepository repository;
    private final LiveData<List<CartItem>> cartItems;
    private final LiveData<Integer> totalCount;
    
    public CartViewModel(@NonNull Application application) {
        super(application);
        repository = CartRepository.getInstance(application);
        cartItems = repository.getCartItems();
        totalCount = repository.getTotalCount();
    }
    
    public LiveData<List<CartItem>> getCartItems() {
        return cartItems;
    }
    
    public LiveData<Integer> getTotalCount() {
        return totalCount;
    }
    
    public void addToCart(CartItem item) {
        repository.addToCart(item);
    }
    
    public void updateQuantity(CartItem item, int quantity) {
        repository.updateQuantity(item, quantity);
    }
    
    public void removeFromCart(CartItem item) {
        repository.removeFromCart(item);
    }
    
    public void clearCart() {
        repository.clearCart();
    }
}
