package com.fooddelivery.app.ui.screens.checkout;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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
import com.fooddelivery.app.data.model.Order;
import com.fooddelivery.app.data.model.OrderItem;
import com.fooddelivery.app.data.model.OrderStatus;
import com.fooddelivery.app.ui.adapters.CartAdapter;
import com.fooddelivery.app.ui.viewmodels.AddressViewModel;
import com.fooddelivery.app.ui.viewmodels.CartViewModel;
import com.fooddelivery.app.ui.viewmodels.OrderViewModel;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * 结算页面 Fragment
 */
public class CheckoutFragment extends Fragment {
    
    private ImageView btnBack;
    private TextView textAddress;
    private ImageView btnSelectAddress;
    private RecyclerView recyclerCartItems;
    private EditText editNote;
    private TextView textFoodTotal;
    private TextView textDeliveryFee;
    private TextView textTotalPrice;
    private TextView textPayTotal;
    private Button btnSubmitOrder;
    
    private CartViewModel cartViewModel;
    private OrderViewModel orderViewModel;
    private CartAdapter adapter;
    private NavController navController;
    
    private double deliveryFee = 5.0;
    private String selectedAddress = "";
    
    @Override
    public void onResume() {
        super.onResume();
        // 重新加载地址
        loadSelectedAddress();
    }
    
    private void loadSelectedAddress() {
        // 从 AddressViewModel 获取已选择的地址
        AddressViewModel addressViewModel = new ViewModelProvider(requireActivity()).get(AddressViewModel.class);
        addressViewModel.getAddresses().observe(getViewLifecycleOwner(), addresses -> {
            if (addresses != null && !addresses.isEmpty()) {
                // 使用第一个地址作为默认地址
                selectedAddress = addresses.get(0).getDetailAddress();
                textAddress.setText(selectedAddress);
            } else {
                textAddress.setText("请选择配送地址");
            }
        });
    }
    
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, 
                           @Nullable ViewGroup container, 
                           @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_checkout, container, false);
    }
    
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        
        // 初始化导航
        navController = Navigation.findNavController(view);
        
        // 初始化视图
        btnBack = view.findViewById(R.id.btn_back);
        textAddress = view.findViewById(R.id.text_address);
        btnSelectAddress = view.findViewById(R.id.btn_select_address);
        recyclerCartItems = view.findViewById(R.id.recycler_cart_items);
        editNote = view.findViewById(R.id.edit_note);
        textFoodTotal = view.findViewById(R.id.text_food_total);
        textDeliveryFee = view.findViewById(R.id.text_delivery_fee);
        textTotalPrice = view.findViewById(R.id.text_total_price);
        textPayTotal = view.findViewById(R.id.text_pay_total);
        btnSubmitOrder = view.findViewById(R.id.btn_submit_order);
        
        // 设置返回按钮
        btnBack.setOnClickListener(v -> {
            requireActivity().onBackPressed();
        });
        
        // 选择地址
        btnSelectAddress.setOnClickListener(v -> {
            // 打开地址选择页面
            navController.navigate(R.id.action_checkoutFragment_to_addressFragment);
        });
        
        // 设置购物车列表
        adapter = new CartAdapter();
        recyclerCartItems.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerCartItems.setAdapter(adapter);
        
        // 初始化 ViewModel
        cartViewModel = new ViewModelProvider(requireActivity()).get(CartViewModel.class);
        orderViewModel = new ViewModelProvider(requireActivity()).get(OrderViewModel.class);
        
        // 加载购物车数据
        cartViewModel.getCartItems().observe(getViewLifecycleOwner(), cartItems -> {
            if (cartItems != null && !cartItems.isEmpty()) {
                adapter.setCartItems(cartItems);
                
                // 计算费用
                double foodTotal = calculateFoodTotal(cartItems);
                double totalPrice = foodTotal + deliveryFee;
                
                textFoodTotal.setText(String.format("¥%.1f", foodTotal));
                textDeliveryFee.setText(String.format("¥%.1f", deliveryFee));
                textTotalPrice.setText(String.format("¥%.1f", totalPrice));
                textPayTotal.setText(String.format("¥%.1f", totalPrice));
            } else {
                Toast.makeText(getContext(), "购物车为空", Toast.LENGTH_SHORT).show();
                requireActivity().onBackPressed();
            }
        });
        
        // 提交订单
        btnSubmitOrder.setOnClickListener(v -> {
            submitOrder();
        });
    }
    
    private double calculateFoodTotal(List<CartItem> items) {
        double total = 0;
        for (CartItem item : items) {
            total += item.getPrice() * item.getQuantity();
        }
        return total;
    }
    
    private void submitOrder() {
        List<CartItem> cartItems = cartViewModel.getCartItems().getValue();
        if (cartItems == null || cartItems.isEmpty()) {
            Toast.makeText(getContext(), "购物车为空", Toast.LENGTH_SHORT).show();
            return;
        }
        
        // 创建订单
        Order order = new Order();
        order.setId(System.currentTimeMillis());
        
        // 设置餐厅信息（假设所有商品来自同一家餐厅）
        Long restaurantId = cartItems.get(0).getRestaurantId();
        order.setRestaurantId(restaurantId);
        
        // 根据 restaurantId 设置餐厅名称
        String restaurantName = getRestaurantNameById(restaurantId);
        order.setRestaurantName(restaurantName != null ? restaurantName : "未知餐厅");
        
        // 创建订单商品
        List<OrderItem> orderItems = new ArrayList<>();
        for (CartItem item : cartItems) {
            OrderItem orderItem = new OrderItem();
            orderItem.setDishId(item.getDishId());
            orderItem.setDishName(item.getName());
            orderItem.setPrice(item.getPrice());
            orderItem.setQuantity(item.getQuantity());
            orderItem.setPackingFee(item.getPackingFee());
            orderItems.add(orderItem);
        }
        
        double foodTotal = calculateFoodTotal(cartItems);
        order.setItems(orderItems);
        order.setTotalAmount(foodTotal + deliveryFee);
        order.setDeliveryFee(deliveryFee);
        order.setStatus(OrderStatus.PAID);
        order.setAddressDetail(selectedAddress.isEmpty() ? "未选择配送地址" : selectedAddress);
        
        // 保存订单
        orderViewModel.addOrder(order);
        
        // 清空购物车
        for (CartItem item : cartItems) {
            cartViewModel.removeFromCart(item);
        }
        
        Toast.makeText(getContext(), "订单提交成功", Toast.LENGTH_SHORT).show();
        
        // 返回到订单页面
        navController.popBackStack();
    }
    
    /**
     * 根据餐厅 ID 获取餐厅名称
     */
    private String getRestaurantNameById(Long restaurantId) {
        // 从 Mock 数据中获取餐厅名称映射
        switch (restaurantId.intValue()) {
            case 1:
                return "麦当劳";
            case 2:
                return "肯德基";
            case 3:
                return "必胜客";
            case 4:
                return "海底捞火锅";
            case 5:
                return "星巴克";
            default:
                return null;
        }
    }
}
