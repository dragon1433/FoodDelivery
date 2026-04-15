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
import com.fooddelivery.app.data.model.Address;
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
 * Checkout Fragment - Order submission page
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
    private AddressViewModel addressViewModel;
    private CartAdapter adapter;
    private NavController navController;
    
    private double deliveryFee = 5.0;
    private String selectedAddress = "";
    private String receiverName = "";
    private String receiverPhone = "";
    private long selectedAddressId = -1;
    
    @Override
    public void onResume() {
        super.onResume();
        // Address will be updated automatically by LiveData observer
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
        
        // Initialize navigation
        navController = Navigation.findNavController(view);
        
        // Initialize views
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
        
        // Setup back button
        btnBack.setOnClickListener(v -> {
            requireActivity().onBackPressed();
        });
        
        // Select address
        btnSelectAddress.setOnClickListener(v -> {
            // Open address selection page
            navController.navigate(R.id.action_checkoutFragment_to_addressFragment);
        });
        
        // Setup cart items list
        adapter = new CartAdapter();
        recyclerCartItems.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerCartItems.setAdapter(adapter);
        
        // Initialize ViewModel
        cartViewModel = new ViewModelProvider(requireActivity()).get(CartViewModel.class);
        orderViewModel = new ViewModelProvider(requireActivity()).get(OrderViewModel.class);
        addressViewModel = new ViewModelProvider(requireActivity()).get(AddressViewModel.class);
        
        // Observe selected address changes
        addressViewModel.getAddresses().observe(getViewLifecycleOwner(), addresses -> {
            if (addresses != null && !addresses.isEmpty()) {
                // Find the default address
                Address defaultAddress = null;
                for (Address addr : addresses) {
                    if (addr.isDefault()) {
                        defaultAddress = addr;
                        break;
                    }
                }
                
                // If no default address, use the first one
                if (defaultAddress == null) {
                    defaultAddress = addresses.get(0);
                }
                
                // 保存完整的地址信息
                selectedAddressId = defaultAddress.getId();
                receiverName = defaultAddress.getName() != null ? defaultAddress.getName() : "";
                receiverPhone = defaultAddress.getPhone() != null ? defaultAddress.getPhone() : "";
                selectedAddress = defaultAddress.getDetailAddress() != null ? defaultAddress.getDetailAddress() : "";
                
                // 显示地址信息（包含姓名和电话）
                StringBuilder addressDisplay = new StringBuilder();
                if (!receiverName.isEmpty()) {
                    addressDisplay.append(receiverName);
                }
                if (!receiverPhone.isEmpty()) {
                    if (addressDisplay.length() > 0) {
                        addressDisplay.append("  ");
                    }
                    addressDisplay.append(receiverPhone);
                }
                if (!selectedAddress.isEmpty()) {
                    if (addressDisplay.length() > 0) {
                        addressDisplay.append("\n");
                    }
                    addressDisplay.append(selectedAddress);
                }
                
                textAddress.setText(addressDisplay.toString());
            } else {
                textAddress.setText("Please select delivery address");
                selectedAddress = "";
                receiverName = "";
                receiverPhone = "";
                selectedAddressId = -1;
            }
        });
        
        // Load cart data
        cartViewModel.getCartItems().observe(getViewLifecycleOwner(), cartItems -> {
            if (cartItems != null && !cartItems.isEmpty()) {
                adapter.setCartItems(cartItems);
                
                // Calculate fees
                double foodTotal = calculateFoodTotal(cartItems);
                double totalPrice = foodTotal + deliveryFee;
                
                textFoodTotal.setText(String.format("¥%.1f", foodTotal));
                textDeliveryFee.setText(String.format("¥%.1f", deliveryFee));
                textTotalPrice.setText(String.format("¥%.1f", totalPrice));
                textPayTotal.setText(String.format("¥%.1f", totalPrice));
            } else {
                Toast.makeText(getContext(), "Cart is empty", Toast.LENGTH_SHORT).show();
                requireActivity().onBackPressed();
            }
        });
        
        // Submit order
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
            Toast.makeText(getContext(), "Cart is empty", Toast.LENGTH_SHORT).show();
            return;
        }
        
        // 获取当前用户ID
        android.content.SharedPreferences prefs = requireActivity().getSharedPreferences("user_session", 
            requireActivity().MODE_PRIVATE);
        long currentUserId = prefs.getLong("current_user_id", -1);
        
        if (currentUserId <= 0) {
            Toast.makeText(getContext(), "Please login first", Toast.LENGTH_SHORT).show();
            return;
        }
        
        // Create order
        Order order = new Order();
        order.setId(System.currentTimeMillis());
        order.setUserId(currentUserId);  // 设置用户ID
        
        // Set restaurant info (assume all items from same restaurant)
        Long restaurantId = cartItems.get(0).getRestaurantId();
        order.setRestaurantId(restaurantId);
        
        // Get restaurant name by restaurantId
        String restaurantName = getRestaurantNameById(restaurantId);
        order.setRestaurantName(restaurantName != null ? restaurantName : "Unknown Restaurant");
        
        // Create order items
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
        
        // 保存完整的地址信息到订单
        order.setAddressId(selectedAddressId);
        order.setAddressDetail(selectedAddress.isEmpty() ? "No delivery address selected" : selectedAddress);
        order.setReceiverName(receiverName.isEmpty() ? "Unknown" : receiverName);
        order.setReceiverPhone(receiverPhone.isEmpty() ? "N/A" : receiverPhone);
        
        // Save order
        orderViewModel.addOrder(order);
        
        // Clear cart
        for (CartItem item : cartItems) {
            cartViewModel.removeFromCart(item);
        }
        
        Toast.makeText(getContext(), "Order submitted successfully", Toast.LENGTH_SHORT).show();
        
        // Return to orders page
        navController.popBackStack();
    }
    
    /**
     * Get restaurant name by ID
     */
    private String getRestaurantNameById(Long restaurantId) {
        // Get restaurant name mapping from Mock data
        switch (restaurantId.intValue()) {
            case 1:
                return "McDonald's";
            case 2:
                return "KFC";
            case 3:
                return "Pizza Hut";
            case 4:
                return "Haidilao Hot Pot";
            case 5:
                return "Starbucks";
            default:
                return null;
        }
    }
}
