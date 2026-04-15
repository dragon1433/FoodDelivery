package com.fooddelivery.app.ui.screens.restaurant;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
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
import com.bumptech.glide.Glide;
import com.fooddelivery.app.R;
import com.fooddelivery.app.data.model.CartItem;
import com.fooddelivery.app.data.model.Dish;
import com.fooddelivery.app.data.model.Restaurant;
import com.fooddelivery.app.ui.adapters.DishAdapter;
import com.fooddelivery.app.ui.viewmodels.CartViewModel;
import com.fooddelivery.app.ui.viewmodels.DishViewModel;
import java.util.List;

/**
 * Restaurant Detail Fragment
 */
public class RestaurantDetailFragment extends Fragment implements DishAdapter.OnItemClickListener {
    
    private FrameLayout layoutHeaderImage;
    private ImageView imageRestaurant;
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
        
        // Initialize navigation
        navController = Navigation.findNavController(view);
        
        // Get arguments
        if (getArguments() != null) {
            restaurantId = getArguments().getLong("restaurant_id");
        }
        
        // Initialize views
        layoutHeaderImage = view.findViewById(R.id.layout_header_image);
        imageRestaurant = view.findViewById(R.id.image_restaurant);
        btnBack = view.findViewById(R.id.btn_back);
        textRestaurantName = view.findViewById(R.id.text_restaurant_name);
        textDescription = view.findViewById(R.id.text_description);
        textDeliveryTime = view.findViewById(R.id.text_delivery_time);
        textDeliveryFee = view.findViewById(R.id.text_delivery_fee);
        recyclerDishes = view.findViewById(R.id.recycler_dishes);
        layoutBottomBar = view.findViewById(R.id.layout_bottom_bar);
        textTotalPrice = view.findViewById(R.id.text_total_price);
        btnCheckout = view.findViewById(R.id.btn_checkout);
        
        // Initially hide checkout bar
        layoutBottomBar.setVisibility(View.GONE);
        
        // Setup back button
        btnBack.setOnClickListener(v -> {
            requireActivity().onBackPressed();
        });
        
        // Setup RecyclerView
        adapter = new DishAdapter();
        adapter.setOnItemClickListener(this);
        recyclerDishes.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerDishes.setAdapter(adapter);
        
        // Initialize ViewModel
        dishViewModel = new ViewModelProvider(requireActivity()).get(DishViewModel.class);
        cartViewModel = new ViewModelProvider(requireActivity()).get(CartViewModel.class);
        
        // Observe restaurant data
        dishViewModel.getRestaurantById(restaurantId).observe(getViewLifecycleOwner(), restaurantData -> {
            if (restaurantData != null) {
                restaurant = restaurantData;
                
                // Load restaurant image using Glide
                Glide.with(requireContext())
                    .load(restaurant.getImageUrl())
                    .placeholder(R.drawable.restaurant_storefront)
                    .error(R.drawable.restaurant_storefront)
                    .into(imageRestaurant);
                
                textRestaurantName.setText(restaurant.getName());
                textDescription.setText(restaurant.getDescription());
                textDeliveryTime.setText(restaurant.getDeliveryTime());
                textDeliveryFee.setText(String.format("%s ¥%.1f", getString(R.string.cart_delivery_fee), restaurant.getDeliveryFee()));
            } else {
                // If no restaurant found, use default values
                textRestaurantName.setText(getString(R.string.restaurant_detail_title));
                textDescription.setText(getString(R.string.empty_data));
                textDeliveryTime.setText("30 min");
                textDeliveryFee.setText(String.format("%s ¥5.0", getString(R.string.cart_delivery_fee)));
            }
        });
        
        // Observe dish data
        dishViewModel.getDishesByRestaurant(restaurantId).observe(getViewLifecycleOwner(), dishes -> {
            if (dishes != null) {
                adapter.setDishes(dishes);
                updateCartQuantities();
            }
        });
        
        // Update cart quantities display
        cartViewModel.getCartItems().observe(getViewLifecycleOwner(), cartItems -> {
            updateCartQuantities();
        });
        
        // Observe cart data and update checkout bar
        cartViewModel.getCartItems().observe(getViewLifecycleOwner(), cartItems -> {
            if (cartItems != null && !cartItems.isEmpty()) {
                // Calculate total price for current restaurant items
                double totalPrice = 0;
                int itemCount = 0;
                for (CartItem item : cartItems) {
                    if (item.getRestaurantId() == restaurantId) {
                        totalPrice += item.getPrice() * item.getQuantity();
                        itemCount += item.getQuantity();
                    }
                }
                
                if (totalPrice > 0) {
                    textTotalPrice.setText(String.format("¥%.1f", totalPrice));
                    btnCheckout.setText(String.format("Checkout (%d)", itemCount));
                    layoutBottomBar.setVisibility(View.VISIBLE);
                } else {
                    layoutBottomBar.setVisibility(View.GONE);
                }
            } else {
                layoutBottomBar.setVisibility(View.GONE);
            }
        });
        
        // Checkout button click
        btnCheckout.setOnClickListener(v -> {
            navController.navigate(R.id.action_restaurantDetailFragment_to_checkoutFragment);
        });
    }
    
    /**
     * Update cart quantities display in dish list
     */
    private void updateCartQuantities() {
        List<CartItem> cartItems = cartViewModel.getCartItems().getValue();
        if (cartItems != null && adapter != null) {
            java.util.Map<Long, Integer> quantities = new java.util.HashMap<>();
            for (CartItem item : cartItems) {
                if (item.getRestaurantId() == restaurantId) {
                    quantities.put(item.getDishId(), item.getQuantity());
                }
            }
            adapter.updateCartQuantities(quantities);
        }
    }
    
    @Override
    public void onAddToCart(Dish dish, int quantity) {
        // Create cart item from dish
        CartItem cartItem = new CartItem();
        cartItem.setFromDish(dish, Math.abs(quantity)); // Use absolute value for quantity
        
        if (quantity > 0) {
            // Add to cart - Repository will handle existing items
            cartViewModel.addToCart(cartItem);
            Toast.makeText(getContext(), "Added to cart", Toast.LENGTH_SHORT).show();
        } else if (quantity < 0) {
            // Remove from cart
            List<CartItem> cartItems = cartViewModel.getCartItems().getValue();
            if (cartItems != null) {
                for (CartItem item : cartItems) {
                    if (item.getDishId() == dish.getId()) {
                        int newQty = item.getQuantity() - 1;
                        if (newQty <= 0) {
                            cartViewModel.removeFromCart(item);
                        } else {
                            cartViewModel.updateQuantity(item, newQty);
                        }
                        break;
                    }
                }
            }
        }
        // The UI will be updated automatically by the observer
    }
}
