package com.fooddelivery.app.ui.screens.cart;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.fooddelivery.app.R;
import com.fooddelivery.app.data.model.CartItem;
import com.fooddelivery.app.ui.adapters.CartAdapter;
import com.fooddelivery.app.ui.viewmodels.CartViewModel;
import java.util.List;

/**
 * 购物车 Fragment
 */
public class CartFragment extends Fragment implements CartAdapter.OnItemClickListener {
    
    private RecyclerView recyclerCart;
    private LinearLayout layoutEmpty;
    private LinearLayout layoutBottomBar;
    private TextView textTotalPrice;
    private Button btnCheckout;
    
    private CartViewModel viewModel;
    private CartAdapter adapter;
    private NavController navController;
    
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, 
                           @Nullable ViewGroup container, 
                           @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cart, container, false);
    }
    
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        
        // 初始化导航
        navController = Navigation.findNavController(view);
        
        // 初始化视图
        recyclerCart = view.findViewById(R.id.recycler_cart);
        layoutEmpty = view.findViewById(R.id.layout_empty);
        layoutBottomBar = view.findViewById(R.id.layout_bottom_bar);
        textTotalPrice = view.findViewById(R.id.text_total_price);
        btnCheckout = view.findViewById(R.id.btn_checkout);
        
        // 设置 RecyclerView
        adapter = new CartAdapter();
        adapter.setOnItemClickListener(this);
        recyclerCart.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerCart.setAdapter(adapter);
        
        // 初始化 ViewModel
        viewModel = new ViewModelProvider(requireActivity()).get(CartViewModel.class);
        
        // 观察购物车数据
        viewModel.getCartItems().observe(getViewLifecycleOwner(), cartItems -> {
            if (cartItems != null && !cartItems.isEmpty()) {
                adapter.setCartItems(cartItems);
                recyclerCart.setVisibility(View.VISIBLE);
                layoutEmpty.setVisibility(View.GONE);
                layoutBottomBar.setVisibility(View.VISIBLE);
                
                // 计算总价
                double totalPrice = calculateTotal(cartItems);
                textTotalPrice.setText(String.format("¥%.1f", totalPrice));
            } else {
                recyclerCart.setVisibility(View.GONE);
                layoutEmpty.setVisibility(View.VISIBLE);
                layoutBottomBar.setVisibility(View.GONE);
            }
        });
        
        // 去结算按钮点击
        btnCheckout.setOnClickListener(v -> {
            navController.navigate(R.id.action_cart_fragment_to_checkout_fragment);
        });
    }
    
    private double calculateTotal(List<CartItem> items) {
        double total = 0;
        for (CartItem item : items) {
            total += item.getPrice() * item.getQuantity();
        }
        return total;
    }
    
    @Override
    public void onUpdateQuantity(CartItem item, int quantity) {
        if (quantity <= 0) {
            viewModel.removeFromCart(item);
        } else {
            viewModel.updateQuantity(item, quantity);
        }
    }
    
    @Override
    public void onDeleteItem(CartItem item) {
        viewModel.removeFromCart(item);
    }
}
