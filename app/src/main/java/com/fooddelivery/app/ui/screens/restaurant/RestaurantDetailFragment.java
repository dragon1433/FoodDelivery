package com.fooddelivery.app.ui.screens.restaurant;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
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
import com.fooddelivery.app.data.model.Dish;
import com.fooddelivery.app.data.model.Restaurant;
import com.fooddelivery.app.ui.adapters.DishAdapter;
import com.fooddelivery.app.ui.viewmodels.CartViewModel;
import com.fooddelivery.app.ui.viewmodels.DishViewModel;
import java.util.List;

/**
 * 餐厅详情 Fragment
 */
public class RestaurantDetailFragment extends Fragment implements DishAdapter.OnItemClickListener {
    
    private ImageView btnBack;
    private TextView textRestaurantName;
    private TextView textDescription;
    private TextView textDeliveryTime;
    private TextView textDeliveryFee;
    private RecyclerView recyclerDishes;
    private LinearLayout layoutBottomBar;
    private TextView textTotalPrice;
    private Button btnCheckout;
    
    private DishViewModel dishViewModel;
    private CartViewModel cartViewModel;
    private DishAdapter adapter;
    
    private long restaurantId;
    private Restaurant restaurant;
    private NavController navController;
    private double deliveryFee = 5.0;
    
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, 
                           @Nullable ViewGroup container, 
                           @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_restaurant_detail, container, false);
    }
    
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        
        // 初始化导航
        navController = Navigation.findNavController(view);
        
        // 获取参数
        if (getArguments() != null) {
            restaurantId = getArguments().getLong("restaurant_id");
        }
        
        // 初始化视图
        btnBack = view.findViewById(R.id.btn_back);
        textRestaurantName = view.findViewById(R.id.text_restaurant_name);
        textDescription = view.findViewById(R.id.text_description);
        textDeliveryTime = view.findViewById(R.id.text_delivery_time);
        textDeliveryFee = view.findViewById(R.id.text_delivery_fee);
        recyclerDishes = view.findViewById(R.id.recycler_dishes);
        layoutBottomBar = view.findViewById(R.id.layout_bottom_bar);
        textTotalPrice = view.findViewById(R.id.text_total_price);
        btnCheckout = view.findViewById(R.id.btn_checkout);
        
        // 初始化隐藏结算栏
        layoutBottomBar.setVisibility(View.GONE);
        
        // 设置返回按钮
        btnBack.setOnClickListener(v -> {
            requireActivity().onBackPressed();
        });
        
        // 显示餐厅信息
        if (restaurant != null) {
            textRestaurantName.setText(restaurant.getName());
            textDescription.setText(restaurant.getDescription());
            textDeliveryTime.setText(restaurant.getDeliveryTime());
            textDeliveryFee.setText("配送费¥" + restaurant.getDeliveryFee());
        } else {
            // 如果没有传入 restaurant 对象，使用默认值
            textRestaurantName.setText("餐厅详情");
            textDescription.setText("加载中...");
            textDeliveryTime.setText("30 分钟");
            textDeliveryFee.setText("配送费¥5");
        }
        
        // 设置 RecyclerView
        adapter = new DishAdapter();
        adapter.setOnItemClickListener(this);
        recyclerDishes.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerDishes.setAdapter(adapter);
        
        // 初始化 ViewModel
        dishViewModel = new ViewModelProvider(requireActivity()).get(DishViewModel.class);
        cartViewModel = new ViewModelProvider(requireActivity()).get(CartViewModel.class);
        
        // 观察菜品数据
        dishViewModel.getDishesByRestaurant(restaurantId).observe(getViewLifecycleOwner(), dishes -> {
            if (dishes != null) {
                adapter.setDishes(dishes);
            }
        });
        
        // 观察购物车数据，更新结算栏
        cartViewModel.getCartItems().observe(getViewLifecycleOwner(), cartItems -> {
            if (cartItems != null && !cartItems.isEmpty()) {
                // 计算当前餐厅商品总价
                double totalPrice = 0;
                for (CartItem item : cartItems) {
                    if (item.getRestaurantId() == restaurantId) {
                        totalPrice += item.getPrice() * item.getQuantity();
                    }
                }
                
                if (totalPrice > 0) {
                    textTotalPrice.setText(String.format("¥%.1f", totalPrice));
                    layoutBottomBar.setVisibility(View.VISIBLE);
                } else {
                    layoutBottomBar.setVisibility(View.GONE);
                }
            } else {
                layoutBottomBar.setVisibility(View.GONE);
            }
        });
        
        // 去结算按钮点击
        btnCheckout.setOnClickListener(v -> {
            navController.navigate(R.id.action_restaurantDetailFragment_to_checkoutFragment);
        });
    }
    
    @Override
    public void onAddToCart(Dish dish, int quantity) {
        // quantity 为正数表示增加，负数表示减少
        if (quantity > 0) {
            // 增加商品 - 直接查询当前购物车数据
            List<CartItem> cartItems = cartViewModel.getCartItems().getValue();
            if (cartItems != null) {
                boolean exists = false;
                for (CartItem item : cartItems) {
                    if (item.getDishId() == dish.getId()) {
                        // 已存在，增加数量
                        item.setQuantity(item.getQuantity() + quantity);
                        cartViewModel.updateQuantity(item, item.getQuantity());
                        exists = true;
                        break;
                    }
                }
                if (!exists) {
                    // 不存在，新增
                    CartItem cartItem = new CartItem();
                    cartItem.setFromDish(dish, quantity);
                    cartViewModel.addToCart(cartItem);
                }
            } else {
                // 购物车为空，直接添加
                CartItem cartItem = new CartItem();
                cartItem.setFromDish(dish, quantity);
                cartViewModel.addToCart(cartItem);
            }
            
            Toast.makeText(getContext(), "已加入购物车", Toast.LENGTH_SHORT).show();
        } else if (quantity < 0) {
            // 减少商品
            List<CartItem> cartItems = cartViewModel.getCartItems().getValue();
            if (cartItems != null) {
                for (CartItem item : cartItems) {
                    if (item.getDishId() == dish.getId()) {
                        int newQty = item.getQuantity() + quantity; // quantity 是负数
                        if (newQty <= 0) {
                            cartViewModel.removeFromCart(item);
                        } else {
                            item.setQuantity(newQty);
                            cartViewModel.updateQuantity(item, newQty);
                        }
                        break;
                    }
                }
            }
            
            Toast.makeText(getContext(), "已减少", Toast.LENGTH_SHORT).show();
        }
    }
}
