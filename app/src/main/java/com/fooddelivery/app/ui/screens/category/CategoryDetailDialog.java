package com.fooddelivery.app.ui.screens.category;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.fooddelivery.app.R;
import com.fooddelivery.app.data.model.CartItem;
import com.fooddelivery.app.data.model.Dish;
import com.fooddelivery.app.data.model.Restaurant;
import com.fooddelivery.app.data.mock.MockDataGenerator;
import com.fooddelivery.app.data.repository.CartRepository;
import com.fooddelivery.app.ui.adapters.DishAdapter;
import com.fooddelivery.app.ui.adapters.RestaurantAdapter;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Category Detail Bottom Sheet Dialog
 */
public class CategoryDetailDialog extends BottomSheetDialogFragment {
    
    private static final String ARG_CATEGORY_NAME = "category_name";
    private static final String ARG_CATEGORY_IMAGE = "category_image";
    private static final String ARG_CATEGORY_ID = "category_id";
    
    private ImageView imageCategoryHeader;
    private ImageView btnClose;
    private TextView textCategoryTitle;
    private RecyclerView recyclerRestaurants;
    private RecyclerView recyclerDishes;
    
    private RestaurantAdapter restaurantAdapter;
    private DishAdapter dishAdapter;
    private CartRepository cartRepository;
    
    private String categoryName;
    private String categoryImage;
    private long categoryId;
    
    public static CategoryDetailDialog newInstance(String categoryName, String categoryImage, long categoryId) {
        CategoryDetailDialog dialog = new CategoryDetailDialog();
        Bundle args = new Bundle();
        args.putString(ARG_CATEGORY_NAME, categoryName);
        args.putString(ARG_CATEGORY_IMAGE, categoryImage);
        args.putLong(ARG_CATEGORY_ID, categoryId);
        dialog.setArguments(args);
        return dialog;
    }
    
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, 
                           @Nullable ViewGroup container, 
                           @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_category_detail, container, false);
    }
    
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        
        // Initialize cart repository using singleton pattern
        cartRepository = CartRepository.getInstance(requireContext());
        
        // Get arguments
        if (getArguments() != null) {
            categoryName = getArguments().getString(ARG_CATEGORY_NAME);
            categoryImage = getArguments().getString(ARG_CATEGORY_IMAGE);
            categoryId = getArguments().getLong(ARG_CATEGORY_ID);
        }
        
        // Initialize views
        imageCategoryHeader = view.findViewById(R.id.image_category_header);
        btnClose = view.findViewById(R.id.btn_close);
        textCategoryTitle = view.findViewById(R.id.text_category_title);
        recyclerRestaurants = view.findViewById(R.id.recycler_restaurants);
        recyclerDishes = view.findViewById(R.id.recycler_dishes);
        
        // Set category header
        textCategoryTitle.setText(categoryName);
        Glide.with(this)
            .load(categoryImage)
            .centerCrop()
            .placeholder(R.drawable.ic_home)
            .error(R.drawable.ic_home)
            .into(imageCategoryHeader);
        
        // Close button
        btnClose.setOnClickListener(v -> dismiss());
        
        // Setup restaurants RecyclerView
        setupRestaurants();
        
        // Setup dishes RecyclerView
        setupDishes();
    }
    
    private void setupRestaurants() {
        restaurantAdapter = new RestaurantAdapter();
        restaurantAdapter.setOnItemClickListener(new RestaurantAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Restaurant restaurant) {
                // TODO: Navigate to restaurant detail
                dismiss();
            }
            
            @Override
            public void onFavoriteClick(Restaurant restaurant, int position) {
                // Toggle favorite
            }
        });
        
        recyclerRestaurants.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerRestaurants.setAdapter(restaurantAdapter);
        
        // Filter restaurants by category (simplified - in real app would use proper filtering)
        List<Restaurant> allRestaurants = MockDataGenerator.generateRestaurants();
        List<Restaurant> filteredRestaurants = new ArrayList<>();
        
        // For demo, just show first 3-4 restaurants
        for (int i = 0; i < Math.min(4, allRestaurants.size()); i++) {
            filteredRestaurants.add(allRestaurants.get(i));
        }
        
        restaurantAdapter.setRestaurants(filteredRestaurants);
    }
    
    private void setupDishes() {
        dishAdapter = new DishAdapter();
        dishAdapter.setOnItemClickListener(new DishAdapter.OnItemClickListener() {
            @Override
            public void onAddToCart(Dish dish, int quantity) {
                if (quantity > 0) {
                    addToCart(dish);
                    // Refresh the display after adding to cart
                    refreshCartQuantities();
                }
            }
        });
        
        recyclerDishes.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerDishes.setAdapter(dishAdapter);
        
        // Filter dishes by category name
        List<Dish> allDishes = getAllDishes();
        List<Dish> filteredDishes = allDishes.stream()
            .filter(dish -> {
                String dishCategory = dish.getCategoryName() != null ? dish.getCategoryName().toLowerCase() : "";
                String searchCategory = categoryName.toLowerCase();
                
                // Match based on category name
                if (searchCategory.contains("burger") && dishCategory.contains("burger")) {
                    return true;
                } else if (searchCategory.contains("pizza") && dishCategory.contains("pizza")) {
                    return true;
                } else if (searchCategory.contains("chicken") && dishCategory.contains("chicken")) {
                    return true;
                } else if ((searchCategory.contains("coffee") || searchCategory.contains("drink")) && 
                           (dishCategory.contains("coffee") || dishCategory.contains("drink"))) {
                    return true;
                } else if (searchCategory.contains("noodle") && dishCategory.contains("noodle")) {
                    return true;
                } else if (searchCategory.contains("dessert") && dishCategory.contains("dessert")) {
                    return true;
                }
                return false;
            })
            .collect(Collectors.toList());
        
        dishAdapter.setDishes(filteredDishes);
    }
    
    /**
     * Get all dishes from mock data
     */
    private List<Dish> getAllDishes() {
        List<Dish> dishes = new ArrayList<>();
        
        // McDonald's Dishes - Burger Category
        Dish bigMac = new Dish(1, 1L, "Big Mac", "Classic double beef burger with special sauce", 
            25.0, "https://images.unsplash.com/photo-1568901346375-23c9450c58cd?w=400", true);
        bigMac.setCategoryName("Burger");
        dishes.add(bigMac);
        
        Dish nuggets = new Dish(2, 1L, "Chicken McNuggets", "Crispy chicken nuggets (6 pieces)", 
            18.0, "https://images.unsplash.com/photo-1608039829572-78524f79c4c7?w=400", true);
        nuggets.setCategoryName("Fried Chicken");
        dishes.add(nuggets);
        
        Dish fries = new Dish(3, 1L, "French Fries", "Golden crispy fries", 
            10.0, "https://images.unsplash.com/photo-1573080496219-bb080dd4f877?w=400", true);
        fries.setCategoryName("Side Dish");
        dishes.add(fries);
        
        // KFC Dishes - Fried Chicken Category
        Dish kfcChicken = new Dish(5, 2L, "Original Recipe Chicken", "Classic finger-lickin' chicken (2 pieces)", 
            28.0, "https://images.unsplash.com/photo-1626645738196-c2a7c87a8f58?w=400", true);
        kfcChicken.setCategoryName("Fried Chicken");
        dishes.add(kfcChicken);
        
        Dish zingerBurger = new Dish(6, 2L, "Zinger Burger", "Spicy chicken leg burger", 
            22.0, "https://images.unsplash.com/photo-1606755962773-d324e0a13086?w=400", true);
        zingerBurger.setCategoryName("Burger");
        dishes.add(zingerBurger);
        
        // Pizza Hut Dishes - Pizza Category
        Dish supremePizza = new Dish(9, 3L, "Super Supreme Pizza", "Deluxe toppings pizza (Large)", 
            88.0, "https://images.unsplash.com/photo-1565299624946-b28f40a0ae38?w=400", true);
        supremePizza.setCategoryName("Pizza & Pasta");
        dishes.add(supremePizza);
        
        Dish hawaiianPizza = new Dish(10, 3L, "Hawaiian Pizza", "Ham and pineapple pizza (Large)", 
            75.0, "https://images.unsplash.com/photo-1565299507177-b0ac66763828?w=400", true);
        hawaiianPizza.setCategoryName("Pizza & Pasta");
        dishes.add(hawaiianPizza);
        
        // Starbucks - Coffee Category
        Dish latte = new Dish(17, 5L, "Caffe Latte", "Classic Italian latte", 
            32.0, "https://images.unsplash.com/photo-1541167760496-1628856ab772?w=400", true);
        latte.setCategoryName("Coffee");
        dishes.add(latte);
        
        Dish americano = new Dish(18, 5L, "Caffe Americano", "Pure Americano coffee", 
            28.0, "https://images.unsplash.com/photo-1551030173-122aabc4489c?w=400", true);
        americano.setCategoryName("Coffee");
        dishes.add(americano);
        
        return dishes;
    }
    
    /**
     * Add dish to cart
     */
    private void addToCart(Dish dish) {
        CartItem cartItem = new CartItem();
        cartItem.setFromDish(dish, 1);
        cartItem.setId(System.currentTimeMillis());
        
        cartRepository.addToCart(cartItem);
        
        Toast.makeText(getContext(), 
            "Added " + dish.getName() + " to cart!", 
            Toast.LENGTH_SHORT).show();
    }
    
    /**
     * Refresh cart quantities display
     */
    private void refreshCartQuantities() {
        // Get current cart items and update the adapter's quantity map
        cartRepository.getCartItems().observe(this, cartItems -> {
            Map<Long, Integer> quantities = new HashMap<>();
            for (CartItem item : cartItems) {
                // Use absolute value of dishId because we store it as-is
                quantities.put(Math.abs(item.getDishId()), item.getQuantity());
            }
            dishAdapter.updateCartQuantities(quantities);
        });
    }
}
